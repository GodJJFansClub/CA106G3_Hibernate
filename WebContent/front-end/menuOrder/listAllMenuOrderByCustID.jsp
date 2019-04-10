<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menuOrder.model.*"%>
<%@ page import="com.cust.model.*"%>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<jsp:useBean id="menuSvc" class="com.menu.model.MenuService"/>

<%
	MenuOrderService menuOrderSvc = new MenuOrderService();
	CustVO custVO =(CustVO) session.getAttribute("custVO");
	String cust_ID = custVO.getCust_ID();
	List<MenuOrderVO> listAll = menuOrderSvc.getAllByCustID(cust_ID);
	pageContext.setAttribute("listAll", listAll);
%>

<html>
<title>List_All_Menu_Order_By_Cust_ID</title>
<head>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
<jsp:include page="/froTempl/headerCust.jsp" flush="true" />
	 <!-- ##### Contact Area Start #####-->
    <section class="contact-area section-padding-100">
	    <%--Error Message--%>
		<c:if test="${not empty errorMsgs} }">
			<font style="color: red; font-size: 30px;">Error</font>
			<ul>
				<c:forEach var="errMsgs" items="${errorMsgs}">
					<li style="color: red;">${errMsgs}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="container-fliud justify-content-center">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-10">
					<%@ include file="page1.file"%>
					<table class="table">
					  <thead>
					    <tr>
					    	<th scope="col" style="width:100px;">訂單編號</th>
					    	<th scope="col" style="width:100px;">套餐名稱</th>
					    	<th scope="col" style="width:100px;">主廚</th>
							<th scope="col" style="width:120px;">訂單狀態</th>
							<th scope="col" >下訂時間</th>
							<th scope="col" >預約時間</th>
							<th scope="col" >完成時間</th>
							<th scope="col" style="width:120px;">訂單評價</th>
							<th scope="col" style="width:120px;">訂單留言</th>
<!-- 							<th scope="col" style="width:100px;">給評</th> -->
					    </tr>
					  </thead>
					  <tbody>
						<c:forEach var="menuOrderVO" items="${listAll}" begin="<%=pageIndex %>" end="<%=pageIndex+rowsPerPage-1 %>">
							<tr>
								<th scope="row">${menuOrderVO.menu_od_ID}</th>
								<td>${menuSvc.getOneMenu(menuOrderVO.menu_ID).menu_name}</td>
								<td>${custSvc.getOneCust(menuOrderVO.chef_ID).cust_name}</td>
								<td>
									<c:if test="${menuOrderVO.menu_od_status=='g0'}">未審核</c:if>
									<c:if test="${menuOrderVO.menu_od_status=='g1'}">審核未通過</c:if>
									<c:if test="${menuOrderVO.menu_od_status=='g2'}">未付款</c:if>
									<c:if test="${menuOrderVO.menu_od_status=='g3'}">已付款</c:if>
									<c:if test="${menuOrderVO.menu_od_status=='g4'}">主廚到府</c:if>
									<c:if test="${menuOrderVO.menu_od_status=='g5'}">訂單完成</c:if>
								</td>
								<td style="width:120px;">${menuOrderVO.menu_od_start}</td>
								<td style="width:120px;">${menuOrderVO.menu_od_book}</td>
								<td style="width:120px;">${menuOrderVO.menu_od_end}</td>
								<td>
									<c:if test="${menuOrderVO.menu_od_rate >= 1 }" >
										<c:forEach var="i" begin="1" end="${menuOrderVO.menu_od_rate}" varStatus="loop">
											<c:out escapeXml="false" value="<img src='/CA106G3/images/star.png' style='width:20px;height:20px;'>" />
										</c:forEach>
									</c:if>
									<c:if test="${menuOrderVO.menu_od_rate=='0'}"></c:if></td>
								<td>
								${menuOrderVO.menu_od_msg}
								
								</td>
<!-- 								<td> -->
<%-- 								<c:if test="${(menuOrderVO.menu_od_end!=null)&&(menuOrderVO.menu_od_rate==0)}"> --%>
<%-- 									<form method="post" action="<%=request.getContextPath()%>/menuOrder/menuOrder.do"> --%>
<%-- 										<input type="submit" class="btn btn-warning" value="評價"> <input type="hidden" name="menu_od_ID" value="${menuOrderVO.menu_od_ID}"> --%>
<!-- 										<input type="hidden" name="action" value="getOneForRate"> -->
<!-- 									</form> -->
<%-- 								</c:if> --%>
<!-- 								</td> -->
								<!-- <td>
									<form method="post" action="<%=request.getContextPath()%>/menuOrder/menuOrder.do">
										<input type="submit" value="刪除"> <input type="hidden" name="menu_od_ID" value="${menuOrderVO.menu_od_ID}"> 
										<input type="hidden" name="action" value="delete">
									</form>
								</td> -->
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="page2.file"%>
				</div>
				<div class="col-1"></div>
			</div>
		</div>
    </section>
    <!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>