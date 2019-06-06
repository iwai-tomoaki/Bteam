package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import action.Authority;
import model.User;


public class Division_DAO {
	// ドライバが読み込まれているかの確認
	 //public static void main(String[] args) throws InstantiationException,
	 //IllegalAccessException{
	 //String msg = "";
	 //try{
	 //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	 //msg = "ドライバのロードに成功しました";
	 //}catch(ClassNotFoundException e){
	 //msg = "ドライバのロードに失敗しました";
	 //}
	 //System.out.println(msg);
	 //}


/*	public static void main(String[] args) {

		Connection conn = null;

		try {

			String dbURL = "jdbc:sqlserver://MGT2019\\SQLEXPRESS;databaseName=TeamB";		// データベースのURL情報
			String usre = "TeamB";		// データベースのユーザー情報
			String pass = "teamb";		// SQL serverインストール時に設定したパスワード
			conn = DriverManager.getConnection(dbURL,usre,pass);

			if(conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version:"+ dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
			}

		}catch(SQLException ex) {
			ex.printStackTrace();
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
	*/

	public boolean select(User user) {

		Connection conn = null;
		int ch = user.getNum();
		String kku = user.getPass();
		int upid1 = 0;


		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String dbURL = "jdbc:sqlserver://MGT2019\\SQLEXPRESS;databaseName=TeamB";		// データベースのURL情報
			String usree = "TeamB";		// データベースのユーザー情報
			String pas = "teamb";		// SQL serverインストール時に設定したパスワード
			conn = DriverManager.getConnection(dbURL,usree,pas);
			PreparedStatement pstmt = conn.prepareStatement("SELECT emp_id,pass,auth_id FROM Employee");
            ResultSet rs = pstmt.executeQuery();

    		while(rs.next()) {
    			int ggg = rs.getInt("emp_id");
    			String papa = rs.getString("pass");
    			if(ch==ggg && kku.equals(papa)) {
    				upid1 = rs.getInt("auth_id");
    				Authority authority = new Authority(upid1);
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
		if(upid1 == 2) {
			return true;
		}
		return false;
	}
}
