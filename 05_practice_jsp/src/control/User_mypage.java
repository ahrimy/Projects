package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserDAO;
import model.UserDTO;

/**
 * Servlet implementation class User_mypage
 */
@WebServlet("/User_mypage.do")
public class User_mypage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
			HttpSession session = request.getSession();
			int user_num = (int)session.getAttribute("log");
			
			UserDTO user = UserDAO.getInstance().getOneUser(user_num);
			String user_id = user.getUser_id();
			String user_name = user.getUser_name();
			String user_pw = user.getUser_pw();
			String user_email = user.getUser_email();
			
			request.setAttribute("user_id", user_id);
			request.setAttribute("user_name", user_name);
			request.setAttribute("user_pw", user_pw);
			request.setAttribute("user_email", user_email);
			
		
		// jsp 페이지로 이동하기
		// 이동할 JSP페이지 명

		RequestDispatcher dis = request.getRequestDispatcher("user_mypage.jsp");
		dis.forward(request, response);
	}

}
