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
@WebServlet("/ManagementSetting")
public class ManagementSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			//再設定用に入力した内容をString型で取得
			String emp_name = request.getParameter("emp_name");
			String num = request.getParameter("emp_num");
			String pass = request.getParameter("pass");
			String divi = request.getParameter("divi_id");
			String workPlace = request.getParameter("workPlace_id");
			String auth = request.getParameter("auth_id");
			String pass_confi = request.getParameter("new_pass_confi");		//確認のためにもう一度入力した新パスワードを取得
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_setting.jsp");		//元の画面にフォワード、複数あるので外側にリンク指定

			if(!(pass.equals(pass_confi))){		//一致していない場合分岐、上の条件に一致したパターンによって代入したcheckの値によっても分岐
				System.out.println("パスワード不一致");		//エクリプスでの確認用
				//フォワード
				dispatcher.forward(request, response);
				return;		//書かないと動きはするがエラーが出る
			}

			//String型で取得していた内容の一部をデータベースに合わせてint型に変換
			int emp_num = Integer.parseInt(num);
			int divi_id = Integer.parseInt(divi);
			int workPlace_id = Integer.parseInt(workPlace);
			int auth_id = Integer.parseInt(auth);

			User user = (User)request.getSession().getAttribute("loginUser");
			// 自身を変更していないか判定
			if (emp_num == user.getEmp_num()) {
				Boolean result = false;

				System.out.print(result);

				request.setAttribute("managementResult", result);

				dispatcher.forward(request, response);
			} else {
				User change_user = new User(emp_name, emp_num, pass, divi_id, workPlace_id, auth_id);
				//データベースに使う値をchangeDataメソッドに送りデータベースを更新
				Boolean result = empDao.changeData(change_user);

				System.out.print(result);

				request.setAttribute("managementResult", result);

				//フォワード
				dispatcher.forward(request, response);
			}

		}
	}
}