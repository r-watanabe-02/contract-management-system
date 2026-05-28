package sample;

import org.apache.struts.action.ActionForm;

public class ContractForm extends ActionForm {
    
    // 画面の入力項目と同じ名前の変数を用意する
    private String contractorName;
    private String age;
    private String plan;

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPlan() {
        return plan;
    }
}
