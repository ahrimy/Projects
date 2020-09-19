package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;
import model.EnrollDAO;
import model.EnrollDTO;
import model.ScoreDAO;
import model.ScoreDTO;

public class User_12_Gradeinfo implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int testNum = Integer.parseInt(request.getParameter("testNum"));
		HttpSession session = request.getSession();
		// int stuNum = 1;
		int classNum = (int) session.getAttribute("classNum");
		ArrayList<EnrollDTO> enrollList = EnrollDAO.getInstance().getEnrollsClass(classNum);
		ArrayList<ScoreDTO> scoreList = new ArrayList<ScoreDTO>();
		for (int i = 0; i < enrollList.size(); i++) {
			ScoreDTO score = ScoreDAO.getInstance().getScore(enrollList.get(i).getStuCode(), testNum);
		
				scoreList.add(score);
			
		}
		request.setAttribute("size", enrollList.size());
		request.setAttribute("enrollList", enrollList);
		request.setAttribute("scoreList", scoreList);
		request.setAttribute("testNum", testNum);

		return "/user_12_gradeinfo.jsp";
	}
}