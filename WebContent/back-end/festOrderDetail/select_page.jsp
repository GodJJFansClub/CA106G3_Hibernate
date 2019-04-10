<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>FestOrderDetail Home</title>


</head>

<jsp:include page="/froTempl/header.jsp" flush="true" />

<!-- ##### Contact Area Start #####-->
<section class="contact-area section-padding-100">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<!-- Section Heading -->
				<div class="section-heading text-center wow fadeInUp"
					data-wow-delay="100ms">
					<h2>Get In Touch</h2>
					<img src="img/core-img/x.png" alt="">
				</div>
			</div>
		</div>


		<div class="row justify-content-center">
			<div class="col-12 col-lg-8">
				<!-- Contact Form -->
				<div class="contact-form-area text-center">
					<form
						action="<%=request.getContextPath()%>/festOrderDetail/festOrderDetail.do"
						method="post">
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
								href='<%=request.getContextPath()%>/back-end/festOrderDetail/listAllFestOrderDetail.jsp'>List</a>
								all FestOrderDetail. <br>
							<br></li>


							<li>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/festOrderDetail/festOrderDetail.do">
									<b>節慶主題料理訂單編號 :</b> <input type="text" name="fest_or_ID">
									<input type="hidden" name="action"
										value="getOne_For_Display_Back"> <input type="submit"
										value="送出">
								</FORM>
							</li>

							<jsp:useBean id="festOrderDetailSvc" scope="page"
								class="com.festOrderDetail.model.FestOrderDetailService" />

							<li>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/festOrderDetail/festOrderDetail.do">
									<b>節慶主題料理訂單編號 ::</b> <select size="1" name="fest_or_ID">
										<%-- <c:forEach var="reportVO" items="${reportSvc.all}"> --%>
										<c:forEach var="festOrderDetailVO"
											items="${festOrderDetailSvc.all}">
											<option value="${festOrderDetailVO.fest_or_ID}">${festOrderDetailVO.fest_or_ID}
										</c:forEach>
									</select> <input type="hidden" name="action"
										value="getOne_For_Display_Back"> <input type="submit"
										value="送出">
								</FORM>
							</li>

							<li>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/festOrderDetail/festOrderDetail.do">
									<b>節慶料理編號:</b> <select size="1" name="fest_or_ID">
										<c:forEach var="festOrderDetailVO"
											items="${festOrderDetailSvc.all}">
											<option value="${festOrderDetailVO.fest_or_ID}">${festOrderDetailVO.fest_m_ID}
										</c:forEach>
									</select> <input type="hidden" name="action"
										value="getOne_For_Display_Back"> <input type="submit"
										value="送出">
								</FORM>
							</li>
						</ul>

					</form>
				</div>
			</div>
		</div>

	</div>
</section>

<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>