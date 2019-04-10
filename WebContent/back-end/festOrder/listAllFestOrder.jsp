<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.festOrder.model.*"%>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<jsp:useBean id="festOrderSvc" scope="page" class="com.festOrder.model.FestOrderService" />

<%
	List<FestOrderVO> list = festOrderSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title></title>


</head>
<body bgcolor='white'>
		<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/custMana.jsp" flush="true"/>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>
	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">節慶主題料理訂單</font></div>
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
	
 <div class="col">
                        <div class="card">
                            
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                        <th scope="col">訂單編號</th>
										<th scope="col">訂單狀態</th>
										<th scope="col">價格</th>
										<th scope="col">訂單成立日期</th>
										<th scope="col">出貨日期</th>
										<th scope="col">訂單結束日期</th>
										<th scope="col">會員編號</th>
										<th scope="col">修改</th>
										<th scope="col">送出查詢</th>
                                        </tr>
                                    </thead>
		
		<%@ include file="page1.file"%>
		<c:forEach var="festOrderVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				 <th scope="row">${festOrderVO.fest_or_ID}</th>
				 <th scope="row">${mallOrStatusMap[festOrderVO.fest_or_status]}</th>
				 <th scope="row">${festOrderVO.fest_or_price}</th>
				 <th scope="row">${festOrderVO.fest_or_start}</th>
				 <th scope="row">${festOrderVO.fest_or_send}</th>
				 <th scope="row">${festOrderVO.fest_or_end}</th>
				 <th scope="row">${custSvc.getOneCust(festOrderVO.cust_ID).cust_name}</th>
				 <th scope="row">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/festOrder/festOrder.do"
						style="margin-bottom: 0px;">
						<button type="submit" class="btn btn-outline-secondary btn-block">修改</button>
<!-- 						<input type="submit" value="修改"> -->
						 <input type="hidden"
							name="fest_or_ID" value="${festOrderVO.fest_or_ID}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</th>

				 <th scope="row">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/festOrder/festOrder.do"
						style="margin-bottom: 0px;">
						<button type="submit" class="btn btn-secondary text-white">送出查詢</button>
<!-- 						<input type="submit" value="送出查詢" >  -->
						<input type="hidden"
							name="fest_or_ID" value="${festOrderVO.fest_or_ID}"> <input
							type="hidden" name="action" 
							value="listFestOrderDetail_ByFest_or_ID_Back">
					</FORM>
				</th>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
	</div>
	
	<%@ include file="page2.file"%>

	<%
		if (request.getAttribute("listFestOrderDetail_ByFest_or_ID") != null) {
	%>
	<jsp:include
		page="/back-end/festOrder/listFestOrderDetail_ByFest_or_ID.jsp" />
	<%
		}
	%>

			</div>
		</div>
	</div>
</body>
</html>