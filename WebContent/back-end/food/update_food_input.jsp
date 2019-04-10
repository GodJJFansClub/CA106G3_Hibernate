<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.food.model.*"%>

<%
	FoodVO foodVO = (FoodVO) request.getAttribute("foodVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>食材資料修改 - update_food_input.jsp</title>
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
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<form method="post"
					action="<%=request.getContextPath()%>/food/food.do" name="form1">
					<table align="center">
						<tr>
							<td>食材編號:<font color=red><b>*</b></font></td>
							<td>${foodVO.food_ID}</td>
						</tr>
						<tr>
							<td>食材名稱:</td>
							<td><input type="TEXT" name="food_name" size="20"
								value="${foodVO.food_name}" /></td>
						
						</tr>
						
						<tr>
							<td>食材種類:</td>
							<td>
								<select size="1" name="food_type_ID">
									<c:forEach var="foodType" items="${foodTypeMap}">
										<option value="${foodType.key}"
											${(foodVO.food_type_ID == foodType.key)?'selected':''}>${foodType.value}
									</c:forEach>
								</select>
							</td>
						</tr>
						
					</table>
					<br> <input type="hidden" name="action" value="update">
					<input type="hidden" name="food_ID" value="${foodVO.food_ID}">
					<input type="submit" value="送出修改">
				</form>

			</div>
		</div>
	</div>
</body>
</html>