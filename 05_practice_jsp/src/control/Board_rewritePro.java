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
 * Servlet implementation class Board_rewritePro
 */
@WebServlet("/Board_rewritePro.do")
public class Board_rewritePro extends HttpServlet {
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

		BoardDTO board = new BoardDTO();
		int ref = Integer.parseInt(request.getParameter("ref"));
		int restep = Integer.parseInt(request.getParameter("restep"));
		int relevel = Integer.parseInt(request.getParameter("relevel"));

		String board_subject = request.getParameter("board_subject");
		String board_content = request.getParameter("board_content");

		board.setBoard_content(board_content);
		board.setBoard_subject(board_subject);
		board.setRef(ref);
		board.setRestep(restep);
		board.setRelevel(relevel);
		board.setUser_num(user_num);

		BoardDAO.getInstance().addRewriteBoard(board);

		// jsp 페이지로 이동하기
		// 이동할 JSP페이지 명

		RequestDispatcher dis = request.getRequestDispatcher("board_rewritePro.jsp");
		dis.forward(request, response);
	}

}
