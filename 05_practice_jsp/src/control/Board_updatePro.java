package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.BoardDTO;
import model.UserDAO;
import model.UserDTO;

/**
 * Servlet implementation class Board_updatePro
 */
@WebServlet("/Board_updatePro.do")
public class Board_updatePro extends HttpServlet {
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

		int user_num = (int) session.getAttribute("log");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String board_pw = request.getParameter("board_pw");
		String board_subject = request.getParameter("board_subject");
		String board_content = request.getParameter("board_content");
		int check = BoardDAO.getInstance().updateBoard(board_num, user_num, board_pw, board_subject, board_content);
		request.setAttribute("check", check);
		// jsp 페이지로 이동하기
		// 이동할 JSP페이지 명

		RequestDispatcher dis = request.getRequestDispatcher("board_updatePro.jsp");
		dis.forward(request, response);
	}
}
