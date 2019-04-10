<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cust.model.*"%>
<%@ page import="com.chef.model.*"%>

<%
	CustVO custVO = (CustVO) request.getAttribute("custVO");
	ChefVO chefVO = (ChefVO) request.getAttribute("chefVO");
%>

<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>主廚資料新增</title>
<link
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css"
	rel="stylesheet">

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 700px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	width: 1px;
	padding: 1px;
}
</style>
>

</head>

<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
<jsp:include page="/froTempl/headerJoin.jsp" flush="true" />

	<!-- ##### Contact Area Start #####-->
	<section class="contact-area section-padding-100">

		<section class="contact-area section-padding-100">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<!-- Section Heading -->
						<div class="section-heading text-center wow fadeInUp"
							data-wow-delay="100ms">
							<h2>成為主廚</h2>
							<img
								src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/x.png"
								alt="">
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
						</div>
					</div>
				</div>


				<div class="row justify-content-center">
					<div class="col-12 col-lg-8">
						<!-- Contact Form -->
						<div class="contact-form-area text-center">
							<FORM name="form1" METHOD="post"
								ACTION="<%=request.getContextPath()%>/chef/chef.do"
								enctype="multipart/form-data">

								<table>
									<tr>
										<td>主廚帳號:</td>
										<td><input type="TEXT" id="cust_acc" name="cust_acc"
											size="45"
											value="<%=(custVO == null) ? "" : custVO.getCust_acc()%>"
											placeholder="請輸入帳號" autocomplete="off"
											class="form-control wow fadeInUp" data-wow-delay="100ms" />
											<p id="answer"></p></td>
									</tr>

									<tr>
										<td>主廚密碼:</td>
										<td><input type="password" name="cust_pwd" size="45"
											value="<%=(custVO == null) ? "" : custVO.getCust_pwd()%>"
											placeholder="請輸入密碼" autocomplete="off"
											class="form-control wow fadeInUp" data-wow-delay="100ms" /></td>
									</tr>

									<tr>
										<td>主廚姓名:</td>
										<td><input type="TEXT" name="cust_name" size="45"
											value="<%=(custVO == null) ? "" : custVO.getCust_name()%>"
											placeholder="請輸入姓名" autocomplete="off"
											class="form-control wow fadeInUp" data-wow-delay="100ms" /></td>
									</tr>

									<tr>
										<td>身分證字號:</td>
										<td><input type="TEXT" name="cust_pid" size="45"
											value="<%=(custVO == null) ? "" : custVO.getCust_pid()%>"
											placeholder="請輸入身分證字號" autocomplete="off" /></td>
									</tr>

									<tr>
										<td>主廚性別:</td>
										<td>
											<div class="form-check form-check-inline">
											  <input class="form-check-input" type="radio" name="cust_sex" id="inlineRadio1" value="M" ${custVO.cust_sex=='M'||custVO ==null ? 'checked':''} style="width:15px;height:15px;">
											  <label class="form-check-label" for="inlineRadio1">男</label>
											</div>
											<div class="form-check form-check-inline">
											  <input class="form-check-input" type="radio" name="cust_sex" id="inlineRadio2" value="F" ${custVO.cust_sex=='F'? 'checked':''  } style="width:15px;height:15px;">
											  <label class="form-check-label" for="inlineRadio2">女</label>
											</div>
										</td>
									</tr>

									<tr>
										<td>主廚生日:</td>
										<td><input type="TEXT" name="cust_brd" id="f_date1"
											size="45" autocomplete="off"
											class="form-control wow fadeInUp" data-wow-delay="100ms" /></td>
									</tr>

									<tr>
										<td>主廚電話:</td>
										<td><input type="TEXT" name="cust_tel" size="45"
											value="<%=(custVO == null) ? "" : custVO.getCust_tel()%>"
											placeholder="請輸入手機號碼" autocomplete="off"
											class="form-control wow fadeInUp" data-wow-delay="100ms" /></td>
									</tr>

									<tr>
										<td>主廚地址:</td>
										<td><select name="cityName"id="twCityName">
												<option value="-1">--請選擇縣市--</option>
										</select> 
											<select name="areaName" id="CityAreaName">
												<option value="-1">--請選擇區域--</option>
										</select> <input readonly id="zipCode" name="zipCode" type="text"
											 placeholder="區域號碼" style="width:75px;font-size:18px">
											 <select name="roadName"id="AreaRoadName">
												<option value="-1">--請選擇路名--</option>
										</select> <input type="text" name="partAddr" class="form-control">
										</td>

									</tr>

									<tr>
										<td>主廚信箱:</td>
										<td><input type="TEXT" name="cust_mail" size="45"
											value="<%=(custVO == null) ? "" : custVO.getCust_mail()%>"
											placeholder="請輸入電子郵件" autocomplete="off"
											class="form-control wow fadeInUp" data-wow-delay="100ms" /></td>
									</tr>

									<tr>
										<td>主廚暱稱:</td>
										<td><input type="TEXT" name="cust_niname" size="45"
											value="<%=(custVO == null) ? "" : custVO.getCust_niname()%>"
											autocomplete="off" class="form-control wow fadeInUp"
											data-wow-delay="100ms" /></td>
									</tr>

									<tr>
										<td>主廚大頭照:</td>
										<td><input type="file" name="cust_pic" size="45" id="doc"
											onchange="javascript:setImagePreview();"
											class="form-control wow fadeInUp" data-wow-delay="100ms" />
											<div id="localImag">
												<img id="preview" width=-1 height=-1 style="display: none" />
											</div>
										</td>
									</tr>
									<tr>
										<td>服務地區:</td>
										<td>
											<div class="form-check form-check-inline">
											  <input class="form-check-input" type="radio" name="chef_area" id="inlineRadio3" value="0" ${chefVO.chef_area=='0'||chefVO ==null ? 'checked':''} style="width:15px;height:15px;">
											  <label class="form-check-label" for="inlineRadio3">北</label>
											</div>
											<div class="form-check form-check-inline">
											  <input class="form-check-input" type="radio" name="chef_area" id="inlineRadio4" value="1" ${chefVO.chef_area=='1'? 'checked':''  } style="width:15px;height:15px;">
											  <label class="form-check-label" for="inlineRadio4">中</label>
											</div>
											<div class="form-check form-check-inline">
											  <input class="form-check-input" type="radio" name="chef_area" id="inlineRadio5" value="2" ${chefVO.chef_area=='2'? 'checked':''} style="width:15px;height:15px;">
											  <label class="form-check-label" for="inlineRadio5">南</label>
											</div>
											<div class="form-check form-check-inline">
											  <input class="form-check-input" type="radio" name="chef_area" id="inlineRadio6" value="3" ${chefVO.chef_area=='3'? 'checked':''  } style="width:15px;height:15px;">
											  <label class="form-check-label" for="inlineRadio6">東</label>
											</div>
										</td>
									</tr>
									<tr>
										<td>主廚簡介:</td>
										<td><textarea name="chef_resume" placeholder="請輸入簡介...">${chefVO==null? chefVO.chef_resume:''}</textarea>

											<script>CKEDITOR.replace('chef_resume');</script></td>
									</tr>
								</table>
								<br> <img src="<%=request.getContextPath()%>/images/x.png"
									height="20" width="20" onClick="idwrite(this)"> <input
									type="hidden" name="action" value="insert">
								<button type="submit" class="btn pixel-btn wow fadeInUp"
									data-wow-delay="300ms">送出</button>
							</form>
						</div>
					</div>
				</div>

			</div>
		</section>
		<!-- ##### Contact Area End #####-->



	</section>
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<%
	java.sql.Date cust_brd = null;
	try {
		cust_brd = custVO.getCust_brd();
	} catch (Exception e) {
		cust_brd = new java.sql.Date(System.currentTimeMillis());
	}
%>

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
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       //step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d' ,         //format:'Y-m-d H:i:s',
		   //value: '<%=cust_brd%>' , // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>
<script>
      function idwrite(name){
    	  form1.cust_acc.value="david"
    	  form1.cust_pwd.value="123456"
    	  form1.cust_name.value="小吳師傅"
    	  form1.cust_pid.value="B123456789"
    	  form1.cust_sex.value="M"
    	  form1.cust_brd.value="2017-12-01"
    	  form1.cust_tel.value="0906077543"
    	  form1.partAddr.value="1號"
    	  form1.cust_mail.value="ca106g3@hotmail.com"
    	  form1.chef_area.value="0"
    	  form1.cust_niname.value="海鮮小當家" 
    	  form1.chef_resume.text="30年老廚師"
      }
</script>

<script>
	function setImagePreview() {
		var docObj = document.getElementById("doc");
		var imgObjPreview = document.getElementById("preview");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '200px';
			imgObjPreview.style.height = '120px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL();
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");
			//必须设置初始大小
			localImagId.style.width = "250px";
			localImagId.style.height = "200px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("圖片格式不正確，請重新選擇!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}
</script>

<script>
		var timer;
		
		$(function(){
			$('#cust_acc').on('keyup', function(){
				$('#answer').text('輸入中');
				var cust_acc = $(this).val();
				_debounce(function(){ 
					return getAnswer(cust_acc); 
				}, 500);
			});
		});
		
		function getAnswer(cust_acc){
			
			$('#answer').text('帳號驗證中');
			$.ajax({
 			
				url: '<%=request.getContextPath()%>/cust/cust.do',
			type : "POST",
			data : {
				action : 'ask',
				cust_acc : $('#cust_acc').val()
			},
			//dataType: 'json',
			success : function(res) {
				console.log(res);
				$('#answer').text(res);

			},
			error : function(res) {
				console.log(res);
				$('#answer').text('Error! Could not reach the API. ');
			}
		});
	}

	function _debounce(callback, time) {
		if (timer)
			clearTimeout(timer);
		timer = setTimeout(function() {
			callback();
		}, time);
	}
	
	
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
	
	
	
	
	
	
	
	
	
	
	
</script>

</html>