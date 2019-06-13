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


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		// リクエスト先の指定
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"WEB-INF/jsp/menu.jsp");
		dispatcher.forward(request, response);

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		// リクエスト先の指定
		String select_button = request.getParameter("select");

		//switch分岐用の変数の初期化
		int button = 0;
		//ログイン情報、前回選択したボタンを判別できるように
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");		//ログイン情報をスコープより取得
		Integer login_user_num = loginUser.getEmp_num();
		String login_user = login_user_num.toString();
		String already = (String) session.getAttribute("select_button");		//前回選択したボタンの番号を取得
			if(already != null && select_button == null) {			//初回ではない、部署ボタンを押してない場合分岐
				select_button = (String) session.getAttribute("select_button");
			}
			User user_auth_id = (User)session.getAttribute("user_auth_id");
			Integer user_auth = user_auth_id.getAuth_id();
			String changeup = request.getParameter("changeup");
			//不在の社員か判定+押した社員と操作した社員が一致するか判定
			if((changeup != null && user_auth ==2) || (changeup != null && login_user.equals(changeup))){
				EmployeeDAO adddao = new EmployeeDAO();
				adddao.DivisionChangeup(changeup);
			}
			//在席の社員か判定+押した社員と操作した社員が一致するか判定
			String changedown = request.getParameter("changedown");
			if((changedown != null && user_auth ==2) || (changedown != null && login_user.equals(changedown))) {
				EmployeeDAO divdao = new EmployeeDAO();
				divdao.DivisionChangedown(changedown);
			}

		switch(select_button) {		//押したボタンごとに変数定義
		case "all":
			button = 0;
			break;
		case "tokyo":
			button = 1;
			break;
		case "tokyo_make":
			button = 2;
			break;
		case "miyazaki":
			button = 3;
			break;
		case "sapporo":
			button = 4;
			break;
		case "new_pass":		//setting.jspに移動
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/setting.jsp");
			dispatcher.forward(request, response);
			return;
		}
		User select_user = new User(button);
		EmployeeDAO dao = new EmployeeDAO();
		List<User> userList = dao.DivisionSelect(select_user,loginUser);
		System.out.println(userList);
		session.setAttribute("select_button",select_button);
		session.setAttribute("userList",userList);


		if(user_auth == 2) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_menu.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
			dispatcher.forward(request, response);
		}
		//doGet(request, response);
	}

}
