<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cust.model.CustVO"%>
<%@ page import="com.foodSup.model.FoodSupVO" %>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<%
	CustVO custVO = custSvc.getOneCust(
			((FoodSupVO)request.getAttribute("foodSupVO")).getFood_sup_ID());
	pageContext.setAttribute("custVO", custVO);
%>
<html>
<head>
<title></title>
</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/custMana.jsp" flush="true" />
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
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodSup/foodSup.do" name="form1">
				<table>
					<tr>
						<td>食材供應商編號:</td>
						<td>${foodSupVO.food_sup_ID}</td>
					</tr>
					<tr>
						<td>食材供應商名稱:</td>
						<td>${foodSupVO.food_sup_name}</td>
					</tr>
					<tr>
						<td>食材供應商電話:</td>
						<td>${foodSupVO.food_sup_tel}</td>
					</tr>
					<tr>
						<td>狀態:</td>
						<td>
						<select size="1" name="food_sup_status">
							<c:forEach var="foodSupStatus" items="${foodSupStatusMap}">
								<c:if test="${foodSupStatus.key != 's0'}">
									<option value="${foodSupStatus.key}"
									${(foodSupVO.food_sup_status == foodSupStatus.key)?'selected':''}>${foodSupStatus.value}
								</c:if>
							</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td>介紹:</td>
						<td>${foodSupVO.food_sup_resume}</td>
					</tr>
				</table>
				<br>
				<input type="hidden" name="action" value="updateBack">
				<input type="hidden" name="food_sup_ID" value="${foodSupVO.food_sup_ID}">
				<input type="submit" value="送出修改">
			</FORM>
			
			
			<div class="container-fluid">
			<div class="card-body">
				<center class="m-t-30"> 
					<c:if test="${not empty custVO.cust_pic}"><img src="<%=request.getContextPath()%>/cust/cust.do?cust_ID=${custVO.cust_ID}" width="150"></c:if>
					<c:if test="${empty custVO.cust_pic}">
						<img src="<%=request.getContextPath()%>/images/null2.jpg" width="150"></c:if></td>
	                      
	                     <h4 class="card-title m-t-10">Hanna Gover</h4>
	                      <h6 class="card-subtitle">Accoubts Manager Amix corp</h6>
	                      <div class="row text-center justify-content-md-center">
	                     <div class="col-4"><a href="javascript:void(0)" class="link"><i class="icon-people"></i> <font class="font-medium">254</font></a></div>
	                     <div class="col-4"><a href="javascript:void(0)" class="link"><i class="icon-picture"></i> <font class="font-medium">54</font></a></div>
                  </div>
               </center>
          	</div>
          	</div>
          	<table>
		<tr>
			<th>聯絡人姓名</th>
			<th>聯絡人性別</th>
			<th>聯絡人電話</th>
			<th>地址</th>
			<th>聯絡人身份證字號</th>
			<th>E-mail</th>
			<th>生日</th>
			<th>註冊日</th>
			<th>大頭照</th>
			<th>暱稱</th>
		</tr>
		<tr>
				<td><%=custVO.getCust_name()%></td>
				<c:if test="${custVO.cust_sex.equals('M')}" var="true" scope="request">
					<td>男生</td>
				</c:if>
				<c:if test="${custVO.cust_sex.equals('F')}" var="true" scope="request">
					<td>女生</td>
				</c:if>
				<td><%=custVO.getCust_tel()%></td>
				<td><%=custVO.getCust_addr()%></td>
				<td><%=custVO.getCust_pid()%></td>
				<td><%=custVO.getCust_mail()%></td>
				<td><%=custVO.getCust_brd()%></td>
				<td><%=custVO.getCust_reg()%></td>
				<td>
	
				<td><%=custVO.getCust_niname()%></td>
	
			</tr>
		</table>

			</div>
		</div>
	</div>
</body>
</html>