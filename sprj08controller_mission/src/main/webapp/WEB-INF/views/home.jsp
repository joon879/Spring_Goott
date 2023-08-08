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
<a href="./board/loginform">loginform</a> <br />
<a href="./board/student">studentform</a> <br />
<a href="./join/joinform">joinform</a> <br />
<a href="./studentConfirm?id=abc">studentconfirmOk</a>
<a href="./studentConfirm?id=a">studentconfirmNg</a>
<hr />
<a href="./food/kfood">go kfood</a>
<a href="./food/wfood">go wfood</a>

</body>
</html>
