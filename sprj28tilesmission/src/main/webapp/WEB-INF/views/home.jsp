<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="notice.list">list</a> <br />
<a href="notice.detail">detail</a> <br />
<a href="notice.edit">edit</a> <br />
</body>
</html>
