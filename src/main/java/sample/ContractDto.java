package sample;

import java.io.Serializable;

/**
 * 契約データを保持するDTOクラス（データ専用の箱）
 */
public class ContractDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name; // データベースの contractor_name に対応
    private int age;     // データベース側が数値型なら int で持つのが綺麗です
    private String plan;

    // 引数なしのコンストラクタ
    public ContractDto() {
    }

    // すべてのフィールドを持つ便利なコンストラクタ（任意ですがあると便利です）
    public ContractDto(int id, String name, int age, String plan) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.plan = plan;
    }

    // 以下、すべての Getter / Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getPlan() {
        return plan;
    }
    public void setPlan(String plan) {
        this.plan = plan;
    }
}
