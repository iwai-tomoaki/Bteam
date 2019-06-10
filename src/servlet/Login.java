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
import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		int input_num = 0;
		String input_pass = null;
		//入力内容の取得、および"num"をint型に変換
		try{
			input_num = Integer.parseInt(request.getParameter("input_num"));		//入力したnumをStringで取得してint型に変換
			input_pass = request.getParameter("input_pass");		//入力した内容をStringで取得
		}catch (Exception E) {			//入力内容でエラーが発生した時に実行
			response.sendRedirect("/Bteam");		//エラーが発生した時にリダイレクトを実行
			return;			//returnを使わないとコンパイルエラー
		}

		//Userインスタンス(ユーザー情報)の生成
		User user = new User(input_num,input_pass);

		//ログイン処理
		LoginLogic loginLogic = new LoginLogic(user);		//LoginLogicクラスの引数userを実行

 		//ログイン成功時の処理、loginLogiのtrue_or_falseを参照し真偽判定
 		if(loginLogic.true_or_false)
 		{
 			//ユーザー情報をセクションスコープへ保存
 			HttpSession session = request.getSession();
 			session.setAttribute("loginUser",user);
 			EmployeeDAO dao = new EmployeeDAO(user);
 			//登録ユーザーの権限判定処理、daoのtrue_or_falseを参照し真偽判定
 			if(dao.true_or_false) {
 				//判定結果画面のフォワード(管理者画面へ)
			 	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_menu.jsp");
			 	dispatcher.forward(request, response);
 			}else {
 				//判定結果画面のフォワード(一般画面へ)
 				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
 				dispatcher.forward(request, response);
 			}
 		}else {
 			System.out.print("ログインに失敗しました");
			//リダイレクト
			response.sendRedirect("/Bteam");
 		}
	}
}