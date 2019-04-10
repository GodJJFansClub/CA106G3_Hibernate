<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cust.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CustVO custVO = (CustVO) request.getAttribute("custVO"); //SustServlet.java(Concroller), 存入req的custVO物件
%>

<html>
<head>
<title>顧客資料 - listOneCust.jsp</title>
<link href="../../dist/css/style.min.css" rel="stylesheet">

</head>
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
	<%
		if (custVO != null) {
	%>

	
	
				<h3>顧客資料</h3>
				
				<div class="col">
					<div class="card">

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th scope="col">顧客編號</th>
										<th scope="col">顧客帳號</th>
										<th scope="col">顧客密碼</th>
										<th scope="col">顧客姓名</th>
										<th scope="col">顧客性別</th>
										<th scope="col">顧客電話</th>
										<th scope="col">顧客地址</th>
										<th scope="col">顧客身份證字號</th>
										<th scope="col">E-mail</th>
										<th scope="col">顧客生日</th>
										<th scope="col">顧客註冊日</th>
										<th scope="col">大頭照</th>
										<th scope="col">顧客狀態</th>
										<th>顧客暱稱</th>
										
									</tr>
								</thead>
								
									<tr>
										<th scope="row"><%=custVO.getCust_ID()%></th>
										<th scope="row"><%=custVO.getCust_acc()%></th>
										<th scope="row"><%=custVO.getCust_pwd()%></th>
										<th scope="row"><%=custVO.getCust_name()%></th>
										<c:if test="${custVO.cust_sex.equals('M')}" var="true"
											scope="request">
											<td>男生</td>
										</c:if>
										<c:if test="${custVO.cust_sex.equals('F')}" var="true"
											scope="request">
											<td>女生</td>
										</c:if>
										<th scope="row"><%=custVO.getCust_tel()%></th>
										<th scope="row"><%=custVO.getCust_addr()%></th>
										<th scope="row"><%=custVO.getCust_pid()%></th>
										<th scope="row"><%=custVO.getCust_mail()%></th>
										<th scope="row"><%=custVO.getCust_brd()%></th>
										<th scope="row"><%=custVO.getCust_reg()%></th>
										<th scope="row"><c:if test="${not empty custVO.cust_pic}">
												<img
													src="<%=request.getContextPath()%>/cust/cust.do?cust_ID=${custVO.cust_ID}"
													width="300" height="200">
											</c:if> <c:if test="${empty custVO.cust_pic}">
												<img src="<%=request.getContextPath()%>/images/null2.jpg"
													width="150" height="150">
											</c:if></th>

										<c:if test="${custVO.cust_status.equals('a0')}" var="true"
											scope="session">
											<td>未停權</td>
										</c:if>
										<c:if test="${custVO.cust_status.equals('a1')}" var="true"
											scope="session">
											<td>停權</td>
										</c:if>
										<th scope="row"><%=custVO.getCust_niname()%></th>

									</tr>
									<tr>
			<td>
				
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/cust/listAllCust.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
								</table>
								</div>
					</div>
				</div>
								<%
		}	
	%>
								
						</div>
					</div>
				</div></body>
</html>