<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="chefSvc" class="com.chef.model.ChefService" />
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
  font-size: 50px; }

</style>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerMall.jsp" flush="true" />

	<!-- ##### Contact Area Start #####-->
	<section class="contact-area section-padding-100">
		<jsp:include page="/front-end/foodMall/shoppingcartIn.jsp" />
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div id="mallItem" class="container foodMCard">
			
			<div class="col" id="festMName">節慶主題料理名稱 :<a>
				${festMenuVO.fest_m_name}</a></div>
			<div class="col">數量 : <a>${festMenuVO.fest_m_qty}</a></div>
			<div class="col">開始預購日期 :<a> ${festMenuVO.fest_m_start}</a></div>
			<div class="col">結束預購日期 : <a>${festMenuVO.fest_m_end}</a></div>
			<div class="col">統一寄送日期 : <a>${festMenuVO.fest_m_send}</a></div>
			<div class="col">
				<img
					src="<%=request.getContextPath()%>/festMenu/festMenu.do?fest_m_ID=${festMenuVO.fest_m_ID}"
					height="400" width="300">
			</div>
			<div class="col">種類 : <a>${festMenuVO.fest_m_kind}</a></div>
			<div class="col">價格 : <a>${festMenuVO.fest_m_price}</a></div>
			<div class="col">介紹 :<u> ${festMenuVO.fest_m_resume}</u></div>
			<form class="foodMCard"
				action="<%=request.getContextPath()%>/mall/mall.do" method="POST">
				<button type="button" id="addShoppingcart" name="foodMBtn"
					class="btn btn-info">加入購物車</button>
				<input type="hidden" name="fest_m_ID" value="${festMenuVO.fest_m_ID}"> <input type="number"
					name="fest_or_qty" min="1" max="20" size="3" value="1">
			</form>
			<p class="card-text errorMsgs"></p>
		</div>
	</section>
	<!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
	<script>
		$(document).ready(function(){
			$("#addShoppingcart").click(function(){
				let foodMName = $("#festMName").text();
				foodMName = foodMName.substring(foodMName.indexOf(':')+2);
				$.ajax({
					type:"POST",
					url: "<%=request.getContextPath()%>/mall/mall.do",
					data : crtQryStrFoodM("addFestMenu",$(this).parent("form").serializeArray()),
					dataType : "json",
					success : function(data) {
						if (data["foodMCardID"]) {
							$("#mallItem").find(".errorMsgs").text(data["cartErrorMsgs"]);
						} else {
							cartItem(foodMName,data);
						}

					},
					error : function(errdata) {
								alert("ajax 錯誤");
								console.log(errdata);
					}
				});
			});
		});
		// 產生查詢字串
		function crtQryStrFoodM( action , festMArr) {
			
			let queryString = {
				"action" : action
			};
			let festMArrLen = festMArr.length;
			for(let i = 0; i < festMArrLen; i++){
				queryString[festMArr[i].name] = festMArr[i].value;
			}
			console.log(queryString);
			
			return queryString;
		}
		// 懶得在伺服器再查詢, 所以透過此方法再加入購物商時取得對應的食材名, 標題名, 供應商名
		// 並暫存到記憶體中, 等伺服器回應時就可以一起加入到購物車
		// 新增或更改已在購物車的商品
		function cartItem(foodMName, data) {

			let shopCartTrs = $("#shopCartList>tr");
			let shopCartLen = shopCartTrs.length;
			let isNewCartItem = true;

			jQuery.each(shopCartTrs, function(i, val) {
				let inputArr = $(this).find("form").serializeArray();
				if (inputArr[0].value === data.fest_m_ID) {
					$(this).children("td:eq(1)").text(data.fest_or_qty);
					$(this).children("td:eq(2)").text(data.fest_or_stotal);
					isNewCartItem = false;
					return;
				}
			});

			if (isNewCartItem) {
				let shopCartItem = $("#copyShopFest").clone();
				shopCartItem.children("td:eq(0)").text(foodMName);
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