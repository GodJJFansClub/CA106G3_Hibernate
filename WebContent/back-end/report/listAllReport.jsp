<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*"%>

<%
    ReportService reportSvc = new ReportService();
    List<ReportVO> list = reportSvc.getAll();
    pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>List_All_Report.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<body bgcolor='white'>
		<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<aside class="left-sidebar" data-sidebarbg="skin5">
<%--==============<jsp:include page="/back-end/XXXX/sidebar.jsp" flush="true" />=================================--%>
	</aside>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>

<table id="table-1">
	<tr><td>
		 <h3>所有檢舉文章資料 - listAllReport.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/report/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>檢舉編號</th>
		<th>檢舉標題</th>
		<th>檢舉分類</th>
		<th>檢舉時間</th>
		<th>檢舉狀態</th>
		<th>檢舉內容</th>
		<th>會員編號</th>
		<th>文章編號</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="reportVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${reportVO.report_ID}</td>
			<td>${reportVO.report_title}</td>
			<td>${reportVO.report_sort}</td>
			<td>${reportVO.report_start}</td>
			<td>${reportStatusMap[reportVO.report_status]}</td>
			<td>${reportVO.report_con}</td> 
			<td>${reportVO.cust_ID}</td>
			<td>${reportVO.forum_art_ID}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/report/report.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="report_ID"  value="${reportVO.report_ID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/report/report.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="report_ID"  value="${reportVO.report_ID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>