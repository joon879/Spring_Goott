<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script>
	function lengthdiv() {
		//alert("hi")
		var d1width = document.getElementById("d1").clientWidth;
		//alert(d1width);
		var y = document.getElementById("window");
		y.innerHTML = "스크린폭: "+screen.width+", 창폭: "+window.innerWidth+", div폭: "+d1width;
		
	}


</script>





</head>
<body onresize="lengthdiv();">
<h3>bootstrap1</h3>
<div class="jumbotron text-center">
	<h1>My First Bootstrap page</h1>
	<p>Resize this responsive page to see the effect!</p>
</div>
<div class="container">
	<div class="row">
		<div id="d1" class="col-sm-4">
			Column
		</div>
		<div id="d2" class="col-sm-4">
			Column
		</div>
		<div id="d3" class="col-sm-4">
			Column
		</div>
	</div>
</div>
<p align="center" style="font-size: 20px" id="window"></p>


</body>
</html>