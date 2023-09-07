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
    	 //alert(data)
        const element = document.getElementById('my_div');
        element.innerHTML += '<input type="text" name="'+str+'" /><br />';
        document.querySelector('#my_div').setAttribute("id", "my_div"+cnt);
        const newDiv=document.createElement('div');
        newDiv.id='my_div';
      /*   const newText=document.createTextNode('aa');
        newDiv.appendChild(newText);
        document.querySelector('#btnline').appendChild(newDiv); */
        $("<div>"+cnt+"hi  </div>").insertBefore("p");
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
<div id='my_div2'>
        <input type="text" name="name0"><br />
    </div>
    <div id='my_div'>
        <input type="text" name="name0"><br />
    </div>

     <p> <input type='button' id="btnline"
             value='innerHTML'
             onclick='setInnerHTML()'/></p>
      
      <input type='button'
             value='refresh(초기화)'
             onclick='refresh()' />
      <br />
 <input type="submit"  value="send" />
</form>
</body>
</html>

<!-- 참조 : https://hianna.tistory.com/479 -->