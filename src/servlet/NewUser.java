
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
import model.User;


@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ログインしているか確認するため
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) { //ログインしていない場合
			//リダイレクト
			response.sendRedirect("/Bteam/");
		} else { //ログイン済みの場合
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_user.jsp");
			dispatcher.forward(request, response);
		}
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		

		// 社員情報を追加
		EmployeeDAO empDao = new EmployeeDAO();
		User user = new User(0,"",0,"",0,0,0,0);
		empDao.create(user);
		
		// 社員リストの取得
		List<User> userList = empDao.findAll();
		request.setAttribute("userList", userList);
		
		doGet(request, response);

	}
}
