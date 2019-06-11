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
				"/WebContent/WEB-INF/jsp/menu.jsp");
		dispatcher.forward(request, response);

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		// リクエスト先の指定
		String select_button = request.getParameter("select");

		int button = 0;
		HttpSession session = request.getSession();
		String already = (String) session.getAttribute("select_button");
			if(already != null && select_button == null) {
				select_button = (String) session.getAttribute("select_button");
			}

			String changeup = request.getParameter("changeup");
			if(changeup != null) {
				EmployeeDAO adddao = new EmployeeDAO();
				adddao.DivisionChangeup(changeup);
			}
			String changedown = request.getParameter("changedown");
			if(changedown != null) {
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
		case "sapporo":
			button = 3;
			break;
		case "miyazaki":
			button = 4;
			break;
		case "new_pass":		//いったん放置

			break;
		}
		User select_user = new User(button);
		EmployeeDAO dao = new EmployeeDAO();
		List<User> userList = dao.DivisionSelect(select_user);
		System.out.println(userList);
		session.setAttribute("select_button",select_button);
		session.setAttribute("userList",userList);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
		dispatcher.forward(request, response);

		//doGet(request, response);
	}

}
