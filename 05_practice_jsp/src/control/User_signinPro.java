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

@WebServlet("/User_signinPro.do")
public class User_signinPro extends HttpServlet {
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
		

		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		int check = UserDAO.getInstance().signInUser(user_id, user_pw);
		request.setAttribute("check", check);
		
		if(check>0) {
			HttpSession session = request.getSession();
			session.setAttribute("log", check);
			UserDTO user = UserDAO.getInstance().getOneUser(check);
			session.setAttribute("id", user.getUser_id());
		}
		// jsp 페이지로 이동하기
		// 이동할 JSP페이지 명

		RequestDispatcher dis = request.getRequestDispatcher("user_signinPro.jsp");
		dis.forward(request, response);
	}

}
