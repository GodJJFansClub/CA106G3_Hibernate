<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<%@ page import="com.chefDish.model.*"%>

<%	
	ChefDishService chefDishSvc = new ChefDishService();
	ChefVO chefVO = (ChefVO)session.getAttribute("chefVO"); 
	String chef_ID = chefVO.getChef_ID();
	List<ChefDishVO> listAll = chefDishSvc.getAllByChefID(chef_ID);
	session.setAttribute("listAll", listAll);
%>

<html>
<head>
<title>擅長菜色管理</title>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerChef.jsp" flush="true" />
	<!-- #####  Start ##### -->
		<div class="container-fliud">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-3" style="font-family:Microsoft JhengHei;">
					<form method="post" action="<%=request.getContextPath() %>/chefDish/chefDish.do">
						<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService" />
						<div class="form-group">
						    <label for="dish_ID">請選擇欲新增擅長菜色</label>
						    <select class="form-control" name="dish_ID" id="dish_ID">
								<c:forEach var="dishVO" items="${dishSvc.all}">
									<option value="${dishVO.dish_ID}">${dishVO.dish_name}
								</c:forEach>
						    </select>
						</div>
						<input type="hidden" class="form-control" name="chef_ID" value="${chefVO.chef_ID}" />
						<input type="hidden" name="action" value="insert">
						<input type="submit" class="btn btn-success btn-lg btn-block" value="新增菜色">
					</form>
					<%--Error Message --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red; font-size: 20px;">Error:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
				
				<div class="col-7" style="font-family:Microsoft JhengHei;">
					<%@ include file="page1.file"%>
					<div class="container-fliud">
						<div class="row">
							<div class="col-6">
								<h5 style="font-family:Microsoft JhengHei;">未審核菜色</h5>
								<table class="table">
									<thead>
										<tr>
											<th scope="col">菜色名稱</th>
											<th scope="col">狀態</th>
											<th scope="col">刪除菜色</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="chefDishVO" items="${listAll}" begin="<%=pageIndex %>" end="<%=pageIndex+rowsPerPage-1 %>">
											<c:if test="${chefDishVO.chef_dish_status=='d0'}">
											<tr>
												<th scope="row">${chefDishVO.dish_name}</th>
												<td>
													<c:if test="${chefDishVO.chef_dish_status=='d0'}">
														<img src="<%=request.getContextPath() %>/front-end/chefDish/img/uncheck.png" style="width:35px;height:35px;">
													</c:if>
												</td>
												<td>
													<form method="post" action="<%=request.getContextPath()%>/chefDish/chefDish.do">
														<input type="submit" class="btn btn-danger" value="刪除"> 
														<input type="hidden" name="chef_ID" value="${chefDishVO.chef_ID}"> 
														<input type="hidden" name="dish_ID" value="${chefDishVO.dish_ID}">
														<input type="hidden" name="action" value="delete">
													</form>
												</td>
											</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="col-6">
								<h5 style="font-family:Microsoft JhengHei;">已審核菜色</h5>
								<table class="table">
									<thead>
										<tr>
											<th scope="col">菜色名稱</th>
											<th scope="col">狀態</th>
											<th scope="col">刪除菜色</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="chefDishVO" items="${listAll}" begin="<%=pageIndex %>" end="<%=pageIndex+rowsPerPage-1 %>">
											<c:if test="${chefDishVO.chef_dish_status=='d1'}">
												<tr>
													<th scope="row">${chefDishVO.dish_name}</th>
													<td>
														<c:if test="${chefDishVO.chef_dish_status=='d1'}">
															<img src="<%=request.getContextPath() %>/front-end/chefDish/img/check.png" style="width:35px;height:35px;">
														</c:if>
													</td>
													<td>
														<form method="post" action="<%=request.getContextPath()%>/chefDish/chefDish.do">
															<input type="submit" class="btn btn-danger" value="刪除"> 
															<input type="hidden" name="chef_ID" value="${chefDishVO.chef_ID}"> 
															<input type="hidden" name="dish_ID" value="${chefDishVO.dish_ID}">
															<input type="hidden" name="action" value="delete">
														</form>
													</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<%@ include file="page2.file"%>
				</div>
				<div class="col-1"></div>
			</div>
		</div>
	<!-- #####  End #####-->
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>