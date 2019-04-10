<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad.model.*"%>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>


<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AdService adSvc = new AdService();
	List<AdVO> list = adSvc.getAll();
	;
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有廣告資料</title>
</head>
<body>
    <div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
        data-layout="vertical" data-sidebartype="full"
        data-boxed-layout="full">
        <jsp:include page="/back-endTemplate/header.jsp" flush="true" />
        <jsp:include page="/back-end/sideBar/adMana.jsp" flush="true" />
        <div class="page-wrapper">
            <div class="page-breadcrumb">
                <%--=================================工作區================================================--%>
                
 	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">廣告資料管理</font></div>
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
                                            <th scope="col">廣告標題:</th>
                                            <th scope="col">圖片:</th>
                                            <th scope="col">廣告內文:</th>
                                            <th scope="col">廣告下架日期:</th>
                                            <th scope="col">廣告狀態:</th>
                                            <th scope="col">食材供應商:</th>
                                          
                                        </tr>
                                    </thead>
                
								<%@ include file="/file/page1.file"%>
		                  <c:forEach var="adVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<th scope="row">${adVO.ad_title}</th>
				<th scope="row"><c:if test="${not empty adVO.ad_pic}"><img src="<%=request.getContextPath()%>/ad/ad.do?ad_ID=${adVO.ad_ID}" width="150" height="100"></c:if>
				    <c:if test="${empty adVO.ad_pic}"><img src="<%=request.getContextPath()%>/images/null2.jpg" width="140" height="150"></c:if></th>
				<th scope="row" width="20%">${adVO.ad_con}</th>
				
				<th scope="row">${adVO.ad_end}</th>
				<th scope="row">
				<c:choose>
				<c:when test="${adVO.ad_status eq 'd2'}">
					<span class="label label-danger label-rounded">
						${adStatusMap[adVO.ad_status]}
					</span>
				</c:when>
				<c:otherwise>${adStatusMap[adVO.ad_status]}</c:otherwise>
				</c:choose>
				</th>
				
				
				<th scope="row">${custSvc.getOneCust(adVO.food_sup_ID).cust_name}</th>
				
				<th scope="row">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/ad/ad.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-secondary" value="審核廣告"> 
						<input type="hidden" name="ad_ID" value="${adVO.ad_ID}">
						<input type="hidden" name="action" value="getOne_For_UpdateBack">
					</FORM>
				</th>			
			</tr>
		</c:forEach>
	</table>
	</div>
           </div>
        </div>
	<%@ include file="/file/page2.file"%>

			</div>
		</div>
	</div>
	
<br>
<br>
<br>
<br>

<br>
<br>
<br>
<br>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>
</html>