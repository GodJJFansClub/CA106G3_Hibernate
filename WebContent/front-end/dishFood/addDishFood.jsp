<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dishFood.model.*,com.food.model.*"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>

<jsp:useBean id="dishFoodSvc" scope="page" class="com.dishFood.model.DishFoodService" />
<jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService" />
<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService" />
<jsp:useBean id="gson" class="com.google.gson.Gson"/>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章新增 - addForumArt.jsp</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<style>
	
</style>

</head>
<body bgcolor='white'>

	<div class="container-fluid">
		<div class="row">
			<div class="col-6">
				<table id="table-1" style="background-color:blue;">
					<tr>
						<td>
							<h3>菜色食材新增 - addDishFood.jsp</h3>
						</td>
						<td>
							<h4>
								<a href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp">回首頁</a>
							</h4>
						</td>
					</tr>
				</table>

				<h3>資料新增:</h3>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/dishFood/dishFood.do">
					<table>
						<tr>
							<td>選擇菜色:</td>
							
							<td>
								<select size="1" name="dish_ID">
									<c:forEach var="dishFoodVO" items="${dishSvc.all}" >
										<option value="${dishFoodVO.dish_ID}">${dishSvc.getOneDish(dishFoodVO.dish_ID).dish_name}
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
						
					<h3>選擇食材:</h3>	
						<div class="container">
							<div class="row">
							<c:forEach var="food_type" items="${foodTypeMap}">
								<div class="col-sm ${food_type.key}">${food_type.value}</div>
							</c:forEach>
							</div>
						</div>
							
						<div id="foodList" class="container">
							
						</div>
					<input type="hidden" name="action" value="AllFood">
					<input type="submit" value="送出"><br>
				</FORM>
				
			</div>
			<div class="col-6" style="backgoung-color:gray;">Right</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
	<script>
		
		<%	
			Map<String, String> foodTypeMap = (Map<String, String>)application.getAttribute("foodTypeMap");
			Set<String> food_type_IDs = foodTypeMap.keySet();
			List<Set<FoodVO>> foodCatogry = new ArrayList<>();
			
			
			for(String foodKey:food_type_IDs){
				foodCatogry.add(foodSvc.getFoodsByFood_type_ID(foodKey));
			}
			
		%>
		let foodJsonObj = <%=gson.toJson(foodCatogry)%>;
		let maxLength = 0;
		jQuery.each(foodJsonObj,function(foodProper){
			if(foodJsonObj[foodProper].length > maxLength){
				maxLength = foodJsonObj[foodProper].length;
			}
		});
		console.log(foodJsonObj);
		for(let i = 0; i < maxLength; i++){
			let divE = document.createElement('div');
			divE.setAttribute("class", "row");
			
			jQuery.each(foodJsonObj,function(foodProper){
				let divCE = document.createElement('div');
				divCE.setAttribute("class", "col");
				
				if(i < foodJsonObj[foodProper].length){
					let chE = document.createElement('input');
					chE.setAttribute("value", foodJsonObj[foodProper][i].food_ID);
					chE.setAttribute("type", "checkbox");
					chE.setAttribute("name", "food_ID");
					
					divCE.appendChild(chE);
					divCE.appendChild(document.createTextNode(foodJsonObj[foodProper][i].food_name));
				}
				
				divE.appendChild(divCE);
			});
			$("#foodList").append(divE);
		}
		
		
		
	</script>
	

</body>
</html>