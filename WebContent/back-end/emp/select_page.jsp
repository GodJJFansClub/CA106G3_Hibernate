<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.emp.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />

<html>
<head>
<title>員工管理</title>



</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/empMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>

 	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">員工管理</font></div>
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

				<div class="col-lg-8 col-xlg-9 col-md-7">
					<div class="card">
						<div class="card-body">

							<div class="form-group">
								<label class="col-md-12"></label>
								<div class="col-md-12">
									<a
										href='<%=request.getContextPath()%>/back-end/emp/listAllEmp.jsp'>查詢所有員工</a>
									<br>
								</div>
							</div>
							<form METHOD="post"
								ACTION="<%=request.getContextPath()%>/emp/emp.do"
								class="form-horizontal form-material">
								<div class="form-group">
									<label for="example-email" class="col-md-12">輸入員工編號:</label>
									<div class="col-md-12">
										<input type="text" name="emp_ID"> <input type="hidden"
											name="action" value="getOne_For_Display">
										<button type="submit" class="btn btn-success">送出</button>
							</form>
						</div>
					</div>
					<div class="form-group">
						<form FMETHOD="post"
							ACTION="<%=request.getContextPath()%>/emp/emp.do">
							<label class="col-md-12">選擇員工編號:</label>
							<div class="col-md-12">
								<select size="1" name="emp_ID"
									class="form-control form-control-line">
									<c:forEach var="empVO" items="${empSvc.all}">
										<option value="${empVO.emp_ID}">${empVO.emp_name}
									</c:forEach>
								</select>
								<button type="submit" class="btn btn-success"
									value="getOne_For_Display">送出</button>
						</form>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-12"></label>
					<div class="col-md-12">
						<a href='addEmp.jsp'>新增員工</a>
					</div>
				</div>

			</div>
		</div>

	</div>





</body>
</html>