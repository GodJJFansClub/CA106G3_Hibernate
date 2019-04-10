<%@page import="com.cust.model.CustVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerMall.jsp" flush="true" />
	<section class="contact-area section-padding-100">
	<div class="row justify-content-center">
					<div class="col-12 col-lg-8">
						<!-- Contact Form -->
						<div class="contact-form-area text-center">
	<%-- 錯誤列表 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
	</c:if>
		<div class="row">
			<div class="col-12">
				<div class="card card-body">
					<h4 class="card-title" style="font-size:50px">付款</h4>
					<form id="endOrder" name="form1" class="form-horizontal m-t-30" method="post" action="<%=request.getContextPath()%>/foodOrder/foodOrder.do">
						<div class="form-group">
						<label style="font-size:25px">收件人姓名</label>
						<input type="text" class="form-control" name="food_or_name"
											value="${empty foodOrderVO ? '': foodOrderVO.food_or_name}">
						</div>
						<div class="form-group">
							<label style="font-size:25px">收件地址</label>
							<select name="cityName" id="twCityName">
								<option value="-1">--請選擇縣市--</option>
							</select>
							<select name="areaName" id="CityAreaName">
								<option value="-1">--請選擇區域--</option>
							</select>
							<input readonly id="zipCode" style="width:100px" name="zipCode" type="text" size="5" placeholder="區域號碼">
							<select name="roadName" id="AreaRoadName">
								<option value="-1">--請選擇路名--</option>
							</select>
							<input type="text" name="partAddr" class="form-control" >
						</div>
						<div class="form-group">
							<label style="font-size:25px">收件人電話</label>
							<input type="text" name="food_or_tel" class="form-control" placeholder="09XXXXXXXX"
									value="${empty foodOrderVO ? '':foodOrderVO.food_or_tel}">
						</div>
						<div class="form-group">
							<label style="font-size:25px">卡號</label>
							<div class="form-row">
								<div class="form-group col-md-1">
									<input type="text" class="form-control" id="credNum1" name="credNum1" maxlength="4">
								</div>
								<div class="form-group col-md-1">
									<input type="password" class="form-control" id="credNum2" name="credNum2" maxlength="4">
								</div>
								<div class="form-group col-md-1">
									<input type="password" class="form-control" id="credNum3" name="credNum3" maxlength="4">
								</div>
								<div class="form-group col-md-1">
									<input type="text" class="form-control" id="credNum4" name="credNum4" maxlength="4">
								</div>
							</div>
						</div>
							<input type="hidden" name="cust_ID" value="${custVO.cust_ID}">
							<input type="hidden" name="action" value="insertOrODs">
							<img src="<%=request.getContextPath()%>/images/x.png"
									height="20" width="20" onClick="idwrite(this)">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<button id="btnLoc" class="btn btn-info" type="submit"  >付款</button>
					</form>
				</div>
			</div>
		</div>
		</div>
			</div>
		</div>
	</section>
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
	<jsp:include page="/froTempl/headerMall.jsp" flush="true" />
	<style>
		.xdsoft_datetimepicker .xdsoft_datepicker {
			width: 300px; /* width:  300px; */
		}
		
		.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
			height: 151px; /* height:  151px; */
		}
	</style>
<script>
      function idwrite(name){
    	  
    	  form1.food_or_name.value="動保協會"
    		  form1.food_or_tel.value="0988777564"
    	  form1.partAddr.value="1號"
    		  form1.credNum1.value="8457"
    	  form1.credNum1.value="8457" 
    		  form1.credNum2.value="8080" 
    			  form1.credNum3.value="8081" 
    				  form1.credNum4.value="6689" 
    		  
    	 
      }
</script>
<script>
		$(document).ready(function(){
			$.ajax({
				type: "POST",
				url:"<%=request.getContextPath()%>/food/AddrSelect.do",
				data: {"action":"getCity"},
				dataType: "json",
				success: function(result){
					 let len = result.length;
					 console.log(result);
					 for(let i = 0; i < len; i++){
						 $("#twCityName").append('<option value="'+result[i]+'">'+result[i]+'</option>');
					 }
				 },
		         error: function(){
		        	 alert("AJAX-grade發生錯誤囉!");
				}
			});
			
			$("#twCityName").change(function(event){
				
				if(event.target.value != "-1"){				
					$.ajax({
						type: "POST",
						url:"<%=request.getContextPath()%>/food/AddrSelect.do",
						data: {"action":"twCityName","twCityName":$('#twCityName option:selected').val()},
						dataType: "json",
						success: function(result){
							
							 $("#CityAreaName").empty();
							
							 $("#CityAreaName").append("<option value='-1'>--請選擇區域--</option>")
							 for(var i=0; i<result.length; i++){
							 	$("#CityAreaName").append('<option value="'+result[i]+'">'+result[i]+'</option>');
							 }
						 },
				         error: function(){
				        	 alert("AJAX-grade發生錯誤囉!");
						}
					});
				}
			});
			
			
			$("#CityAreaName").change(function(){
				if(event.target.value != "-1"){
					$.ajax({
						 type: "POST",
						 url: "<%=request.getContextPath()%>/food/AddrSelect.do",
						 data: {"action":"CityAreaName",
							 	"twCityName":$('#twCityName option:selected').val(),
							 	"CityAreaName":$('#CityAreaName option:selected').val()},
						 dataType: "json",
						 success: function(result){
							 console.log(result);
							 $("#AreaRoadName").empty();
							 $("#zipCode").val(result.ZipCode);
							 $("#AreaRoadName").append('<option value="-1">--請選擇區域--</option>');
							 console.log(result.roadName);
							 for(var i=0; i<result.roadName.length; i++){
							 	$("#AreaRoadName").append('<option value="'+result.roadName[i]+'">'+result.roadName[i]+'</option>');
							 }
						 },
				         error: function(result){
				        	 console.log(result);
				        	 alert("AJAX-grade發生錯誤囉!");
				         }
				    });
				}
			});
			
		});
		
		
		
        $.datetimepicker.setLocale('zh');
        $('#credEnd').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=request.getAttribute("credEnd")%>', // value:   new Date(),
			//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	            '2017/07/10',  // 起始日
			minDate:               '-1970-01-01', // 去除今日(不含)之前
			//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
		});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</body>
</html>