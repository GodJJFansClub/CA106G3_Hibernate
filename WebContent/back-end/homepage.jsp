<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<!-- Font-family -->	
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=ZCOOL+QingKe+HuangYou">
</head>
<style>
	body, .form-all{
		background:none !important;
	}
	*{
		background-image: url("bakimg2.jpg");
	}
	.outer{
	  height: 1em;
	  margin: 0 1em 0 1em;
	}
	.content{
	  border: 5px double white;
	  border-left: none;
	  border-right: none;
	  color:white;
	}
	.content:hover{
	  border: 5px double orange;
	  border-left: none;
	  border-right: none;
	  color:orange;
	}
	.innerContent{
	  margin: 0 1em 0 1em;
	}
	.borderLeftRight{
	  border-left: 3px solid white;
	  border-right: 3px solid white;
	}
</style>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light" data-layout="vertical" data-sidebartype="full" data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
	</div>
	<div class="container-fliud">
		<div class="row" style="margin-top:180px;">
			<div class="col-2"></div>
			<div class="col-2">
				<div class="outer borderLeftRight"></div>
				<div class="content">
					<a class="hpg" href="<%=request.getContextPath()%>/back-end/foodSup/listAllFoodSup.jsp" style="font-family:'ZCOOL QingKe HuangYou';color:white;">
						<div class="innerContent borderLeftRight" style="text-align:center;">
					    	<font style="font-size:28px;">合作夥伴</font>
						</div>
					</a>
				</div>
				<div class="outer borderLeftRight"></div>
			</div>
			<div class="col-1"></div>
			<div class="col-2">
				<div class="outer borderLeftRight"></div>
				<div class="content">
					<a class="hpg" href="<%=request.getContextPath()%>/back-end/cust/listAllCust.jsp" style="font-family:'ZCOOL QingKe HuangYou';color:white;">
						<div class="innerContent borderLeftRight" style="text-align:center;">
					    	<font style="font-size:28px;">顧客資料管理</font>
						</div>
					</a>
				</div>
				<div class="outer borderLeftRight"></div>
			</div>
			<div class="col-1"></div>
			<div class="col-2">
				<div class="outer borderLeftRight"></div>
				<div class="content">
					<a class="hpg" href="<%=request.getContextPath()%>/back-end/foodMall/listAllFoodMall.jsp" style="font-family:'ZCOOL QingKe HuangYou';color:white;">
						<div class="innerContent borderLeftRight" style="text-align:center;">
					    	<font style="font-size:28px;">商城管理</font>
						</div>
					</a>
				</div>
				<div class="outer borderLeftRight"></div>			
			</div>
			<div class="col-2"></div>
		</div>
		<div class="row" style="margin-top:120px;">
			<div class="col-2"></div>
			<div class="col-2">
				<div class="outer borderLeftRight"></div>
				<div class="content">
					<a class="hpg" href="<%=request.getContextPath()%>/back-end/dish/AllDish.jsp" style="font-family:'ZCOOL QingKe HuangYou';color:white;">
						<div class="innerContent borderLeftRight" style="text-align:center;">
					    	<font style="font-size:28px;">菜色食材管理</font>
						</div></a>
				</div>
				<div class="outer borderLeftRight"></div>
			</div>
			<div class="col-1">
			</div>
			<div class="col-2">
				<div class="outer borderLeftRight"></div>
				<div class="content">
					<a class="hpg" href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp" style="font-family:'ZCOOL QingKe HuangYou';color:white;">
						<div class="innerContent borderLeftRight" style="text-align:center;">
					    	<font style="font-size:28px;">員工管理</font>
						</div>
					</a>
				</div>
				<div class="outer borderLeftRight"></div>
			</div>
			<div class="col-1"></div>
			<div class="col-2">
				<div class="outer borderLeftRight"></div>
				<div class="content">
					<a class="hpg" href="<%=request.getContextPath()%>/back-end/ad/listAllAd.jsp" style="font-family:'ZCOOL QingKe HuangYou';color:white;">
						<div class="innerContent borderLeftRight" style="text-align:center;">
					    	<font style="font-size:28px;">廣告管理</font>
						</div>
					</a>
				</div>
				<div class="outer borderLeftRight"></div>			
			</div>
			<div class="col-2"></div>
		</div>
	</div>
</body>
</html>