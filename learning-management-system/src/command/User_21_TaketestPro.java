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

public class User_21_TaketestPro implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int testNum = Integer.parseInt(request.getParameter("testNum"));
		ArrayList<QuestionDTO> questions = QuestionDAO.getInstance().getQuestions(testNum);
		HttpSession session = request.getSession();
		String str = (String)session.getAttribute("id");
		int stuNum = Integer.parseInt(str);// 나중에 학생번호 설정

		for (int i = 0; i < questions.size(); i++) {
			String stuAnswer = "";
			if (questions.get(i).getQueType().equals("checkbox")) {
				String[] temp = request.getParameterValues("que" + questions.get(i).getQueNum());
				stuAnswer = String.valueOf(temp.length);
				for (int j = 0; j < temp.length; j++) {
					stuAnswer += "/";
					stuAnswer += temp[j];
				}
			} else {
				stuAnswer = request.getParameter("que" + questions.get(i).getQueNum());
			}
			int queNum = questions.get(i).getQueNum();
			String stuResult = null;
			int score = 0;
			if (!questions.get(i).getQueType().equals("text")) {
				if (questions.get(i).getQueAnswer().equals(stuAnswer)) {
					stuResult = "true";
					score = questions.get(i).getQueScore();
				}else {
					stuResult = "false";
				}
			}
			int scoreNum = ScoreDAO.getInstance().getScoreNum(stuNum, testNum);
			AnswerDTO answer = new AnswerDTO();
			answer.setAnswer(stuAnswer);
			answer.setQueNum(queNum);
			answer.setResult(stuResult);
			answer.setScoreNum(scoreNum);
			AnswerDAO.getInstance().addAnswer(answer);
			ScoreDAO.getInstance().updateScore(scoreNum, score);

		}
		request.setAttribute("testNum", testNum);
		return "/user_21_taketestPro.jsp";
	}
}