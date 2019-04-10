<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dish.model.*"%>
<%@ page import="com.broadcast.model.*" %>

<%
  DishVO dishVO = (DishVO) request.getAttribute("dishVO");//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
  
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>菜色資料修改 - update_Dish_input.jsp</title>

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
	width: 600px;
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
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<aside class="left-sidebar" data-sidebarbg="skin5">
<%--==============<jsp:include page="/back-end/XXXX/sidebar.jsp" flush="true" />=================================--%>
		
		</aside>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>

<table id="table-1">
	<tr><td>
		 <h3>菜色資料修改 </h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>菜色修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="dish.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>菜色編號:<font color=red><b>*</b></font></td>
		<td><%=dishVO.getDish_ID()%></td>
	</tr>
	<tr>
		<td>菜色名稱:</td>
		<td><input type="TEXT" name="dish_name" size="45" value="<%=dishVO.getDish_name()%>" /></td>
	</tr>
	
	<tr>
		<td>菜色照片:</td>
		<td>
		<img id="preview_progressbarTW_img" src="<%=request.getContextPath()%>/dish/dish.do?dish_ID=${dishVO.dish_ID}" width="300" height="200"/><br>
		<input id="progressbarTWInput"  type="file" name="dish_pic" size="45"	id="progressbarTWInput" value="<%=dishVO.getDish_pic()%>" /><br>
		</td>
		
	</tr>
	<tr>
		<td>菜色內容:</td>
		<td><textarea rows="20" cols="40" name="dish_resume" ><%=dishVO.getDish_resume()%></textarea><br></td>
	</tr>
	
	<tr>
		<td>菜色價格:</td>
		<td><input type="TEXT" name="dish_price" size="45"	value="<%=dishVO.getDish_price()%>" /></td>
	</tr>
	
	<tr>
	<td>菜色狀態:</td>
		<td>
		<%-- 在XML註冊CookGodListener,狀態寫在com.cookGodPub,controller判斷寫是不是空值,不是空值直接進入料庫--%>
			<c:forEach var="dishStu" items="${dishStatusMap}">
				<input type="radio" name="dish_status" value="${dishStu.key}">${dishStu.value}
			</c:forEach>
			
			
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
<input type="hidden" name="action" value="update">
<input type="hidden" name="dish_ID" value="<%=dishVO.getDish_ID()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
<input type="submit" value="送出修改"></FORM>

			</div>
		</div>
	</div>
</body>
</html>