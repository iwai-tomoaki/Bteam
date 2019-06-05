package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Division_DAO {
	// データベース接続に使用する情報
	private static String DRIVER_NAME = "";	// ドライバの名前
	private static String JDBC_URL ="";		// 接続先のURL
	private static String DB_USER = ""; 		// 接続先のユーザー名
	private static String DB_PASS = "";		// 接続先のパスワード

	// データベースに接続
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER_NAME);
			con = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
