package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class _03_JoinForm implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String type = request.getParameter("memType");
		
		request.setAttribute("type", type);
		
		
		
		return "/03_joinForm.jsp";
	}
}
