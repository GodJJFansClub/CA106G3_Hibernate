<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="foodSupSvc" class="com.foodSup.model.FoodSupService"/>
<jsp:useBean id="foodMallSvc" class="com.foodMall.model.FoodMallService"/>
<jsp:useBean id="foodSvc" class="com.food.model.FoodService"/>
<jsp:useBean id="foodOrService" class="com.foodOrder.model.FoodOrderService"/>
<html>
<head>


</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerFoodSup.jsp" flush="true" />
	<!-- ##### Breadcrumb Area Start ##### -->
	<br>
	<br>
	<br>
	<br>
	<section class="team-member-area section-padding-100-0">
        <div class="container">
	<!-- ##### Breadcrumb Area End ##### -->
    <table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col">訂單編號</th>
	      <th scope="col">標題</th>
	      <th scope="col">食材名稱</th>
	      <th scope="col">數量</th>
	      <th scope="col">小計</th>
	      <th scope="col">評價</th>
	      <th scope="col">狀態</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach var="foodODVO" items="${foodSupSvc.getFoodODByFood_sup_ID(foodSupVO.food_sup_ID)}" varStatus="foodODIn">
	    <tr>
	      <td>${foodODVO.food_or_ID}</td>
	      <td>${foodMallSvc.getOneFoodMall(foodSupVO.food_sup_ID, foodODVO.food_ID).food_m_name}</td>
	      <td>${foodSvc.getOneFood(foodODVO.food_ID).food_name}</td>
	      <td>${foodODVO.food_od_qty}</td>
	      <td>${foodODVO.food_od_stotal}</td>
	      <td>${foodODVO.food_od_rate}</td>
	      <td>
	      	<form action="<%=request.getContextPath()%>/foodOrDetail/foodOrDetail.do" method="post">
	      		<input type="hidden" name="food_or_ID" value="${foodODVO.food_or_ID}">
	      		<input type="hidden" name="food_sup_ID" value="${foodSupVO.food_sup_ID}">
	      		<input type="hidden" name="food_ID" value="${foodODVO.food_ID}">
	      		<input type="hidden" name="food_od_status" value="${(foodODVO.food_od_status eq 'd0')?'d1':''}">
	      		<input type="hidden" name="action" value="fsUpdateFoodODstatus">
	      		<button type="button" id="checkSBtn${foodODIn.index}" class="checkToSend" ${(foodODVO.food_od_status eq 'd0')?'':'disabled'}>
	      			<c:choose>
	      				<c:when test="${foodODVO.food_od_status eq 'd2'}">
	      					 已到食材中心
	      				</c:when>
	      				<c:otherwise>
	      					${foodODStatusMap[foodODVO.food_od_status]}
	      				</c:otherwise>
	      			</c:choose>
	      		</button>
	      	</form>
	      </td>
	    </tr>
	    </c:forEach>
	</table>
    
   </div>
    </section> 
    <br>
	<br>
	<br>
	<br>
    <!-- ##### Contact Area End #####-->
	<script>
		$(document).ready(function(){
			$(".checkToSend").click(function(even){
				let btnID = even.target.id;
				$.ajax({
					type:"POST",
					url:"<%=request.getContextPath()%>/foodOrDetail/foodOrDetail.do",
					data:$(this).parent("form").serialize(),
					dataType:"json",
					success: function(data){
						console.log(data.food_od_status);
						if(data.food_od_status==="d1"){
							$("#"+btnID).attr('disabled','true');
							$("#"+btnID).text("已出貨");
						}
					},
					error:function(errdata){
						alert("ajax 錯誤" + errdata);
						console.log(errdata);
					}
				});
			});	
		});
		
		
		
	</script>

			
		<!-- ##### Contact Area End #####-->




	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>