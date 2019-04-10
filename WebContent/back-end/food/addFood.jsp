<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.food.model.*"%>


<% FoodVO foodVO = (FoodVO) request.getAttribute("foodVO"); %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增食材</title>
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
				<%-- 錯誤列表 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/food/food.do" name="form1">
					<table>
						<tr>
								 
							<td>食材名稱:${foodVO.food_name}</td>
							<td><input type="TEXT" name="food_name" size="15" 
								 <%--value="<%= (foodVO==null)? "大白菜" : foodVO.getFood_name()%>" /></td> --%>
								 value=${empty foodVO.food_name ? "大白菜":foodVO.food_name} /></td>
						</tr>
						<tr>
							<td>食材種類:</td>
							<td>
								<select size="1" name="food_type_ID">
									<c:forEach var="food_type" items="${foodTypeMap}">
										<option value="${food_type.key}" ${(foodVO.food_type_ID == food_type.key)?'selected':''}>${food_type.value}
									</c:forEach>
								</select>
							</td>
						</tr>
					
					
					</table>
					<br>
					<input type="hidden" name="action" value="insert">
					<input type="submit" class="btn btn-secondary" value="送出新增">
				</FORM>

			</div>
		</div>
	</div>
</body>
</html>