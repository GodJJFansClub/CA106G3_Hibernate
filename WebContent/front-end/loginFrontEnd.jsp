<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cust.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=Big5">
<!-- Font-family -->	
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=ZCOOL+QingKe+HuangYou">
<title>登入</title>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />

	<!-- ##### Contact Area Start #####-->
	<br>
	
	 <section class="contact-area section-padding-100">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Section Heading -->
                    <div class="section-heading text-center wow fadeInUp" data-wow-delay="100ms">
                        
                        <img src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/logo_cut.gif" width="200px"
                         height="500px"alt=""><br>
                        <font style="font-size:80px;font-family:'ZCOOL QingKe HuangYou';">"&nbsp;食&nbsp;神&nbsp;來&nbsp;了&nbsp;"</font>
                    </div>
                </div>
            </div>

            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <!-- Contact Form -->
                    <div class="contact-form-area text-center">
                        <form action="<%=request.getContextPath()%>/front-end/login.do" method="post">
                           
							<font style="font-size:28px;font-family:'ZCOOL QingKe HuangYou';">帳&nbsp;號</font>
							<input type=text name="account" class="form-control wow fadeInUp" data-wow-delay="300ms" placeholder="請輸入帳號..." autocomplete="off" style="font-size:20px;font-family:'ZCOOL QingKe HuangYou';">
							<font style="font-size:28px;font-family:'ZCOOL QingKe HuangYou';">密&nbsp;碼</font>
							<input type=password name="password" class="form-control wow fadeInUp" data-wow-delay="500ms" placeholder="請輸入密碼..." autocomplete="off" style="font-size:20px;font-family:'ZCOOL QingKe HuangYou';">
							<input type="hidden" name="action" value="cust">
							<button type="submit" class="btn pixel-btn wow fadeInUp" data-wow-delay="800ms" style="font-size:18px;font-family:'ZCOOL QingKe HuangYou';">L&nbsp;o&nbsp;g&nbsp;i&nbsp;n</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    <!-- ##### Contact Area End #####-->
	</section>
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>