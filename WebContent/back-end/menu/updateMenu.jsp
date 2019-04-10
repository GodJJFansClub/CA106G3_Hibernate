<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>

<%
	MenuVO menuVO = (MenuVO) request.getAttribute("menuVO");
%>

<html>
<head>
<title>updateMenu</title>
<style type="text/css">
table {
	border: 2px solid gray;
	margin: 15px;
}

th, td {
	border: 1px solid;
	width: 100px;
	height: 50px;
	text-align: center;
	padding: 3px;
}

img {
	width: 320px;
	height: 240px;
}
</style>
</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<aside class="left-sidebar" data-sidebarbg="skin5">
			<%--==============<jsp:include page="/back-end/XXXX/sidebar.jsp" flush="true" />=================================--%>

		</aside>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>
				<h3>資料新增:</h3>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/menu/menu.do" name="form1" enctype="multipart/form-data">

					<table>
						<tr>
							<td>套餐名稱:</td>
							<td><input type="text" name="menu_name" value="${menuVO==null? '': menuVO.menu_name}" /></td>
						</tr>

						<tr>
							<td>套餐價錢:</td>
							<td><input type="number" name="menu_price" value="${menuVO==null? '': menuVO.menu_price}" /></td>
						</tr>

						<tr>
							<td>套餐簡介:</td>
							<td><input type="text" name="menu_resume" value="${menuVO==null? '':menuVO.menu_resume}" placeholder="請輸入簡介"><br></td>
						</tr>

						<tr>
							<td>套餐照片:</td>
							<td><input type="file" name="menu_pic" id="doc" onchange="javascript:setImagePreview();" /><br></td>
						</tr>

					</table>
					<div id="localImag">
						<img id="preview" width=-1 height=-1 style="display: none" />
					</div>
					<input type="hidden" name="action" value="update"> 
					<input type="submit" value="送出修改"><br>
				</FORM>

		
			</div>
		</div>
	</div>
</body>
</html>