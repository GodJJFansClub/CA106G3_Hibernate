<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ad.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	AdVO adVO = (AdVO) session.getAttribute("adVO"); //SustServlet.java(Concroller), 存入req的adVO物件
%>

<html>
<head>
<title>廣告資料 - listOneAd.jsp</title>

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
	width: 600px;
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
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />

	 <!-- ##### Contact Area Start #####-->
    <section class="contact-area section-padding-100">
	<%
		if (adVO != null) {
	%>

	
	<table id="table-1">
		<tr>
			<td>
				<h3>廣告資料</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/ad/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>廣告編號</th>
			<th>廣告標題</th>
			<th>廣告圖片</th>
			<th>廣告內文</th>
			<th>廣告上架日期</th>
			<th>廣告下架日期</th>
			<th>廣告狀態</th>
			
			<th>食材供應商</th>
			
		</tr>
		<tr>
			<td><%=adVO.getAd_ID()%></td>
			<td><c:if test="${not empty adVO.ad_pic}"><img src="<%=request.getContextPath()%>/ad/ad.do?ad_ID=${adVO.ad_ID}" width="300" height="200"></c:if>
				    <c:if test="${empty adVO.ad_pic}"><img src="<%=request.getContextPath()%>/images/null2.jpg"></c:if></td>
			<td><%=adVO.getAd_con()%></td>
			<td><%=adVO.getAd_start()%></td>
			<td><%=adVO.getAd_end()%></td>
			<td>${adStatusMap[adVO.ad_status]}</td>
		
			<td><%=adVO.getFood_sup_ID()%></td>
		

		</tr>
	</table>
	<%
		}
	%>
 </section>
    <!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>