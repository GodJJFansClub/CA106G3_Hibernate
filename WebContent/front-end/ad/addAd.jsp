<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.foodSup.model.*"%>
<%@ page import="java.util.*"%>
<%
	FoodSupVO foodSupVO = (FoodSupVO) session.getAttribute("foodSupVO");
	AdVO adVO = (AdVO) request.getAttribute("adVO");
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>廣告資料新增</title>
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
	width: 600px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>


</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerFoodSup.jsp" flush="true" />
	<!-- ##### Breadcrumb Area Start ##### -->
	<br>
	
	<!-- ##### Breadcrumb Area End ##### -->

	<!-- ##### Contact Area Start #####-->
	<section class="contact-area section-padding-100">
		<section class="contact-area section-padding-100">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<!-- Section Heading -->
						<div class="section-heading text-center wow fadeInUp"
							data-wow-delay="100ms">

							<h2 style="font-weight: bold">新增廣告</h2>
							<img src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/x.png" alt="">

							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<div class="row justify-content-center">
								<div class="col-12 col-lg-8">
									<!-- Contact Form -->
									<div class="contact-form-area text-center">
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/ad/ad.do" name="form1"
											enctype="multipart/form-data">

											<table>

												<tr>
													<td>廣告標題:</td>
													<td><input type="TEXT" name="ad_title" size="45" class="form-control"
														value="<%=(adVO == null) ? "請輸入標題" : adVO.getAd_title()%>" /></td>
												</tr>


												<tr>
													<td>廣告上架日期:</td>
													<td><input type="TEXT" name="ad_start" id="f_date1" class="form-control"
														size="45" /></td>
												</tr>


												<tr>
													<td>廣告下架日期:</td>
													<td><input type="TEXT" name="ad_end" id="f_date2" class="form-control"
														size="45" /></td>
												</tr>




												<tr>
													<td>廣告圖片:</td>
													<td>
													
													<input type="file" class="form-control-file" name="ad_pic" size="1" class="form-control"
														id="doc" onchange="javascript:setImagePreview();" /></td>
												</tr>

												<tr>
													<td>廣告內文:</td>
													<td><textarea type="message" name="ad_con" size="45" class="form-control"
														value="<%=(adVO == null) ? "請輸入內文" : adVO.getAd_con()%>" ></textarea></td>
												</tr>





											</table>
											<div id="localImag">
												<img id="preview" width=-1 height=-1 style="diplay: none" />
											</div>


											<br> <input type="hidden" name="action" value="insert">
											<input type="hidden" name="food_sup_ID" size="45"
												value="<%=foodSupVO.getFood_sup_ID()%>" />
											</td>
											<img src="<%=request.getContextPath()%>/images/x.png"
									height="20" width="20" onClick="idwrite(this)">
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
<script>
      function idwrite(name){
    	  
    	  form1.ad_title.value="南極真的有烏魚子嗎?"
    	  form1.ad_end.value="2019-12-12 10:10:10"
    	  form1.ad_con.value="在南極的深海底下有一個大鳳梨，也有很多的帝王蟹，但是只有一支是老闆。蟹老闆每賣出一個烏魚子，就會有一隻企鵝沒辦法游泳。沒有買賣就不會有傷害!!"
    	  
    	 
      }
</script>

<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
		value : '${adVO.ad_start}', // value:   new Date(),
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
<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date2').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker:true,
		step : 60, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
		value : '${adVO.ad_end}',
	});
	// value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
</script>
<script>
	function setImagePreview() {
		var docObj = document.getElementById("doc");
		var imgObjPreview = document.getElementById("preview");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '800px';
			imgObjPreview.style.height = '480px';
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
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}
</script>





</html>