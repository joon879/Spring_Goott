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
		<td>${content_view.brd_id }</td>
	</tr>
	<tr>
		<td class="left">히트</td>
		<td>${content_view.brd_hit }</td>
	</tr>
	<tr>
		<td class="left">이름</td>
		<td>${content_view.brd_name }</td>
	</tr>
	<tr>
		<td class="left">제목</td>
		<td>${content_view.brd_title }</td>
	</tr>
	<tr>
		<td class="left">내용</td>
		<td>${content_view.brd_content }</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="content_update?brd_id=${content_view.brd_id }">수정</a> <br />
			<a href="list">목록</a> <br />
			<a href="delete?brd_id=${content_view.brd_id }">삭제</a> <br />
			<a href="reply_view?brd_id=${content_view.brd_id }">답변</a> <br /> 			
		</td>
	</tr>
</table>


</body>
</html>