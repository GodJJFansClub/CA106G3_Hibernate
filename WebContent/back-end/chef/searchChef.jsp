<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" />
<html>
<head>
<title></title>
</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/foodSupMana.jsp" flush="true"/>
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
			<div class="container-fliud justify-content-center">
				<div class="row">
					<div class="col-12">
		
						<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">查詢主廚</font></div>
						<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>
						
						<form method="post"
							action="<%=request.getContextPath()%>/back-end/chef/listOneByChefID.jsp">
							<div class="input-group">
								<select size="1" name="chef_ID" class="form-control">
									<option value="請選擇主廚姓名">請選擇主廚姓名
										<c:forEach var="chefVO" items="${chefSvc.all}">
											<option value="${chefVO.chef_ID}">${custSvc.getOneCust(chefVO.chef_ID).cust_name}
										</c:forEach>
								</select>
								<div class="input-group-append">
									<input type="submit" class="btn btn-outline-secondary" value="送出">
								</div>
							</div>
						</form>
		
						<form method="post" action="<%=request.getContextPath()%>/back-end/chef/listAllByChefArea.jsp">
							<div class="input-group">
								<select size="1" name="chef_area" class="form-control">
									<option value="請選擇服務地區">請選擇服務地區
									<option value="0">北部</option>
									<option value="1">中部</option>
									<option value="2">南部</option>
									<option value="3">東部</option>
								</select>
								<div class="input-group-append">
									<input type="submit" class="btn btn-outline-secondary" value="送出">
								</div>
							</div>
						</form>
						<form method="post" action="<%=request.getContextPath()%>/back-end/chef/listAllByMenuID.jsp">
							<div class="input-group">
								<jsp:useBean id="menuSvc" scope="page" class="com.menu.model.MenuService" />
								<select size="1" name="menu_ID" class="form-control">
									<option value="請選擇套餐名稱">請選擇套餐名稱</option>
									<c:forEach var="menuVO" items="${menuSvc.all}">
										<option value="${menuVO.menu_ID}">${menuVO.menu_name}</option>
									</c:forEach>
								</select>
								<div class="input-group-append">
									<input type="submit" class="btn btn-outline-secondary" value="送出">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			</div>
		</div>
	</div>
</body>
</html>