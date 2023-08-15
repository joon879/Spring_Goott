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
<h3>reply_view.jsp</h3>

<form action="reply" method="post">
<input type="hidden" name="pzid" value="${reply_view.pzid }" />
<input type="hidden" name="pzgroup" value="${reply_view.pzgroup }" />
<input type="hidden" name="pzstep" value="${reply_view.pzstep }" />
<input type="hidden" name="pzintent" value="${reply_view.pzintent }" />


<table>
	<tr>
		<td class="left">번호</td>
		<td>${reply_view.pzid }</td>
	</tr>
	<tr>
		<td class="left">히트</td>
		<td>${reply_view.pzhit }</td>
	</tr>
	<tr>
		<td class="left">이름</td>
		<td>
			<input type="text" name="pzname" value="${reply_view.pzname }" />
		</td>
	</tr>
	<tr>
		<td class="left">제목</td>
		<td>
			<input type="text" name="pzsubj" value="${reply_view.pzsubj }" />
		</td>
	</tr>
	<tr>
		<td class="left">내용</td>
		<td>
			<textarea name="pzcontent" cols="50" rows="10">${reply_view.pzcontent }</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="reply" />
			<a href="list">목록</a>
		</td>
	</tr>
</table>


</form>



</body>
</html>