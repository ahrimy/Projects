package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;
import model.AnswerDAO;
import model.AnswerDTO;
import model.QuestionDAO;
import model.QuestionDTO;
import model.ScoreDAO;
import model.ScoreDTO;
import model.TestDAO;
import model.TestDTO;

public class User_22_Testresult implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int classNum = (int)session.getAttribute("classNum");
		String str = (String)session.getAttribute("id");
		int stuNum = Integer.parseInt(str);//나중에 다시 설정
		ArrayList<TestDTO> testList = TestDAO.getInstance().getAllTest(classNum);
		ArrayList<ScoreDTO> scoreList = new ArrayList<>();
		for(int i=0;i<testList.size();i++) {
			ScoreDTO temp = ScoreDAO.getInstance().getScore(stuNum, testList.get(i).getTestNum());
			scoreList.add(temp);
		}
	request.setAttribute("size", testList.size());
		request.setAttribute("scoreList", scoreList);
		request.setAttribute("testList", testList);
		return "/user_22_testresult.jsp";
	}
}