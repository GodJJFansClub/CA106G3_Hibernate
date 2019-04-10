<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAll();
	;
	pageContext.setAttribute("list", list);
%>



<html>
<head>
<title>所有員工資料</title>

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

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<div class="col">
					<div class="card">

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th scope="col">員工編號:</th>
										<th scope="col">員工帳號:</th>
										<th scope="col">員工帳號:</th>
										<th scope="col">員工姓名:</th>
										<th scope="col">大頭照:</th>
									</tr>
								</thead>


								<%@ include file="/file/page1.file"%>
								<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">

									<tr>
										<th scope="row">${empVO.emp_ID}</th>
										<th scope="row">${empVO.emp_acc}</th>
										<th scope="row">${empVO.emp_pwd}</th>
										<th scope="row">${empVO.emp_name}</th>

										<th scope="row"><c:if test="${not empty empVO.emp_pic}">
												<img
													src="<%=request.getContextPath()%>/emp/emp.do?emp_ID=${empVO.emp_ID}"
													width="300" height="200">
											</c:if> <c:if test="${empty empVO.emp_pic}">
												<img src="<%=request.getContextPath()%>/images/null2.jpg"
													width="300" height="200">
											</c:if></th>

										<th scope="row">
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/emp/emp.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="emp_ID" value="${empVO.emp_ID}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</th>
									</tr>
								</c:forEach>

								<tr>
									<td>

										<h4>
											<a
												href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp">回首頁</a>
										</h4>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<%@ include file="/file/page2.file"%>
				
			</div>
		</div>
	</div>
</body>
</html>