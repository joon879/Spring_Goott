<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<style>
	.fa-solid{
		color: #333; 
	}
	
	.fa-solid: hover{
		color: orange; 
	}
</style>
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
		<td>${dto.brd_id }</td>
		<td>${dto.brd_name }</td>
		<td>
			<!-- 들여쓰기 -->
			<c:set value="${dto.brd_indent }" var="endindent" />
			<c:forEach begin="1" end="${dto.brd_indent }" var="cnt">
				&nbsp;
				<c:if test="${cnt eq endindent }">
					[re]<img src="resources/img/reply.png" alt="" />
				</c:if>
			</c:forEach>
						
			<a href="content_view?brd_id=${dto.brd_id }">${dto.brd_title }</a>
		</td>
		<td>${dto.brd_date }</td>
		<td>${dto.brd_hit }</td>
	</tr>
</c:forEach>
	<tr>
		<td colspan="5"><a href="write_view">글쓰기</a></td>
	</tr>
	
</table>
<hr />
totCnt: ${totRowcnt } <br />
현재페이지/토탈페이지: ${searchVO.page }/${searchVO.totPage }
<hr />

<form action="list" method="post">

	<!-- 페이지 모음 -->
	<!-- 처음페이지에서 처음 링크 없애기 -->
	<c:if test="${searchVO.page > 1}">
		<a href="list?page=1"><i class="fa-solid fa-angles-left"></i></a>
		<a href="list?page=${searchVO.page-1 }"><i class="fa-solid fa-circle-chevron-left"></i></a>
	</c:if>

	<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
		<c:choose>
			<c:when test="${i eq searchVO.page }">
				<span style="color:red; font-weight: bold;">${i }</span>
			</c:when>
			<c:otherwise>
				<a href="list?page=${i }&sk=${resk}&brd_title=${brd_title==true?'brd_title':''}&brd_content=${brd_content==true?'brd_content':''}" style="text-decoration: none">${i }</a>
			</c:otherwise>
		</c:choose>	
	</c:forEach>

	<!-- 마지막페이지에서 마지막 링크 없애기 -->
	<c:if test="${searchVO.page < searchVO.totPage }">
		<a href="list?page=${searchVO.page+1 }"><i class="fa-solid fa-circle-chevron-right"></i></a>
		<a href="list?page=${searchVO.totPage }"><i class="fa-solid fa-angles-right"></i></a>
	</c:if>
	
	<div>
	<!-- 검색조건 검색창에 남아있도록 -->
	<c:choose>
		<c:when test="${brd_title }">
			<input type="checkbox" name="searchType" value="brd_title" checked />
		</c:when>	
		<c:otherwise>
			<input type="checkbox" name="searchType" value="brd_title" />
		</c:otherwise>
	</c:choose>
	제목
		
	<c:choose>
		<c:when test="${brd_content }">
			<input type="checkbox" name="searchType" value="brd_content" checked />
		</c:when>	
		<c:otherwise>
			<input type="checkbox" name="searchType" value="brd_content" />
		</c:otherwise>
	</c:choose>
	내용
	
		<input type="text" name="sk" value="${resk }" style="width: 150px;" maxlength="50" />
		<input type="submit" value="검색" />
	
	</div>
</form>




</body>
</html>