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
<title>食材供應商資料 - listOneEmp.jsp</title>
</head>
<body>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	<div class=""></div>
	<div class="container">
		<div class="row">
		<div class="col">
			<table>
				<tr>
					<th>食材供應商編號</th>
					<th>食材供應商名稱</th>
					<th>電話號碼</th>
					<th>狀態</th>
					<th>介紹</th>
				</tr>
				<tr>
					<td>${foodSupVO.food_sup_ID}</td>
					<td>${foodSupVO.food_sup_name}</td>
					<td>${foodSupVO.food_sup_tel}</td>
					<td>${foodSupStatusMap[foodSupVO.food_sup_status]}</td>
					<td>${foodSupVO.food_sup_resume}</td>
				</tr>
			</table>
		</div>
		<div class="col">
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
						<td><c:if test="${not empty custVO.cust_pic}"><img src="<%=request.getContextPath()%>/cust/cust.do?cust_ID=${custVO.cust_ID}"></c:if>
							    <c:if test="${empty custVO.cust_pic}"><img src="<%=request.getContextPath()%>/images/null2.jpg"></c:if></td>
			
						<td><%=custVO.getCust_niname()%></td>
			
					</tr>
				</table>
			</div>
		</div>
	</div>

</body>
</html>