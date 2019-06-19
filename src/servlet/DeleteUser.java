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

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/delete_user.jsp");
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
			String str = request.getParameter("emp_num");		//入力した社員番号String型で取得
			String str_user = request.getParameter("emp_user_num");		//ボタンで取得した社員番号String型で取得
			int emp_num = 0;

			if(str != null) {
				emp_num = Integer.parseInt(str);		//入力して取得した社員番号をString型からint型に変換
			}else {
				emp_num = Integer.parseInt(str_user);		//ボタンで取得した社員番号をString型からint型に変換
			}
			User user = (User)request.getSession().getAttribute("loginUser");
			int check = 0;

			// 自身を消そうとしていないか判定
			if (emp_num != user.getEmp_num()) {		//消そうとしていない(ログインしているユーザーの社員番号と消そうとしている社員番号が一致しない場合実行)
				Boolean result = empDao.delete(emp_num);

				System.out.print(result);
				check = 1;
				request.setAttribute("deleteResult", result);
			} else {
				Boolean result = false;

				System.out.print(result);
				request.setAttribute("deleteResult", result);
			}
			if(check == 1 && str != null) {
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/delete_user.jsp");
				dispatcher.forward(request, response);
			}else {
				session.setAttribute("delete",check);		//部署表示処理からdeleteページに戻れるように適当な数を入れておく
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Menu");
				dispatcher.forward(request, response);
			}
		}
	}
}