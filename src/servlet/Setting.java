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
			String numbar = request.getParameter("num");
			int num = Integer.parseInt(numbar);			  // 社員番号の取得
			String pass = request.getParameter("pass");		  // パスワードの取得

			//Userインスタンス(ユーザー情報)の生成
			User user = new User(num,pass);

			// 結果をフォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(
					"/WEB-INF/jsp/management_menu.jsp");
			dispatcher.forward(request, response);
		//doGet(request, response);
	}

}
