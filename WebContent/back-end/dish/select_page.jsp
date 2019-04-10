<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Dish: Home</title>



</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/dishFoodMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>
<h3>查詢:</h3>
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
<br>


  <li><a href='listAllDish.jsp'>新菜色申請</a><br><br></li>
  <li><a href='AllDish.jsp'>目前上架菜色</a><br><br></li>
  

  <jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService"/>
  <jsp:useBean id="menuSvc" scope="page" class="com.menu.model.MenuService"/>
  <jsp:useBean id="menuDishSvc" scope="page" class="com.menuDish.model.MenuDishService" />
  <jsp:useBean id="dishFoodSvc" scope="page" class="com.dishFood.model.DishFoodService" />
  
</ul>
<h3>新增</h3>
<br>
<ul>
<li><a href='addDish.jsp'>新增菜色</a> </li>
</ul>
<ul>
<li><a href="<%=request.getContextPath() %>/back-end/menu/index.jsp">新增套餐菜色</a> </li>
</ul>




			</div>
		</div>
	</div>
</body>
</html>