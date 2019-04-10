<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cust.model.CustVO,com.broadcast.model.BroadcastVO"%>
	<jsp:useBean id="broadcastSvc" scope="page"
			class="com.broadcast.model.BroadcastService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title -->
<title>Pixel - Digital Agency HTML Template</title>

<!-- Favicon -->
<link rel="icon"
	href="<%=request.getContextPath()%>/froTempl/temp/img/core-img/favicon.ico">

<!-- Stylesheet -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/froTempl/temp/style.css">
<link
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css"
	rel="stylesheet">

<!-- Font-family -->	
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=ZCOOL+QingKe+HuangYou">

<style>

/* :: 12.0 Blog Area CSS */
.breadcrumb-area1 {
  position: relative;
  z-index: 1;
  width: 100%;
  height: 380px;
  padding: 0 35px; }
  @media only screen and (min-width: 992px) and (max-width: 1199px) {
    .breadcrumb-area1 {
      height: 300px; } }
  @media only screen and (min-width: 768px) and (max-width: 991px) {
    .breadcrumb-area1 {
      height: 280px; } }
  @media only screen and (max-width: 767px) {
    .breadcrumb-area1 {
      height: 250px; } }
  .breadcrumb-area1::after {
    content: '';
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: -1;
    top: 0;
    left: 0;
    background-color: rgba(163, 163, 163, 0.3); }
  .breadcrumb-area1 .breadcrumb-content {
    text-align: center; }
    .breadcrumb-area1 .breadcrumb-content h2 {
      color: #ffffff;
      font-size: 72px;
      text-transform: capitalize;
      margin-bottom: 0;
      display: inline-block; }
      @media only screen and (min-width: 992px) and (max-width: 1199px) {
        .breadcrumb-area1 .breadcrumb-content h2 {
          font-size: 42px; } }
      @media only screen and (min-width: 768px) and (max-width: 991px) {
        .breadcrumb-area1 .breadcrumb-content h2 {
          font-size: 36px; } }
      @media only screen and (max-width: 767px) {
        .breadcrumb-area1 .breadcrumb-content h2 {
          font-size: 24px; } }

</style>
</head>

<body>
 <br>
	  
	 <section class="breadcrumb-area1 bg-img bg-overlay jarallax" style="background-image: url(<%=request.getContextPath()%>/froTempl/temp/img/bg-img/F4_2.png);">
        <div class="container-fluid h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="breadcrumb-content">
                        <h2 style="font-family:'ZCOOL QingKe HuangYou';">主&nbsp;厨&nbsp;專&nbsp;區</h2>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Breadcrumb Area End ##### -->
	
	<!-- ##### All Javascript Script ##### -->
	<!-- jQuery-2.2.4 js -->
	<script
		src="<%=request.getContextPath()%>/froTempl/temp/js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Popper js -->
	<script
		src="<%=request.getContextPath()%>/froTempl/temp/js/bootstrap/popper.min.js"></script>
	<!-- Bootstrap js -->
	<script
		src="<%=request.getContextPath()%>/froTempl/temp/js/bootstrap/bootstrap.min.js"></script>
	<!-- All Plugins js -->
	<script
		src="<%=request.getContextPath()%>/froTempl/temp/js/plugins/plugins.js"></script>
	<!-- Active js -->
	<script src="<%=request.getContextPath()%>/froTempl/temp/js/active.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>


	

</html>
