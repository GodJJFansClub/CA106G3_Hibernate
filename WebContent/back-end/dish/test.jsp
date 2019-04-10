<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
	
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增</title>



</head>

<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/empMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>

				<span class="hide-menu"><h3>資料新增:</h3></span>
				<div class="col-lg-8 col-xlg-9 col-md-7">
					<div class="card">
						<div class="card-body">
							<form METHOD="post"
								ACTION="<%=request.getContextPath()%>/emp/emp.do" name="form1"
								enctype="multipart/form-data">
								<div class="form-group">
									<label class="col-md-12">員工帳號:</label>
									<div class="col-md-12">
										<input type="TEXT" name="emp_acc" size="45"
					value="<%=(empVO == null) ? "Aa158556" : empVO.getEmp_acc()%>" />
                                        
									</div>
								</div>

								<div class="form-group">
									<label for="example-email" class="col-md-12">員工姓名:</label>
									<div class="col-md-12">
										<input type="TEXT" name="emp_name" size="45"
											value="<%=(empVO == null) ? "teddy" : empVO.getEmp_name()%>" />
									</div>
									<div class="form-group">
										
											<label class="col-md-12">員工大頭照:</label>
											<div class="col-md-12">
												<input type="file" name="emp_pic" size="45" id="doc"
													onchange="javascript:setImagePreview();" />
											</div>
									</div>
									<div class="form-group">
										<div class="col-sm-12">
											<button class="btn btn-success" type="submit">送出新增</button>
											<div id="localImag">
												<img id="preview" width=-1 height=-1 style="diplay: none" />
											</div>
											<br> <input type="hidden" name="action" value="insert">

										</div>


									</div>
							</form>
						</div>
					</div>
				</div>
				<%--=================================工作區================================================--%>


				<script>
	function setImagePreview() {
		var docObj = document.getElementById("doc");
		var imgObjPreview = document.getElementById("preview");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '200px';
			imgObjPreview.style.height = '120px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL();
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");
			//必须设置初始大小
			localImagId.style.width = "250px";
			localImagId.style.height = "200px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}
</script>


			
			</div>
		</div>
	</div>
</body>
</html>