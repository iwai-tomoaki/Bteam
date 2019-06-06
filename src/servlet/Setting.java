package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/Setting")
public class Setting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException,IOException{
		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
		dispatcher.forward(request, response);
	}




	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {


			// リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			int num = 0;
			String pass = null;
			String newpass = null;
			try{
				String numbar = request.getParameter("num");
				num = Integer.parseInt(numbar);			  // 社員番号の取得
				pass = request.getParameter("pass");		  // パスワードの取得
				newpass = request.getParameter("newpass");		  // 新パスワードの取得
			}catch (Exception e) {
				response.sendRedirect("/Bteam");
				return;
			}

			//Userインスタンス(ユーザー情報)の生成
			if(num != 0 && newpass != null) {
				User user = new User(num,newpass);
			}else {
				User user = new User(pass,newpass);
			}

			// 結果をフォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(
					"/WEB-INF/jsp/management_menu.jsp");
			dispatcher.forward(request, response);
		//doGet(request, response);

	}

}
