package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;
import model.QuestionDAO;
import model.QuestionDTO;
import model.ScoreDAO;
import model.TestDAO;

public class User_08_MakequestionPro implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int testNum = Integer.parseInt( request.getParameter("testNum"));
		String question = request.getParameter("question");
		String queType = request.getParameter("queType");
		String queAnswer = "";
		if(queType.equals("radio")) {
			queAnswer = request.getParameter("queAnswer");
		}else if(queType.equals("checkbox")) {
			String[] temp = request.getParameterValues("queAnswer");
			queAnswer = String.valueOf(temp.length);
			for(int i=0;i<temp.length;i++) {
				queAnswer += "/";
				queAnswer+=temp[i];
			}
		}else {
			queAnswer = request.getParameter("queAnswer");
		}
		int num = 1;
		String queOpts = "";
		while(request.getParameter("opt"+num)!=null) {
			queOpts +="/";
			queOpts +=request.getParameter("opt"+num);
			num++;
		}
		if(queOpts.length()>0) {
			queOpts = String.valueOf(num-1)+queOpts;
		}
		int queScore = Integer.parseInt(request.getParameter("queScore"));
		System.out.println(testNum);
		System.out.println(question);
		System.out.println(queType);
		System.out.println(queAnswer);
		System.out.println(queOpts);
		System.out.println(queScore);
		
		QuestionDTO que  = new QuestionDTO(testNum, question, queType, queOpts, queAnswer,queScore );
		QuestionDAO.getInstance().addQuestion(que);
		
		int check = 0;
		if(request.getParameter("next").equals("end")) {
			HttpSession session = request.getSession();
			int classNum = (int)session.getAttribute("classNum");
			ScoreDAO.getInstance().addScore(testNum, classNum);
			check = 1;
		}
		request.setAttribute("check", check);
		request.setAttribute("testNum", testNum);
		return "/user_08_makequestionPro.jsp";
	}
}