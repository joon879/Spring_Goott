<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>coffeeform.jsp</h3>

<form action="./coffeeresult">
	종류 <br />
	<input type="radio" name="name" value="아메리카노" />아메리카노 <br />
	<input type="radio" name="name" value="카페라떼" />카페라떼 <br />

 	<!-- name <input type="text" id="name" name="name" /> <br /> -->
	price <input type="text" id="price" name="price" /> <br />
	<input type="submit" value="선택완료" />
</form>

</body>
</html>