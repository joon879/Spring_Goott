<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<script>
var cnt=1
function setInnerHTML() {
    if (cnt<3) {
     var str="name"+cnt
     $.ajax({
     type:"get",
     url:'dynamicnamerest',
     cache:false,
     success:function(data){  	
        $('<div><input type="text" name="'+str+'" /><br /></div>').insertBefore("cj");
     	}
     });
     
 }else{
 alert("are you hungry!!!!!")
 }
     cnt++;
     console.log("cnt : "+cnt)
} 

function refresh()  {
  const element = document.getElementById('my_div');
  element.innerHTML 
    = '<input type="text" name="name0"><br />';
    cnt=1
}


    </script>
<form action="godata">

    <div id='my_div'>
        <input type="text" name="name0"><br />
    </div>

     <cj> <input type='button' id="btnline"
             value='innerHTML'
             onclick='setInnerHTML()'/></cj>
      
      <input type='button'
             value='refresh(초기화)'
             onclick='refresh()' />
      <br />
 <input type="submit"  value="send" />
</form>
</body>
</html>

<!-- 참조 : https://hianna.tistory.com/479 -->

<!-- https://www.devkuma.com/docs/jquery/element-append-prepend-before-after/
var i = 0;
$("button").on("click", function() {
    $("<div>" + (++i) + "번째 문장입니다.</div>").insertBefore("p");
});
 -->
<!-- https://joshwon.tistory.com/9 
 document.querySelector('.popupfooter').setAttribute("id", "edit_Project");
 -->
<!-- 
https://hianna.tistory.com/484 
 function createDiv() {
  // 1. <div> element 만들기
  const newDiv = document.createElement('div');
  // 2. <div>에 들어갈 text node 만들기
  const newText = document.createTextNode('안녕하세요'); 
  // 3. <div>에 text node 붙이기
  newDiv.appendChild(newText);
  // 4. <body>에 1에서 만든 <div> element 붙이기
  document.body.appendChild(newDiv);
} --> 
