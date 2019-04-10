<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dish.model.*"%>

<%
  DishVO dishVO = (DishVO) request.getAttribute("dishVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

 
 <script 
  src="https://code.jquery.com/jquery-3.3.1.min.js"
   integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" 
   crossorigin="anonymous"></script> 
   
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width:400px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
	<br>
		 <h3>新增菜色</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dish/dish.do" name="form1" enctype="multipart/form-data">
<table>
	
	<tr>
		<td>菜色名稱:</td>
		<td><input type="TEXT" name="dish_name" size="42"
			 value="<%= (dishVO==null)? "請輸入菜色名稱" : dishVO.getDish_name()%>" /><br></td>
	</tr>
	

	<tr>
		<td>菜色照片:</td>
		<td>
		<img id="preview_progressbarTW_img" src="<%=request.getContextPath()%>/dish/dish.do?dish_ID=${dishVO.dish_ID}" width="300" height="200"/>
		<br>
		<input type="file" name="dish_pic" size="43" id="progressbarTWInput" 
			 	value="<%= (dishVO==null)? "請輸入照片" : dishVO.getDish_pic()%>" />
		<br>
		</td>	 
	</tr>
	
	<tr>
		<td>菜色內容:</td>
		<td><textarea rows="20" cols="40" name="dish_resume" ></textarea><br></td>
	</tr>
	<tr>
		<td>菜色價格:</td>
		<td><input type="TEXT" name="dish_price" size="43"
			 value="<%= (dishVO==null)? "請輸入菜色價格" : dishVO.getDish_price()%>" /></td>
	</tr>


</table>

<script>

$("#progressbarTWInput").change(function(){

  readURL(this);

});

 

function readURL(input){

  if(input.files && input.files[0]){

    var reader = new FileReader();

    reader.onload = function (e) {

       $("#preview_progressbarTW_img").attr('src', e.target.result);

    }

    reader.readAsDataURL(input.files[0]);

  }

}

</script>


<br>
<input type="hidden"  name="dish_status" value="D0">
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>