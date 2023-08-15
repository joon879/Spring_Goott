<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<h3>list.jsp</h3>

<table width="500" border="1" >
	<tr>
		<td>번호</td>
		<td>이름</td>
		<td>제목</td>
		<td>날짜</td>
		<td>히트</td>
	</tr>
	
<c:forEach items="${list }" var="dto">
	<tr>
		<td>${dto.bid }</td>
		<td>${dto.bname }</td>
		<td>
				<!-- 들여쓰기 -->
			<c:set value="${dto.bindent }" var="endindent" />
			<c:forEach begin="1" end="${dto.bindent }" var="cnt">
				&nbsp;
				<c:if test="${cnt eq endindent }">
					[re]<img src="resources/img/reply.png" alt="" />
				</c:if>
			</c:forEach>
						
			<a href="content_view?bid=${dto.bid }">${dto.btitle }</a>
		</td>
		<td>${dto.bdate }</td>
		<td>${dto.bhit }</td>
	</tr>
</c:forEach>
	<tr>
		<td colspan="5"><a href="write_view">글쓰기</a></td>
	</tr>
	
</table>
</body>
</html>