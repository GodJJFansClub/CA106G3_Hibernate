<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.foodMall.model.*" %>
<%@ page import="com.foodSup.model.FoodSupVO" %>
<%@ page import="java.util.Map" %>
<jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService" />
<%
	FoodMallVO foodMallVO = (FoodMallVO) request.getAttribute("foodMallVO");
%>

<html>
<head>

<title>新增食材商品</title>


<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
	font-size: 22px;
}

table, th, td {
	border: 0px solid #CCCCFF;
	font-size: 22px;
}

th, td {
	padding: 1px;
	width: 1px;
	font-size: 22px;
}
</style>




</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerFoodSup.jsp" flush="true" />
	<!-- ##### Breadcrumb Area Start ##### -->
	<br>
	
	
	<!-- ##### Breadcrumb Area End ##### -->

	<!-- ##### Contact Area Start #####-->
	<section class="contact-area section-padding-100">
		<section class="contact-area section-padding-100">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<!-- Section Heading -->
						<div class="section-heading text-center wow fadeInUp"
							data-wow-delay="100ms">

							<h2 style="font-weight: bold">新增食材</h2>
							<img
								src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/x.png"
								alt="">



							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
		
		<div class="row justify-content-center">
			<div class="col-12 col-lg-8">
									<!-- Contact Form -->
			<div class="contact-form-area text-center">				
		<form id="magicRes" method="post" action="<%=request.getContextPath()%>/foodMall/foodMall.do" name="form1" enctype="multipart/form-data">
			<table>
			<tr><td>${errorMsgs.excMsgs}</td></tr>
			<tr>
				<td>標題:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" id="food_m_name" name="food_m_name" size="20" 
					 value="<%= (foodMallVO==null)? "" : foodMallVO.getFood_m_name()%>" class="form-control wow fadeInUp" data-wow-delay="100ms"/></td>
					 <td><font color=red>${errorMsgs.em_name}</font></td>
			</tr>
			<tr>
				<td>商品價格:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" id="food_m_price" name="food_m_price" size="7"
					 value="<%= (foodMallVO==null)? "" : foodMallVO.getFood_m_price()%>" class="form-control wow fadeInUp" data-wow-delay="100ms"/></td>
					 <td><font color=red>${errorMsgs.em_price}</font></td>
			</tr>
			<tr>
				<td>單位:<font color=red><b>*</b></font></td>
				<td>
					<c:forEach var="foodUnit" items="${foodUnitMap}" varStatus="ds">
						<input type="radio" id="rad${ds.index}" name="food_m_unit" value="${foodUnit.key}"
							${(foodUnit.key == foodMallVO.food_m_unit)?'checked':''} style="width:15px;height:15px">${foodUnit.value}
					</c:forEach>
				</td><td><font color=red>${errorMsgs.em_unit}</font></td>
			</tr>
			<tr>
				<td>產地:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" id="food_m_place" name="food_m_place" size="7"
					 value="<%= (foodMallVO==null)? "" : foodMallVO.getFood_m_place()%>" class="form-control wow fadeInUp" data-wow-delay="100ms"/></td>
				<td><font color=red>${errorMsgs.em_place}</font></td>
			</tr>
			<tr>
				<td>請選擇食材種類:<font color=red><b>*</b></font></td>
				<td>
					<select id="foodTypeSelect" size="1" name="food_type_ID">
						<option value="-1">請選擇</option>
					<c:forEach var="foodType" items="${foodTypeMap}">
						<option value="${foodType.key}" ${(foodType.key eq food_type_ID)?'selected':''}>${foodType.value}
					</c:forEach>
					</select>
				</td>
				<td>${errorMsgs.em_foodType}</td>
			</tr>
			<tr>
				<td>食材:<font color=red><b>*</b></font></td>
				<td>
					<select id="foodSelect" size="1" name="food_ID">
						<option value="-1"></option>
						<c:if test="${not empty food_type_ID}">
							<c:forEach var="typeFoodVO" items="${foodSvc.getFoodsByFood_type_ID(food_type_ID)}">
								<option value="${typeFoodVO.food_ID}" ${(typeFoodVO.food_ID eq foodMallVO.food_ID)?'selected':''}>${typeFoodVO.food_name}</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
				<td><font color=red>${errorMsgs.em_foodID}</font></td>
			</tr>
			<tr>
				<td>商品照片:<font color=red><b>*</b></font></td>
				<td><input type="file" name="food_m_pic"  /></td>
				<td><font color=red>${errorMsgs.em_pic}</font></td>
			</tr>
			<tr>
				<td>介紹:</td>
				<td>
					<textarea id="food_m_resume"  style="height:600px;"  class="form-control" name="food_m_resume">${empty foodMallVO.food_m_resume ? "" : foodMallVO.food_m_resume}</textarea>
					
				</td>
			</tr>
			</table>
			<input type="hidden" name="food_sup_ID" value="${foodSupVO.food_sup_ID}">
			<input type="hidden" name="action" value="insert">
			<img src="/CA106G3/images/x.png" height="20" width="20" onclick="idwrite(this)">
			<button type="submit" class="btn pixel-btn wow fadeInUp"
				data-wow-delay="300ms">送出</button>
		</form>
								
		<!-- ##### Contact Area End #####-->
		
			</div>
			</div>
		</div>


	
		<img id="preView">
		<script>
			$(document).ready(
				function(){
					$(":file").change(
						function(){
							if(this.files && this.files[0]){
								let reader = new FileReader();
								reader.onload = function(e){
									$('#preView').attr('src', e.target.result);
								}
								reader.readAsDataURL(this.files[0]);
							}
						}
					);
					
					$("#foodTypeSelect").change(function(){
						$.ajax({
							type:"POST",
							url:"<%=request.getContextPath()%>/foodMall/foodMall.do",
							data:{"action":"getSelFoodFSAdd","food_type_ID":$("#foodTypeSelect").val()},
							dataType:"json",
							success: function(data){
								clearFoodSelect();
								let foodOpt;
								jQuery.each(data, function(inx,mval){
									foodOpt += 
										'<option value="'+ mval.food_ID +'">'+ mval.food_name +'</option>';

								})
								$("#foodSelect").append(foodOpt);
								console.log(data);
							},
							error: function(data){
								alert(ajax錯誤);
								console.log(data);
							}
						});
					});
				}
				
				
			);
			
			function clearFoodSelect(){
				$('#foodSelect').empty();
				$('#foodSelect').append("<option value='-1'>請選擇</option>");
			}
			
			function idwrite(eve){
				$("#magicRes").find("#food_m_name").val("好吃的烏魚子");
				$("#magicRes").find("#food_m_price").val("600");
				$("#rad6").attr("checked",true);
				$("#magicRes").find("#food_m_place").val("南極");
				$("#magicRes").find("#food_m_resume").val(
				"在南極的深海底下有一個大鳳梨，也有很多的帝王蟹，但是只有一支是老闆。"
				+ "蟹老闆每賣出一個烏魚子，就會有一隻企鵝沒辦法游泳。沒有買賣就不會有傷害!!");
				
			}
		</script>

			</div>
		</section>
	</section>
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>