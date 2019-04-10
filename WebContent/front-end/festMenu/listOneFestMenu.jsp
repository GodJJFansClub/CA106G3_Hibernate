<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.festMenu.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	FestMenuVO festMenuVO = (FestMenuVO) request.getAttribute("festMenuVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>新增節慶主題料理資料 - listOneFestMenu.jsp</title>

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
	<jsp:include page="/froTempl/headerMall.jsp" flush="true" />
	
	
	
	
	    <!-- ##### Contact Area Start #####-->
  
           
          
               
    <!-- ##### Contact Area End #####-->
	<!-- ##### Contact Area Start #####-->
	<section class="contact-area section-padding-100">
	  <section class="contact-area section-padding-100">
        <div class="container">
		<jsp:include page="/front-end/foodMall/shoppingcartIn.jsp" />
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<table id="table-1">
			<tr>
				<td>
					<h3>節慶主題料理資料 - ListOneFestMenu.jsp</h3>
					<h4>
						<a
							href="<%=request.getContextPath()%>/front-end/festMenu/select_page.jsp">
							<img
							src="<%=request.getContextPath()%>/front-end/festMenu/images/back1.gif"
							width="100" height="32" border="0">回首頁
						</a>

					</h4>
				</td>
			</tr>
		</table>

		<table>
			<tr>
				
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

			
			<jsp:include page="/froTempl/footer.jsp" flush="true" />
		</table>
		
		 </div>
           </section>
           </section>
</body>
</html>