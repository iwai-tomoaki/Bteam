package action;

import java.util.List;

import dao.EmployeeDAO;
import model.User;

public class EmployeeDAOTest{
	public static void main(String[] args) {
		testFind1();		// ユーザーが見つかる場合のテスト
		//testFind2();		// ユーザーが見つからない場合のテスト
	}
	public static void testFind1() {
		//Login login = new Login();
		EmployeeDAO empDAO = new EmployeeDAO();
		List<User> userList = empDAO.findAll();
	}

}
