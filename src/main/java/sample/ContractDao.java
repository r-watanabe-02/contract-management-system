package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
     * Oracleからすべての契約データを取得する
     * @return 契約データのリスト(DTO)
     */
    public List<ContractDto> selectAll() {
        List<ContractDto> list = new ArrayList<>();
        String sql = "SELECT id, contractor_name, age, plan_code FROM contracts ORDER BY id";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ContractDto dto = new ContractDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("contractor_name"));
                dto.setAge(rs.getInt("age"));
                dto.setPlanCode(rs.getString("plan_code"));

                list.add(dto);
            }
            System.out.println("【DAOログ】データ全件取得が成功しました。");
        } catch (SQLException e) {
            System.out.println("【DAOエラー】データ全件取得中に例外が発生しました。");
            e.printStackTrace();
        }
        return list;
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
            System.out.println("【DAOログ】名前の重複チェックが成功しました。");
        } catch (SQLException e) {
            System.out.println("【DAOエラー】名前の重複チェック中に例外が発生しました。");
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * 指定されたIDの契約データを削除する
     * @param id 削除したいデータのID
     */
    public void deleteContract(int id) {
        String sql = "DELETE FROM contracts WHERE id = ?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
            System.out.println("【DAOログ】ID " + id + " の契約データの削除が成功しました。");
        } catch (SQLException e) {
            System.out.println("【DAOエラー】データ削除中に例外が発生しました。");
            e.printStackTrace();
        }
    }
}
