<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.festOrderDetail.model.*"%>

<%
	FestOrderDetailService festOrderDetailSvc = new FestOrderDetailService();
	List<FestOrderDetailVO> list = festOrderDetailSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<jsp:useBean id="festOrderSvc" scope="page"
	class="com.festOrder.model.FestOrderService" />
<html>
<head>
<title>List_All_FestOrderDetail.jsp</title>


</head>



	<body bgcolor='white'>

			

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
						<ul>
						<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<table>
								<tr>
									<th>節慶主題料理訂單編號</th>
									<th>節慶料理編號</th>
									<th>訂單評價</th>
									<th>訂單評價留言</th>
									<th>訂單數量</th>
									<th>訂單小計</th>
									<th>修改</th>
									<th>刪除</th>

								</tr>
								<%@ include file="page1.file"%>
								<c:forEach var="festOrderDetailVO" items="${list}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

									<tr>
										<td>${festOrderDetailVO.fest_or_ID}</td>
										<td>${festOrderDetailVO.fest_m_ID}</td>
										<td>${festOrderDetailVO.fest_or_rate}</td>
										<td>${festOrderDetailVO.fest_or_msg}</td>
										<td>${festOrderDetailVO.fest_or_qty}</td>
										<td>${festOrderDetailVO.fest_or_stotal}</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/festOrderDetail/festOrderDetail.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="fest_or_ID"
													value="${festOrderDetailVO.fest_or_ID}"> <input
													type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/festOrderDetail/festOrderDetail.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="刪除"> <input
													type="hidden" name="fest_or_ID"
													value="${festOrderDetailVO.fest_or_ID}"> <input
													type="hidden" name="action" value="delete">
											</FORM>
										</td>
									</tr>
								</c:forEach>
							</table>
							<%@ include file="page2.file"%>

<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>