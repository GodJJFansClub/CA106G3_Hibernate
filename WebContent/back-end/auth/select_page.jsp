<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.auth.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>權限管理</title>



</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/empMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>

 	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">權限管理</font></div>
	<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a
			href='<%=request.getContextPath()%>/back-end/auth/listAllAuth.jsp'>查詢</a>
			所有權限<br>
		<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/auth/auth.do">
				<b>輸入員工編號:</b> <input type="text" name="emp_ID"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

<jsp:useBean id="authSvc" scope="session" class="com.auth.model.AuthService" />
<jsp:useBean id="empSvc" scope="session" class="com.emp.model.EmpService" />
<jsp:useBean id="funSvc" scope="session" class="com.fun.model.FunService" />
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/auth/auth.do">
				<b>選擇員工編號:</b> <select size="1" name="emp_ID">
					<c:forEach var="authVO" items="${authSvc.all}">
						<option value="${authVO.emp_ID}">${authVO.emp_ID}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/auth/auth.do">
				<b>選擇功能編號:</b> <select size="1" name="emp_ID">
					<c:forEach var="authVO" items="${authSvc.all}">
						<option value="${authVO.emp_ID}">${authVO.fun_ID}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>權限管理</h3>

	<ul>
		<li><a href='<%=request.getContextPath()%>/back-end/auth/addAuth.jsp'>新增管理員</a></li>
	</ul>


			</div>
		</div>
	</div>
</body>
</html>