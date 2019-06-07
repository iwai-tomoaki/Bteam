package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;


public class DivisionDAO {
	public String db_URL = "jdbc:sqlserver://MGT2019\\SQLEXPRESS;databaseName=TeamB";		// データベースのURL情報
	public String db_usre = "TeamB";		// データベースのユーザー情報
	public String db_pass = "teamb";		// SQL serverインストール時に設定したパスワード
	public boolean true_or_false = false;

	public DivisionDAO(User user) {		//登録ユーザーの権限を識別

		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			conn = DriverManager.getConnection(db_URL,db_usre,db_pass);
			PreparedStatement pre_stmt = conn.prepareStatement("SELECT emp_num,pass,auth_id FROM Employee");		//実行するSQl文を入力
            ResultSet result_set = pre_stmt.executeQuery();		//入力したSQL文を実行

            int input_num = user.getEmp_num();		//入力した社員番号をUserクラスから取得
			String input_pass = user.getPass();		//入力したパスワードをUserクラスから取得

    		while(result_set.next()) {			//テーブルの行数分ループ実行
    			if(result_set.getInt("emp_num") == input_num && input_pass.equals(result_set.getString("pass"))) {		//入力した社員番号とパスワードがテーブル内から取得した社員番号とパスワードに一致するか判定
    				if(result_set.getInt("auth_id") == 2) {		//一致したユーザーの権限番号を判定
    					this.true_or_false = true;		//権限が2(管理者)の場合はtrue_or_falseにtrueが入る
    					return;		//returnでLoginサーブレットに戻る
    				}
    			}
			}

		}catch(SQLException ex) {
			ex.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public ArrayList<Integer> emp_num = new ArrayList<Integer>();		//取得したテーブル内のemp_numを格納するList
	public ArrayList<String> pass = new ArrayList<String>();		//取得したテーブル内のpassを格納するList
	public DivisionDAO() {		//登録ユーザー識別用

		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			conn = DriverManager.getConnection(db_URL,db_usre,db_pass);
			PreparedStatement pre_stmt = conn.prepareStatement("SELECT emp_num,pass FROM Employee");		//実行するSQL文を入力
            ResultSet result_set = pre_stmt.executeQuery();		//入力したSQL文を実行


    		while(result_set.next()) {		//テーブルの数分ループ実行
    		emp_num.add(result_set.getInt("emp_num"));		//テーブル内のemp_numをListのall_user_numに入れていく
    		pass.add(result_set.getString("pass"));		//テーブル内のpassをListのall_user_passに入れていく
			}

		}catch(SQLException ex) {
			ex.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
