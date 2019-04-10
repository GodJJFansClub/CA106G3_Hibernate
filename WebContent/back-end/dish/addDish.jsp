<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dish.model.*"%>

<%
	DishVO dishVO = (DishVO) request.getAttribute("dishVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

</head>

<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/dishFoodMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>
				
 	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">新增菜色</font></div>
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


				<div class="col-lg-8 col-xlg-9 col-md-7">
					<div class="card">
						<div class="card-body">
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/dish/dish.do" name="form1"
								enctype="multipart/form-data"
								class="form-horizontal form-material">
								<div class="form-group">
									<label class="col-md-12">菜色名稱:</label>
									<div class="col-md-12">
										<input type="TEXT" name="dish_name" size="42"
											value="<%=(dishVO == null) ? "請輸入菜色名稱" : dishVO.getDish_name()%>" />
									</div>
								</div>
								<div class="form-group">
									<label for="example-email" class="col-md-12">菜色照片:</label>
									<div class="col-md-12">
										<img id="preview_progressbarTW_img"
											src="<%=request.getContextPath()%>/images/null2.jpg"
											width="300" height="200" /> <br> <input type="file"
											name="dish_pic" size="43" id="progressbarTWInput"
											value="<%=(dishVO == null) ? "請輸入照片" : dishVO.getDish_pic()%>" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-12">菜色內容:</label>
									<div class="col-md-12">
										<script
											src="<%=request.getContextPath()%>/publibrary/ckeditor4/ckeditor.js"></script>
										<textarea rows="20" cols="40" name="dish_resume">
       									 </textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-12">菜色價格:</label>
									<div class="col-md-12">
										<input type="TEXT" name="dish_price" size="43"
											value="<%=(dishVO == null) ? "請輸入菜色價格" : dishVO.getDish_price()%>" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-12">

										<button class="btn btn-success" type="submit">送出新增</button><br>
										<input type="hidden" name="dish_status" value="D0"> <input
											type="hidden" name="action" value="insert"> <img
											src="<%=request.getContextPath()%>/images/x.png" height="20"
											width="20" onClick="idwrite(this)">
										<script>
											$("#progressbarTWInput").change(
													function() {

														readURL(this);

													});

											function readURL(input) {

												if (input.files
														&& input.files[0]) {

													var reader = new FileReader();

													reader.onload = function(e) {

														$(
																"#preview_progressbarTW_img")
																.attr(
																		'src',
																		e.target.result);

													}

													reader
															.readAsDataURL(input.files[0]);

												}

											}
										</script>

										<script>
											function idwrite(name) {
												form1.dish_name.value = "蘋果烏魚子"
												form1.dish_price.value = "808"
												form1.dish_resume.value ="   除了搭配蒜苗還有水果，可上桌做年菜，還能當茶點，這一道就是將烏魚子組合配料做成茶點、下酒菜，搭配水果及異國配料更添不同風味，而且是大人小孩都可食用。"
											}
										</script>
									</div>
								</div>
							</form>
						</div>

						<br>
					</div>
				</div>




		
			</div>
		</div>
	</div>
</body>
</html>