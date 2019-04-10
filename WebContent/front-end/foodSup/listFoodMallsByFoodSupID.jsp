<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodMall.model.FoodMallService"%>
<%@ page import="com.foodMall.model.FoodMallVO"%>
<%@ page import="com.foodSup.model.FoodSupVO"%>

<%@ page import="java.util.List"%>
<jsp:useBean id="foodMallSvc" class="com.foodMall.model.FoodMallService"/>
<jsp:useBean id="foodSupSvc" class="com.foodSup.model.FoodSupService"/>
<jsp:useBean id="foodSvc" class="com.food.model.FoodService"/>


<html>
<head>
<title>供應商顯示所有食材商品</title>

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
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerFoodSup.jsp" flush="true" />
	<!-- ##### Breadcrumb Area Start ##### -->
	<br>
	<br>
	<br>
	<br>

	<!-- ##### Breadcrumb Area End ##### -->
	<section class="team-member-area section-padding-100-0">
        <div class="container">
	
	
	<%-- 錯誤列表 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
	</c:if>
	<table class="table">
			<tr>
				<th>食材商名稱</th>
				<th>食材名稱</th>
				<th>食材種類</th>
				<th>標題</th>
				<th>狀態</th>
				<th>上/下架</th>
				<th>價格</th>
				<th>單位</th>
				<th>產地</th>
				<th>圖片</th>
			</tr>

					
			<c:forEach var="foodMallVO" items="${foodSupSvc.getFoodMallsByFood_sup_ID(foodSupVO.food_sup_ID)}" varStatus="ma">
					<tr class="foodMallEntiy">
						<td>${foodSupSvc.getOneFoodSup(foodMallVO.food_sup_ID).food_sup_name}</td>
						<td>${foodSvc.getOneFood(foodMallVO.food_ID).food_name}</td>
						<td>${foodTypeMap[foodSvc.getOneFood(foodMallVO.food_ID).food_type_ID]}</td>
						<td>${foodMallVO.food_m_name}</td>
						<td>${mallStatusMap[foodMallVO.food_m_status]}</td>
						<td><form method="post" action="<%=request.getContextPath()%>/foodMall/foodMall.do">
								<input type="hidden" name="food_ID"class="btn btn-warning" value="${foodMallVO.food_ID}">
								<input type="hidden" name="food_sup_ID" value="${foodMallVO.food_sup_ID}">
							<c:choose>
								<c:when test="${foodMallVO.food_m_status eq 'p1' || foodMallVO.food_m_status eq 'p3'}">
									<button id="staBtn${ma.index}" class="btn btn-primary" value="p4" type="submit">上架商品</button>
									<input type="hidden"  name="food_m_status" value="p4">
								</c:when>
								<c:when test="${foodMallVO.food_m_status eq 'p4'}">
									<button id="staBtn${ma.index}" class="btn btn-warning" value="p3" type="submit">下架商品</button>
									<input type="hidden"  name="food_m_status" value="p3">
								</c:when>
							</c:choose>
							<input type="hidden" name="action" value="fsChStaNoAjax">
							</form>
						</td>
						<td>${foodMallVO.food_m_price}</td>
						<td>${foodUnitMap[foodMallVO.food_m_unit]}</td>
						<td>${foodMallVO.food_m_place}</td>
						<td><img src="<%=request.getContextPath()%>/foodMall/foodMall.do?food_sup_ID=${foodMallVO.food_sup_ID}&food_ID=${foodMallVO.food_ID}"
							width="300" height="400"></td>

						<td>
							<form method="post" action="<%=request.getContextPath()%>/foodMall/foodMall.do">
								<button type="submit" class="btn pixel-btn wow fadeInUp" data-wow-delay="300ms">修改</button>
								<input type="hidden" name="food_ID" value="${foodMallVO.food_ID}">
								<input type="hidden" name="food_sup_ID" value="${foodMallVO.food_sup_ID}">
								<input type="hidden" id="action" name="action" value="foodSupGetUpdate">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
	
	</div>
    </section>
    <!-- ##### Contact Area End #####-->
    
<br>
<br>
<br>
<br>

	<jsp:include page="/froTempl/footer.jsp" flush="true" />


	
		<!-- ##### Contact Area End #####-->


	
</body>
</html>