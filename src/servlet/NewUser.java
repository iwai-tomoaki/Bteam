
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
			User user_auth_id = (User) session.getAttribute("user_auth_id");
			int user_auth = user_auth_id.getAuth_id();
			if (user_auth == 2) {
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_user.jsp");
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

			EmployeeDAO empDao = new EmployeeDAO();		//EmployeeDAOクラスをempDaoの変数名で使えるように

			//入力した社員情報をString型で取得
			String emp_name = request.getParameter("emp_name");
			String num = request.getParameter("emp_num");
			String pass = request.getParameter("pass");
			String divi = request.getParameter("divi_id");
			String workPlace = request.getParameter("workPlace_id");
			String auth = request.getParameter("auth_id");

			if(emp_name.equals("") || num.equals("")) {		//ユーザー名、社員番号が未入力の場合分岐
				Boolean result = false;

				System.out.print(result);

				request.setAttribute("managementResult", result);
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_setting.jsp");		//エラーが発生した時にリダイレクトを実行
				dispatcher.forward(request, response);
			}

			if (pass.equals("")) {  	//パスワードが未入力の場合分岐
				pass = "1234";		//未入力の場合デフォルトでいったん1234を入れる
			}
			//データベースでint型の物をStringからint型に変換(下x4)
			int emp_num = Integer.parseInt(num);
			int divi_id = Integer.parseInt(divi);
			int workPlace_id = Integer.parseInt(workPlace);
			int auth_id = Integer.parseInt(auth);
			//入力した内容、変換した内容をEmployeeDAOクラスのcreateメソッドに送る
			Boolean result = empDao.create(emp_name, emp_num, pass, divi_id, workPlace_id, auth_id);

			System.out.println(result);

			request.setAttribute("newResult", result);

			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_user.jsp");
			dispatcher.forward(request, response);

		}
	}
}