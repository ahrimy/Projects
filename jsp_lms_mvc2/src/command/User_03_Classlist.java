package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandAction;
import model.ClassDAO;
import model.ClassDTO;
import model.EnrollDAO;
import model.EnrollDTO;
import model.StudentDAO;

public class User_03_Classlist implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int type = (int) session.getAttribute("type");
		String str = (String) session.getAttribute("id");
		System.out.println(str);
		int code = 1;
		if(str!=null) {
			code = Integer.parseInt(str);
		}
		System.out.println(code);

		ArrayList<EnrollDTO> enrolls = null;
		ArrayList<ClassDTO> classes = new ArrayList<ClassDTO>();
		if (type == 2) {
			// student
			enrolls = EnrollDAO.getInstance().getEnrollsStu(code);

			for (int i = 0; i < enrolls.size(); i++) {
				System.out.println(enrolls.get(i).getClassNum());
				ClassDTO temp = ClassDAO.getInstance().getClass(enrolls.get(i).getClassNum());
				classes.add(temp);
			}
		} else if (type == 3) {
			// professor
			classes = ClassDAO.getInstance().getProfclass(code);
		}
		int[] participants = new int[classes.size()];
		String[] titles = new String[classes.size()];
		String[] professors = new String[classes.size()];
		for (int i = 0; i < classes.size(); i++) {
			ArrayList<EnrollDTO> temp = EnrollDAO.getInstance().getEnrollsClass(classes.get(i).getClassNum());
			participants[i] = temp.size();
			String info[] = classes.get(i).getClassInfo().split("/");
			titles[i] = info[0];
			professors[i] = info[1];
		}
		
		request.setAttribute("classes", classes);
		request.setAttribute("titles", titles);
		request.setAttribute("professors", professors);
		request.setAttribute("participants", participants);
		
		return "/user_03_classlist.jsp";
	}
}
