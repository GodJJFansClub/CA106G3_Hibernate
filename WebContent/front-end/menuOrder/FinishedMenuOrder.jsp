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
	List<MenuOrderVO> listAll = menuOrderSvc.getAllFinished(chef_ID);
	pageContext.setAttribute("listAll", listAll);
%>

<html>
<head>
<title>未完成訂單</title>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerChef.jsp" flush="true" />
	<!-- #####  Start ##### -->
		<div class="container-fliud">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-10" style="font-family:Microsoft JhengHei;">
					<%@ include file="page1.file"%>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">訂單編號</th>
								<th scope="col">套餐名稱</th>
								<th scope="col">套餐價格</th>
								<th scope="col">訂單狀態</th>
								<th scope="col">預約日期</th>
								<th scope="col">完成日期</th>
								<th scope="col">訂單評價</th>
								<th scope="col">訂單留言</th>
								<th scope="col">顧客姓名</th>
							</tr>
						</thead>
						<tbody>
							
							<c:forEach var="menuOrderVO" items="${listAll}" begin="<%=pageIndex %>" end="<%=pageIndex+rowsPerPage-1 %>">
								<tr>
									<th scope="row">${menuOrderVO.menu_od_ID}</th>
									<td>${menuSvc.getOneMenu(menuOrderVO.menu_ID).menu_name}</td>
									<td>$${menuSvc.getOneMenu(menuOrderVO.menu_ID).menu_price}</td>
									<td>
										<c:if test="${menuOrderVO.menu_od_status=='g5'}">已完成</c:if>
									</td>
									<td>${menuOrderVO.menu_od_book}</td>
									<td>${menuOrderVO.menu_od_end}</td>
									<td style="width:150px;">
										<c:if test="${menuOrderVO.menu_od_rate >= 1 }" >
									        <c:forEach var="i" begin="1" end="${menuOrderVO.menu_od_rate}" varStatus="loop">
									            <c:out escapeXml="false" value="<img src='/CA106G3/images/star.png' style='width:20px;height:20px;'>" />
									        </c:forEach>
									    </c:if>
									</td>
									<td style="width:250px;">${menuOrderVO.menu_od_msg}</td>
									<td>${custSvc.getOneCust(menuOrderVO.cust_ID).cust_name}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="page2.file"%>
				</div>
				<div class="col-1"></div>
			</div>
		</div>
	<!-- #####  End #####-->
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>