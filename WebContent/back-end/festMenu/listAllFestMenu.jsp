<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.festMenu.model.*"%>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<%
	FestMenuService festMenuSvc = new FestMenuService();
	List<FestMenuVO> list = festMenuSvc.getAll();
	pageContext.setAttribute("list", list);
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
		<jsp:include page="/back-end/sideBar/mallMana.jsp" flush="true"/>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>

 	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">管理節慶主題料理</font></div>
	<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
				<div class="col">
					<div class="card">

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th scope="col">節慶料理編號</th>
										<th scope="col">節慶主題料理名稱</th>
										<th scope="col">數量</th>
										<th scope="col">開始預購日期</th>
										<th scope="col">結束預購日期</th>
										<th scope="col">照片</th>
										<th scope="col">出貨日期</th>
										<th scope="col">價格</th>
										<th scope="col">主廚</th>
									</tr>
								</thead>
									<%@ include file="page1.file"%>
		<c:forEach var="festMenuVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<th scope="row">${festMenuVO.fest_m_ID}</th>
				<th scope="row">${festMenuVO.fest_m_name}</th>
				<th scope="row">${festMenuVO.fest_m_qty}</th>
				<th scope="row">${festMenuVO.fest_m_start}</th>
				<th scope="row">${festMenuVO.fest_m_end}</th>
				<th scope="row"><c:if test="${not empty festMenuVO.fest_m_pic}">
						<img src="<%=request.getContextPath()%>/festMenu/festMenu.do?fest_m_ID=${festMenuVO.fest_m_ID}" style="width:300px;height:200px;">
					</c:if>
					<c:if test="${empty festMenuVO.fest_m_pic}">
						<img src="<%=request.getContextPath()%>/images/null2.jpg" style="width:300px;height:200px;">
					</c:if></th>
				<th scope="row">${festMenuVO.fest_m_send}</th>
				<th scope="row">${festMenuVO.fest_m_price}</th>
				<th scope="row">${custSvc.getOneCust(festMenuVO.chef_ID).cust_name}</th>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
	</div>
	<%@ include file="page2.file"%>

			</div>
		</div>
	</div>
</body>
</html>