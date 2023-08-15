<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/pstyle.css" />
</head>
<body>
<h3>content_view.jsp</h3>

<table>
	<tr>
		<td class="left">번호</td>
		<td>${content_view.pzid }</td>
	</tr>
	<tr>
		<td class="left">히트</td>
		<td>${content_view.pzhit }</td>
	</tr>
	<tr>
		<td class="left">이름</td>
		<td>${content_view.pzname }</td>
	</tr>
	<tr>
		<td class="left">제목</td>
		<td>${content_view.pzsubj }</td>
	</tr>
	<tr>
		<td class="left">내용</td>
		<td>${content_view.pzcontent }</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="content_update?pzid=${content_view.pzid }">수정</a> &nbsp;
			<a href="list">목록</a> &nbsp;
			<a href="delete?pzid=${content_view.pzid }">삭제</a> &nbsp;
			<a href="reply_view?pzid=${content_view.pzid }">답변</a> &nbsp;
		</td>
	</tr>
</table>

</body>
</html>