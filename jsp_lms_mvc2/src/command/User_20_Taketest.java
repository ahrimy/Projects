package command;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;
import model.QuestionDAO;
import model.QuestionDTO;
import model.ScoreDAO;
import model.ScoreDTO;
import model.TestDAO;
import model.TestDTO;

public class User_20_Taketest implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("taketest");
		int testNum =  Integer.parseInt(request.getParameter("testNum"));
		
		ArrayList<QuestionDTO> questions = QuestionDAO.getInstance().getQuestions(testNum);
		TestDTO test = TestDAO.getInstance().getTest(testNum);

		request.setAttribute("test", test);
		request.setAttribute("questions", questions);

		return "/user_20_taketest.jsp";
	}
}