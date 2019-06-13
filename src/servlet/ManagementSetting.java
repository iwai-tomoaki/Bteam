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
@WebServlet("/ManagementSetting")
public class ManagementSetting extends HttpServlet {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_setting.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EmployeeDAO empDao = new EmployeeDAO();
		String emp_name = request.getParameter("emp_name");
		String num = request.getParameter("emp_num");
		String pass = request.getParameter("pass");
		String divi = request.getParameter("divi_id");
		String workPlace = request.getParameter("workPlace_id");
		String auth = request.getParameter("auth_id");

		int emp_num = Integer.parseInt(num);
		int divi_id = Integer.parseInt(divi);
		int workPlace_id = Integer.parseInt(workPlace);
		int auth_id = Integer.parseInt(auth);

		Boolean result = empDao.changeData(emp_name, emp_num, pass, divi_id, workPlace_id, auth_id);

		System.out.print(result);

		request.setAttribute("managementResult", result);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management_setting.jsp");
		dispatcher.forward(request, response);
	}


}