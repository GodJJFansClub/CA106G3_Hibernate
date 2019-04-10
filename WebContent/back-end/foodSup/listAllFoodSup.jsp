<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="foodSupSvc" scope="page" class="com.foodSup.model.FoodSupService"/>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<html>
<head>
</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/foodSupMana.jsp" flush="true"/>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>
	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">食材供應商資料</font></div>
	<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
				<font color='red'>請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				
				<div class="col">
					<div class="card">

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th scope="col">食材供應商編號</th>
										<th scope="col">食材供應商名稱</th>
										<th scope="col">聯絡電話</th>
										<th scope="col">狀態</th>
										<th scope="col">更改狀態</th>
										
									</tr>
								</thead>
				
					<c:forEach var="foodSupVO" items="${foodSupSvc.all}">
						<tr class="foodSupEntiy">
							<th scope="row">${foodSupVO.food_sup_ID}</th>
							<th scope="row">${foodSupVO.food_sup_name}</th>
							<th scope="row">${foodSupVO.food_sup_tel}</th>
							<th scope="row">
							<c:choose>
								<c:when test="${foodSupVO.food_sup_status eq 's0'}">
									<span class="label label-danger label-rounded">
									${foodSupStatusMap[foodSupVO.food_sup_status]}
									</span>
								</c:when>
								<c:otherwise>
									
										${foodSupStatusMap[foodSupVO.food_sup_status]}
									
								</c:otherwise>
							</c:choose>
						</th>
							<th scope="row">
							<form method="post" action="<%=request.getContextPath()%>/foodSup/foodSup.do">
								<button id="update" class="btn btn-secondary">更改狀態</button>
								<input type="hidden" id="food_sup_ID" name="food_sup_ID" value="${foodSupVO.food_sup_ID}">
								<input type="hidden" id="action" name="action">
								<input type="hidden" id="requestURL" name="requestURL" value="<%=request.getServletPath()%>">
							</form>
							</th>
						</tr>
					</c:forEach>
				</table>
				</div>
					</div>
				</div>
				<%if (request.getAttribute("listFoodMalls_ByFood_sup_ID") != null ) {%>
					<jsp:include page="/back-end/foodSup/listFoodMalls_ByFood_sup_ID.jsp" flush="true" />
				<%}%>

			</div>
		</div>
	</div>
	
	<script>
	
		$(document).ready(function(){
			$(".foodSupEntiy").click(function(evt){
				let foodForm = $(this).find("form");
				if(evt.target.id != "update") { 
					foodForm.children("#requestURL").val("<%=request.getServletPath()%>");
					foodForm.children("#action").val("listFoodMalls_ByFood_sup_ID");
				}else{
					foodForm.children("#action").val("getOne_For_Update");	
				}
				foodForm.submit();
			});
		});
	</script>
</body>
</html>