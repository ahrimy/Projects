package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandAction;
import model.Student;
import model.StudentDAO;

public class _04_JoinStuPro  implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		int stuCode = StudentDAO.getInstance().randomCode();
		int grade = Integer.parseInt(request.getParameter("grade"));
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String adds = request.getParameter("adds");
		String major = request.getParameter("major");
		
		// add Data
		Student bean = new Student(stuCode, grade, pw, name, birth, tel, email, adds, major);
		
		StudentDAO sdao = StudentDAO.getInstance();
		int check = sdao.check(bean.getDupl());
		
		if(check == 1) {
			System.out.println("Dupl : " + bean.getDupl());
			System.out.println("CHECK : " + check);
			sdao.joinStd(bean);
		}
		
		// dispatcher
		request.setAttribute("check", check);
		request.setAttribute("stuCode", stuCode);
		request.setAttribute("name", name);
		
		
		return "/04_joinStuPro.jsp";
	}
}
