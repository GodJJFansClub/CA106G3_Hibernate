<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.fun.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	AuthVO authVO = (AuthVO) request.getAttribute("authVO"); //SustServlet.java(Concroller), 存入req的authVO物件
%>

<html>
<head>
<title>權限資料</title>




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



	<%
		if (authVO != null) {
	%>

	
	<table id="table-1">
		<tr>
			<td>
				<h3>權限資料</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/auth/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>員工名稱</th>
			
			<th>權限名稱</th>
		
			
		</tr>
		<tr>
			<td><%=authVO.getEmp_ID()%>->${empSvc.getOneEmp(authVO.emp_ID).emp_name}</td>
			<td><%=authVO.getFun_ID()%>->${funSvc.getOneFun(authVO.fun_ID).fun_name}</td>
					

		</tr>
	</table>
	<%
		}
	%>

		
			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>
</html>