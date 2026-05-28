package sample;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ContractListAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 1. DAOを呼んで、Oracleから全データを取ってくる
        ContractDao dao = new ContractDao();
        List<ContractDto> contractList = dao.selectAll();
        
        // 2. 取ってきたリストを、JSP（画面）に引き渡すためにリクエストにセットする
        // ※「contractList」という名前でJSPから読めるようになります
        request.setAttribute("contractList", contractList);
        
        // 3. 一覧画面（success）へ進む
        return mapping.findForward("success");
    }
}
