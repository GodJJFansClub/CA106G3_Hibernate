<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>FestOrder Home</title>

</head>
		<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/custMana.jsp" flush="true"/>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>

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
								href='<%=request.getContextPath()%>/back-end/festOrder/listAllFestOrder.jsp'>List</a>
								all FestOrder. <br> <br></li>


							<li>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/festOrder/festOrder.do">
									<b>請輸入訂單編號 :</b> <input type="text" name="fest_or_ID">
									<input type="hidden" name="action"
										value="getOne_For_Display_Back"> <input type="submit"
										value="送出">
								</FORM>
							</li>

							<jsp:useBean id="festOrderSvc" scope="page"
								class="com.festOrder.model.FestOrderService" />

							<li>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/festOrder/festOrder.do">
									<b>選擇訂單編號 ::</b> <select size="1" name="fest_or_ID">
										<%-- <c:forEach var="reportVO" items="${reportSvc.all}"> --%>
										<c:forEach var="festOrderVO" items="${festOrderSvc.all}">
											<option value="${festOrderVO.fest_or_ID}">${festOrderVO.fest_or_ID}
										</c:forEach>
									</select> <input type="hidden" name="action"
										value="getOne_For_Display_Back"> <input type="submit"
										value="送出">
								</FORM>
							</li>

							<li>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/festOrder/festOrder.do">
									<b>請選擇訂單狀態:</b> <select size="1" name="fest_or_ID">
										<c:forEach var="festOrderVO" items="${festOrderSvc.all}">
											<option value="${festOrderVO.fest_or_ID}">${festOrderVO.fest_or_status}
										</c:forEach>
									</select> <input type="hidden" name="action"
										value="getOne_For_Display_Back"> <input type="submit"
										value="送出">
								</FORM>
							</li>
						</ul>

			</div>
		</div>
	</div>
						</body>
</html>