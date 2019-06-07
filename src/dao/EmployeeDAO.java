package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class EmployeeDAO {

	// データベースのURL情報
	private final String dbURL = "jdbc:sqlserver://MGT2019\\SQLEXPRESS;databaseName=TeamB";
	// データベースのユーザー情報
	private final String dbUser = "TeamB";
	// SQL serverインストール時に設定したパスワード
	private final String dbPass = "teamb";

	// 社員を全件表示
	public List<User> findAll() {
		List<User> userList = new ArrayList<>();

		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass)) {
			//SELECT文(Employee表全件表示)
			String sql = "SELECT * FROM Employee";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SQL文実行
			ResultSet rs = pStmt.executeQuery();

			//SQL結果をArrayListに格納
			while (rs.next()) {
				int emp_id = rs.getInt("EMP_ID");
				String name = rs.getString("EMP_NAME");
				int num = rs.getInt("EMP_NUM");
				String pass = rs.getString("PASS");
				int status = rs.getInt("PRES_STATUS");
				int divi_id = rs.getInt("DIVI_ID");
				int place_id = rs.getInt("PLACE_ID");
				int auth_id = rs.getInt("AUTH_ID");

				User user = new User
						(emp_id, name, num, pass, status, divi_id, place_id, auth_id);
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userList;

	}

	// 社員表に新しい社員を追加
	public boolean create(User user) {
		try (Connection conn = DriverManager.getConnection
				(dbURL, dbUser, dbPass)) {

			// INSERT文
			String sql = "INSERT INTO Employee VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文中の?に使用する値を設定しSQLを完成
			pStmt.setString(1, user.getName());
			pStmt.setInt(2, user.getNum());
			pStmt.setString(3, user.getPass());
			pStmt.setInt(4, user.getStatus());
			pStmt.setInt(5, user.getDivi_id());
			pStmt.setInt(6, user.getPlace_id());
			pStmt.setInt(7, user.getAuth_id());

			//INSERT文を実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("作成に失敗しました");
			return false;
		}
		return true;
	}

	// Employeeにある社員を社員番号で選択して削除
	public boolean delete(User user) {
		try (Connection conn = DriverManager.getConnection
				(dbURL, dbUser, dbPass)) {

			// DELETE文
			String sql = "DELETE FROM Employee WHERE emp_num = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// DELETE文中の?に使用する値を設定しSQLを完成
			// このままだと自分自身を削除する可能性があるため一応コメントアウトしています
//			pStmt.setInt(1, user.getNum());

			// DELETE文を実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("削除に失敗しました");
			return false;
		}
		return true;
	}

	// Employeeにある社員のパスワードを社員番号で選択して更新
	public boolean changePass(User user) {
		try (Connection conn = DriverManager.getConnection
				(dbURL, dbUser, dbPass)) {

			// UPDATE文
			String sql = "UPDATE Employee SET pass = ? WHERE emp_num = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// UPDATE文中の?に使用する値を設定しSQLを完成
			pStmt.setString(1, user.getPass());
			pStmt.setInt(2, user.getNum());

			// UPDATE文を実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更新に失敗しました");
			return false;
		}
		return true;
	}
}
