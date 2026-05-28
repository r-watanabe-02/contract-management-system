package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContractDao {
    
    // Oracleの接続情報
    private static final ResourceBundle rb = ResourceBundle.getBundle("db");

    private static final String URL = rb.getString("db.url");
    private static final String USER = rb.getString("db.user");
    private static final String PASSWORD = rb.getString("db.password");

    /**
     * 契約データをOracleに保存するメソッド
     * @param form 画面から入力されたデータが入ったForm
     */
    public void insert(ContractForm form) {
        
        String sql = "INSERT INTO contracts (contractor_name, age, plan_code) VALUES (?, ?, ?)";

        // 1. ドライバーのロード
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2. データベースへの接続とSQLの実行
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Formから値を取り出してSQLにセット
            pstmt.setString(1, form.getContractorName());
            pstmt.setInt(2, Integer.parseInt(form.getAge()));
            pstmt.setString(3, form.getPlan());

            pstmt.executeUpdate();
            System.out.println("【DAOログ】Oracleへのインサートが成功しました。");

        } catch (SQLException e) {
            System.out.println("【DAOエラー】データベース操作中に例外が発生しました。");
            e.printStackTrace();
        }
    }
    
    /**
     * 指定された名前が既に登録されているかチェックする
     * @param name チェックしたい名前
     * @return 既に存在する場合はtrue、存在しない場合はfalse
     */
    public boolean existsName(String name) {
        String sql = "SELECT COUNT(*) FROM contracts WHERE contractor_name = ?";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // カウントが0より大きければ「存在する(true)」
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("【DAOエラー】名前の重複チェック中に例外が発生しました。");
            e.printStackTrace();
        }
        
        return false;
    }
}
