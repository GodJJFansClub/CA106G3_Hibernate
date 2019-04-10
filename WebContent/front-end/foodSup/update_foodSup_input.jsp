<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>食材供應商資料修改</title>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />

	<!-- ##### Breadcrumb Area Start ##### -->
	<jsp:include page="/froTempl/headerFoodSup.jsp" flush="true" />
	<!-- ##### Breadcrumb Area End ##### -->

	<!-- ##### Contact Area Start #####-->
	<section class="contact-area section-padding-100">
		<section class="contact-area section-padding-100">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<!-- Section Heading -->
						<div class="section-heading text-center wow fadeInUp"
							data-wow-delay="100ms">

							<h2 style="font-weight:bold">食材供應商資料修改</h2>
							<img
								src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/x.png"alt="">



							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<div class="row justify-content-center">
								<div class="col-12 col-lg-8">
									<!-- Contact Form -->
									<div class="contact-form-area text-center">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodSup/foodSup.do" name="form1">
			<table>
				<tr>
					<td>食材供應商編號:</td>
					<td>${foodSupVO.food_sup_ID}</td>
				</tr>
				<tr>
					<td><font color=red><b>*</b></font>食材供應商名稱:</td>
					<td><input type="TEXT" name="food_sup_name" size="45" value="${foodSupVO.food_sup_name}" /></td>
				</tr>
				<tr>
					<td>食材供應商電話:</td>
					<td><input type="TEXT" name="food_sup_tel" size="45"	value="${foodSupVO.food_sup_tel}" /></td>
				</tr>
				<tr>
					<td>狀態:</td>
					<td>${foodSupStatusMap[foodSupVO.food_sup_status]}
					</td>
				</tr>
				<tr>
					<td>介紹:</td>
					<td><textarea name="food_sup_resume">${foodSupVO.food_sup_resume}</textarea>
					<script>CKEDITOR.replace('food_sup_resume');</script>
					</td>
				</tr>
			</table>
				<br>
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="food_sup_ID" value="${foodSupVO.food_sup_ID}">
				<input type="hidden" name="food_sup_status" value="${foodSupVO.food_sup_status}">
				<button type="submit" class="btn pixel-btn wow fadeInUp"
												data-wow-delay="300ms">送出</button>
										</form>
									</div>
								</div>
							</div>

						</div>
		</section>
		<!-- ##### Contact Area End #####-->



	</section>
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
