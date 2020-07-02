package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;

public class User_07_Makequestion  implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int testNum = Integer.parseInt(request.getParameter("testNum"));
		request.setAttribute("testNum", testNum);
		return "/user_07_makequestion.jsp";
	}
}