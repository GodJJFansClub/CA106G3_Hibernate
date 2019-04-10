<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ad.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>廣告管理</title>



</head>
<body bgcolor='white'>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/adMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>

 	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">廣告查詢</font></div>
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
						href='<%=request.getContextPath()%>/back-end/ad/listAllAd.jsp'>List</a>
						查看所有廣告 <br> <br></li>


					<li>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ad/ad.do">
							<b>輸入廣告編號:</b> <input type="text" name="ad_ID"> <input
								type="hidden" name="action" value="getOne_For_DisplayBack">
							<input type="submit" value="送出">
						</FORM>
					</li>

					<jsp:useBean id="adSvc" scope="page" class="com.ad.model.AdService" />

					<li>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ad/ad.do">
							<b>選擇廣告編號:</b> <select size="1" name="ad_ID">
								<c:forEach var="adVO" items="${adSvc.all}">
									<option value="${adVO.ad_ID}">${adVO.ad_ID}
								</c:forEach>
							</select> <input type="hidden" name="action" value="getOne_For_DisplayBack">
							<input type="submit" value="送出">
						</FORM>
					</li>

					<li>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ad/ad.do">
							<b>選擇廣告標題:</b> <select size="1" name="ad_ID">
								<c:forEach var="adVO" items="${adSvc.all}">
									<option value="${adVO.ad_ID}">${adVO.ad_title}
								</c:forEach>
							</select> <input type="hidden" name="action" value="getOne_For_DisplayBack">
							<input type="submit" value="送出">
						</FORM>
					</li>
				</ul>



			
			</div>
		</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>
</html>