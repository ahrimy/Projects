<%@page import="java.util.ArrayList"%>
<%@page import="rental.db.CarDAO"%>
<%@page import="rental.db.CarDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reserve_selectName</title>
</head>
<body>
	<%
		String car_category = request.getParameter("car_category");
	ArrayList<CarDTO> carList = CarDAO.getInstance().getCarList(car_category);
	%>
	<jsp:include page="_top.jsp" />
	<div align="center">
		<h2>Select Car</h2>
		<form method="post" action="reserve_rental.jsp">
			<table border="1">

				<tr height="40">

					<td align="center" width="150">CAR</td>
					<td width="450"><select name="car_num" id="car_num" required>

							<option value="" disabled selected>--select--</option>
							<%
								for (int i = 0; i < carList.size(); i++) {
								if (!carList.get(i).isCar_isreserved()) {
							%>
							<option value="<%=carList.get(i).getCar_num()%>"><%=carList.get(i).getCar_name()%></option>
							<%
								}
							}
							%>
					</select></td>
				</tr>
				<tr height="40">

					<td align="center" width="300" colspan = "2"><input
						type="button" value="Back" onclick= "back()"/> &nbsp;&nbsp;
					<input
						type="submit" value="Next" /> &nbsp;&nbsp;</td>
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