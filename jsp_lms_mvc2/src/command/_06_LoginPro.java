package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;
import model.StudentDAO;

public class _06_LoginPro implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

String id = request.getParameter("id");
String pw = request.getParameter("pw");

StudentDAO sdao = StudentDAO.getInstance();
int check = sdao.loginCheck(id, pw);

if(check == 1) {
	HttpSession session = request.getSession();
	session.setAttribute("id", id);
}

request.setAttribute("check", check);		
		
		return "/06_loginPro.jsp";
	}
}

