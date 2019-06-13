
package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.User;


@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログインしているか確認するため
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) { //ログインしていない場合
			//リダイレクト
			response.sendRedirect("/Bteam/");
		} else { //ログイン済みの場合

			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_user.jsp");
			dispatcher.forward(request, response);
		}
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EmployeeDAO empDao = new EmployeeDAO();

		// 社員情報を追加
		String emp_name = request.getParameter("emp_name");
		String num = request.getParameter("emp_num");
		String pass = request.getParameter("pass");
		String divi = request.getParameter("divi_id");
		String workPlace = request.getParameter("workPlace_id");
		String auth = request.getParameter("auth_id");

		if(emp_name.equals("") || num.equals("")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_user.jsp");		//エラーが発生した時にリダイレクトを実行
			dispatcher.forward(request, response);
		}

		if (pass.equals("")) {
			pass = "1234";
		}

		int emp_num = Integer.parseInt(num);
		int divi_id = Integer.parseInt(divi);
		int workPlace_id = Integer.parseInt(workPlace);
		int auth_id = Integer.parseInt(auth);

		Boolean result = empDao.create(emp_name, emp_num, pass, divi_id, workPlace_id, auth_id);

		System.out.println(result);

		// 社員リストの取得
//		List<User> userList = empDao.findAll();
//		request.setAttribute("userList", userList);

		request.setAttribute("newResult", result);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_user.jsp");
		dispatcher.forward(request, response);

	}
}
