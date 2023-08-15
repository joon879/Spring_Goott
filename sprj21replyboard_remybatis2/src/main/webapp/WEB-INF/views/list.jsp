<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
  />
</head>
<body>
<h3>list.jsp</h3>

<table width="500" border="1">
	<tr>
		<td>번호</td>
		<td>이름</td>
		<td>제목</td>
		<td>날자</td>
		<td>히트</td>	
	</tr>
	
<c:forEach items="${list }" var="dto">
	<tr>
		<td>${dto.bid }</td>
		<td>${dto.bname }</td>
		<td>
			<c:set value="${dto.bindent }" var="endindent" />
			<c:forEach begin="1" end="${dto.bindent }" var="cnt">
				&nbsp;
				<c:if test="${cnt eq endindent }">
					<img src="resources/img/reply.gif" alt="" />
					[re]
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
<hr />
totCnt : ${totRowcnt } <br />
현재페이지/토탈페이지:${searchVO.page }/${searchVO.totPage }
<hr />
<c:if test="${searchVO.page>1}">
	<a href="list?page=1">처음</a>
	<a href="list?page=${searchVO.page-1 }">이전</a>
</c:if>
<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
	<c:choose>
		<c:when test="${i eq searchVO.page }">
			<span style="color:red; font-weight:bold;">${i }</span>
		</c:when>
		<c:otherwise>
			<a href="list?page=${i }" style="text-decoration:none;">${i }</a> 
		</c:otherwise>
	</c:choose>	
</c:forEach>
<c:if test="${searchVO.page < searchVO.totPage}">
	<a href="list?page=${searchVO.page+1 }">다음</a>
	<a href="list?page=${searchVO.totPage }">마지막</a>
</c:if>

<i class="fas fa-camera"></i>
</body>
</html>