package action;

import java.util.List;

import dao.EmployeeDAO;
import model.User;

public class SelectEmployeeSample{
	public static void main(String[] args) {
		// employeeテーブルの全レコードを取得
		EmployeeDAO empDAO = new EmployeeDAO();
		List<User> empList = empDAO.findAll();

		// 取得したレコードの内容を出力
		for(User user : empList) {
			System.out.println("ID:" + user.getEmp_id());
			System.out.println("名前:" + user.getEmp_name());
			System.out.println("社員番号:" + user.getEmp_num());
			System.out.println("パスワード:" + user.getPass());
			System.out.println("ステータス:" + user.getPres_status());
			System.out.println("ID:" + user.getDivi_id());
			System.out.println("ID:" + user.getPlace_id());
			System.out.println("ID:" + user.getAuth_id());
		}
	}
}
