<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cust.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>



</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />

	 <!-- ##### Contact Area Start #####-->
    <section class="contact-area section-padding-100">


            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <!-- Contact Form -->
                    <div class="contact-form-area text-center">
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
			href='<%=request.getContextPath()%>/front-end/cust/listAllCust.jsp'>List</a>
			all Cust. <br>
		<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/cust/cust.do">
				<b>輸入顧客帳號:</b> <input type="text" name="cust_acc"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
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
				</select> <input type="hidden" name="action" value="getOne_For_Display">
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
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>顧客管理</h3>

	<ul>
		<li><a href='<%=request.getContextPath() %>/front-end/login/addCust.jsp'>Add</a> a new Cust.</li>
	</ul>

                        </form>
         
</section>
    <!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>