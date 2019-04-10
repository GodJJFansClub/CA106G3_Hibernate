<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>

<%
	MenuVO menuVO = (MenuVO) request.getAttribute("menuVO");
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>套餐資料新增 - addMenu.jsp</title>
<link
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css"
	rel="stylesheet">
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>

<body bgcolor='white'>
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
					ACTION="<%=request.getContextPath()%>/menu/menu.do" name="form1"
					enctype="multipart/form-data">

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
					<input type="hidden" name="action" value="insert"> <input
						type="submit" value="送出新增"><br>
				</FORM>

		
			</div>
		</div>
	</div>
</body>

<script>
	function setImagePreview() {
		var docObj = document.getElementById("doc");
		var imgObjPreview = document.getElementById("preview");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '320px';
			imgObjPreview.style.height = '240px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL();
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");
			//必须设置初始大小
			localImagId.style.width = "250px";
			localImagId.style.height = "200px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("圖片格式不正確，請重新選擇!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}
</script>
</html>