package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Division_DAO;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException
	{

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		int num = 0;
		String pass = null;
		try{
			String numbar = request.getParameter("num");
			num = Integer.parseInt(numbar);
			pass = request.getParameter("pass");
		}catch (Exception e) {
			response.sendRedirect("/Bteam");
			return;
		}

		//Userインスタンス(ユーザー情報)の生成
		User user = new User(num,pass);

		//ログイン処理
		Division_DAO loginLogic = new Division_DAO();
		boolean isLogin = loginLogic.exext(user,1);

 		//ログイン成功時の処理
 		if(isLogin)
 		{
 			//ユーザー情報をセクションスコープへ保存
 			HttpSession session = request.getSession();
 			session.setAttribute("loginUser",user);

 			Division_DAO dao = new Division_DAO();
 			boolean dao1 = dao.select(user);
 			if(dao1) {
 				//ログイン結果画面のフォワード
			 	RequestDispatcher dispatcher =
			 		request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
			 	dispatcher.forward(request, response);
 			}else {
 				//ログイン結果画面のフォワード
 				RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
 				dispatcher.forward(request, response);
 			}
 		}else {
			//リダイレクト
			response.sendRedirect("/Bteam");
 		}
	}
}
//