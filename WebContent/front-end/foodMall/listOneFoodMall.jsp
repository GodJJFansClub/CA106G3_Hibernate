<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="foodSupSvc" class="com.foodSup.model.FoodSupService"/>
<jsp:useBean id="foodSvc" class="com.food.model.FoodService"/>
<html>
<head>
<style>
.col{
font-weight:bold;
font-size:25px;
}
.col a{
font-size:30px;

}
.col a:focus {
  -webkit-transition-duration: 500ms;
  -o-transition-duration: 500ms;
  transition-duration: 500ms;
  text-decoration: none;
  outline: 0 solid transparent;
  color: #000000;
  font-weight: 500;
  font-size: 45px; }

</style>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerMall.jsp" flush="true" />
	
	 <!-- ##### Contact Area Start #####-->
    <section class="contact-area section-padding-100">
    <jsp:include page="/front-end/foodMall/shoppingcartIn.jsp"/>
    	
    	<div id="mallItem" class="container foodMCard">
	    	<div class="col">
	    		食材供應商 :<a> ${foodSupSvc.getOneFoodSup(foodMallVO.food_sup_ID).food_sup_name}</a>
	    	</div>
	    	<div class="col" id="foodMName">
	    		標題 :<a> ${foodMallVO.food_m_name}</a>
	    	</div>
	    	<div class="col">
	    		食材名 :<a> ${foodSvc.getOneFood(foodMallVO.food_ID).food_name}</a>
	    	</div>
	    	<div class="col">
	    		價格 :<a> ${foodMallVO.food_m_price}</a>
	    	</div>
	    	<div class="col">
	    		單位 :<a> ${foodUnitMap[foodMallVO.food_m_unit]}</a>
	    	</div>
	    	<div class="col">
	    		產地 :<a> ${foodMallVO.food_m_place}</a>
	    	</div>
	    	<div class="col">
	    		<img src="<%=request.getContextPath()%>/foodMall/foodMall.do?food_sup_ID=${foodMallVO.food_sup_ID}&food_ID=${foodMallVO.food_ID}"
	    			height = "400" width="300">
	    	</div>
	    	<div class="col">
	    		介紹 :<u> ${foodMallVO.food_m_resume}</u>
	    	</div>
	    	<form class="foodMCard" action="<%=request.getContextPath()%>/mall/mall.do" method="POST">
				<button type="button" id="addShoppingcart" name="foodMBtn" class="btn btn-info">加入購物車</button>
				<input type="hidden" name="food_ID" value="${foodMallVO.food_ID}">
				<input type="hidden" name="food_sup_ID" value="${foodMallVO.food_sup_ID}">	
				<input type="number" style="font-size:25px"name="food_od_qty" min="1" max="20" size="3" value="1">
			</form>
			<p class="card-text errorMsgs"></p>
		</div>
    </section>
    <!-- ##### Contact Area End #####-->
	<script>
		
		$(document).ready(function(){
			$("#addShoppingcart").click(function(){
				let foodMName = $("#foodMName").text();
				foodMName = foodMName.substring(foodMName.indexOf(':')+2);
				console.log(foodMName);
				$.ajax({
					 type:"POST",
					 url: "<%=request.getContextPath()%>/mall/mall.do",
					 data: crtQryStrFoodM( $(this).attr("id") , "addFoodMShoppingCart", $(this).parent("form").serializeArray()),
					 dataType: "json",
					 success: function (data){
						 
						 if(data["foodMCardID"]){
							 $("#mallItem").find(".errorMsgs").text(data["cartErrorMsgs"]);	 
						 }else{
							
							 cartItem(foodMName, data);
						 }
						 
				     },
		             error: function(errdata){
		            	alert("ajax 錯誤");
		            	console.log(errdata);
		             }
		         });
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
		// 新增或更改已在購物車的商品
		function cartItem(foodMName, data){
			
			let shopCartTrs = $("#shopCartList>tr");
			let shopCartLen = shopCartTrs.length;
			let food_sup_ID = data.food_sup_ID;
			let food_ID = data.food_ID;
			let isNewCartItem = true;
			
			jQuery.each( shopCartTrs, function(i, val){
				let inputArr = $(this).find("form").serializeArray();
				if(food_sup_ID === inputArr[0].value 
						&& inputArr[1].value === data.food_ID){
					$(this).children("td:eq(1)").text(data.food_od_qty);
					$(this).children("td:eq(2)").text(data.food_od_stotal);
					isNewCartItem = false;
					return;
				}
			});
			
			if(isNewCartItem){
				let shopCartItem = $("#copyShopIF").clone();
				shopCartItem.children("td:eq(0)").text(foodMName);
				shopCartItem.children("td:eq(1)").text(data.food_od_qty);
				shopCartItem.children("td:eq(2)").text(data.food_od_stotal);
				shopCartItem.find("button").click(delShoppingCartItem);
				shopCartItem.find(":input:eq(0)").attr('name', "food_sup_ID");
				shopCartItem.find(":input:eq(0)").attr('value', data.food_sup_ID);
				shopCartItem.find(":input:eq(1)").attr('name', "food_ID");
				shopCartItem.find(":input:eq(1)").attr('value', data.food_ID);
				shopCartItem.removeAttr('style');
				shopCartItem.removeAttr('id');
				$("#shopCartList").append(shopCartItem);
				
			}
	
		}
	</script>
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>