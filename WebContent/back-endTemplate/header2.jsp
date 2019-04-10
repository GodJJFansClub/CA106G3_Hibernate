<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>CA106G3</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/back-endTemplate/assets/libs/chartist/dist/chartist.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/back-endTemplate/dist/css/style.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css"
	rel="stylesheet">
	
	<style type="text/css">
		.topbar {
			background:#2E4053;
		}
	</style>
	
</head>
<body>
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="<%=request.getContextPath()%>/back-endTemplate/assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="<%=request.getContextPath()%>/back-endTemplate/assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-endTemplate/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="<%=request.getContextPath()%>/back-endTemplate/assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <script src="<%=request.getContextPath()%>/back-endTemplate/dist/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="<%=request.getContextPath()%>/back-endTemplate/dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="<%=request.getContextPath()%>/back-endTemplate/dist/js/custom.min.js"></script>
    <!--This page JavaScript -->
    <!--chartis chart-->
    <script src="<%=request.getContextPath()%>/back-endTemplate/assets/libs/chartist/dist/chartist.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-endTemplate/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-endTemplate/dist/js/pages/dashboards/dashboard1.js"></script>
    <script src="<%=request.getContextPath()%>/publibrary/ckeditor4/ckeditor.js"></script>
    <script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	<header class="topbar" data-navbarbg="skin6">
		<nav class="navbar top-navbar navbar-expand-md navbar-light">
			<div class="navbar-header" data-logobg="skin5">

				<!-- ============================================================== -->
				<!-- End Logo -->
				<!-- ============================================================== -->
				<!-- ============================================================== -->
				<!-- Toggle which is visible on mobile only -->
				<!-- ============================================================== -->
				
			</div>
			<!-- ============================================================== -->
			<!-- End Logo -->
			<!-- ============================================================== -->
			<div class="navbar-collapse collapse" id="navbarSupportedContent"
				data-navbarbg="skin6">
				<!-- ============================================================== -->
				<!-- toggle and nav items -->
				<!-- ============================================================== -->
				<ul class="navbar-nav float-left mr-auto">
					<!-- ============================================================== -->
					<!-- Search -->
					<!-- ============================================================== -->
					<li class="nav-item"><a
						class="nav-link waves-effect waves-dark"
						href="<%=request.getContextPath()%>/back-end/foodSup/listAllFoodSup.jsp">
							<div class="d-flex align-items-center">
								<i class="font-20 mr-1"></i>
								<div class="ml-1 d-none d-sm-block" style="font-family:Microsoft JhengHei;font-size:18px;font-weight:bold;color:white;">
									<span>合作夥伴</span>
								</div>
							</div>
					</a></li>
					<li class="nav-item"><a
						class="nav-link waves-effect waves-dark"
						href="<%=request.getContextPath()%>/back-end/cust/listAllCust.jsp">
							<div class="d-flex align-items-center">
								<i class="font-20 mr-1"></i>
								<div class="ml-1 d-none d-sm-block" style="font-family:Microsoft JhengHei;font-size:18px;font-weight:bold;color:white;">
									<span>顧客資料管理</span>
								</div>
							</div>
					</a></li>
					
					<li class="nav-item"><a
						class="nav-link waves-effect waves-dark"
						href="<%=request.getContextPath()%>/back-end/foodMall/listAllFoodMall.jsp">
							<div class="d-flex align-items-center">
								<i class="font-20 mr-1"></i>
								<div class="ml-1 d-none d-sm-block" style="font-family:Microsoft JhengHei;font-size:18px;font-weight:bold;color:white;">
									<span>商城管理</span>
								</div>
							</div>
					</a></li>
					
					<li class="nav-item"><a
						class="nav-link waves-effect waves-dark"
						href="<%=request.getContextPath()%>/back-end/dish/AllDish.jsp">
							<div class="d-flex align-items-center">
								<i class="font-20 mr-1"></i>
								<div class="ml-1 d-none d-sm-block" style="font-family:Microsoft JhengHei;font-size:18px;font-weight:bold;color:white;">
									<span>菜色及食材管理</span>
								</div>
							</div>
					</a></li>
					
					<li class="nav-item"><a
						class="nav-link waves-effect waves-dark"
						href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp">
							<div class="d-flex align-items-center">
								<i class="font-20 mr-1"></i>
								<div class="ml-1 d-none d-sm-block" style="font-family:Microsoft JhengHei;font-size:18px;font-weight:bold;color:white;">
									<span>員工管理</span>
								</div>
							</div>
					</a></li>
					<li class="nav-item"><a
						class="nav-link waves-effect waves-dark"
						href="<%=request.getContextPath()%>/back-end/ad/listAllAd.jsp">
							<div class="d-flex align-items-center">
								<i class="font-20 mr-1"></i>
								<div class="ml-1 d-none d-sm-block" style="font-family:Microsoft JhengHei;font-size:18px;font-weight:bold;color:white;">
									<span>廣告管理</span>
								</div>
							</div>
					</a></li>
					<li class="nav-item"><a
						class="nav-link waves-effect waves-dark">
							<div class="d-flex align-items-center" style="margin-left:150px;">
								<i class="font-20 mr-1"></i>
								<div class="ml-1 d-none d-sm-block" style="font-family:Microsoft JhengHei;font-size:18px;font-weight:bold;color:white;">
									<span>歡迎:<font style="text-transform:capitalize;font-family:Microsoft JhengHei;font-size:18px;font-weight:bold;color:red;"> ${empVO.emp_name} </font>您好
									</span>
								</div>
							</div>
					</a></li>
					<li class="nav-item"><a
						class="nav-link waves-effect waves-dark">
							<div class="d-flex align-items-center">
								<i class="font-20 mr-1"></i>
								<div class="ml-1 d-none d-sm-block">
									<FORM METHOD="get"
										action="<%=request.getContextPath()%>/back-end/logoutBack.do">
										<input type="submit" class="btn btn-danger" value="登出" style="font-family:Microsoft JhengHei;font-size:18px;font-weight:bold;color:white;margin-top:12px;">
									</FORM>
								</div>
							</div>
					</a></li>
				</ul>
				
			</div>
		</nav>
	</header>
</body>
</html>