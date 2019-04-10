<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef.model.*"%>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<% //String chef_ID = request.getParameter(chef_ID); %>

<html>
<head>
<title>updateFavFdSup.jsp</title>

</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/foodSupMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>
	<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>



	<div class="card text-center" style="background-color: #D4E6F1">
		<div class="card-body">
			<h5 class="card-title">審核主廚</h5>
		</div>
	</div>

	
	<div class="container justify-content-center">
		<div class="row">
			<div class="col-12">
				<form method="post" action="<%=request.getContextPath()%>/chef/chef.do">
					<jsp:useBean id="chef" scope="page" class="com.chef.model.ChefService" />
					
					<div class="form-group">
						<label>主廚編號</label> 
						<input type="text" readonly class="form-control" name="chef_ID" value="${chefVO.chef_ID}" />
					</div>
					<div class="form-group">
						<label>主廚名稱</label> 
						<input type="text" readonly class="form-control" name="chef_ID" value="${custSvc.getOneCust(chefVO.chef_ID).cust_name}" />
					</div>

					<div class="form-group">
						<label>主廚簡介</label>
						<textarea readonly rows="3" cols="100" name="chef_resume" class="form-control">${chefVO.chef_resume}</textarea>
					</div>
					
					<%	
						String status[] = new String [4];
						status[0]="b0";
						status[1]="b1";
						status[2]="b2";
						status[3]="b3";
						request.setAttribute("mystatus", status);
					%>

					<div class="form-group">
						<label>主廚狀態</label> <select size="1" name="chef_status"
							class="form-control">
							<c:forEach var="mydata" items="${chefStatusMap}">
								<option value="${mydata.key}"
									${(chefVO.chef_status==mydata.key)? 'selected':'' }>${mydata.value}
							</c:forEach>
						</select>
					</div>
					<input type="hidden" name="chef_channel" value="${chefVO.chef_channel}">
					<input type="hidden" name="chef_area" value="${chefVO.chef_area}">
					<input type="hidden" name="action" value="getOneForUpdate"> 
					<input type="submit" class="btn btn-success btn-lg btn-block" value="送出">
				</form>
				</div>
			</div>
		</div>
		</div>
	</div>
</div>
	
</body>
</html>