<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/nstyle.css" />
</head>
<body>
<h3>content_view.jsp</h3>



<table>
	<tr>
		<td class="left">번호</td>
		<td>${content_view.bid }</td>
	</tr>
	<tr>
		<td class="left">히트</td>
		<td>${content_view.bhit }</td>
	</tr>
	<tr>
		<td class="left">이름</td>
		<td>${content_view.bname }</td>
	</tr>
	<tr>
		<td class="left">제목</td>
		<td>${content_view.btitle }</td>
	</tr>
	<tr>
		<td class="left">내용</td>
		<td>${content_view.bcontent }</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="content_update?bid=${content_view.bid }">수정</a> <br />
			<a href="list">list</a> <br />
			<a href="delete?bid=${content_view.bid }">삭제</a> <br />
			답변 <br />					
		</td>
	</tr>
</table>


</body>
</html>