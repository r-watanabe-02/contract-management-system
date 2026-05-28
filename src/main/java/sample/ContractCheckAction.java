package sample;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ContractCheckAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = request.getParameter("name");
        String resultMessage = "OK"; 

        ContractDao dao = new ContractDao();
        if (dao.existsName(name)) {
            resultMessage = "NG";
        }

        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(resultMessage); 
        out.flush();

        // 次の画面へは進まない
        return null; 
    }
}