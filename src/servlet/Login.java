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

	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		int num = 0;
		String pass = null;
		//入力内容の取得、および"num"をint型に変換
		try{
			num = Integer.parseInt(request.getParameter("num"));
			pass = request.getParameter("pass");
		}catch (Exception E) {
			response.sendRedirect("/Bteam");
			return;
		}

		//Userインスタンス(ユーザー情報)の生成
		User user = new User(num,pass);

		//ログイン処理
		LoginLogic loginLogic = new LoginLogic(user);		//LoginLogicクラスの引数userを実行

 		//ログイン成功時の処理、loginLogiのtrue_or_falseを参照し真偽判定
 		if(loginLogic.true_or_false)
 		{
 			//ユーザー情報をセクションスコープへ保存
 			HttpSession session = request.getSession();
 			session.setAttribute("loginUser",user);
 			Division_DAO dao = new Division_DAO(user);
 			//登録ユーザーの権限判定処理、daoのtrue_or_falseを参照し真偽判定
 			if(dao.true_or_false) {
 				//判定結果画面のフォワード
			 	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_menu.jsp");
			 	dispatcher.forward(request, response);
 			}else {
 				//判定結果画面のフォワード
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
 				dispatcher.forward(request, response);
 			}
 		}else {
			//リダイレクト
			response.sendRedirect("/Bteam");
 		}
	}
}