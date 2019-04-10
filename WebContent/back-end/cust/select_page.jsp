<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cust.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Cust: Home</title>


</head>
<body bgcolor='white'>
		<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/custMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>
	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">查詢顧客資料</font></div>
	<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>

                        <form action="#" method="post">
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
			href='<%=request.getContextPath()%>/back-end/cust/listAllCust.jsp'>List</a>
			all Cust. <br>
		<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/cust/cust.do">
				<b>輸入顧客帳號:</b> <input type="text" name="cust_acc"> <input
					type="hidden" name="action" value="getOne_For_DisplayBack"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="custSvc" scope="session"
			class="com.cust.model.CustService" />

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/cust/cust.do">
				<b>選擇顧客編號:</b> <select size="1" name="cust_ID">
					<c:forEach var="custVO" items="${custSvc.all}">
						<option value="${custVO.cust_ID}">${custVO.cust_ID}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_DisplayBack">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/cust/cust.do">
				<b>選擇顧客姓名:</b> <select size="1" name="cust_ID">
					<c:forEach var="custVO" items="${custSvc.all}">
						<option value="${custVO.cust_ID}">${custVO.cust_name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_DisplayBack">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


                        </form>
          

			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>
</html>