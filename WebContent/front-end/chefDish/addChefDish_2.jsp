<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chefDish.model.*"%>

<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>主廚擅長菜色</title>
</head>
<body>
	<div class="card text-center" style="background-color: #D4E6F1">
		<div class="card-body">
			<h5 class="card-title">主廚擅長菜色</h5>
			<a
				href="<%=request.getContextPath()%>/front-end/chef/chef_profile.jsp"
				class="btn btn-primary">回上一頁</a>
		</div>
	</div>

	<%--Error Message --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red; font-size: 20px;">Error:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<div class="container-fliud">
		<div class="row">
			<div class="col-6">
				<%@ include file="listAllByChefID.jsp"%>
			</div>
			<div class="col-6">
				<div class="container">
					<div class="row">
						<div class="col-12">
							<form method="post"
								action="<%=request.getContextPath() %>/chefDish/chefDish.do">

								<div class="form-group">
									<jsp:useBean id="chefSvc" scope="page"
										class="com.chef.model.ChefService" />
									<label>主廚編號</label> <input type="text" readonly
										class="form-control" name="chef_ID" value="${chefVO.chef_ID}" />
								</div>

								<jsp:useBean id="dishSvc" scope="page"
									class="com.dish.model.DishService" />
								請選擇欲新增菜色 <select size="1" name="dish_ID" class="form-control">
									<option value="請選擇新增菜色">請選擇新增菜色
										<c:forEach var="dishVO" items="${dishSvc.all}">
											<option value="${dishVO.dish_ID}">${dishVO.dish_name}
										</c:forEach>
								</select> <br> <input type="hidden" name="action" value="insert">
								<input type="submit" class="btn btn-success btn-lg btn-block"
									value="新增菜色">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous">
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous">
	</script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous">
	</script>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<% 
  java.sql.Timestamp chef_sch_date = null;
  try {
	  	chef_sch_date = java.sql.Timestamp.valueOf(request.getParameter("chef_sch_date").trim());
   } catch (Exception e) {
	    chef_sch_date = null;
   }
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#sch_date').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       //step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d' ,         //format:'Y-m-d H:i:s',
		   //value: '<%=chef_sch_date%>' , // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           maxDate:               '+1970-04-01'  // 去除今日(不含)之後
        });
</script>
</html>