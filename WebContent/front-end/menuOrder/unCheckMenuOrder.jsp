<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menuOrder.model.*"%>
<%@ page import="com.cust.model.*"%>
<%@ page import="com.chef.model.*"%>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<jsp:useBean id="menuSvc" class="com.menu.model.MenuService"/>

<%
	MenuOrderService menuOrderSvc = new MenuOrderService();
	ChefVO chefVO = (ChefVO)session.getAttribute("chefVO"); 
	String chef_ID = chefVO.getChef_ID();
	List<MenuOrderVO> listAll = menuOrderSvc.getUnCheck(chef_ID);
	pageContext.setAttribute("listAll", listAll);
%>

<html>
<head>
<title>未審核訂單</title>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerChef.jsp" flush="true" />
	<!-- #####  Start ##### -->
		<div class="container-fliud">
			<div class="row">
				<div class="col-2"></div>
				<div class="col-8" style="font-family:Microsoft JhengHei;">
					<%@ include file="page1.file"%>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">訂單編號</th>
								<th scope="col">套餐名稱</th>
								<th scope="col">套餐價格</th>
								<th scope="col">訂單日期</th>
								<th scope="col">預約日期</th>
								<th scope="col">顧客姓名</th>
								<th colspan="2">審核訂單</th>
							</tr>
						</thead>
						<tbody>
							
							<c:forEach var="menuOrderVO" items="${listAll}" begin="<%=pageIndex %>" end="<%=pageIndex+rowsPerPage-1 %>">
								<tr>
									<th scope="row">${menuOrderVO.menu_od_ID}</th>
									<td>${menuSvc.getOneMenu(menuOrderVO.menu_ID).menu_name}</td>
									<td style="width:100px;">$${menuSvc.getOneMenu(menuOrderVO.menu_ID).menu_price}</td>
									<td>${menuOrderVO.menu_od_start}</td>
									<td>${menuOrderVO.menu_od_book}</td>
									<td style="width:100px;">${custSvc.getOneCust(menuOrderVO.cust_ID).cust_name}</td>
									<td>
										<form method="post"
											action="<%=request.getContextPath()%>/menuOrder/menuOrder.do">
											<input type="submit" class="btn btn-success" value="確認訂單"> 
											<input type="hidden" name="menu_od_ID" value="${menuOrderVO.menu_od_ID}"> 
											<input type="hidden" name="menu_od_status" value="g1"> 
											<input type="hidden" name="action" value="getOneForCheck">
										</form>
									</td>
									<td>
										<form method="post"
											action="<%=request.getContextPath()%>/menuOrder/menuOrder.do">
											<input type="submit" class="btn btn-danger" value="退回訂單"> 
											<input type="hidden" name="menu_od_ID" value="${menuOrderVO.menu_od_ID}"> 
											<input type="hidden" name="menu_od_status" value="g2"> 
											<input type="hidden" name="action" value="getOneForCheck">
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="page2.file"%>
				</div>
				<div class="col-2"></div>
			</div>
		</div>
	<!-- #####  End #####-->
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>