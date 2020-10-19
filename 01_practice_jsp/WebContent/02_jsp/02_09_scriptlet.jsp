<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2차원 반복문</title>
</head>
<body>
	<h2>2차원반복문</h2>
	<%
		int line = 20;
		int k = (line-1)/2;
	   	for(int i = 0; i < line; i++){
	   		for(int n = k; n > 0; n--){ %>
	   			&nbsp;
	   		<% }
			for(int n = 0; n < line-(k*2); n++){ %>
	   			*
	   		<% } %>
			<br>
	   <% 
	   if(i<line/2){if(k>0)k--;}
	   else if(i>=line/2){k++;}
	   }%>

</body>
</html>