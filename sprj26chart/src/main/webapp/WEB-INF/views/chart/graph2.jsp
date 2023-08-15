<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>


<head>
<meta charset="UTF-8">
<title>line1</title>
</head>
<body>

<h3>graph2.jsp</h3>
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
const ctx1 = document.getElementById('myChartOne').getContext('2d');
const ctx2 = document.getElementById('myChartTwo').getContext('2d');
const ctx3 = document.getElementById('myChartThree').getContext('2d');

const myChart = new Chart(ctx1, {
	type:'line',
	/*type: bar, pie, line, polarArea, doughnut   */
	data:{
		labels:['aa1', 'aa2', 'aa3', 'aa4', 'aa5'],
		datasets:[{
			label:'# 청바지매출액',
			data:[
				10,
				100,
				100,
				200,
				1000
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
	
	
const myChart2 = new Chart(ctx2, {
	type:'polarArea',
	/*type: bar, pie, line, polarArea, doughnut   */
	data:{
		labels:['aa1', 'aa2', 'aa3', 'aa4', 'aa5'],
		datasets:[{
			label:'# 수영복매출액',
			data:[
				50,
				200,
				300,
				200,
				2000
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
	type:'doughnut',
	/*type: bar, pie, line, polarArea, doughnut   */
	data:{
		labels:['aa1', 'aa2', 'aa3', 'aa4', 'aa5'],
		datasets:[{
			label:'# 모자매출액',
			data:[
				50,
				200,
				300,
				200,
				2000
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