<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.tech.db.DBcon"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<%
//student테이블에서 grade별 height 합계 
String sql = "select grade, sum(height) hei_sum from student group by grade order by grade";

//DB 접속
Connection con = DBcon.getConnection();
PreparedStatement pstmt = con.prepareStatement(sql);
ResultSet rs = pstmt.executeQuery();


JSONArray arr = new JSONArray();
while(rs.next()){
	JSONObject obj = new JSONObject();
	String grade = rs.getString("grade");
	String hei_sum = rs.getString("hei_sum");
	obj.put("grade", grade);
	obj.put("hei_sum", hei_sum);
	if(obj != null){
		arr.add(obj);
	}
}
rs.close();
pstmt.close();
con.close();
%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<h3>stuheight.jsp</h3>

<div class="container">
	<div class="row">
		<div class="col-md-6">
			<canvas width="200" height="200" id="stuChart"></canvas>
		</div>
	</div>
</div>

<script>
var jArray = new Array();
jArray = '<%=arr%>';

jArray = JSON.parse(jArray);
alert(jArray[0].hei_sum);

const ctx1 = document.getElementById('stuChart').getContext('2d');

const stuChart = new Chart(ctx1, {
	type:'bar',
	/*type: bar, pie, line, polarArea, doughnut   */
	data:{
		labels:[
			jArray[0].grade,
			jArray[1].grade,
			jArray[2].grade,
			jArray[3].grade
			],
		datasets:[{
			label:'# 학년별 키의 합',
			data:[
				jArray[0].hei_sum,
				jArray[1].hei_sum,
				jArray[2].hei_sum,
				jArray[3].hei_sum
				],
			backgroundColor:[
				'rgba(255, 99, 132, 1.0)',
				'rgba(55, 19, 12, 0.2)',
				'rgba(10, 39, 32, 0.2)',
				'rgba(2, 99, 13, 0.7)',
				'#0000ff'
			],
			borderColor:[
				'rgba(255, 99, 13, 1)',
				'rgba(55, 99, 13, 1)',
				'rgba(255, 99, 13, 1)',
				'rgba(55, 19, 13, 1)',
				'rgba(255, 99, 13, 1)'
			],
			borderWidth:3
		}]
	},
	options:{
		scales:{
			y:{
				beginAtZero:true
			}
		}
	}
});





</script>






</head>
<body>

</body>
</html>