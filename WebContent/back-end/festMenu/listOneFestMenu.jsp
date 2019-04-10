<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.festMenu.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	FestMenuVO festMenuVO = (FestMenuVO) request.getAttribute("festMenuVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>新增節慶主題料理資料</title>



</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
<%--==============<jsp:include page="/back-end/XXXX/sidebar.jsp" flush="true" />=================================--%>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>
	<table>
		<tr>
			<th>節慶料理編號</th>
			<th>節慶主題料理名稱</th>
			<th>數量</th>
			<th>開始預購日期</th>
			<th>結束預購日期</th>
			<th>照片</th>
			<th>介紹</th>
			<th>出貨日期</th>
			<th>節慶主題料理狀態</th>
			<th>種類</th>
			<th>價格</th>
			<th>主廚編號</th>

		</tr>
		<tr>
			<td>${festMenuVO.fest_m_ID}</td>
			<td>${festMenuVO.fest_m_name}</td>
			<td>${festMenuVO.fest_m_qty}</td>
			<td>${festMenuVO.fest_m_start}</td>
			<td>${festMenuVO.fest_m_end}</td>
			<td><c:if test="${not empty festMenuVO.fest_m_pic}">
					<img
						src="<%=request.getContextPath()%>/festMenu/festMenu.do?fest_m_ID=${festMenuVO.fest_m_ID}">
				</c:if> <c:if test="${empty festMenuVO.fest_m_pic}">
					<img src="<%=request.getContextPath()%>/images/null2.jpg">
				</c:if></td>
			<td>${festMenuVO.fest_m_resume}</td>
			<td>${festMenuVO.fest_m_send}</td>
			<td>${festMenuVO.fest_m_status}</td>
			<td>${festMenuVO.fest_m_kind}</td>
			<td>${festMenuVO.fest_m_price}</td>
			<td>${festMenuVO.chef_ID}</td>

		</tr>
	</table>

			</div>
		</div>
	</div>
</body>
</html>