<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fun.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	FunVO funVO = (FunVO) request.getAttribute("funVO"); //SustServlet.java(Concroller), 存入req的funVO物件
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
		if (funVO != null) {
	%>

	
	<table id="table-1">
		<tr>
			<td>
				<h3>權限資料</h3>
				
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>權限編號</th>
			
			<th>權限名稱</th>
		
			
		</tr>
		<tr>
			<td><%=funVO.getFun_ID()%></td>
			<td><%=funVO.getFun_name()%></td>
					

		</tr>
	</table>
	<%
		}
	%>


			</div>
		</div>
	</div>
</body>
</html>