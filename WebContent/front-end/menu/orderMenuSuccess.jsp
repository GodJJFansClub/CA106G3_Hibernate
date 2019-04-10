<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!-- Font-family -->	
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=ZCOOL+QingKe+HuangYou">
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerJoin.jsp" flush="true" />

	<!-- ##### Contact Area Start #####-->
	
	<br>
	<br>
	
	
	
	<div class="pixel-portfolio-area section-padding-100-0">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Section Heading -->
                    <div class="section-heading text-center wow fadeInUp" data-wow-delay="100ms">
                        <img src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/logo_cut.gif" width="200px"
                         height="500px"alt="">
                         <font style="font-size:60px;font-family:'ZCOOL QingKe HuangYou';">訂單已送出，請等待主厨審核。</font>
                        <br><br>
                        <font style="font-size:36px;font-family:'ZCOOL QingKe HuangYou';">將於 <span id='timer'>5</span> 秒後，跳轉至首頁。</font>
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
<script type='text/javascript'>
	setTimeout('countdown()', 1000);

	function countdown() {
		var s = document.getElementById('timer');
		s.innerHTML = s.innerHTML - 1;
		if (s.innerHTML == 0)
			window.location = '<%=request.getContextPath()%>/front-end/index.jsp';
		else
			setTimeout('countdown()', 1000);
	}
</script>
</html>