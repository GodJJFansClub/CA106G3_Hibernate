<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cust.model.CustVO,com.ad.model.AdVO,com.chef.model.ChefVO,com.menu.model.*,com.foodMall.model.*"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="adSvc" class="com.ad.model.AdService"/>
<jsp:useBean id="chefSvc" class="com.chef.model.ChefService"/>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<jsp:useBean id="menuSvc" class="com.menu.model.MenuService"/>
<jsp:useBean id="foodMallSvc" class="com.foodMall.model.FoodMallService"/>
<%
	HttpSession sessiond = request.getSession();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta HTTP-EQUIV="Refresh" content="120">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title -->
<title>食神來了</title>

<!-- Favicon -->
<link rel="icon"
	href="<%=request.getContextPath()%>/froTempl/temp/img/core-img/favicon.ico">

<!-- Stylesheet -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/froTempl/temp/style.css">


<style>
	.pixel-projects-menu {
  position: relative;
  z-index: 1;
  margin-bottom: 30px; }
  .pixel-projects-menu .btn {
    padding: 10px 20px;
    background-color: transparent;
    font-size: 14px;
    text-transform: capitalize;
    font-weight: 500;
    color: #969696;
    border-radius: 50px;
    border: 2px solid transparent; }
    .pixel-projects-menu .btn.active, .pixel-projects-menu .btn:focus {
      box-shadow: none;
      color: #ff7902;
      border-color: #ff7902; }

.single_gallery_item {
  display: inline-block;
  float: left;
  position: relative;
  z-index: 2;
  width: 33.3333334%;
  overflow: hidden;
  -webkit-transition-duration: 500ms;
  -o-transition-duration: 500ms;
  transition-duration: 500ms; }
  @media only screen and (max-width: 767px) {
    .single_gallery_item {
      width: 100%; } }
  .single_gallery_item img {
    width: 100%;
    -webkit-transition-duration: 2000ms;
    -o-transition-duration: 2000ms;
    transition-duration: 2000ms; }
  .single_gallery_item .hover-content1 {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(92, 92, 92, 0.7);
    opacity: 0;
    visibility: hidden;
    -webkit-transition-duration: 500ms;
    -o-transition-duration: 500ms;
    transition-duration: 500ms; }
    .single_gallery_item .hover-content1 .zoom-img {
      display: inline-block;
      width: 50px;
      height: 50px;
      border-radius: 50%;
      border: 2px solid #ffffff;
      font-size: 20px;
      margin-bottom: 30px;
      color: #ffffff;
      line-height: 46px;
      margin-top: -15px;
      opacity: 0;
      -webkit-transition-duration: 500ms;
      -o-transition-duration: 500ms;
      transition-duration: 500ms; }
    .single_gallery_item .hover-content1 h4 {
      margin-bottom: 0;
      color: #ffffff; }
      @media only screen and (min-width: 992px) and (max-width: 1199px) {
        .single_gallery_item .hover-content1 h4 {
          font-size: 20px; } }
      @media only screen and (min-width: 768px) and (max-width: 991px) {
        .single_gallery_item .hover-content1 h4 {
          font-size: 16px; } }
      @media only screen and (max-width: 767px) {
        .single_gallery_item .hover-content1 h4 {
          font-size: 16px; } }
  .single_gallery_item:hover img {
    -webkit-transform: scale(1.1);
    -ms-transform: scale(1.1);
    transform: scale(1.1); }
  .single_gallery_item:hover .hover-content1 {
    opacity: 1;
    visibility: visible; }
    .single_gallery_item:hover .hover-content1 .zoom-img {
      margin-top: 0;
      opacity: 1; }
    
    .single-catagory .hover-content {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(92, 92, 92, 0.7);
    opacity: 0;
    visibility: hidden;
    -webkit-transition-duration: 500ms;
    -o-transition-duration: 500ms;
    transition-duration: 500ms; }
    .single-catagory .hover-content .zoom-img {
      display: inline-block;
      width: 50px;
      height: 50px;
      border-radius: 50%;
      border: 2px solid #ffffff;
      font-size: 20px;
      margin-bottom: 30px;
      color: #ffffff;
      line-height: 46px;
      margin-top: -15px;
      opacity: 0;
      -webkit-transition-duration: 500ms;
      -o-transition-duration: 500ms;
      transition-duration: 500ms; }
    .single-catagory .hover-content h4 {
      margin-bottom: 0;
      color: #ffffff; }
      @media only screen and (min-width: 992px) and (max-width: 1199px) {
        .single-catagory .hover-content h4 {
          font-size: 20px; } }
      @media only screen and (min-width: 768px) and (max-width: 991px) {
        .single-catagory .hover-content h4 {
          font-size: 16px; } }
      @media only screen and (max-width: 767px) {
        .single-catagory .hover-content h4 {
          font-size: 16px; } }
  .single-catagory:hover img {
    -webkit-transform: scale(1.1);
    -ms-transform: scale(1.1);
    transform: scale(1.1); }
  .single-catagory:hover .hover-content {
    opacity: 1;
    visibility: visible; }
    .single-catagory:hover .hover-content .zoom-img {
      margin-top: 0;
      opacity: 1; }
	</style>	
	
	

</head>

<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />


	<!-- ##### Hero Area Start ##### -->
	<br>
	<section class="hero-area">
		<div id="adWall" class="hero-slideshow owl-carousel">


			<!-- Single Slide-->
			<div class="single-slide">
				<!-- Background Image-->
				<div class="slide-bg-img bg-img"
					style="background-image: url(<%=request.getContextPath()%>/froTempl/temp/img/bg-img/1.jpg);"></div>
				<!-- Welcome Text -->
				<div class="container h-100">
					<div class="row h-100 align-items-center">
						<div class="col-12 col-lg-9">
							<div class="welcome-text">
								<h2 data-animation="fadeInUp" data-delay="300ms">
									<span>Hungry?</span><br>Ready to Order
								</h2>
								<h4 data-animation="fadeInUp" data-delay="500ms">
									See the Cook God<span></span>
								</h4>
								
							</div>
						</div>
					</div>
				</div>
			</div>  
		<c:forEach var="adVO" items="${adSvc.allNowAd}">	
			<!-- Single Slide -->
            <div class="single-slide">
                <!-- Background Image-->
                <div class="slide-bg-img bg-img" style="background-image:url(<%=request.getContextPath()%>/ad/ad.do?ad_ID=${adVO.ad_ID});"></div>
                <!-- Welcome Text -->
                <div class="container h-100">
                    <div class="row h-100 align-items-center">
                        <div class="col-12 col-lg-9">
                            <div class="welcome-text">
                                <h2 data-animation="fadeInUp" data-delay="300ms"><span>${adVO.ad_title}</span><br>In Cook God</h2>
                                <h4 data-animation="fadeInUp" data-delay="500ms">${adVO.ad_con}<span></span></h4>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>  
		</c:forEach>
			
		</div>
	</section>

	

	<!-- ##### Hero Area End ##### -->
	
	
	 <!-- ##### Top Catagory Area Start ##### -->
    <div class="top-catagory-area d-flex flex-wrap">
        <!-- Single Catagory -->
        <div class="single-catagory bg-img d-flex align-items-center justify-content-center jarallax" style="background-image: url(<%=request.getContextPath()%>/froTempl/temp/img/bg-img/F4_2.png);">
            <a href="<%=request.getContextPath()%>/front-end/chef/listAllChef.jsp" style="font-weight:bold"><font color='#000000' onmouseout="this.style.color ='#000000';" onmouseover="this.style.color ='#FF7B0F';" style="color:black; font-weight:bold; font-family: Microsoft JhengHei;">瀏覽主廚</font></a>
            
        </div>
        <!-- Single Catagory -->
        <div class="single-catagory bg-img d-flex align-items-center justify-content-center jarallax" style="background-image: url(<%=request.getContextPath()%>/froTempl/temp/img/bg-img/4.jpg);">
            <a href="<%=request.getContextPath()%>/front-end/menu/menu.jsp" style="font-weight:bold;"><font >嚴選套餐</font></a>
        </div>
    </div>
    <!-- ##### Top Catagory Area End ##### -->
    
     <!-- ##### Cool Facts Area Start ##### -->
    <section class="pixel-cool-facts-area bg-gray section-padding-100-0">
        <div class="container-fluid">
            <div class="row">

                <!-- Single Cool Facts -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-cool-fact mb-100">
                        <div class="scf-icon">
                            <img src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/m1.png" alt="">
                        </div>
                        <div class="scf-text">
                            <h2><span class="counter1"  style="font-size:60px">${chefSvc.selectChefTotal}.0</span></h2>
                            <h6 style="font-size:30px">嚴選主廚</h6>
                        </div>
                    </div>
                </div>

                <!-- Single Cool Facts -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-cool-fact mb-100">
                        <div class="scf-icon">
                            <img src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/m5.png" alt="">
                        </div>
                        <div class="scf-text">
                            <h2><span class="counter1"  style="font-size:60px">${custSvc.selectCustTotal}.0</span></h2>
                            <h6 style="font-size:30px">食神的顧客</h6>
                        </div>
                    </div>
                </div>

                <!-- Single Cool Facts -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-cool-fact mb-100">
                        <div class="scf-icon">
                            <img src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/m6.png" alt="">
                        </div>
                        <div class="scf-text">
                            <h2><span class="counter1"  style="font-size:60px">${menuSvc.selectMenuTotal}.0</span></h2>
                            <h6 style="font-size:30px">嚴選套餐</h6>
                        </div>
                    </div>
                </div>

                <!-- Single Cool Facts -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-cool-fact mb-100">
                        <div class="scf-icon">
                            <img src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/m7.png" alt="">
                        </div>
                        <div class="scf-text">
                            <h2 ><span class="counter1" style="font-size:60px">${foodMallSvc.selectFoodMallTotal}.0</span></h2>
                            <h6 style="font-size:30px">嚴選食材</h6>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>
    <!-- ##### Cool Facts Area End ##### -->
    
     <!-- ##### Portfolio Area Start ###### -->
    <div class="pixel-portfolio-area section-padding-100-0">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Section Heading -->
                    <div class="section-heading text-center wow fadeInUp" data-wow-delay="100ms">
                        <h2 style="font-weight:bold">嚴選食材</h2>
                        <img src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/logo_cut.gif" style="width:200px;height=500px" "alt="">
                    </div>
                </div>
            </div>
        </div>

         <!-- Portfolio Menu -->
       
        <div class="pixel-portfolio">

            <!-- Single gallery Item -->
            <div class="single_gallery_item visual wow fadeInUp" data-wow-delay="0.2s">
                <img src="<%=request.getContextPath()%>/froTempl/temp/img/bg-img/foodMall3.jpg" alt="">
                <div class="hover-content1 text-center1 d-flex align-items-center justify-content-center">
                    <div class="hover-text">
                        <a href="<%=request.getContextPath()%>/front-end/foodMall/listFoodMall.jsp" class="zoom-img"><i class="fa fa-eye" aria-hidden="true"></i></a>
                        <h3 style="color:white" >不幸的雞</h3>
                    </div>
                </div>
            </div>

            <!-- Single gallery Item -->
            <div class="single_gallery_item add wow fadeInUp" data-wow-delay="0.4s">
                <img src="<%=request.getContextPath()%>/froTempl/temp/img/bg-img/foodMall2.jpg" alt="">
                <div class="hover-content1 text-center1 d-flex align-items-center justify-content-center">
                    <div class="hover-text">
                        <a href="<%=request.getContextPath()%>/front-end/foodMall/listFoodMall.jsp" class="zoom-img"><i class="fa fa-eye" aria-hidden="true"></i></a>
                        <h3 style="color:white" >威靈頓牛 </h3>
                    </div>
                </div>
            </div>

            <!-- Single gallery Item -->
            <div class="single_gallery_item web wow fadeInUp" data-wow-delay="0.6s">
                <img src="<%=request.getContextPath()%>/froTempl/temp/img/bg-img/foodMall1.jpg" alt="">
                <div class="hover-content1 text-center1 d-flex align-items-center justify-content-center">
                    <div class="hover-text">
                        <a href="<%=request.getContextPath()%>/front-end/foodMall/listFoodMall.jsp" class="zoom-img"><i class="fa fa-eye" aria-hidden="true"></i></a>
                        <h3 style="color:white">嗆辣紅椒</h3>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- ##### Port<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>folio Area End ###### -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
	<script>
	    var MyPoint = "/WebSocketForServlet";
		var host = window.location.host;
		var webCtx = '<%=request.getContextPath()%>';
		var endPointURL = "ws://" + host + webCtx + "/" + MyPoint;

		var adWebSocket;
		$(document).ready(function() {
			//adWSconnect();
			$('#adWall').owlCarousel({
	            items: 1,
	            loop: true,
	            nav: true,
	            navText: ['Prev', 'Next'],
	            dots: false,
	            autoplay: true,
	            autoplayTimeout: 10000,
	            smartSpeed: 500,
	            animateIn: 'fadeIn',
	            animateOut: 'fadeOut'
	        });
		});
		// 觸發connect()時註冊方法, 並建立WebSocket物件

		
		window.onload= function(){
			<% 	CustVO custVO = (CustVO)sessiond.getAttribute("custVO");
				if (custVO!=null){
			%>
				var user = ''+'<%=custVO.getCust_status()%>';
			<%}else{%>
				var user =null;
			<%}%>
				var filter =''+'<%=session.getAttribute("filter")%>'
			
				if(user === "a1"){
					swal("驗證錯誤","此帳號尚未通過驗證，趕快去E-Mail收信哦!","error")
				}else if(filter === "a1"){
					swal("驗證錯誤","此帳號尚無權限","error")				
				}
			}
	</script>
	<script>
	var browserWindow = $(window);

	  // :: 7.0 ScrollUp Active Code
    if ($.fn.scrollUp) {
        browserWindow.scrollUp({
            scrollSpeed: 10,
            scrollText: '<i class="fa fa-angle-up"></i> Top'
        });
    }

    // :: 8.0 CounterUp Active Code
    if ($.fn.counterUp) {
        $('.counter1').counterUp({
            delay: 10,
            time: 2000
        });
    }
	</script>
</body>
</html>