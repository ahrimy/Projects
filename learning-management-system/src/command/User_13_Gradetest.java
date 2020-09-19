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


public class User_13_Gradetest implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int scoreNum = Integer.parseInt(request.getParameter("scoreNum"));
		
		// int stuNum = 1;
		
		ArrayList<AnswerDTO> answerList = AnswerDAO.getInstance().getAnswerList(scoreNum);
		ArrayList<QuestionDTO> queList = new ArrayList<>();
		for (int i = 0; i < answerList.size(); i++) {
			if(answerList.get(i).getResult()!=null) {
				answerList.remove(answerList.get(i));
				i--;
			}
		}
		for (int i = 0; i < answerList.size(); i++) {
				QuestionDTO temp = QuestionDAO.getInstance().getQuestion(answerList.get(i).getQueNum());
				queList.add(temp);			
		}
		request.setAttribute("size", queList.size());
		request.setAttribute("answerList", answerList);
		request.setAttribute("queList", queList);


		return "/user_13_gradetest.jsp";
	}
}