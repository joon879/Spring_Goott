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
<h3>write_view.jsp</h3>
<form action="write">
	<table>
		<tr>
			<td class="left">이름</td>
			<td><input type="text" name="brd_name" value="sj" /></td>
		</tr>
		<tr>
			<td class="left">제목</td>
			<td><input type="text" name="brd_title" value="sjtitle" /></td>
		</tr>
		<tr>
			<td class="left">내용</td>
			<td><textarea type="text" name="brd_content" rows="10">content</textarea></td>
		</tr>
		<tr>	
			<td colspan="2">
				<input type="submit" value="입력" />
				<a href="list">목록</a>
			</td>
		</tr>
	</table>

</form>

</body>
</html>