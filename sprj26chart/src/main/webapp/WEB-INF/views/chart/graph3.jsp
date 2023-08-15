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
String sql = "select decode(colc,1,'청1',2,'청2',3,'청3',4,'청4',5,'청5') "+
		"goods ,round(sum(cold)*sum(cole)/1000000) totalsum "+
		"from sale2 group by colc order by colc";

//DB접속
Connection con = DBcon.getConnection();
PreparedStatement pstmt = con.prepareStatement(sql);
ResultSet rs = pstmt.executeQuery();

//while(rs.next()){
//	System.out.println(rs.getString("goods")+": "+rs.getInt(2));
//}

JSONArray arr = new JSONArray();
while(rs.next()){
	JSONObject obj = new JSONObject();
	String goods = rs.getString("goods");
	String sum = rs.getString("totalsum");
	obj.put("goods", goods);
	obj.put("sum", sum);
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
<title>line1</title>
</head>
<body>

<h3>graph3.jsp</h3>

<div class="container">
	<div class="row">
		<div class="col-md-6">
			<canvas width="400" height="400" id="myChartOne"></canvas>
		</div>
		<div class="col-md-6">
			<canvas width="400" height="400" id="myChartTwo"></canvas>
		</div>
		<div class="col-md-6">
			<canvas width="400" height="400" id="myChartThree"></canvas>
		</div>
	</div>
</div>

<script>
var jArray = new Array();
jArray = '<%=arr%>';

//파싱
jArray = JSON.parse(jArray);
alert(jArray[0].sum);







const ctx1 = document.getElementById('myChartOne').getContext('2d');
const ctx2 = document.getElementById('myChartTwo').getContext('2d');
const ctx3 = document.getElementById('myChartThree').getContext('2d');


const myChart = new Chart(ctx1, {
	type:'line',
	/*type: bar, pie, line, polarArea, doughnut   */
	data:{
		labels:[
			jArray[0].goods,
			jArray[1].goods,
			jArray[2].goods,
			jArray[3].goods,
			jArray[4].goods
			],
		datasets:[{
			label:'# 청바지매출액',
			data:[
				jArray[0].sum,
				jArray[1].sum,
				jArray[2].sum,
				jArray[3].sum,
				jArray[4].sum
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


const myChart2= new Chart(ctx2, {
	type:'pie',
	/*type: bar, pie, line, polarArea, doughnut   */
	data:{
		labels:[
			jArray[0].goods,
			jArray[1].goods,
			jArray[2].goods,
			jArray[3].goods,
			jArray[4].goods
			],
		datasets:[{
			label:'# 청바지매출액',
			data:[
				jArray[0].sum,
				jArray[1].sum,
				jArray[2].sum,
				jArray[3].sum,
				jArray[4].sum
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


const myChart3 = new Chart(ctx3, {
	type:'polarArea',
	/*type: bar, pie, line, polarArea, doughnut   */
	data:{
		labels:[
			jArray[0].goods,
			jArray[1].goods,
			jArray[2].goods,
			jArray[3].goods,
			jArray[4].goods
			],
		datasets:[{
			label:'# 청바지매출액',
			data:[
				jArray[0].sum,
				jArray[1].sum,
				jArray[2].sum,
				jArray[3].sum,
				jArray[4].sum,
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

</body>
</html>