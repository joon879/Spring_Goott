<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>loginform.jsp</h3>
<form action="./login">
	id <input type="text" name="id"/><br />
	email <input type="email" name="email" /><br />
	pass <input type="password" name="pass" /> <br />
	<input type="submit" value="로그인" />

</form>


<a href="./login">go login</a> <br />
<a href="../join/joinform">go join</a> <br />


</body>
</html>