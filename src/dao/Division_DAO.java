package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Division_DAO {
	// ドライバが読み込まれているかの確認
	// public static void main(String[] args) throws InstantiationException,
	// IllegalAccessException{
	// String msg = "";
	// try{
	// Class.forName("").newInstance(
	// msg = "ドライバのロードに成功しました";
	// }catch(ClassNotFoundException e){
	// msg = "ドライバのロードに失敗しました";
	// }
	// System.out.println(msg);
	// }


	public static void main(String[] args) {

		Connection conn = null;

		try {

			String dbURL = "";		// データベースのURL情報
			String usre = "";		// データベースのユーザー情報
			String pass = "";		// SQL serverインストール時に設定したパスワード
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

}
