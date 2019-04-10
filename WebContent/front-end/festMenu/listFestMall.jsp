<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cust.model.CustVO" %>
<%@ page import="com.foodOrDetail.model.FoodOrDetailVO" %>
<jsp:useBean id="festMenuSvc" class="com.festMenu.model.FestMenuService"/>
<jsp:useBean id="foodMallSvc" class="com.foodMall.model.FoodMallService"/>
<jsp:useBean id="checkType" class="com.mall.controller.CheckType"/>

<html>
<head>
<style type="text/css">
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
		    opacity: 0;
		    background-color: rgba(92, 92, 92, 0.7);
		    left: 0;
		    right: 0;
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
	      .marginall{
	     
	      
	</style>

</head>
<body>
	
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerMall.jsp" flush="true" />
	
	
	<section class="contact-area section-padding-100">
	<jsp:include page="/front-end/foodMall/shoppingcartIn.jsp"/>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
		
<div class="pixel-portfolio-area section-padding-100-0">
		 <div class="container">
		   <div class="pixel-portfolio">
			
			
				<c:forEach var="festMenuVO" items="${festMenuSvc.allIndate}" varStatus="s">
					
						<div id="foodMCard${s.index}" class="single_gallery_item visual wow fadeInUp foodMCard" data-wow-delay="0.2s">
				  			<img src="<%=request.getContextPath()%>/festMenu/festMenu.do?fest_m_ID=${festMenuVO.fest_m_ID}" style="height:600px; weight:600px" >
				  			<div class="hover-content1 text-center1 d-flex align-items-center justify-content-center">
				    			<div class="hover-text"  >
				    			<h3 class="shopUse fest_m_name" style="color:#ff359a; font-weight:bolder; font-size:30px;">
				    				${festMenuVO.fest_m_name}
				    			</h3>
				    			<div >
				    			<p style="color:white; font-size:25px">
				    				價格:${festMenuVO.fest_m_price}
				    			</p>
				    			<p style="color:white; font-size:25px">
				    				數量:${festMenuVO.fest_m_qty}
				    			</p>
				    			<p style="color:white ; font-size:25px">
				    				預購結束日期 : ${festMenuVO.fest_m_end}
				    			</p>
				    			<form action="<%=request.getContextPath()%>/mall/mall.do" method="POST">
				    				<button type="button" style="font-size:25px"  name="foodMBtn" class="btn btn-info">加入購物車</button>
				    				<input type="hidden" name="fest_m_ID" value="${festMenuVO.fest_m_ID}">
				    				<input type="number" style="font-size:25px"  name="fest_or_qty" min="1" max="20" size="3" value="1">
				    			</form>
				    			<p class="card-text errorMsgs"></p>
				  			</div>
						</div>
	            		</div>
	            	</div>
				</c:forEach>
	         </div>
	         </div>
	    </div>
	    </section>
	
	<script>
		
		$(document).ready(function(){
			$(".foodMCard").click(function(eventData){
				
				if(eventData.target.name === "foodMBtn"){
					let foodMNames = getfoodMNames($(this).find(".shopUse"));
					
					$.ajax({
						 type:"POST",
						 url: "<%=request.getContextPath()%>/mall/mall.do",
						 data: crtQryStrFoodM( $(this).attr("id") , "addFestMenu", $(this).find("form").serializeArray()),
						 dataType: "json",
						 success: function (data){
							 
							 if(data["foodMCardID"]){
								 $("#"+data["foodMCardID"]).find(".errorMsgs").text(data["cartErrorMsgs"]);	 
							 }else{
								 
								 cartItem(foodMNames, data);
							 }
							 
					     },
			             error: function(errdata){
			            	alert("ajax 錯誤");
			            	console.log(errdata);
			             }
			         });	
				} else if(eventData.target.name === "food_od_qty"){
					// 防止觸發displayOne
				} else {
					// 送出displayOne
					let dpFestForm = $(this).find("form");
					let action = document.createElement("input");
					action.setAttribute("type","hidden");
					action.setAttribute("name","action");
					action.setAttribute("value","getOneDisplayFestMall");
					dpFestForm.append(action);
					dpFestForm.submit();
				}
			});
			
			
		});
		// 產生查詢字串
		function crtQryStrFoodM( foodMCardID , action, foodMArr){
			
			let queryString = { "foodMCardID":foodMCardID , "action":action };
			let foodMArrLen = foodMArr.length;
			for(let i = 0; i < foodMArrLen; i++){
				queryString[foodMArr[i].name] = foodMArr[i].value;
			}
			
			return queryString;
		}
		// 懶得在伺服器再查詢, 所以透過此方法再加入購物商時取得對應的食材名, 標題名, 供應商名
		// 並暫存到記憶體中, 等伺服器回應時就可以一起加入到購物車
		function getfoodMNames(shopUseClass){
			console.log(shopUseClass);
			let shopUseClassLen = shopUseClass.length;
			let foodMNames = {};

			for(let i = 0; i < shopUseClassLen; i++){
				console.log(shopUseClass[i].className);
				foodMNames[shopUseClass[i].className.split(" ",2)[1]] = shopUseClass[i].innerText;
			}
			console.log(foodMNames);
			return foodMNames;
		}
		// 新增或更改已在購物車的商品
		function cartItem(foodMNames, data){
			
			let shopCartTrs = $("#shopCartList>tr");
			let shopCartLen = shopCartTrs.length;
			let isNewCartItem = true;
			
			jQuery.each( shopCartTrs, function(i, val){
				let inputArr = $(this).find("form").serializeArray();
				if(inputArr[0].value === data.fest_m_ID){
					$(this).children("td:eq(1)").text(data.fest_or_qty);
					$(this).children("td:eq(2)").text(data.fest_or_stotal);
					isNewCartItem = false;
					return;
				}
			});
			
			if(isNewCartItem){
				let shopCartItem = $("#copyShopFest").clone();
				shopCartItem.children("td:eq(0)").text(foodMNames.fest_m_name);
				shopCartItem.children("td:eq(1)").text(data.fest_or_qty);
				shopCartItem.children("td:eq(2)").text(data.fest_or_stotal);
				shopCartItem.find("button").click(delShoppingCartItem);
				shopCartItem.find(":input:eq(0)").attr('name', "fest_m_ID");
				shopCartItem.find(":input:eq(0)").attr('value', data.fest_m_ID);
				shopCartItem.removeAttr('style');
				shopCartItem.removeAttr('id');
				$("#shopCartList").append(shopCartItem);
				
			}
	
		}
		
	</script>
</body>
</html>