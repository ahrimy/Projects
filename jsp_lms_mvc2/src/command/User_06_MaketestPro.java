package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;
import model.QuestionDAO;
import model.TestDAO;
import model.TestDTO;

public class User_06_MaketestPro implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		int classNum = (int)session.getAttribute("classNum");
		String testTitle = request.getParameter("testTitle");
		String testDesc = request.getParameter("testDesc");
		String str = (String)session.getAttribute("id");
		int profNum = Integer.parseInt(str);
		TestDTO test = new TestDTO(testTitle, testDesc, classNum, profNum);
		int testNum = TestDAO.getInstance().addTest(test);

		request.setAttribute("testNum", testNum);
		return "/user_06_maketestPro.jsp";
	}
}