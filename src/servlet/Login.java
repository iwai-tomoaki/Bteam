 package servlet;

import java.io.IOException;
import java.util.List;

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
		int input_num = 0;			//tryの中で定義する数字をtry外でも使えるようにするために外で初期化
		String input_pass = null;		//tryの中で定義する数字をtry外でも使えるようにするために外で初期化
		//入力内容の取得、および"num"をint型に変換
		try{
			input_num = Integer.parseInt(request.getParameter("input_num"));		//入力したnumをStringで取得してint型に変換
			input_pass = request.getParameter("input_pass");		//入力した内容をStringで取得
		}catch (Exception E) {			//入力内容でエラーが発生した時に実行
			response.sendRedirect("/Bteam");		//エラーが発生した時にリダイレクトを実行
			return;			//returnを使わないとコンパイルエラー
		}
		if(true) {		//前回ログインした時の部署選択情報が残っているので実行するたびにスコープを破棄
			HttpSession session = request.getSession();
			session.invalidate();
		}
		//Userインスタンス(ユーザー情報)の生成
		User user = new User(input_num,input_pass);

		//ログイン処理
		LoginLogic loginLogic = new LoginLogic(user);		//LoginLogicクラスの引数userを実行

 		//ログイン成功時の処理、loginLogiのtrue_or_falseを参照し真偽判定
 		if(loginLogic.true_or_false)
 		{
 			User user_name = new User(loginLogic.user_name);
 			//ユーザー情報をセクションスコープへ保存
 			HttpSession session = request.getSession();
 			session.setAttribute("loginUserName",user_name);		//ユーザーの名前をスコープに保存
 			session.setAttribute("loginUser",user);			//ユーザーの社員番号とパスワードをスコープに保存
 			EmployeeDAO dao = new EmployeeDAO();		//Dao.javaをdao変数に初期化
 			List<User> my_user = dao.MyUser(user);		//ログインユーザーの情報を取得
 			session.setAttribute("my_user",my_user);		//ログインしたユーザーの情報をスコープに保存
 			//daoのauthに権限番号を代入、その社員に一致する権限番号をuser_auth_idに
 	 		User user_auth_id = new User(dao.auth,0);			//ログインしたユーザーの権限を1(一般)に設定
 	 		session.setAttribute("user_auth_id",user_auth_id);		//ログインしたユーザーの権限をスコープに保存

 			//判定結果画面のフォワード(メニュー画面の表示のためにMenu.javaに移動)
 			RequestDispatcher dispatcher = request.getRequestDispatcher("/Menu");
 			dispatcher.forward(request, response);
 		}else {
 			Boolean result = false;		//ログインに失敗した場合に知らせる用の変数

			System.out.print(result);

			request.setAttribute("loginResult", result);		//リクエストスコープに保存

 			System.out.println("ログインに失敗しました");
 			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
 		}
	}
}