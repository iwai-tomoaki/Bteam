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
			String num = request.getParameter("emp_num");		//入力した識別用の社員番号を取得
			String pass = request.getParameter("new_pass");		//入力した新しいパスワードを取得
			String pass_confi = request.getParameter("new_pass_confi");		//確認のためにもう一度入力した新パスワードを取得

			if(!(pass.equals(pass_confi))){		//一致していない場合分岐、上の条件に一致したパターンによって代入したcheckの値によっても分岐
				System.out.println("不一致");		//エクリプスでの確認用
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_setting.jsp");		//元の画面にフォワード
				dispatcher.forward(request, response);
				return;		//書かないと動きはするがエラーが出る
			}

			int emp_num = Integer.parseInt(num);		//String型で取得したemp_numをint型に変換

			Boolean result = empDao.changeUserPass(pass, emp_num);		//ユーザーのパスワードを再設定するメソッドを実行、成功をjspで通知できるように戻り値をbooean型にする

			System.out.println(result);

			request.setAttribute("changeUserResult", result);		//リクエストスコープに保存

			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_setting.jsp");
			dispatcher.forward(request, response);
		}
	}
}
