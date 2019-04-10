<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="foodOrSvc" class="com.foodOrder.model.FoodOrderService"/>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<html>
<head>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<!-- ##### Breadcrumb Area Start ##### -->
	<jsp:include page="/froTempl/headerCust.jsp" flush="true" />
	<!-- ##### Breadcrumb Area End ##### -->
<section class="team-member-area section-padding-100-0">
        <div class="container">
	<!-- ##### Contact Area Start #####-->
	<section>
	<div>
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th scope="col">訂單編號</th>
			  <th scope="col">顧客姓名</th>
			  <th scope="col">收件人姓名</th>
			  <th scope="col">訂單狀態</th>
			  <th scope="col">下訂日期</th>
			  <th scope="col">出貨日期</th>
			  <th scope="col">到貨日期</th>
			  <th scope="col">完成日期</th>
			  <th scope="col">收件地址</th>
			  <th scope="col">收件人電話</th>
			  <th scope="col">總計</th>
			  <th scope="col"></th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach var="foodOrderVO" items="${foodOrSvc.getFoodOrdersByCust_ID(custVO.cust_ID)}" >
		    <tr>
		      <td scope="col">${foodOrderVO.food_or_ID}</td>
			  <td scope="col">${custSvc.getOneCust(foodOrderVO.cust_ID).cust_name}</td>
			  <td scope="col">${foodOrderVO.food_or_name}</td>
			  <td scope="col">
			  	<c:choose>
			  		<c:when test="${foodOrderVO.food_or_status eq 'o3'}">
			  			<form method="POST" action="<%=request.getContextPath()%>/foodOrder/foodOrder.do">
			  				<input type="hidden" name="food_or_status" value="o4">
			  				<input type="hidden" name="food_or_ID" value="${foodOrderVO.food_or_ID}">
			  				<input type="hidden" name="action" value="frontChaStatus">
			  				<button type="submit">${mallOrStatusMap[foodOrderVO.food_or_status]}</button>
			  			</form>
			  		</c:when>
			  		<c:otherwise>
			  		${mallOrStatusMap[foodOrderVO.food_or_status]}
			  	</c:otherwise>
			  	</c:choose>
			  	
			  </td>
			  <td scope="col">${foodOrderVO.food_or_start}</td>
			  <td scope="col">${foodOrderVO.food_or_send}</td>
			  <td scope="col">${foodOrderVO.food_or_rcv}</td>
			  <td scope="col">${foodOrderVO.food_or_end}</td>
			  <td scope="col">${foodOrderVO.food_or_addr}</td>
			  <td scope="col">${foodOrderVO.food_or_tel}</td>
			  <td scope="col">
			  	<form action="<%=request.getContextPath()%>/foodOrder/foodOrder.do" method="post">
			  		<button type="submit" class="btn btn-info">查看詳情</button>
			  		<input type="hidden" name="action" value="frontGetOneDisplay">
			  		<input type="hidden" name="food_or_ID" value="${foodOrderVO.food_or_ID}">
			  	</form>
			  </td>
		    </tr>
		    </c:forEach>
		</table>
	</div>
	</section>
	<!-- ##### Feature Area End ##### -->
	
	</div>
    </section>
<br>
<br>
<br>
<br>
<br>
	<!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>