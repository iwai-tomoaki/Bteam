package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;		// 追加したインポート文
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		// リクエスト先の指定
		String select_button = request.getParameter("select");
		System.out.println(select_button);		//文字列の確認用


		switch(select_button) {
		case "all":

			break;
		case "tokyo":

			break;
		case "tokyo_make":

			break;
		case "sapporo":

			break;
		case "miyazaki":

			break;
		case "new_pass":

			break;
		}


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
		dispatcher.forward(request, response);

		//doGet(request, response);
	}

}
