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

<form action="modify" method="post">
<input type="hidden" name="pzid" value="${content_view.pzid }" />
<!-- 이 번호에 대한 글을 수정하겠다! -->
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
		<td>
			<input type="text" name="pzname" value="${content_view.pzname }" />
		</td>
	</tr>
	<tr>
		<td class="left">제목</td>
		<td>
			<input type="text" name="pzsubj" value="${content_view.pzsubj }" />
		</td>
	</tr>
	<tr>
		<td class="left">내용</td>
		<td>
			<textarea name="pzcontent" cols="50" rows="10">${content_view.pzcontent }</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="수정" /> &nbsp;&nbsp;
			<a href="list">목록</a> &nbsp;&nbsp;
		</td>
	</tr>
</table>


</form>



</body>
</html>