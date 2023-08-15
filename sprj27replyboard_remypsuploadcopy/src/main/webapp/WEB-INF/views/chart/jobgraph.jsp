<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<h3>jobgraph.jsp</h3>

${arr }
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
jArray = '${arr}';

//파싱
jArray = JSON.parse(jArray);
alert(jArray[0].sum);







const ctx1 = document.getElementById('myChartOne').getContext('2d');
const ctx2 = document.getElementById('myChartTwo').getContext('2d');
const ctx3 = document.getElementById('myChartThree').getContext('2d');


const myChartOne = new Chart(ctx1, {
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
			label:'# 청바지매출액',
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


const myChartTwo= new Chart(ctx2, {
	type:'bar',
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
			label:'# 청바지매출액',
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


const myChartThree = new Chart(ctx3, {
	type:'polarArea',
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
			label:'# 청바지매출액',
			data:[
				jArray[0].sal_sum,
				jArray[1].sal_sum,
				jArray[2].sal_sum,
				jArray[3].sal_sum,
				jArray[4].sal_sum,
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