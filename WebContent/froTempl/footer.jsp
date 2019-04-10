<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Pixel - Digital Agency HTML Template</title>

	<style type="text/css">
		#Main {
	　padding-bottom: 100px;
	}
	#front-footer {
	　height: 100px;
	　position: relative;
	　margin-top: -100px;
	}
	</style>
</head>

<body>
    
	
    <!-- ##### Footer Area Start ##### -->
    <footer id="front-footer" class="footer-area section-padding-100-0">
        <div class="container-fluid">
            <div class="row justify-content-between">

                <!-- Single Footer Widget -->
                <div class="col-12 col-sm-6 col-md-3">
                    <div class="single-footer-widget mb-100">
                        <!-- Footer Logo -->
                        <a href="<%=request.getContextPath()%>/front-end/index.jsp" class="footer-logo"><img src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/logo.png" alt=""></a>
                    </div>
                </div>

                <!-- Single Footer Widget -->
                <div class="col-12 col-md-9 col-lg-8 col-xl-7">
                    <div class="row">
                        <!-- Single Footer Widget -->
                        <div class="col-sm-4">
                            <div class="single-footer-widget mb-100">
                                <h5 class="widget-title">Address</h5>
                                <p>320桃園市<br> 中壢區中大路300號</p>
                            </div>
                        </div>

                        <!-- Single Footer Widget -->
                        <div class="col-sm-4">
                            <div class="single-footer-widget mb-100">
                                <h5 class="widget-title">Support</h5>
                                <p><i class="fa fa-phone"></i> <br>03 425 7387</p>
                            </div>
                        </div>

                        <!-- Single Footer Widget -->
                        <div class="col-sm-4">
                            <div class="single-footer-widget mb-100">
                                <h5 class="widget-title">Social</h5>
                                <div class="footer-social-info">
                                    <a href="https://www.facebook.com/iiichunglii/"><i class="fa fa-facebook"></i></a>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Copywrite Area -->
        <div class="copywrite-area">
            <div class="container-fluid">
                <div class="row align-items-center">
                    <div class="col-12 col-md-6">
                        <div class="copywrite-content">
                            <!-- Copywrite Text -->
                            <p class="copywrite-text"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> <i class="fa fa-heart-o" aria-hidden="true"></i> by <a target="_blank">CA106G3</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
</p>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <nav class="footer-nav">
                            <ul>
                                <li><a href="<%=request.getContextPath()%>/front-end/index.jsp">首頁</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/menu/listAllMenu.jsp">食神來了</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/foodMall/listFoodMall.jsp">嚴選食材</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/festMenu/listFestMall.jsp">節慶主題</a></li>
                                <li><a href="<%=request.getContextPath()%>/front-end/login/addCust.jsp">加入我們</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    
</body>
</html>