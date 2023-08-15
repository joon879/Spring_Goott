<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/pstyle.css" />
</head>
<body>
<h3>list.jsp</h3>

<table width="800" border="1">
	<tr class="firstline">
		<td>번호</td>
		<td>이름</td>
		<td>제목</td>
		<td>날짜</td>
		<td>조회</td>
	</tr>
	
<c:forEach items="${list }" var="dto">
	<tr>
		<td>${dto.pzid }</td>
		<td>${dto.pzname }</td>
		<td>
			<!-- 들여쓰기 -->
			<c:set value="${dto.pzintent }" var="endintent" />
			<c:forEach begin="1" end="${dto.pzintent }" var="cnt">
				&nbsp;
				<c:if test="${cnt eq endintent }">
					[re]<img src="resources/img/reply.png" alt="" />
				</c:if>
			</c:forEach>
						
			<a href="content_view?pzid=${dto.pzid }">${dto.pzsubj }</a>
		</td>
		<td>${dto.pzdate }</td>
		<td>${dto.pzhit }</td>
	</tr>

</c:forEach>
	<tr>
		<td colspan="5"><a href="write_view">글쓰기</a></td>
	</tr>
	
</table>
</body>
</html>