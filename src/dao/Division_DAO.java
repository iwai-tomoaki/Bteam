package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;


public class Division_DAO {
	public String dbURL = "jdbc:sqlserver://MGT2019\\SQLEXPRESS;databaseName=TeamB";		// データベースのURL情報
	public String usree = "TeamB";		// データベースのユーザー情報
	public String pas = "teamb";		// SQL serverインストール時に設定したパスワード
	public boolean kai = false;

	public Division_DAO(User user) {		//権限2(管理者識別用)

		Connection conn = null;
		int ch = user.getNum();
		String kku = user.getPass();
		//int upid1 = 0;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			conn = DriverManager.getConnection(dbURL,usree,pas);
			PreparedStatement pstmt = conn.prepareStatement("SELECT emp_id,pass,auth_id FROM Employee");
            ResultSet rs = pstmt.executeQuery();

    		while(rs.next()) {
    			int ggg = rs.getInt("emp_id");
    			String papa = rs.getString("pass");
    			if(ch==ggg && kku.equals(papa)) {
    				this.kai = true;
    				return;
    				//upid1 = rs.getInt("auth_id");
    				//Authority authority = new Authority(upid1);
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

	public ArrayList<Integer> user_int = new ArrayList<Integer>();
	public ArrayList<String> user_str = new ArrayList<String>();
	public Division_DAO() {		//登録ユーザー識別用

		Connection conn = null;
		int ggg = 0;
		String papa = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			conn = DriverManager.getConnection(dbURL,usree,pas);
			PreparedStatement pstmt = conn.prepareStatement("SELECT emp_id,pass FROM Employee");
            ResultSet rs = pstmt.executeQuery();

    		while(rs.next()) {
    			ggg = rs.getInt("emp_id");
    			papa = rs.getString("pass");
    			user_int.add(ggg);
    			user_str.add(papa);
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
