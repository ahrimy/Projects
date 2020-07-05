package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;
import model.ScoreDAO;
import model.ScoreDTO;
import model.TestDAO;
import model.TestDTO;

public class User_10_Testinfo implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("testinfo");
		int testNum = Integer.parseInt(request.getParameter("testNum"));
		HttpSession session = request.getSession();
		int type = (int) session.getAttribute("type");
		if (type == 10) {
			String str = (String) session.getAttribute("id");
			int stuNum = Integer.parseInt(str);
			ScoreDTO score = ScoreDAO.getInstance().getScore(stuNum, testNum);
			request.setAttribute("status", score.getStatus());
		}
		TestDTO test = TestDAO.getInstance().getTest(testNum);

		
		request.setAttribute("testTitle", test.getTestTitle());
		request.setAttribute("testDesc", test.getTestDesc());
		request.setAttribute("testNum", testNum);

		return "/user_10_testinfo.jsp";
	}
}