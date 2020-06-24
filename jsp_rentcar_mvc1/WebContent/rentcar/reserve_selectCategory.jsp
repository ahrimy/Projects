<%@page import="rental.db.CarDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="rental.db.CarDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reserve_selectCategory</title>
</head>
<body>
	<jsp:include page="_top.jsp" />
		<%
		if(session.getAttribute("log")==null){
			%>
			<script>
			alert("Only available when you are signed in");
			history.go(-1);
		</script>	<%
			

		}
	%>
	<div align="center">
		<h2>Select Car</h2>
		<form method="post" action="reserve_selectName.jsp">
			<table border="1">

				<tr height="40">

					<td align="center" width="150">BRAND</td>
					<td width="450"><select name="car_category" id="car_category" >
					
					<option value=""disabled selected>--select--</option>
							<option value="HUNDAI">HUNDAI</option>
							<option value="KIA">KIA</option>
							<option value="CHEVROLET">CHEVROLET</option>
							<option value="RENAULTSAMSUNG">RENAULTSAMSUNG</option>
					</select>					</td>
				</tr>
				<tr height="40">
					<td align="center" width="150" colspan="2"><input
						type="submit" value="Next" /> &nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>