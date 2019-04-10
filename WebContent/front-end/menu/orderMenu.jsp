<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<%@ page import="com.dish.model.*"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="com.menuDish.model.*"%>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<jsp:useBean id="dishSvc" class="com.dish.model.DishService"/>
<jsp:useBean id="menuDishSvc" class="com.menuDish.model.MenuDishService"/>
<html>
<head>
<title>訂購套餐</title>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerCookGod.jsp" flush="true" />
	<section class="contact-area section-padding-100">
	<!-- ##### Contact Area Start #####-->
    	<%--Error Message--%>
	<c:if test="${not empty errorMsgs} }">
		<font style="color: red; font-size: 30px;">Error</font>
		<ul>
			<c:forEach var="errMsgs" items="${errorMsgs}">
				<li style="color: red;">${errMsgs}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="container" style="margin-top:50px;">
		<div class="row">
			<div class="col-12">
				<div class="container-fliud">
					<font style="font-size:16px;font-weight:bold;font-family: Microsoft JhengHei;">已選擇套餐<br></font>
					<div class="header"><h2 class="card-title" style="font-family:Microsoft JhengHei;font-weight:bold;font-size:36px;text-align:center">${menuVO.menu_name}</h2></div>
					<div class="row">
						<div class="col-5">
							<c:if test="${not empty menuVO.menu_pic}">
								<img class="card-img-top" name="showMenuPic" src="<%=request.getContextPath()%>/menu/menu.do?showMenuPic=showMenuPic&menu_ID=${menuVO.menu_ID}">
							</c:if>
							<c:if test="${empty menuVO.menu_pic}">
								<img class="card-img" src="<%=request.getContextPath()%>/images/noimage.jpg">
							</c:if>
						</div>
						<div class="col-7">
							<div class="card-body">
								<form method="post" action="<%=request.getContextPath()%>/menu/menu.do">
									<p class="card-text" style="height: 240px;" style="font-family:Microsoft JhengHei;">${menuVO.menu_resume}</p>
									<p class="card-text text-right" style="font-family:Microsoft JhengHei;font-weight:bold;font-size:18px;">$${menuVO.menu_price}</p>
									<input type="hidden" name="menu_ID" value="${menuVO.menu_ID}">
								</form>
							</div>
						</div>
					</div>
					<p class="card-text"><font style="font-size:16px;font-family: Microsoft JhengHei;">菜色詳情：
										<c:forEach var="menuDishVO" items="${menuDishSvc.getAllByMenuID(menuVO.menu_ID)}">
											${dishSvc.getOneDish(menuDishVO.dish_ID).dish_name}&nbsp;&nbsp;&nbsp;
										</c:forEach>
									</font>
				</div>
				<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>
				<c:if test="${listChefByMenuID!=null}">
					<p>請選擇地區主廚</p>
				</c:if>
				<div class="container-fliud">
					<div class="row">
						<div class="col-3">
							<p class="text-center">北部</p>
							<c:forEach var="chefVO" items="${listChefByMenuID}">
								<c:if test="${chefVO.chef_area == '0'}">
									<form method="post" action="<%=request.getContextPath()%>/chefSch/chefSch.do">
										<button type="submit" class="chef_ID btn btn-outline-secondary btn-block" name="chef_ID" value="${chefVO.chef_ID}" style="margin-top:5px;">${custSvc.getOneCust(chefVO.chef_ID).cust_name}</button>
										<input type="hidden" name="action" value="listChefSchByID">
									</form>
								</c:if>
							</c:forEach>
						</div>
						<div class="col-3">
							<p class="text-center">中部</p>
							<c:forEach var="chefVO" items="${listChefByMenuID}">
								<c:if test="${chefVO.chef_area == '1'}">
									<form method="post" action="<%=request.getContextPath()%>/chefSch/chefSch.do">
										<button type="submit" class="chef_ID btn btn-outline-secondary btn-block" name="chef_ID" value="${chefVO.chef_ID}" style="margin-top:5px;">${custSvc.getOneCust(chefVO.chef_ID).cust_name}</button>
										<input type="hidden" name="action" value="listChefSchByID">
									</form>
								</c:if>
							</c:forEach>
						</div>
						<div class="col-3">
							<p class="text-center">南部</p>
							<c:forEach var="chefVO" items="${listChefByMenuID}">
								<c:if test="${chefVO.chef_area == '2'}">
									<form method="post" action="<%=request.getContextPath()%>/chefSch/chefSch.do">
										<button type="submit" class="chef_ID btn btn-outline-secondary btn-block" name="chef_ID" value="${chefVO.chef_ID}" style="margin-top:5px;">${custSvc.getOneCust(chefVO.chef_ID).cust_name}</button>
										<input type="hidden" name="action" value="listChefSchByID">
									</form>
								</c:if>
							</c:forEach>
						</div>
						<div class="col-3">
							<p class="text-center">東部</p>
							<c:forEach var="chefVO" items="${listChefByMenuID}">
								<c:if test="${chefVO.chef_area == '3'}">
									<form method="post" action="<%=request.getContextPath()%>/chefSch/chefSch.do">
										<button type="submit" class="chef_ID btn btn-outline-secondary btn-block" name="chef_ID" value="${chefVO.chef_ID}" style="margin-top:5px;">${custSvc.getOneCust(chefVO.chef_ID).cust_name}</button> 
										<input type="hidden" name="action" value="listChefSchByID">
									</form>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>
					<div class="chef_sch_date">
						<%@ include file="/front-end/chefSch/listChefSchDateByID.jsp"%>
					</div>
					<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>
					<c:if test="${order_chef_sch_date!=null}">
						<div class="chef_sch_time">
							<div class=chef_sch_time_title>
								<p>請選擇日期</p>
							</div>
							<form method="post" action="<%=request.getContextPath()%>/menuOrder/menuOrder.do">
								<input type="text" name="menu_od_book_time" class="form-control" id="order_sch_date_time" /> 
								<input type="hidden" name="menu_od_book_date" value="${order_chef_sch_date}">
								<input type="hidden" name="cust_ID" value="${custVO.cust_ID}">
								<input type="hidden" name="chef_ID" value="${order_chef_ID}">
								<input type="hidden" name="menu_ID" value="${menuVO.menu_ID}">
								<input type="hidden" name="action" value="insert"><br>
								<input type="submit" class="btn btn-outline-secondary btn-block"
									value="送出訂單">
							</form>
						</div>
					</c:if>
				</div>
			</div>
			<div class="col-3"></div>
		</div>
		<div class="footer"></div>
	</div>
    <!-- ##### Contact Area End #####-->
	</section>
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
        $('#order_sch_date_time').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'H:i:s' ,         //format:'Y-m-d H:i:s',
		   value: '${order_chef_sch_date}' , // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate: '${order_chef_sch_date}',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-04-01'  // 去除今日(不含)之後
        });
        
        var somedate = new Date('${order_chef_sch_date}');
        
        $('#order_sch_date_time').datetimepicker({
            beforeShowDay: function(date) {
          	  if (  date.getYear() <  somedate.getYear() || 
  		           (date.getYear() == somedate.getYear() && date.getMonth() <  somedate.getMonth()) || 
  		           (date.getYear() == somedate.getYear() && date.getMonth() == somedate.getMonth() && date.getDate() < somedate.getDate())
  		             ||
  		            date.getYear() >  somedate.getYear() || 
  		           (date.getYear() == somedate.getYear() && date.getMonth() >  somedate.getMonth()) || 
  		           (date.getYear() == somedate.getYear() && date.getMonth() == somedate.getMonth() && date.getDate() > somedate.getDate())
                ) {
                     return [false, ""]
                }
                return [true, ""];
            }
        });
</script>
</html>