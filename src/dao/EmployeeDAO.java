package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

// git ignore test
public class EmployeeDAO {

	// データベースのURL情報
	private final String db_url = "jdbc:sqlserver://MGT2019\\SQLEXPRESS;databaseName=TeamB";
	// データベースのユーザー情報
	private final String db_user = "TeamB";
	// SQL serverインストール時に設定したパスワード
	private final String db_pass = "teamb";
	// 真偽値判定用
	public boolean true_or_false = false;

	//登録ユーザーの権限を識別
	public EmployeeDAO(User user) {

		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			conn = DriverManager.getConnection(db_url,db_user,db_pass);
			//実行するSQl文を入力
			PreparedStatement pre_stmt = conn.prepareStatement("SELECT emp_num,pass,auth_id FROM Employee");
			//入力したSQL文を実行
			ResultSet result_set = pre_stmt.executeQuery();

			//入力した社員番号をUserクラスから取得
			int input_num = user.getEmp_num();
			//入力したパスワードをUserクラスから取得
			String input_pass = user.getPass();

			//テーブルの行数分ループ実行
			while(result_set.next()) {
				//入力した社員番号とパスワードがテーブル内から取得した社員番号とパスワードに一致するか判定
				if(result_set.getInt("emp_num") == input_num && input_pass.equals(result_set.getString("pass"))) {
					//一致したユーザーの権限番号を判定
					if(result_set.getInt("auth_id") == 2) {
						//権限が2(管理者)の場合はtrue_or_falseにtrueが入る
						this.true_or_false = true;
						//returnでLoginサーブレットに戻る
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

	//取得したテーブル内のemp_numを格納するList
	public ArrayList<Integer> emp_num = new ArrayList<Integer>();
	//取得したテーブル内のpassを格納するList
	public ArrayList<String> pass = new ArrayList<String>();

	//登録ユーザー識別用
	public EmployeeDAO() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			conn = DriverManager.getConnection(db_url,db_user,db_pass);
			//実行するSQL文を入力
			PreparedStatement pre_stmt = conn.prepareStatement("SELECT emp_num,pass FROM Employee");
			//入力したSQL文を実行
			ResultSet result_set = pre_stmt.executeQuery();

			//テーブルの数分ループ実行
			while(result_set.next()) {
				//テーブル内のemp_numをListのall_user_numに入れていく
				emp_num.add(result_set.getInt("emp_num"));
				//テーブル内のpassをListのall_user_passに入れていく
				pass.add(result_set.getString("pass"));
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

	// 社員を全件表示
	public List<User> findAll() {
		List<User> userList = new ArrayList<>();

		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(db_url, db_user, db_pass)) {
			//SELECT文(Employee表全件表示)
			String sql = "SELECT * FROM Employee";
			PreparedStatement pre_stmt = conn.prepareStatement(sql);

			//SQL文実行
			ResultSet rs = pre_stmt.executeQuery();

			//SQL結果をArrayListに格納
			while (rs.next()) {
				int emp_id = rs.getInt("EMP_ID");
				String emp_name = rs.getString("EMP_NAME");
				int emp_num = rs.getInt("EMP_NUM");
				String pass = rs.getString("PASS");
				int pres_status = rs.getInt("PRES_STATUS");
				int divi_id = rs.getInt("DIVI_ID");
				int place_id = rs.getInt("WORKPLACE_ID");
				int auth_id = rs.getInt("AUTH_ID");

				User user = new User
						(emp_id, emp_name, emp_num, pass, pres_status, divi_id, place_id, auth_id);
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
		try (Connection conn = DriverManager.getConnection(db_url, db_user, db_pass)) {

			// INSERT文
			String sql = "INSERT INTO Employee VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pre_stmt = conn.prepareStatement(sql);

			//INSERT文中の?に使用する値を設定しSQLを完成
			pre_stmt.setString(1, user.getEmp_name());
			pre_stmt.setInt(2, user.getEmp_num());
			pre_stmt.setString(3, user.getPass());
			pre_stmt.setInt(4, user.getPres_status());
			pre_stmt.setInt(5, user.getDivi_id());
			pre_stmt.setInt(6, user.getPlace_id());
			pre_stmt.setInt(7, user.getAuth_id());

			//INSERT文を実行
			int result = pre_stmt.executeUpdate();
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
		try (Connection conn = DriverManager.getConnection(db_url, db_user, db_pass)) {

			// DELETE文
			String sql = "DELETE FROM Employee WHERE emp_num = ?";
			PreparedStatement pre_stmt = conn.prepareStatement(sql);

			// DELETE文中の?に使用する値を設定しSQLを完成
			// このままだと自分自身を削除する可能性があるため一応コメントアウトしています
			pre_stmt.setInt(1, user.getEmp_num());

			// DELETE文を実行
			int result = pre_stmt.executeUpdate();
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
		try (Connection conn = DriverManager.getConnection(db_url, db_user, db_pass)) {

			// UPDATE文
			String sql = "UPDATE Employee SET pass = ? WHERE emp_num = ?";
			PreparedStatement pre_stmt = conn.prepareStatement(sql);

			// UPDATE文中の?に使用する値を設定しSQLを完成
			pre_stmt.setString(1, user.getPass());
			pre_stmt.setInt(2, user.getEmp_num());

			// UPDATE文を実行
			int result = pre_stmt.executeUpdate();
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
