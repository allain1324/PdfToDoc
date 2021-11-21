<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center" style="margin-top: 100px">
		<h1 a>LOGIN</h1>
		<form action="CheckLoginServlet" method="post">
			Username: <input type="text" name="username" /><br>
			<br> Password: <input type="password" name="password" /><br>
			<div style="margin-left: auto; margin-right: auto; width: 240px; margin-top:20px">
				<input type="submit" name="login" value="LOGIN" style="width: 105px;">
				<input type="reset" name="reset" value="RESET" style="width: 105px;">
			</div>
		</form>
	</div>
</body>
</html>