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

			int check = 0;
			int check_new_old = 0;
			EmployeeDAO empDao = new EmployeeDAO();
			String num = request.getParameter("emp_num");		//入力した識別用の社員番号を取得
			String pass = request.getParameter("new_pass");		//入力した新しいパスワードを取得
			String pass_confi = request.getParameter("new_pass_confi");		//確認のためにもう一度入力した新パスワードを取得


			if(num.equals("")) {		//社員番号が未入力の時実行
				Boolean result = false;		//失敗通知用の条件変数

				System.out.println(result);

				request.setAttribute("changeUserResult", result);		//リクエストスコープに保存
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_setting.jsp");		//エラーが発生した時にリダイレクトを実行
				dispatcher.forward(request, response);
			}

			if (pass.equals("")) {		//パスワードが未入力のとき実行
				pass = "1234";		//デフォルトで1234を入れる
				System.out.println("新パスワードに初期値入力");		//エクリプスでの確認用
				check = 1;
			}
			if (check == 1 && !(pass_confi.equals(""))) {		//確認用パスワードが入力、設定用のパスワードが未入力のとき実行
				System.out.println("確認用パスワードが入力、設定用のパスワードが未入力");		//エクリプスでの確認用
				check = 0;
				check_new_old = 1;
			}
			if (!(pass.equals("")) && !(pass_confi.equals("")) && check == 1) {		//確認用パスワードが入力、設定用のパスワードが入力のとき実行
				System.out.println("確認用パスワードが入力、設定用のパスワードが入力");		//エクリプスでの確認用
				check = 0;
			}
			if (pass.equals("1234") && pass_confi.equals("1234") && check_new_old == 0) {		//新と確認両方にデフォルトパスワードと同じ値を入力したとき実行
				System.out.println("新と確認両方にデフォルトパスワードと同じ値を入力");		//エクリプスでの確認用
				check = 1;
			}

			if((!(pass.equals(pass_confi)) && !(pass.equals("1234"))) || (pass.equals("1234")) && check == 0){		//一致していない場合分岐、上の条件に一致したパターンによって代入したcheckの値によっても分岐
				System.out.println(pass + pass_confi + check);		//エクリプスでの確認用
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
