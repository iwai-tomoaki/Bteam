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
	public String usre = "TeamB";		// データベースのユーザー情報
	public String pass = "teamb";		// SQL serverインストール時に設定したパスワード
	public boolean kai = false;

	public Division_DAO(User user) {		//権限2(管理者識別用)

		Connection conn = null;
		int num = user.getNum();
		String password = user.getPass();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			conn = DriverManager.getConnection(dbURL,usre,pass);
			PreparedStatement pstmt = conn.prepareStatement("SELECT emp_num,pass,auth_id FROM Employee");
            ResultSet rs = pstmt.executeQuery();

    		while(rs.next()) {
    			int emp_num = rs.getInt("emp_num");
    			String pass = rs.getString("pass");
    			if(num==emp_num && password.equals(pass)) {
    				int auth_id = rs.getInt("auth_id");
    				if(auth_id == 2) {
    					this.kai = true;
    					return;
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

	public ArrayList<Integer> user_int = new ArrayList<Integer>();
	public ArrayList<String> user_str = new ArrayList<String>();
	public Division_DAO() {		//登録ユーザー識別用

		Connection conn = null;
		int emp_num = 0;
		String password = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			conn = DriverManager.getConnection(dbURL,usre,pass);
			PreparedStatement pstmt = conn.prepareStatement("SELECT emp_num,pass FROM Employee");
            ResultSet rs = pstmt.executeQuery();

    		while(rs.next()) {
    			emp_num = rs.getInt("emp_num");
    			password = rs.getString("pass");
    			user_int.add(emp_num);
    			user_str.add(password);
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
