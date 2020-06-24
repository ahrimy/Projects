<%@page import="rental.db.CarDAO"%>
<%@page import="rental.db.RentalDTO"%>
<%@page import="rental.db.RentalDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int rental_num = Integer.parseInt(request.getParameter("rental_num"));
		RentalDTO rental = RentalDAO.getInstance().getOneReservation(rental_num);
		RentalDAO.getInstance().cancelReservation(rental_num);
		CarDAO.getInstance().finishRental(rental.getCar_num());
		response.sendRedirect("user_rentalInfo.jsp");
	%>
</body>
</html>