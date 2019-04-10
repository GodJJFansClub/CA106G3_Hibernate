<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dish.model.*"%>

<%-- 此測試頁，練習採用 EL 的寫法取值 --%>

<jsp:useBean id="dishDAO"  scope="page" class="com.dish.model.DishDAO" />
<jsp:useBean id="dishSvc"  scope="page" class="com.dish.model.DishDAO" />
<%
	List<DishVO> list = dishSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>所有菜色資料</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3><font color=red>全部<font color=blue>菜色資料</font></h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>
	<h2><font color=red>菜色狀態:D0=下架 D1=上架</font></h2>
<table>
	
	
		<div class="container">
		<div class="row">
		<c:forEach var="dishVO" items="${list}" >	
			<div class="col-4">
				<div class="${dishVO.dish_name}">
				  <img src ="<%=request.getContextPath()%>/dish/dish.do?dish_ID=${dishVO.dish_ID}"  width="300" height="200">
				  <div class="card-body">
				    <p class="card-text">${dishVO.dish_resume}</p>
				  </div>
				</div>
			</div>
		</c:forEach>
		</div>
	</div>
		
</table> 


</body>
</html>