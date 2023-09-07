<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.tech.db.DBCon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.0/dist/chart.min.js"></script>

<link rel="stylesheet" 
   href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">

<%
String sql="select decode(colc,1,'청1',2,'청2',3,'청3',4,'청4',5,'청5') "+
"goods ,round(sum(cold)*sum(cole)/1000000) totalsum "+
"from random2 group by colc order by colc";

//db접속
Connection con=DBCon.getConnection();
PreparedStatement pstmt=con.prepareStatement(sql);
ResultSet rs=pstmt.executeQuery();
/* while(rs.next()){
	System.out.println(rs.getString("goods")+":"+rs.getInt(2));
} */

JSONArray arr=new JSONArray();
while(rs.next()){
	JSONObject obj=new JSONObject();
	String goods=rs.getString(1);
	String sum=rs.getString(2);
	
	obj.put("goods", goods);
	obj.put("sum",sum);
	if(obj!=null)
		arr.add(obj);
}
rs.close();
pstmt.close();
con.close();
%>   
   
   
</head>
<body>
<h3>graph3</h3>
<div class="container">
	<div class="row">
		<div class="col-md-6">
			<canvas id="myChartOne"></canvas>
		</div>
		<div class="col-md-6">
			<canvas id="myChartTwo"></canvas>
		</div>
		<div class="col-md-6">
			<canvas id="myChartThree"></canvas>
		</div>
	</div>
</div>


<script>
var jArray=new Array();
jArray='<%=arr %>';
jArray=JSON.parse(jArray);

/* alert(jArray[0].sum); */


let myChartOne = document.getElementById('myChartOne').getContext('2d');
let myChartTwo = document.getElementById('myChartTwo').getContext('2d');
let myChartThree = document.getElementById('myChartThree').getContext('2d');



let barChartOne = new Chart(myChartOne, {
    type: 'polarArea',//pie,line,doughnut,polarArea
    data: {
        labels: [
        	jArray[0].goods,
        	jArray[1].goods,
        	jArray[2].goods,
        	jArray[3].goods,
        	jArray[4].goods
        	],
        datasets: [{
            label: '청바지매출액',
            data: [
            	jArray[0].sum,
            	jArray[1].sum,
            	jArray[2].sum, 
            	jArray[3].sum,  
            	jArray[4].sum
            	],
            backgroundColor: [
                'rgba(255, 99, 132, 1.0)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 1.0)',
                '#0000ff'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 3
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});


let barChartTwo = new Chart(myChartTwo, {
    type: 'bar',
    data: {
        labels: ['aa1', 'aa2', 'aa3', 'aa4', 'aa5'],
        datasets: [{
            label: '청바지매출액',
            data: [
            	10,
            	100,
            	100, 
            	200,  
            	1000
            	],
            backgroundColor: [
                'rgba(255, 99, 132, 1.0)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 1.0)',
                '#0000ff'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 3,
            borderColor:'red',
            hoverBorderWidth:10
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});


let barChartThree = new Chart(myChartThree, {
    type: 'bar',
    data: {
        labels: ['aa1', 'aa2', 'aa3', 'aa4', 'aa5'],
        datasets: [{
            label: '청바지매출액',
            data: [
            	10,
            	100,
            	100, 
            	200,  
            	1000
            	],
            backgroundColor: [
                'rgba(255, 99, 132, 1.0)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 1.0)',
                '#0000ff'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 3
        }]
    },
    options:{
		title:{
			display:true,
            text:'청바지 매출액',
            fontSize:20,
            fontColor:'red'
		},
		legend:{
			display:true,
            position:'top'//right,left,top,bottom
		},
		tooltips:{
			enabled:false
		},
		layout:{
			padding:{
				left:10,
                right:10,
                top:10,
                bottom:10	
			}
		}
	}
});
</script>
</body>
</html>