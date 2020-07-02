<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-1.10.1.js"></script>
<script>
	var optNum = 1;
	var selectedOption = "radio";
	$(document)
			.ready(
					function() {

						$("select.queType")
								.change(
										function() {

											selectedOption = $(this).children(
													"option:selected").val();
											optNum = 1;

											alert("You have selected the option - "
													+ selectedOption);
											$(".options").empty();

											if (selectedOption == "checkbox") {

												$(".options")
														.append(
																'<input type="checkbox" name="queAnswer" value="opt'+optNum+'" />'
																		+ '<input type="text" name="opt'+optNum+'" size="50" /><br/>');
												$("#add").show();

											} else if (selectedOption == "radio") {
												$(".options")
														.append(
																' <input type="radio" name="queAnswer"	value="opt'+optNum+'" required/> <input type="text" name="opt'+optNum+'" size="50"/><br/>');
												$("#add").show();
											} else if (selectedOption == "text") {
												$(".options")
														.append(
																' <input type="text" width = "250" name="queAnswer" required/><br/>');
												$("#add").hide();
											}

										});
					});

	$(document)
			.ready(
					function() {
						$("#add")
								.click(
										function() {
											optNum = optNum + 1;
											if (selectedOption == "checkbox") {
												$(".options")
														.append(
																'<input type="checkbox" name="queAnswer" value="opt'+optNum+'" />'
																		+ '<input type="text" name="opt'+optNum+'" size="50" /><br/>');
											} else if (selectedOption == "radio") {
												$(".options")
														.append(
																' <input type="radio" name="queAnswer"	value="opt'+optNum+'" /> <input type="text" name="opt'+optNum+'" size="50"/><br/>');
											}
										});
					});
</script>
<title>makequestion.jsp</title>
</head>
<body>
	<div align="center">
		<h2>Make new Test</h2>
		<form method="post" action="/jsp_lms_mvc2/user/makequestionPro.do">
			<table border="1">
				<tr class="" align="center">
					<td width="300"><input type="text" id="question"
						name="question" value="Question" size="60" required />&nbsp;&nbsp;&nbsp;&nbsp;
						Score : <input type="text" name="queScore" required /> <br /> <%--타입 선택 --%>
						<hr>
						<select class="queType" name="queType">
							<option value="radio">Single Choice</option>
							<option value="checkbox">Check boxes</option>
							<option value="text">Short answer</option>
					</select><br>
						<hr> <%--문제 옵션 --%>
						<div class="options" align="left">
							<input type="radio" name="queAnswer" value="opt1" required /> <input
								type="text" name="opt1" size="50" /><br />

						</div> <br />
					<input type="button" id="add" value="add option" /> <br />
						<hr>
						<div>
				
							<input type="hidden" name="testNum" value="${testNum }" /> <input
								type="submit" name="next" value="next" /> <input type="submit"
								name="next" value="end" /> <br />
							<br />
						</div></td>
				</tr>

			</table>
			<br> <br>

		</form>
	</div>
</body>
</html>