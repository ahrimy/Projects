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

/**
 * Servlet implementation class User_mypagePro
 */
@WebServlet("/User_mypagePro.do")
public class User_mypagePro extends HttpServlet {
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
			
			String user_name = request.getParameter("user_name");
			String user_pw = request.getParameter("current_user_pw");
			String new_user_pw = request.getParameter("new_user_pw");
			String user_email = request.getParameter("user_email");
			System.out.println(user_name+ user_pw+new_user_pw+user_email);
			if(new_user_pw.length()>0) {
				user_pw = new_user_pw;
			}
			
			UserDAO.getInstance().updateUser(user_num,user_pw,user_name,user_email);
			
		
		// jsp 페이지로 이동하기
		// 이동할 JSP페이지 명

		RequestDispatcher dis = request.getRequestDispatcher("user_mypagePro.jsp");
		dis.forward(request, response);
	}

}
