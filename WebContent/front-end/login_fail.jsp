<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<!-- Font-family -->	
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=ZCOOL+QingKe+HuangYou">
</head>
<body>

<jsp:include page="/froTempl/header.jsp" flush="true" />

	<!-- ##### Contact Area Start #####-->
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	
	
	<div class="pixel-portfolio-area section-padding-100-0">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Section Heading -->
                    <div class="section-heading text-center wow fadeInUp" data-wow-delay="100ms">
                        <img src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/logo_cut.gif"  width="200px" height="500px"alt="">
                        <font style="font-size:80px;font-family:'ZCOOL QingKe HuangYou';">帳號密碼錯誤</font>
                        <br><br><br>
                        <a href="<%=request.getContextPath()%>/front-end/loginFrontEnd.jsp" class="btn pixel-btn m-2" style="font-weight:normal;font-size:24px;font-family:'ZCOOL QingKe HuangYou';margin-top:20px;">請重新登入</a>
                        
                    </div>
                </div>
            </div>
        </div>

      
        <div class="pixel-portfolio">

         
        </div>
    </div>
    <!-- ##### Portfolio Area End ###### -->
	
	
	
	
	
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<!-- ##### Contact Area End #####-->



	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>