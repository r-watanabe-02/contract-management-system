package sample;

import java.io.Serializable;

/**
 * 契約データを保持するDTOクラス
 */
public class ContractDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String contractorName;
    private int age;
    private String planCode;
    private String planName; // 画面表示用の日本語プラン名

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return contractorName;
    }
    public void setName(String name) {
        this.contractorName = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getPlanCode() {
        return planCode;
    }
    public void setPlanCode(String plan) {
        this.planCode = plan;
    }
    
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
}
