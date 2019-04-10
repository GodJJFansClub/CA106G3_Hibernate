<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	EmpVO empVO = (EmpVO) session.getAttribute("empVO"); //SustServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />



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
				<%
		if (empVO != null) {
	%>


			
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


								<tr>
									<th scope="row"><%=empVO.getEmp_ID()%></th>
									<th scope="row"><%=empVO.getEmp_acc()%></th>
									<th scope="row"><%=empVO.getEmp_pwd()%></th>
									<th scope="row"><%=empVO.getEmp_name()%></th>
									<th scope="row"><img src="<%=request.getContextPath()%>/emp/emp.do?emp_ID=${empVO.emp_ID}" width="300" height="200"></th>	
								</tr>
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
				<%
		}
	%>

				
			</div>
		</div>
	</div>
</body>
</html>