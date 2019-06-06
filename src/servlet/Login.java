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
import model.LoginLogic;
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
		String numbar = request.getParameter("user_id");
		int num = Integer.parseInt(numbar);
		String pass = request.getParameter("password");

		//Userインスタンス(ユーザー情報)の生成
		User user = new User(num,pass);

		//ログイン処理
		LoginLogic loginLogic = new LoginLogic();
 		boolean isLogin = loginLogic.execute(user);

 		//ログイン成功時の処理
 		if(isLogin)
 		{
 			//ユーザー情報をセクションスコープへ保存
 			HttpSession session = request.getSession();
 			session.setAttribute("loginUser",user);

 			Division_DAO dao = new Division_DAO();
 			int aaa = user.getNum();
 			boolean dao1 = dao.select(aaa);
 			if(dao1) {
 				//ログイン結果画面のフォワード
			 	RequestDispatcher dispatcher =
			 		request.getRequestDispatcher("/WEB-INF/jsp/management_menu.jsp");
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