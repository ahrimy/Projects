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

public class User_09_Testlist implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("testList");
		HttpSession session= request.getSession();
		String str = (String)session.getAttribute("id");
		int stuNum = Integer.parseInt(str);
		int classNum = (int)session.getAttribute("classNum");
		ArrayList<TestDTO> tests = TestDAO.getInstance().getAllTest(classNum);
		ArrayList<String> statusList = new ArrayList<String>();
		for(int i=0;i<tests.size();i++) {
			ScoreDTO score = ScoreDAO.getInstance().getScore(stuNum, tests.get(i).getTestNum());
			statusList.add(score.getStatus());
		}
		
		request.setAttribute("size", tests.size());
		request.setAttribute("statusList", statusList);
		request.setAttribute("tests", tests);

		
		return "/user_09_testlist.jsp";
	}
}