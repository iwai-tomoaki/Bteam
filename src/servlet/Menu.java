package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;		// 追加したインポート文
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.User;

/**
 * Servlet implementation class Menu
 */
@WebServlet("/Menu")
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;


   // public Menu() {
        //super();
        // TODO Auto-generated constructor stub
   // }


	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		//ログインしているか確認するため
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		//ログインしていない場合
		if (loginUser == null) {
			//リダイレクト
			response.sendRedirect("/Bteam/");
		}
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		// リクエスト先の指定
		String select_button = request.getParameter("select");		//選択した部署ボタンの内容を取得

		//switch分岐用の変数の初期化
		int button = -1;
		//ログイン情報、前回選択したボタンを判別できるように
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");		//ログイン情報をスコープより取得
		Integer login_user_num = loginUser.getEmp_num();		//ログインしているユーザーの社員番号を取得
		String login_user = login_user_num.toString();		//判定のためにいったんString型に変換
		String already = (String) session.getAttribute("select_button");		//前回選択したボタンの番号を取得
		User user_auth_id = (User)session.getAttribute("user_auth_id");		//ログインユーザーの権限をできるように
		Integer user_auth = user_auth_id.getAuth_id();			//ログインユーザーの権限を取得し変数に代入
			if(already != null && select_button == null) {			//初回ではない、部署ボタンを押してない場合分岐
				select_button = (String) session.getAttribute("select_button");		//スコープに保存した部署番号を取得
			}else if(select_button == null){		//部署番号を押していない場合分岐、部署を選択せずに在席・不在を切り替えられるように
				select_button = "no";		//nullのままの場合下のswitch分岐で例外発生するので適当な文字列を代入
			}
			if(already == null) {
				EmployeeDAO dao = new EmployeeDAO();
				dao.DivisionChange(login_user,-1,0);
			}
			String change = request.getParameter("change");
			//不在の社員か判定+押した社員と操作した社員が一致するか判定
			if((change != null && user_auth ==2) || (change != null && login_user.equals(change))){
				EmployeeDAO dao = new EmployeeDAO();
				dao.DivisionChange(change,1,0);		//在席状況を変更するメソッドを実行
			}

		switch(select_button) {		//押したボタンごとに変数定義(部署の番号を代入)
		case "all":				//全表示ボタン
			button = 0;
			break;
		case "tokyo":			//東京部署ボタン
			button = 1;
			break;
		case "tokyo_make":		//東京(開発)ボタン
			button = 2;
			break;
		case "miyazaki":		//宮崎部署ボタン
			button = 3;
			break;
		case "sapporo":			//札幌部署ボタン
			button = 4;
			break;
		}
		User select_user = new User(button);		//選択した部署をUserに保存
		EmployeeDAO dao = new EmployeeDAO();		//Employeeクラスをdao変数に初期設定
		List<User> userList = dao.DivisionSelect(select_user,loginUser);		//選択した部署のユーザーを取得
		List<User> my_user = dao.MyUser(loginUser);			//ログインしているユーザーの情報を取得
		session.setAttribute("my_user",my_user);		//ログインしているユーザーをスコープに保存
		session.setAttribute("select_button",select_button);		//選択した部署をスコープに保存
		session.setAttribute("userList",userList);		//部署のユーザー情報をスコープに保存


		if(user_auth == 2) {		//管理者権限を持っているユーザーの場合に分岐
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_menu.jsp");
			dispatcher.forward(request, response);
		}else {			//管理者以外のユーザーの場合に分岐
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
			dispatcher.forward(request, response);
		}
	}

}
