package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;
import model.ProfessorDAO;
import model.StudentDAO;

public class _06_LoginPro implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		StudentDAO sdao = StudentDAO.getInstance();
		ProfessorDAO pdao = ProfessorDAO.getInstance();
		int check = sdao.loginCheck(id, pw);

		int type = 0;
		String name = "";

		if (check == 1) {
			type = sdao.loginType(id);
			System.out.println("type : " + type);
			if (type == 10) {
				name = sdao.getStd(id).getName();
			} else if (type == 20) {
				name = pdao.getPrf(id).getName();
			}

			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("type", type);
			session.setAttribute("name", name);
		}

		request.setAttribute("check", check);
		request.setAttribute("type", type);
		request.setAttribute("name", name);

		return "/06_loginPro.jsp";
	}
}
