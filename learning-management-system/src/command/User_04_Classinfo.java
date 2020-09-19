package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;

public class User_04_Classinfo  implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = request.getParameter("classNum");
		int classNum = Integer.parseInt(str);
		
		HttpSession session = request.getSession();
		session.setAttribute("classNum", classNum);
		return "/user_04_classinfo.jsp";
	}
}