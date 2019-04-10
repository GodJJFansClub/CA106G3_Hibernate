<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Dish: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: #FFFFFF;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
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
   <tr><td><h3>IBM Dish: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Dish: Home</p>

<h6>查詢:</h6>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='AllDish.jsp'>菜色</a><br><br></li>
  <li><a href='listAllMenu_ByDish.jsp'>套餐菜色</a><br><br></li>
   <li><a href='listAllDish_ByFood.jsp'>菜色食材</a> </li>

  <jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dish/dish.do" >
       <b>選擇菜色:</b>
       <select size="1" name="dish_ID">
         <c:forEach var="dishVO" items="${dishSvc.all}" > 
          <option value="${dishVO.dish_ID}">${dishVO.dish_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>
<h6 >新增</h6>

<ul>
<li><a href='addDish.jsp'>新增菜色</a> </li>
</ul>
<ul>
<li><a href="<%=request.getContextPath() %>/back-end/dishFood/addDishFood.jsp">新增菜色食材</a> </li>
</ul>
<ul>
<li><a href="<%=request.getContextPath() %>/back-end/menuDish/addMenuDish.jsp">新增套餐菜色</a> </li>
</ul>




			</div>
		</div>
	</div>
</body>
</html>