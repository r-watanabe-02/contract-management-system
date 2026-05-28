package sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ContractDeleteAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 1. 画面から送られてきた「消したいデータのID」を受け取る
        String idStr = request.getParameter("id");
        
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            
            // 2. DAOを呼び出してOracleから削除
            ContractDao dao = new ContractDao();
            dao.deleteContract(id);
        }
        
        // 3. 削除が完了したら、最新の一覧を表示するActionへ「横流し（転送）」する
        return mapping.findForward("success");
    }
}
