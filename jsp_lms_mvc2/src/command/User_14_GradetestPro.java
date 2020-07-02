package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;
import model.AnswerDAO;
import model.AnswerDTO;
import model.ScoreDAO;

public class User_14_GradetestPro implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int scoreNum = Integer.parseInt(request.getParameter("scoreNum"));
		// int stuNum = 1;

		ArrayList<AnswerDTO> answerList = AnswerDAO.getInstance().getAnswerList(scoreNum);
		for (int i = 0; i < answerList.size(); i++) {
			if(answerList.get(i).getResult()!=null) {
				answerList.remove(i);
				i--;
			}
		}
		for (int i = 0; i < answerList.size(); i++) {
				int score = Integer.parseInt(request.getParameter("answer"+answerList.get(i).getAnswerNum()));
				AnswerDAO.getInstance().updateResult(answerList.get(i).getAnswerNum(), "graded");
				ScoreDAO.getInstance().updateScore(scoreNum, score);
			
		}



		return "/user_14_gradetestPro.jsp";
	}
}