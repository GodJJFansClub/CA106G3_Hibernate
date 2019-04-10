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
<style>

/* :: 12.0 Blog Area CSS */
.breadcrumb-area1 {
	position: relative;
	z-index: 1;
	width: 100%;
	height: 380px;
	padding: 0 35px;
}

@media only screen and (min-width: 992px) and (max-width: 1199px) {
	.breadcrumb-area1 {
		height: 300px;
	}
}

@media only screen and (min-width: 768px) and (max-width: 991px) {
	.breadcrumb-area1 {
		height: 280px;
	}
}

@media only screen and (max-width: 767px) {
	.breadcrumb-area1 {
		height: 250px;
	}
}

.breadcrumb-area1::after {
	content: '';
	position: absolute;
	width: 100%;
	height: 100%;
	z-index: -1;
	top: 0;
	left: 0;
	background-color: rgba(163, 163, 163, 0.3);
}

.breadcrumb-area1 .breadcrumb-content {
	text-align: center;
}

.breadcrumb-area1 .breadcrumb-content h2 {
	color: #ffffff;
	font-size: 60px;
	font-weight: 600;
	text-transform: capitalize;
	margin-bottom: 0;
	display: inline-block;
}

@media only screen and (min-width: 992px) and (max-width: 1199px) {
	.breadcrumb-area1 .breadcrumb-content h2 {
		font-size: 42px;
	}
}

@media only screen and (min-width: 768px) and (max-width: 991px) {
	.breadcrumb-area1 .breadcrumb-content h2 {
		font-size: 36px;
	}
}

@media only screen and (max-width: 767px) {
	.breadcrumb-area1 .breadcrumb-content h2 {
		font-size: 24px;
	}
}
</style>

</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<!-- ##### Breadcrumb Area Start ##### -->
	<br>
	<br>
	<br>
	<br>
	<section class="breadcrumb-area1 bg-img bg-overlay jarallax"
		style="background-image: url(<%=request.getContextPath()%>/froTempl/temp/img/bg-img/foodSupPic.jpg);">
		<div class="container-fluid h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="breadcrumb-content">
						<h2 style="font-weight: bold">食材供應商專區-食材管理</h2>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- ##### Breadcrumb Area End ##### -->
	
	
	
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
				<th>更改狀態</th>
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
						<td><form method="post" action="<%=request.getContextPath()%>/foodMall/foodMall.do">
								<input type="hidden" name="food_ID" value="${foodMallVO.food_ID}">
								<input type="hidden" name="food_sup_ID" value="${foodMallVO.food_sup_ID}">
							<c:choose>
								<c:when test="${foodMallVO.food_m_status eq 'p1' || foodMallVO.food_m_status eq 'p3'}">
									<button id="staBtn${ma.index}" class="manaMallBtn" value="p4" type="button">上架</button>
									<input type="hidden" id="sta${ma.index}" name="food_m_status" value="${foodMallVO.food_m_status}">
								</c:when>
								<c:when test="${foodMallVO.food_m_status eq 'p4'}">
									<button id="staBtn${ma.index}" class="manaMallBtn" value="p3" type="button">下架</button>
									<input type="hidden" id="sta${ma.index}" name="food_m_status" value="${foodMallVO.food_m_status}">
								</c:when>
								<c:otherwise>
									${mallStatusMap[foodMallVO.food_m_status]}
								</c:otherwise>
							</c:choose>
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
				
	</section>
    <!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
	<script>
		$(document).ready(function(){
			
			$(".manaMallBtn").click(manaMallEvt);
		});
		function manaMallEvt(data){
			let manaAction;
			let btnID = data.target.id;
			let staNumber = $("#"+btnID).next().attr("id");
			console.log(staNumber);
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/foodMall/foodMall.do",
				data:crtQryStrfS("foodSupChStat", $(this).parent("form").serializeArray()),
				dataType:"json",
				success:function (data){
					if(data.food_m_status == "p3"){
						$("#"+btnID).text("下架");
						$("#"+staNumber).val("p3");
					} else if(data.food_m_status == "p4"){
						$("#"+btnID).text("上架");
						$("#"+staNumber).val("p4");
					} else {
						console.log(data);
					}
				},
				error:function (data){
					console.log(data);
				}
			});
		}
		
		function crtQryStrfS(action, formInputArr){
			let queryString = {"action":action};
			let leng = formInputArr.length;
			for(let i = 0; i < leng; i++){
				queryString[formInputArr[i].name] = formInputArr[i].value;
			}
			return queryString;
		}
	</script>

										</form>
									</div>
								</div>
							</div>

						</div>
		</section>
		<!-- ##### Contact Area End #####-->



	</section>
	
</body>
</html>