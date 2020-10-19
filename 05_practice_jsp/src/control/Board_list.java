package control;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class Board_list
 */
@WebServlet("/Board_list.do")
public class Board_list extends HttpServlet {
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
		int pageSize = 5;
		if(session.getAttribute("pageSize")!=null) {
			String strpageSize = (String)session.getAttribute("pageSize");
			pageSize = Integer.parseInt(strpageSize);
		}
		int count = BoardDAO.getInstance().getAllCount();
		int curPage = 1;
		if(request.getParameter("pageNum")!=null){
			curPage = Integer.parseInt(request.getParameter("pageNum"));
		}

		
		int startRow = (curPage - 1) * pageSize;
		int endRow = startRow + pageSize;
		if(endRow > count) endRow = count;
		
		int number = count - (curPage - 1) * pageSize;
		
		ArrayList<BoardDTO> boardList = BoardDAO.getInstance().getAllBoard(startRow, endRow-startRow); 
		ArrayList<String> writerID = new ArrayList<>();
		
		for(int i=0;i<boardList.size();i++) {
			UserDTO user =  UserDAO.getInstance().getOneUser(boardList.get(i).getUser_num());
			writerID.add(user.getUser_id());
		}

		request.setAttribute("number", number);
		request.setAttribute("boardList", boardList);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("curPage", curPage);
		
		request.setAttribute("IDs", writerID);
		// jsp 페이지로 이동하기
		// 이동할 JSP페이지 명

		RequestDispatcher dis = request.getRequestDispatcher("board_list.jsp");
		dis.forward(request, response);
	}
}
