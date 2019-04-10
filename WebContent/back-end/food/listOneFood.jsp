<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.food.model.*"%>

<%--測試一下這東西應該是不用
	FoodVO foodVO = (FoodVO) request.getAttribute("foodVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
--%>

<html>
<head>
<title></title>
</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<aside class="left-sidebar" data-sidebarbg="skin5">
			<jsp:include page="/back-endTemplate/sidebar.jsp" flush="true" />
		</aside>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>

				<table>
					<tr>
						<td>
							<h3>食材資料 - listOneFood.jsp</h3>
							<h4>
								<a
									href="<%=request.getContextPath()%>/back-end/food/select_page.jsp"><img
									src="<%=request.getContextPath()%>/back-end/food/images/back1.gif"
									width="100" height="32" border="0">回首頁</a>
							</h4>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<th>食材編號</th>
						<th>食材名稱</th>
						<th>食材種類</th>
					</tr>
					<tr>
						<%-- 思考一下這裡是左邊還是右邊, 左邊上面的其實是script用 --%>
						<td>${foodVO.food_ID}</td>
						<td>${foodVO.food_name}</td>
						<td>${foodTypeMap[foodVO.food_type_ID]}</td>
					</tr>
				</table>

			</div>
		</div>
	</div>
</body>
</html>