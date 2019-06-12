// 北
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

/**
 * Servlet implementation class User_management
 */
@WebServlet("/ManagementUser")
public class ManagementUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインしているか確認するため
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		//ログインしていない場合
		if (loginUser == null) {
			//リダイレクト
			response.sendRedirect("/Bteam/");
		} else { //ログイン済みの場合
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_setting.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EmployeeDAO empDao = new EmployeeDAO();
		String pass = request.getParameter("new_pass");
		String num = request.getParameter("emp_num");

		int emp_num = Integer.parseInt(num);

		System.out.print(empDao.changeUserPass(pass, emp_num));

		doGet(request, response);
	}

}
