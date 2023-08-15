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
<h3>content_update.jsp</h3>

<form action="modify" method="post">
<input type="hidden" name="brd_id" value="${content_view.bid }" /> 
<!-- 이 번호에 대한 글을 수정하겠다! -->
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
		<td>
			<input type="text" name="brd_name" value="${content_view.brd_name }" />		
		</td>
	</tr>
	<tr>
		<td class="left">제목</td>
		<td>
			<input type="text" name="brd_title" size="30" value="${content_view.brd_title }" />
		</td>
	</tr>
	<tr>
		<td class="left">내용</td>
		<td>
			<textarea name="brd_content" cols="50" rows="10">${content_view.brd_content }</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="modify" /> &nbsp;&nbsp;
			<a href="list">list</a>	&nbsp;&nbsp;			
		</td>
	</tr>
</table>



</form>


</body>
</html>