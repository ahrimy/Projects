package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.User_02_Main;
import command.User_03_Classlist;
import command.User_04_Classinfo;
import command.User_05_Maketest;
import command.User_06_MaketestPro;
import command.User_07_Makequestion;
import command.User_08_MakequestionPro;
import command.User_09_Testlist;
import command.User_10_Testinfo;
import command.User_11_Gradelist;
import command.User_12_Gradeinfo;
import command.User_13_Gradetest;
import command.User_14_GradetestPro;
import command.User_20_Taketest;
import command.User_21_TaketestPro;
import command.User_22_Testresult;
import command._01_Login;
import command._02_Join;
import command._03_JoinForm;
import command._04_JoinStuPro;
import command._06_LoginPro;



@WebServlet(urlPatterns = { "*.do"})

public class Controller extends HttpServlet {

	private Map<String, Object> commandMap = new HashMap<String, Object>();	

	public void init() throws ServletException {	

		commandMap.put("/user/main.do", new User_02_Main() );
		commandMap.put("/user/classlist.do", new User_03_Classlist() );
		commandMap.put("/user/classinfo.do", new User_04_Classinfo() );
		commandMap.put("/user/maketest.do", new User_05_Maketest());
		commandMap.put("/user/maketestPro.do", new User_06_MaketestPro());
		commandMap.put("/user/makequestion.do", new User_07_Makequestion());
		commandMap.put("/user/makequestionPro.do", new User_08_MakequestionPro());
		commandMap.put("/user/testlist.do", new User_09_Testlist());
		commandMap.put("/user/testinfo.do", new User_10_Testinfo());
		commandMap.put("/user/gradelist.do", new User_11_Gradelist());
		commandMap.put("/user/gradeinfo.do", new User_12_Gradeinfo());
		commandMap.put("/user/gradetest.do", new User_13_Gradetest());
		commandMap.put("/user/gradetestPro.do", new User_14_GradetestPro());
		commandMap.put("/user/taketest.do", new User_20_Taketest());
		commandMap.put("/user/taketestPro.do", new User_21_TaketestPro());
		commandMap.put("/user/testresult.do", new User_22_Testresult());
		commandMap.put("/user/login.do", new _01_Login());
		commandMap.put("/user/join.do", new _02_Join());
		commandMap.put("/user/joinForm.do", new _03_JoinForm());
		commandMap.put("/user/joinStuPro.do", new _04_JoinStuPro());
		commandMap.put("/user/loginPro.do", new _06_LoginPro());
		
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}
	private void requestPro(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String view = "";
		CommandAction com = null;
		try {
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
			}
			com = (CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("cont", view);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/_index.jsp");
		dispatcher.forward(request, response);
			
	}
}
