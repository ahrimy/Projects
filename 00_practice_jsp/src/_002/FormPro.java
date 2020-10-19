package _002;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/formPro.do")
public class FormPro extends HttpServlet {
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
		//1)jsp페이지에서 넘어온 데이터를 저장한다.
		String name = request.getParameter("name");
		// checkbox의 parameter 값들을 저장하는 방법
		String[] hobbies = request.getParameterValues("hobby");
		String gender = request.getParameter("gender");
		String grade = request.getParameter("grade");

		//2)로직 처리
		
		//3)jsp페이지로 전달할 데이터를 세팅한다.
		request.setAttribute("name", name);
		request.setAttribute("hobbies", hobbies);
		request.setAttribute("gender", gender);
		request.setAttribute("grade", grade);
		
		// jsp 페이지로 이동하기
		// 이동할 JSP페이지 명
		RequestDispatcher dis = request.getRequestDispatcher("formPro.jsp");
		dis.forward(request, response);
	}

}
