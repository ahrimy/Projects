<%@page import="rental.db.CarDAO"%>
<%@page import="rental.db.RentalDAO"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reserve_rental</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>

	<jsp:useBean id="rental" class="rental.db.RentalDTO">
		<jsp:setProperty name="rental" property="*" />
	</jsp:useBean>

	<%
		int user_num = (int) session.getAttribute("log");
	rental.setUser_num(user_num);

	Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startdate"));
	rental.setRental_startdate(new java.sql.Date(date.getTime()));

	date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("enddate"));
	rental.setRental_planneddate(new java.sql.Date(date.getTime()));

	LocalDate dateBefore = LocalDate.parse(request.getParameter("startdate"));
	LocalDate dateAfter = LocalDate.parse(request.getParameter("enddate"));
	int days = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter) + 1;
	rental.setRental_reserveddays(days);

	int price = Integer.parseInt(request.getParameter("car_price"));
	long total = price * days;
	if (rental.isRental_usebluetooth()) {
		total += (5000 * days);
	}
	if (rental.isRental_usegps()) {
		total += (5000 * days);
	}
	rental.setRental_estimatedprice(total);
	System.out.println(rental.getRental_startdate());
	System.out.println(rental.getRental_planneddate());
	RentalDAO.getInstance().makeReservation(rental);
	CarDAO.getInstance().rentCar(rental.getCar_num());
	
	response.sendRedirect("user_rentalInfo.jsp");
	%>
</body>
</html>