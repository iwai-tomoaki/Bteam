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
import model.User;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログインしているか確認するため
			//セッションスコープからユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			//ログインしていない場合
			if (loginUser == null) {
				//リダイレクト
				response.sendRedirect("/Bteam/");
			} else { //ログイン済みの場合
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/delete_division.jsp");
				dispatcher.forward(request, response);
			}
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EmployeeDAO empDao = new EmployeeDAO();
		String str = request.getParameter("emp_num");

		int emp_num = Integer.parseInt(str);
		User user = new User(0,"",emp_num,"",0,0,0,0);

		System.out.print(empDao.delete(user));
		//empDao.delete(user);



		doGet(request, response);
	}
}
