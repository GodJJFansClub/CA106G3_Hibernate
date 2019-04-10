<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.festMenu.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    FestMenuService festMenuSvc = new FestMenuService();
    List<FestMenuVO> list = festMenuSvc.getAll();
    pageContext.setAttribute("list", list);
%>


<html>
<head>
<title></title>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>節慶主題料理訂單資料 - listAllFestMenu.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/festMenu/select_page.jsp">回首頁</a></h4>
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
		<th>節慶料理編號 </th>
		<th>節慶主題料理名稱</th>
		<th>數量</th>
		<th>開始預購日期 </th>
		<th>結束預購日期        </th>
		<th>照片</th>
		<th>介紹</th>
		<th>出貨日期</th>
		<th>節慶主題料理狀態</th>
		<th>種類</th>
		<th>主廚編號</th>
	</tr>
	<c:forEach var="festMenuVO" items="${list}">
		
		<tr>
			<td>${festMenuVO.fest_m_ID}</td>
			<td>${festMenuVO.fest_m_name}</td>
			<td>${festMenuVO.fest_m_qty}</td>
			<td>${festMenuVO.fest_m_start}</td>
			<td>${festMenuVO.fest_m_end}</td>
			<td><c:if test="${not empty festMenuVO.fest_m_pic}"><img src="<%=request.getContextPath()%>/festMenu/festMenu.do?fest_m_ID=${festMenuVO.fest_m_ID}"></c:if>
				    <c:if test="${empty festMenuVO.fest_m_pic}"><img src="images/null2.jpg"></c:if></td>
			<td>${festMenuVO.fest_m_resume}</td>
			<td>${festMenuVO.fest_m_send}</td>
			<td>${festMenuVO.fest_m_status}</td>
			<td>${festMenuVO.fest_m_kind}</td>
			<td>${festMenuVO.chef_ID}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/festMenu/festMenu.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="fest_m_ID"  value="${festMenuVO.fest_m_ID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/festMenu/festMenu.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="fest_m_ID"  value="${festMenuVO.fest_m_ID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>