package sample;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class ContractListAction extends Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        ContractDao dao = new ContractDao();
        List<ContractDto> contractList = dao.selectAll();

        // プラン名を日本語に変換する
        MessageResources resources = getResources(request);
        for (ContractDto dto : contractList) {
            String planCode = dto.getPlanCode(); 
            String planName = resources.getMessage("plan." + planCode);
            dto.setPlanName(planName); 
        }

        request.setAttribute("contractList", contractList);

        return mapping.findForward("success");
    }
}
