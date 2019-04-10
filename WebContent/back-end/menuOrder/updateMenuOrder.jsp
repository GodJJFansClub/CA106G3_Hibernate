<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menuOrder.model.*"%>
<html>
<head>
<title>updateMenuOrder</title>
</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/custMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>
					<%--Error Message --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red; font-size: 20px;">Error:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<div class="container justify-content-center">
						<div class="row">
							<div class="col-12">
								<form method="post" action="menuOrder.do" name="insertMenuOrderForm">
									<jsp:useBean id="menuOrderSvc" scope="page" class="com.menuOrder.model.MenuOrderService" />
				
									<div class="form-group">
										<label>訂單編號</label> <input type="text" readonly class="form-control" name="menu_od_ID" value="${menuOrderVO.menu_od_ID}" />
									</div>
									<%	
											String status[] = new String [5];
											status[0]="g0";
											status[1]="g1";
											status[2]="g2";
											status[3]="g3";
											status[4]="g4";
											request.setAttribute("mystatus", status);
										%>
									<div class="form-group">
										<label>訂單狀態</label> <select size="1" name="menu_od_status" class="form-control">
											<c:forEach var="mydata" items="${menuOrStaMap}">
												<option value="${mydata.key}" ${(menuOrderVO.menu_od_status==mydata.key)? 'selected':'' }>${mydata.value}
											</c:forEach>
										</select>
									</div>
				
									<div class="form-group">
										<label>預約日期</label> 
										<input name="menu_od_book" class="form-control" id="book_time" type="text" value="${menuOrderVO.menu_od_book}" />${errMsgs.menu_od_book}
									</div>
				
									<div class="form-group">
										<jsp:useBean id="custSvc" scope="page" class="com.cust.model.CustService" />
										<label>顧客編號</label> <input type="text" readonly name="cust_ID" class="form-control" value="${menuOrderVO.cust_ID}" />
									</div>
				
									<div class="form-group">
										<jsp:useBean id="chefSvc" scope="page"
											class="com.chef.model.ChefService" />
										<label>主廚編號</label> <select size="1" name="chef_ID"
											class="form-control">
											<c:forEach var="chefVO" items="${chefSvc.all}">
												<option value="${chefVO.chef_ID}"
													${(menuOrderVO.chef_ID==cheftVO.chef_ID)? 'selected':'' }>${chefVO.chef_ID}
											</c:forEach>
										</select>
									</div>
				
									<div class="form-group">
										<jsp:useBean id="menuSvc" scope="page"
											class="com.menu.model.MenuService" />
										<label>套餐編號</label> <select size="1" name="menu_ID"
											class="form-control">
											<c:forEach var="menuVO" items="${menuSvc.all}">
												<option value="${menuVO.menu_ID}" ${(menuOrderVO.menu_ID==menuVO.menu_ID)? 'selected':'' }>${menuVO.menu_ID}
											</c:forEach>
										</select>
									</div>
				
									<div class="form-group">
										<label>完成日期</label> 
										<input name="menu_od_end" id="end_time" class="form-control" id="book_time" type="text" value="${menuOrderVO.menu_od_end}" />${errMsgs.menu_od_end}
									</div>
									<%	
											String rate[] = new String [5];
											rate[0]="1";
											rate[1]="2";
											rate[2]="3";
											rate[3]="4";
											rate[4]="5";
											request.setAttribute("myrate", rate);
										%>
									<div class="form-group">
										<label>訂單評價</label> <select size="1" name="menu_od_rate" class="form-control">
											<c:forEach var="myrate" items="${myrate}">
												<option value="${myrate}" ${(menuOrderVO.menu_od_rate==myrate)? 'selected':'' }>${myrate}
											</c:forEach>
										</select>
									</div>
				
									<div class="form-group">
										<label>訂單留言</label>
										<textarea rows="3" cols="100" name="menu_od_msg" class="form-control">${menuOrderVO.menu_od_msg}</textarea>
									</div>
									<input type="hidden" name="action" value="update"> 
									<input type="submit" class="btn btn-success btn-lg btn-block" value="修改訂單">
								</form>
							</div>
						</div>
					</div>

			
			</div>
		</div>
	</div>
</body>
</html>