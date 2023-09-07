<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page session="false" %>
<%
String path = request.getContextPath();
%>
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>
<script>
	function redeptlist(){
		<%-- alert("<%=path%>"); --%>
		$.ajax({
			type:"post",
			url:"<%=path%>/test/deptlist",
			
			success:function(result){
				htmltxt = "<table border='1'>";			
				for ( var ele in result) {
					/* console.log(result[ele].loc); */
					htmltxt = htmltxt+"<tr><td>no:"+result[ele].deptno+
					"</td><td>dname:"+result[ele].dname+"</td></tr>";
				}
				
				htmltxt = htmltxt+"</table>";
				$("#display").html(htmltxt);
			}
		});
	}
	
	function restemplist(){
		$.ajax({
			type:"post",
			url:"<%=path%>/test/emplist",
			
			success:function(result2){
				htmltxt2 = "<table border='1'>";
				for ( var ele2 in result2) {
					htmltxt2 = htmltxt2+
					"<tr><td>empno: "+result2[ele2].empno+
					"</td><td>ename: "+result2[ele2].ename+
					"</td><td>job: "+result2[ele2].job+
					"</td><td>hiredate: "+result2[ele2].hiredate+
					"</td></tr>";
				}
				
				htmltxt2 = htmltxt2+"</table>";
				$("#display").html(htmltxt2);
			}
		});
	}

</script>

<P>  The time on the server is ${serverTime}. </P>
<a href="list">list</a> <br />
<h3>DB Connect</h3>
<hr />
<a href="javascript:redeptlist();">restdeptlist</a> <br />
<a href="javascript:restemplist()">restemplist</a> <br />
<hr />
<div id="display">data view 한글가능??ㅇㅋ</div>
</body>
</html>
