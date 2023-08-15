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
<h3>reply_view.jsp</h3>

<form action="reply" method="post">
<input type="hidden" name="brd_id" value="${reply_view.brd_id }" />
<input type="hidden" name="brd_group" value="${reply_view.brd_group }" />
<input type="hidden" name="brd_step" value="${reply_view.brd_step }" />
<input type="hidden" name="brd_indent" value="${reply_view.brd_indent }" />
<table>
	<tr>
		<td class="left">번호</td>
		<td>${reply_view.brd_id }</td>
	</tr>
	<tr>
		<td class="left">히트</td>
		<td>${reply_view.brd_hit }</td>
	</tr>
	<tr>
		<td class="left">이름</td>
		<td>
			<input type="text" name="brd_name" value="${reply_view.brd_name }" />		
		</td>
	</tr>
	<tr>
		<td class="left">제목</td>
		<td>
			<input type="text" name="brd_title" size="30" value="${reply_view.brd_title }" />
		</td>
	</tr>
	<tr>
		<td class="left">내용</td>
		<td>
			<textarea name="brd_content" cols="50" rows="10">${reply_view.brd_content }</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="reply" />
			<a href="list">list</a>
		</td>
	</tr>
</table>

</form>




</body>
</html>