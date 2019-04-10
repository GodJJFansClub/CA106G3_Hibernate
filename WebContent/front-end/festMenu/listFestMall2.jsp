<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cust.model.CustVO" %>
<%@ page import="com.foodOrDetail.model.FoodOrDetailVO" %>
<jsp:useBean id="festMenuSvc" class="com.festMenu.model.FestMenuService"/>
<jsp:useBean id="foodMallSvc" class="com.foodMall.model.FoodMallService"/>
<jsp:useBean id="checkType" class="com.mall.controller.CheckType"/>

<html>
<head>
<style>

    
.card {
	overflow: hidden;
}
.card-img-top{
width:100%;
border-top-left-radius:calc(.25rem - 1px);
border-top-right-radius:calc(.25rem - 1px);
transform:scale(1,1);transition: all 1s ease-out;
}

.fest_m_name{
	margin-top:20px;

}
.card-img-top:hover{
width:100%;
border-top-left-radius:calc(.25rem - 1px);
border-top-right-radius:calc(.25rem - 1px);
transform:scale(1.2,1.2);
}
img{
	weight:400px;
	height:600px;
}

</style>

</head>
<body>
	
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerMall.jsp" flush="true" />
	<section class="contact-area section-padding-100">
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<jsp:include page="/front-end/foodMall/shoppingcartIn.jsp"/>
		<div class="container">
			
			
			<div class="row">
				<c:forEach var="festMenuVO" items="${festMenuSvc.allIndate}" varStatus="s">
					<div class="col-3">
						<div id="foodMCard${s.index}" class="card foodMCard">
				  			<img src="<%=request.getContextPath()%>/festMenu/festMenu.do?fest_m_ID=${festMenuVO.fest_m_ID}" class="card-img-top" height="200px" width="100px">
				  			<div class="card-body">
				    			<p class="card-text shopUse fest_m_name">
				    				${festMenuVO.fest_m_name}
				    			</p>
				    			<p class="card-text shopUse fest_m_qty">
				    				${festMenuVO.fest_m_qty}
				    			</p>
				    			<p class="card-text">
				    				預購結束日期 : ${festMenuVO.fest_m_end}
				    			</p>
				    			<form action="<%=request.getContextPath()%>/mall/mall.do" method="POST">
				    				<button type="button" name="foodMBtn" class="btn btn-primary">加入購物車</button>
				    				<input type="hidden" name="fest_m_ID" value="${festMenuVO.fest_m_ID}">
				    				<input type="number"   name="fest_or_qty" min="1" max="20" size="3" value="1">
				    			</form>
				    			<p class="card-text errorMsgs"></p>
				  			</div>
						</div>
	            	</div>
				</c:forEach>
	         </div>
	    </div>
	    </section>
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
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
							 console.log(data["foodMCardID"])
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
			console.log(foodMCardID);
			console.log(foodMArr);
			let queryString = { "foodMCardID":foodMCardID , "action":action };
			let foodMArrLen = foodMArr.length;
			for(let i = 0; i < foodMArrLen; i++){
				queryString[foodMArr[i].name] = foodMArr[i].value;
			}
			console.log(queryString);
			return queryString;
		}
		// 懶得在伺服器再查詢, 所以透過此方法再加入購物商時取得對應的食材名, 標題名, 供應商名
		// 並暫存到記憶體中, 等伺服器回應時就可以一起加入到購物車
		function getfoodMNames(shopUseClass){
			
			let shopUseClassLen = shopUseClass.length;
			let foodMNames = {};
			
			for(let i = 0; i < shopUseClassLen; i++){
				foodMNames[shopUseClass[i].className.split(" ",3)[2]] = shopUseClass[i].innerText;
			}
			
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