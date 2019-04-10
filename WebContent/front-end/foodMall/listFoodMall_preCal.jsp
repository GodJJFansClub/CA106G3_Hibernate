<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cust.model.CustVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.foodMall.model.FoodMallVO" %>
<jsp:useBean id="foodMallSvc" class="com.foodMall.model.FoodMallService"/>
<jsp:useBean id="foodSvc" class="com.food.model.FoodService"/>
<jsp:useBean id="foodSupSvc" class="com.foodSup.model.FoodSupService" />
<jsp:useBean id="festMenuSvc" class="com.festMenu.model.FestMenuService"/>
<jsp:useBean id="checkType" class="com.mall.controller.CheckType"/>
<%
	List<FoodMallVO> list = foodMallSvc.getAllStatus("p4"); 
	pageContext.setAttribute("list", list);
%>
<html>
<head>
</head>
<body>



            <!-- Single gallery Item -->
            <div class="single_gallery_item visual wow fadeInUp" data-wow-delay="0.2s" style="visibility: visible; animation-delay: 0.2s; animation-name: fadeInUp; position: absolute; left: 0%; top: 0px;">
                <img src="img/bg-img/5.jpg" alt="">
                <div class="hover-content text-center d-flex align-items-center justify-content-center">
                    <div class="hover-text">
                        <a href="img/bg-img/5.jpg" class="zoom-img"><i class="fa fa-eye" aria-hidden="true"></i></a>
                        <h4>Branding Identity</h4>
                    </div>
                </div>
            </div>

            <!-- Single gallery Item -->
            <div class="single_gallery_item add wow fadeInUp" data-wow-delay="0.4s" style="visibility: visible; animation-delay: 0.4s; animation-name: fadeInUp; position: absolute; left: 33.2632%; top: 0px;">
                <img src="img/bg-img/6.jpg" alt="">
                <div class="hover-content text-center d-flex align-items-center justify-content-center">
                    <div class="hover-text">
                        <a href="img/bg-img/5.jpg" class="zoom-img"><i class="fa fa-eye" aria-hidden="true"></i></a>
                        <h4>Branding Identity</h4>
                    </div>
                </div>
            </div>

            <!-- Single gallery Item -->
            <div class="single_gallery_item web wow fadeInUp" data-wow-delay="0.6s" style="visibility: visible; animation-delay: 0.6s; animation-name: fadeInUp; position: absolute; left: 66.6316%; top: 0px;">
                <img src="img/bg-img/7.jpg" alt="">
                <div class="hover-content text-center d-flex align-items-center justify-content-center">
                    <div class="hover-text">
                        <a href="img/bg-img/5.jpg" class="zoom-img"><i class="fa fa-eye" aria-hidden="true"></i></a>
                        <h4>Branding Identity</h4>
                    </div>
                </div>
            </div>

        


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
				<div class="pixel-portfolio" >
				 
					<%@ include file="/front-end/foodMall/page1.file" %>
					<c:forEach var="foodMallVO" items="${list}" varStatus="s" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
					 <div class="col-3">
					 <div id="foodMCard${s.index}" class="card foodMCard">
					  <img src="<%=request.getContextPath()%>/foodMall/foodMall.do?food_sup_ID=${foodMallVO.food_sup_ID}&food_ID=${foodMallVO.food_ID}" class="card-img-top">
					 <div class="card-body">
					 <p class="card-text shopUse food_m_name">
					   ${foodMallVO.food_m_name}
					 </p>
					 <p class="card-text shopUse food_name">
					   ${foodSvc.getOneFood(foodMallVO.food_ID).food_name}
					 </p>
					 <p class="card-text shopUse food_sup_name">
					   ${foodSupSvc.getOneFoodSup(foodMallVO.food_sup_ID).food_sup_name}
					 </p>
					 <p class="card-text">
					   ${foodMallVO.food_m_price}
					 </p>
					 <form action="<%=request.getContextPath()%>/mall/mall.do" method="POST">
					    <button type="button" name="foodMBtn" class="btn btn-primary">加入購物車</button>
					    <input type="hidden" name="food_ID" value="${foodMallVO.food_ID}">
					    <input type="hidden" name="food_sup_ID" value="${foodMallVO.food_sup_ID}">	
					    <input type="number"   name="food_od_qty" min="1" max="20" size="3" value="1">
					 </form>
					<p class="card-text errorMsgs"></p>
				  </div>
				 </div>
		        </div>
			 </c:forEach>
			<%@ include file="/front-end/foodMall/page2.file" %>
	      </div>
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
						 data: crtQryStrFoodM( $(this).attr("id") , "addFoodMShoppingCart", $(this).find("form").serializeArray()),
						 dataType: "json",
						 success: function (data){
							 console.log(data);
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
				} else if(eventData.target.name === "food_od_qty") {
					
				} else{
					let mallForm = $(this).find("form");
					if(eventData.target.name !== "foodMBtn"){
						let action = document.createElement("input");
						action.setAttribute("type","hidden");
						action.setAttribute("name","action");
						action.setAttribute("value","getOneDisplayFoodMall");
						mallForm.append(action);
						mallForm.submit();
					}
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
				shopCartItem.children("td:eq(0)").text(foodMNames.food_m_name);
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
</body>
</html>