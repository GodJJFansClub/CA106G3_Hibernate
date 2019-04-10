<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menuDish.model.*"%>
<jsp:useBean id="menuSvc" scope="page"
	class="com.menu.model.MenuService" />
<jsp:useBean id="dishSvc" scope="page"
	class="com.dish.model.DishService" />
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MenuDishService menuDishSvc = new MenuDishService();
    String menu_ID = request.getAttribute("menu_ID").toString();
    List<MenuDishVO> list = menuDishSvc.getAllByMenuID(menu_ID);
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有套餐菜色</title>


</head>
<body>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/dishFoodMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>


				<div class="col">
					<div class="card">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr align='center'>

										<td colspan="4">套餐名稱:<font color="red" size="5">${menuSvc.getOneMenu(menuVO.menu_ID).menu_name}</font></td>

									</tr>
								</thead>


								<c:forEach var="menuDishVO" items="${list}">
									<tr align='center'>

										<th scope="row-4">${dishSvc.getOneDish(menuDishVO.dish_ID).dish_name}</th>

									</tr>
								</c:forEach>

								<td colspan="3" align='center'>
									<h4>
										<a
											href="<%=request.getContextPath()%>/back-end/menu/listAllMenu.jsp">回首頁</a>
									</h4>
								</td>
								</tr>
							</table>

						</div>
					</div>
				</div>
			
			</div>
		</div>
	</div>
</body>
</html>