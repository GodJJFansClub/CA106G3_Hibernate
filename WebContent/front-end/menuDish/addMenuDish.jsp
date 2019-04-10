<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menuDish.model.*"%>
<jsp:useBean id="menuSvc" scope="page"
	class="com.menu.model.MenuService" />
<jsp:useBean id="dishSvc" scope="page"
	class="com.dish.model.DishService" />
<%
  MenuDishVO menuDishVO = (MenuDishVO) request.getAttribute("MenuDishVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章留言新增</title>

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
			<div class="col-8">
				<table id="table-1" style="background-color: blue;">
					<tr>
						<td>
							<h3>套餐菜色新增 - addMenuDish.jsp</h3>
						</td>
						<td>
							<h4>
								<a
									href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp">回首頁</a>
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
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/menuDish/menuDish.do">
					<table>
						<tr>
							<th>選擇套餐:</th>
							<td><select size="1" name="menu_ID">
									<c:forEach var="menuDishVO" items="${menuSvc.all}">
										<option value="${menuDishVO.menu_ID}">${menuSvc.getOneMenu(menuDishVO.menu_ID).menu_name}
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>選擇菜色:</th>
							<td><c:forEach var="menuDishVO" items="${dishSvc.all}"
									varStatus="sc">
									<input type="checkbox" name="dish_ID"
										value="${menuDishVO.dish_ID}">${dishSvc.getOneDish(menuDishVO.dish_ID).dish_name} 
								<c:if test="${sc.count%5 == 0 }">
										<br>
									</c:if>

								</c:forEach></td>

						</tr>


					</table>
					<input type="hidden" name="action" value="insert"> <input
						type="submit" value="送出"><br>
				</FORM>
			</div>
			<div class="col-4" style="backgoung-color: gray;">Right</div>
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


</body>
</html>