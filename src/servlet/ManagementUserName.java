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
 * Servlet implementation class ManagementUserName
 */
@WebServlet("/ManagementUserName")
public class ManagementUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		//ログインしているか確認するため
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_setting.jsp");
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
			EmployeeDAO empDao = new EmployeeDAO();
			//再設定用に入力した内容をString型で取得
			String emp_name = request.getParameter("emp_name");
			String num = request.getParameter("emp_num");


			//String型で取得していた内容の一部をデータベースに合わせてint型に変換
			int emp_num = Integer.parseInt(num);

			User user = (User)request.getSession().getAttribute("loginUser");
			// 自身を変更していないか判定
			if (emp_num == user.getEmp_num()) {
				Boolean result = false;

				System.out.print(result);

				request.setAttribute("managementResult", result);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_setting.jsp");
				dispatcher.forward(request, response);
			} else {

				//データベースに使う値をchangeDataメソッドに送りデータベースを更新
				Boolean result = empDao.namechange(emp_name, emp_num);

				System.out.print(result);

				request.setAttribute("managementResult", result);

				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_setting.jsp");
				dispatcher.forward(request, response);
			}

		}
	}

}
