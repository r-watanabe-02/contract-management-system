package sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

public class ContractAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        ContractForm contractForm = (ContractForm) form;
        
        String ageStr = contractForm.getAge();
        ActionMessages errors = new ActionMessages();

        // 空チェック
        if (ageStr == null || ageStr.trim().isEmpty()) {
            errors.add("ageError", new ActionMessage("error.age.required"));
        } else {
            // 数値範囲チェック
            try {
                int age = Integer.parseInt(ageStr);
                if (age < 0 || age > 120) {
                    errors.add("ageError", new ActionMessage("error.age.range"));
                }
            } catch (NumberFormatException e) {
                // 数値じゃない場合
                errors.add("ageError", new ActionMessage("error.age.invalid"));
            }
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            // 入力画面に戻す
            return mapping.findForward("error");
        }

        ContractDao dao = new ContractDao();
        dao.insert(contractForm);

        // 1. フォームからプランコードを取得
        String planCode = contractForm.getPlan();
        // 2. Strutsのメッセージ機能（プロパティファイル）を呼び出す準備
        MessageResources resources = getResources(request);
        // 3. キーを使って文言を取得
        String planName = resources.getMessage("plan." + planCode);
        // 4. 翻訳した日本語をリクエストに保存する
        request.setAttribute("planName", planName);

        // struts-config.xmlで「success」と名付けた次の画面(result.jsp)へ遷移する
        return mapping.findForward("success");
    }
}
