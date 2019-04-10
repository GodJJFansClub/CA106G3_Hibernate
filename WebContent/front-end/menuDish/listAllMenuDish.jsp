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
    List<MenuDishVO> list = menuDishSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有套餐菜色</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>所有套餐菜色</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>

			</c:forEach>

			<li><a href='addMenuDish.jsp'>Add</a> a new Dish.</li>
		</ul>

	</c:if>

	<table>

		<tr>
			<th>套餐名稱</th>
			<th>菜色編號</th>
			<th>菜色名稱</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="menuDishVO" items="${list}" varStatus="s"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>


				<td>${menuSvc.getOneMenu(menuDishVO.menu_ID).menu_name}</td>
				<td>${dishSvc.getOneDish(menuDishVO.dish_ID).dish_name}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/menuDish/menuDish.do"
						style="margin-bottom: 0px;">
						<A
							href="<%=request.getContextPath()%>/menuDish/menuDish.do?dish_ID=${menuDishVO.dish_ID}&action=getAllDish">${dishSvc.getOneDish(menuDishVO.dish_ID).dish_name}</a>
						<input type="hidden" name="requestURL"
							value="<%=request.getServletPath()%>"> <input
							type="hidden" name="whichPage" value="<%=whichPage%>">
					</FORM>
				</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/menuDish/menuDish.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="menu_ID" value="${menuDishVO.menu_ID}"> <input
							type="hidden" name="dish_ID" value="${menuDishVO.dish_ID}">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>

	</table>
	<%@ include file="page2.file"%>

	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<div class="modal-header">

						<h3 class="modal-title" id="myModalLabel">食神來了</h3>
					</div>

					<div class="modal-body">
						<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
						<jsp:include page="/back-end/dish/listOneDish.jsp" />
						<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
					</div>

				</div>
			</div>
		</div>

		<script>
    		 $("#basicModal").modal({show: true});
        </script>
	</c:if>

</body>
</html>