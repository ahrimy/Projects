package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;

public class _01_Login implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		
		String id = (String)session.getAttribute("memId");
		
		request.setAttribute("id", id);
		

		return "/01_login.jsp";
	}
}
