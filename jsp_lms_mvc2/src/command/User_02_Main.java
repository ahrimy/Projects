package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;

public class User_02_Main implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
	
		session.setAttribute("type",3);
	
		session.setAttribute("classNum", null);
		
		//type==>1:manager, 2:student, 3:professor
		return "/user_02_main.jsp";
	}
}
