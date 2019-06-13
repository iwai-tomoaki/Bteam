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

@WebServlet("/SettingUesr")
public class SettingUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

		//ログインしているか確認するため
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		//ログインしていない場合
		if (loginUser == null) {
			//リダイレクト
			response.sendRedirect("/Bteam/");
		} else { //ログイン済みの場合
			User user_auth_id = (User) session.getAttribute("user_auth_id");
			int user_auth = user_auth_id.getAuth_id();
			if (user_auth == 2) {
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
			dispatcher.forward(request, response);
			}else {
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/");
				dispatcher.forward(request, response);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインしているか確認するため
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		//ログインしていない場合
		if (loginUser == null) {
			//リダイレクト
			response.sendRedirect("/Bteam/");
		} else { //ログイン済みの場合
			// リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");

			EmployeeDAO empDao = new EmployeeDAO();
			String pass = request.getParameter("new_pass");
			User user = (User)request.getSession().getAttribute("loginUser");

			Boolean result = empDao.changePass(pass, user.getEmp_num());

			System.out.println(result);

			request.setAttribute("changeResult", result);

			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
			dispatcher.forward(request, response);
		}
	}
}
