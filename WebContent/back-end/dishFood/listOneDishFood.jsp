<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dishFood.model.*"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="foodSvc" scope="page"
	class="com.food.model.FoodService" />
<jsp:useBean id="dishSvc" scope="page"
	class="com.dish.model.DishService" />
<jsp:useBean id="dishFoodSvc" scope="page"
	class="com.dishFood.model.DishFoodService" />
<jsp:useBean id="BroadSoc" scope="page"
	class="com.broadcast.controller.BroadcastWebSocket" />
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    List<DishFoodVO> listFoods_ByDish = (List<DishFoodVO>) request.getAttribute("listFoods_ByDish"); 
%>

<html>
<head>
<title>菜色食材資料</title>


</head>
<body>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/dishFoodMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>


				
				
				<div class="col">
					<div class="card">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
								<tr align='center'>
								<td colspan="4">菜色名稱:<font color="red" size="5">${dishSvc.getOneDish(dishVO.dish_ID).dish_name}</font></td>
								</tr>
									<tr align='center'>
										
										<th scope="col">食材名稱</th>
										<th scope="col">食材數量</th>
										<th scope="col">食材單位</th>

									</tr>
								</thead>
								<c:forEach var="dishFoodVO" items="${listFoods_ByDish}">
									<tr align='center'>


										<th scope="row">${foodSvc.getOneFood(dishFoodVO.food_ID).food_name}</th>
										<th scope="row">${dishFoodVO.dish_f_qty}</th>
										<th scope="row">${foodUnitMap[dishFoodVO.dish_f_unit]}</th>
									</tr>

								</c:forEach>
								<tr>
									<td colspan="3" align='center'>
										<h4>
											<a
												href="<%=request.getContextPath()%>/back-end/dish/AllDish.jsp">回首頁</a>
										</h4>
									</td>
								</tr>
							</table>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>