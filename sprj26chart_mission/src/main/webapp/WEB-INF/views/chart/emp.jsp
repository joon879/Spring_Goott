<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.tech.db.DBcon"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<%
//emp테이블에서 job별 salery 합계 
String sql = "select job, sum(sal) sal_sum from emp group by job";

//DB 접속
Connection con = DBcon.getConnection();
PreparedStatement pstmt = con.prepareStatement(sql);
ResultSet rs = pstmt.executeQuery();


JSONArray arr = new JSONArray();
while(rs.next()){
	JSONObject obj = new JSONObject();
	String job = rs.getString("job");
	String sal_sum = rs.getString("sal_sum");
	obj.put("job", job);
	obj.put("sal_sum", sal_sum);
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
<h3>emp.jsp</h3>

<div class="container">
	<div class="row">
		<div class="col-md-6">
			<canvas width="500" height="500" id="empChart"></canvas>
		</div>
	</div>
</div>

<script>
var jArray = new Array();
jArray = '<%=arr%>';

jArray = JSON.parse(jArray);
alert(jArray[0].sal_sum);

const ctx1 = document.getElementById('empChart').getContext('2d');

const empChart = new Chart(ctx1, {
	type:'line',
	/*type: bar, pie, line, polarArea, doughnut   */
	data:{
		labels:[
			jArray[0].job,
			jArray[1].job,
			jArray[2].job,
			jArray[3].job,
			jArray[4].job
			],
		datasets:[{
			label:'# 직업별 연봉액',
			data:[
				jArray[0].sal_sum,
				jArray[1].sal_sum,
				jArray[2].sal_sum,
				jArray[3].sal_sum,
				jArray[4].sal_sum
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