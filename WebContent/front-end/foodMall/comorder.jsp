<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<jsp:useBean id="foodOrderSvc" class="com.foodOrder.model.FoodOrderService"/>
<jsp:useBean id="festOrderSvc" class="com.festOrder.model.FestOrderService"/>
<jsp:useBean id="foodSvc" class="com.food.model.FoodService"/>
<jsp:useBean id="foodSupSvc" class="com.foodSup.model.FoodSupService"/>
<html>
<head>
<title>付款成功</title>

</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerMall.jsp" flush="true" />
	<div class="row justify-content-center">
					<div class="col-12 col-lg-8">
						<!-- Contact Form -->
						<div class="contact-form-area text-center">
	
	<section class="contact-area section-padding-100">
        <div >
            
            <div class="row justify-content-center">
                <div >
                

	 <!-- ##### Contact Area Start #####-->
   
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
				    <c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${not empty foodOrderVO}">
				<table class="table">
				  <thead style="font-weight:blod;font-size:25px">
				    <tr>
				      <th scope="col">食材訂單編號</th>
				      <th scope="col">訂單狀態</th>
				      <th scope="col">下訂日期</th>
				      <th scope="col">出貨日期</th>
				      <th scope="col">完成日期</th>
				      <th scope="col">收件人</th>
				      <th scope="col">收件地址</th>
				      <th scope="col">聯絡方式</th>
				      <th scope="col">下訂客戶</th>
				    </tr>
				  </thead>
				  <tbody>
				  	<tr>
				  	  <td scope="col">${foodOrderVO.food_or_ID}</td>
				  	  <td scope="col">${mallOrStatusMap[foodOrderVO.food_or_status]}</td>
				  	  <td scope="col">${foodOrderVO.food_or_start}</td>
				  	  <td scope="col">${foodOrderVO.food_or_send}</td>
				  	  <td scope="col">${foodOrderVO.food_or_end}</td>
				  	  <td scope="col">${foodOrderVO.food_or_name}</td>
				  	  <td scope="col">${foodOrderVO.food_or_addr}</td>
				  	  <td scope="col">${foodOrderVO.food_or_tel}</td>
				  	  <td scope="col">${custSvc.getOneCust(foodOrderVO.cust_ID).cust_name}</td>
				  	</tr>
				  </tbody>
				 </table>
				 <table class="table">
				 	<tr style="font-weight:blod;font-size:25px">
				 		<th scope="col">食材供應商</th> <th scope="col">食材</th> <th scope="col">數量</th> <th scope="col">小計</th>
				 	</tr>
				 	<c:forEach var="foodOrODVO" items="${foodOrODs}">
				 		<tr><td scope="col">${foodSupSvc.getOneFoodSup(foodOrODVO.food_sup_ID).food_sup_name}</td>
				 			<td scope="col">${foodSvc.getOneFood(foodOrODVO.food_ID).food_name}</td><td scope="col">${foodOrODVO.food_od_qty}</td><td scope="col">${foodOrODVO.food_od_stotal}</td></tr>
				 	</c:forEach>
				 </table>
			 </c:if>
			 <c:if test="${not empty festOrderList}">
				<table class="table" style="background-color: transparent;table-layout: fixed;">
				  <thead style="font-weight:blod;font-size:25px">
				    <tr>
				      <th scope="col">節慶訂單編號</th>
				      <th scope="col">訂單狀態</th>
				      <th scope="col">下訂日期</th>
				      <th scope="col">出貨日期</th>
				      <th scope="col">完成日期</th>
				      <th scope="col">收件人</th>
				      <th scope="col">收件地址</th>
				      <th scope="col">聯絡方式</th>
				      <th scope="col">下訂客戶</th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach var="festOrderVO" items="${festOrderList}">
				  	<tr>
				  	  <td scope="col">${festOrderVO.fest_or_ID}</td>
				  	  <td scope="col">${mallOrStatusMap[festOrderVO.fest_or_status]}</td>
				  	  <td scope="col">${festOrderSvc.getOneFestOrder(festOrderVO.fest_or_ID).fest_or_start}</td>
				  	  <td scope="col">${festOrderVO.fest_or_send}</td>
				  	  <td scope="col">${festOrderVO.fest_or_end}</td>
				  	  <c:if test="${not empty foodOrderVO}">
					  	  <td scope="col">${foodOrderVO.food_or_name}</td>
					  	  <td scope="col">${foodOrderVO.food_or_addr}</td>
					  	  <td scope="col">${foodOrderVO.food_or_tel}</td>
				  	  </c:if>
				  	  <c:if test="${empty foodOrderVO}">
					  	  <td scope="col">${custSvc.getOneCust(festOrderVO.cust_ID).cust_name}</td>
					  	  <td scope="col">${custSvc.getOneCust(festOrderVO.cust_ID).cust_addr}</td>
					  	  <td scope="col">${custSvc.getOneCust(festOrderVO.cust_ID).cust_tel}</td>
				  	  </c:if>
				  	  <td scope="col">${custSvc.getOneCust(festOrderVO.cust_ID).cust_name}</td>
				  	</tr>
				  	</c:forEach>
				  </tbody>
				 </table>
			 </c:if>
            

	
	
			</div>
			</div>
		</div>
		
	</section>
	
			</div>
			</div>
		</div>
    <!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>