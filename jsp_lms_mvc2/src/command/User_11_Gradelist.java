package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;

import model.TestDAO;
import model.TestDTO;

public class User_11_Gradelist implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		//int stuNum = 1;
		int classNum = (int)session.getAttribute("classNum");
		ArrayList<TestDTO> tests = TestDAO.getInstance().getAllTest(classNum);

		request.setAttribute("tests", tests);

		
		return "/user_11_gradelist.jsp";
	}
}