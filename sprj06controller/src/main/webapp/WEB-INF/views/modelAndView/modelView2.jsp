<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>modelView2.jsp</h3>
${data } <hr />

<h3>list</h3>
<c:forEach items="${hobbys }" var="hobby">
	${hobby } <br />
</c:forEach>
<hr />

<h3>list</h3>
<c:forEach items="${hobbys }" var="hobby" varStatus="st">
	${hobby }
	<c:if test="${not st.last }">,</c:if>
</c:forEach>
<hr />

<h3>list</h3>
<c:forEach var="i" begin="0" end="${hobbys.size()-1 }">
	<c:if test="${i != hobbys.size()-1 }">
		${hobbys[i] },
	</c:if>
	<c:if test="${i == hobbys.size()-1 }">
		${hobbys[i] }
	</c:if>
</c:forEach>

</body>
</html>