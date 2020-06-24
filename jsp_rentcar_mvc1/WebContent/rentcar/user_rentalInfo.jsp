
<%@page import="rental.db.CarDTO"%>
<%@page import="rental.db.CarDAO"%>
<%@page import="rental.db.RentalDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="rental.db.RentalDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_rentalInfo</title>
</head>
<body>
	<jsp:include page="_top.jsp" />
	<div align="center">
		<h2>[Rental Info]</h2>
		<%
			ArrayList<RentalDTO> list = null;
		if (session.getAttribute("log") != null) {
			list = RentalDAO.getInstance().getReservationList((int) session.getAttribute("log"));
			
		}
			
		%>
		<%
		if(list!=null){for(int i=0;i<list.size();i++){ 
			CarDTO car = CarDAO.getInstance().getOneCar(list.get(i).getCar_num());
		%>
			<table border="1">
				<tr height="40">
					<td width="320" align="center">Brand</td>
					<td width="320" align="center"><%=car.getCar_category()%></td>
				</tr>
				<tr>
					<td width="320" align="center">Car</td>
					<td width="320" align="center"><%=car.getCar_name()%></td>
				</tr>
				<tr height="40">
					<td width="320" align="center">Pick up</td>
					<td width="320" align="center"><%=list.get(i).getRental_startdate()%></td>
				</tr>
				<tr>
					<td width="320" align="center">Drop off</td>
					<td width="320" align="center"><%=list.get(i).getRental_planneddate()%></td>
				</tr>
				<tr>
					<td width="320" align="center">Rental days</td>
					<td width="320" align="center"><%=list.get(i).getRental_reserveddays()%></td>
				</tr>
				
				<tr height="40">
					<td width="320" align="center">Bluetooth (5000 per day)</td>
					<td width="320" align="center"><%if(list.get(i).isRental_usebluetooth()){%>Use<%}else{ %>Not use<%} %></td>
				</tr>
				<tr>
					<td width="320" align="center">GPS (5000 per day)</td>
					<td width="320" align="center"><%if(list.get(i).isRental_usegps()){%>Use<%}else{ %>Not use<%} %></td>
				</tr>
				<tr height="40">
					<td width="320" align="center">Estimated price</td>
					<td width="320" align="center"><%=list.get(i).getRental_estimatedprice()%></td>
				</tr>
				<tr>
					<td width="320" align="center">Actual Return date</td>
					<td width="320" align="center"><%if(list.get(i).getRental_returndate()!=null){%><%=list.get(i).getRental_returndate()!=null%><%} %></td>
				</tr>
				<tr>
					<td width="320" align="center">Extra charge</td>
					<td width="320" align="center"><%=list.get(i).getRental_extracharge()%></td>
				</tr>
				<tr>
					<td width="320" align="center" ><b>Total price</b></td>
					<td width="320" align="center"><b><%=(list.get(i).getRental_estimatedprice()+list.get(i).getRental_extracharge())%></b></td>
				</tr>
				<tr height="40">

				<td width="320" align="center" colspan=2><input type="button"
						value="Change"onclick="location.href='reserve_change.jsp?rental_num=<%=list.get(i).getRental_num()%>'"/>
						</td>

				</tr>
			</table>
		<%}} %>
	</div>
</body>
<script>
	function back() {
		history.go(-1);
	}
</script>
</html>