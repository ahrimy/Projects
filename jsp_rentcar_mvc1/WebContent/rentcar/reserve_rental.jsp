<%@page import="rental.db.CarDAO"%>
<%@page import="rental.db.CarDTO"%>
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
		int car_num = Integer.parseInt(request.getParameter("car_num"));
		CarDTO car = CarDAO.getInstance().getOneCar(car_num);
	%>
	<jsp:include page="_top.jsp" />
	<div align="center">
		<h2>Select Options</h2>
		<form method="post" action="reserve_rentalPro.jsp">
			<table border="1">

				<tr height="40">
					<td width="320" align="center">Brand</td>
					<td width="320" align="center"><%=car.getCar_category()%></td>

				</tr>
				<tr>
					<td width="320" align="center">Name</td>
					<td width="320" align="center"><%=car.getCar_name()%></td>
				</tr>
				<tr>
					<td width="320" align="center">Price(per day)</td>
					<td width="320" align="center"><%=car.getCar_price()%></td>
				</tr>
				<tr>
					<td width="320" align="center">Pick Up</td>
					<td width="320" align="center"><input type="date"
						name="startdate" min="2020-07-01" max="2020-12-31" required></td>
				</tr>
				<tr>
					<td width="320" align="center">Drop Off</td>
					<td width="320" align="center"><input type="date"
						name="enddate" min="2020-07-01" max="2020-12-31" required></td>
				</tr>
				<tr height="40">
					<td align="center" width="150">Bluetooth</td>
					<td width="450"><input type="checkbox"
						name="rental_usebluetooth" value="true" size="60" /></td>
				</tr>
				<tr height="40">
					<td align="center" width="150">GPS</td>
					<td width="450"><input type="checkbox" name="rental_usegps"
						value="true" size="60" /></td>
				</tr>

				<tr height="40">

					<td align="center" width="150" colspan="2"><input
						type="hidden" name="car_num" value="<%=car_num%>" /> <input
						type="hidden" name="car_price" value="<%=car.getCar_price()%>" />
						<input
						type="button" value="Back" onclick= "back()"/> &nbsp;&nbsp;
						<input type="submit" value="Reserve" /> &nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script>
	function back() {
		history.go(-1);
	}
</script>
</html>