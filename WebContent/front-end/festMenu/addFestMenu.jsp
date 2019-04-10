<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.festMenu.model.*"%>

<%
	FestMenuVO festMenuVO = (FestMenuVO) request.getAttribute("festMenuVO");
%>
<%
	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	String formatDate = df.format(new java.util.Date());
	out.println(formatDate);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>節慶主題料理新增</title>

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

.fest_m_pic {
	width: 172.5px;
	height: 230px;
}
</style>

<style>
table {
	width: 800px;
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

	<!-- ##### Breadcrumb Area Start ##### -->
	<jsp:include page="/froTempl/headerChef.jsp" flush="true" />
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

							<h2 style="font-weight:bold">節慶主題料理新增</h2>
							<img
								src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/x.png"alt="">



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
		ACTION="<%=request.getContextPath()%>/festMenu/festMenu.do"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>節慶主題料理名稱:</td>
				<td><input type="TEXT" name="fest_m_name" size="20"
					value="<%=(festMenuVO == null) ? "節慶主題料理名稱一" : festMenuVO.getFest_m_name()%>" /></td>
			</tr>

			<tr>
				<td>數量:</td>
				<td><input type="TEXT" name="fest_m_qty" size="20"
					value="<%=(festMenuVO == null) ? "請輸入數量" : festMenuVO.getFest_m_qty()%>" /></td>
			</tr>

			<tr>
				<td>開始預購日期:</td>
				<td><input type="TEXT" name="fest_m_start" id="f_date1"
					value="<%=(festMenuVO == null) ? "請選擇開始預購日期" : festMenuVO.getFest_m_start()%>" /></td>
			</tr>

			<tr>
				<td>結束預購日期:</td>
				<td><input type="TEXT" name="fest_m_end" id="f_date2"
					value="<%=(festMenuVO == null) ? "請選擇結束預購日期" : festMenuVO.getFest_m_end()%>" /></td>
			</tr>

			<tr>
				<td>出貨日期:</td>
				<td><input type="TEXT" name="fest_m_send" id="f_date3"
					value="<%=(festMenuVO == null) ? "請選擇出貨日期" : festMenuVO.getFest_m_send()%>" /></td>
			</tr>

			<tr>
				<td>價格:</td>
				<td><input type="TEXT" name="fest_m_price" size="20"
					value="<%=(festMenuVO == null) ? "請輸入價格" : festMenuVO.getFest_m_price()%>" /></td>
			</tr>
			
			
			<tr>
				<td>照片:</td>
				<td><input type="file" name="fest_m_pic" size="20" id="doc"
					onchange="javascript:setImagePreview();" /></td>
			</tr>
			
			
			
			<tr>
				<td>介紹:</td>
				<td><textarea type="TEXT" name="fest_m_resume"
					value="<%=(festMenuVO == null) ? "請輸入介紹" : festMenuVO.getFest_m_resume()%>"></textarea></td>
			</tr>

				

		</table>


		<div id="localImag">
			<img id="preview" width=-1 height=-1 style="diplay: none" />
		</div>
		<br>
			<input type="hidden" name="action" value="insert">
			<input type="hidden" name="chef_ID" value="${chefVO.chef_ID}" />
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



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date fest_m_start = null;
	try {
		fest_m_start = festMenuVO.getFest_m_start();
	} catch (Exception e) {
		fest_m_start = new java.sql.Date(System.currentTimeMillis());
	}

	java.sql.Date fest_m_end = null;
	try {
		fest_m_end = festMenuVO.getFest_m_end();
	} catch (Exception e) {
		fest_m_end = new java.sql.Date(System.currentTimeMillis());
	}

	java.sql.Date fest_m_send = null;
	try {
		fest_m_send = festMenuVO.getFest_m_send();
	} catch (Exception e) {
		fest_m_send = new java.sql.Date(System.currentTimeMillis());
	}
%>

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
      function idwrite(name){
    	  
    	  form1.fest_m_name.value="中秋月餅烤肉組"
    	  form1.fest_m_qty.value="99"
    	  form1.fest_m_start.value="2019-03-29"
    	  form1.fest_m_end.value="2019-08-09"
    	  form1.fest_m_send.value="2019-09-09"
    	  form1.fest_m_resume.value="廚神推薦中秋烤肉最佳組合，一年一度的中秋節是闔家團圓一起烤肉的最佳時機，本組合提供10人份的頂級烤肉素材(內附火種)，如果肉烤燒焦或是沒考熟，還有月餅可以充飢。"
    	  form1.fest_m_price.value="999"
    	  
      }
</script>
<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%-- 	       value: '<%=fest_m_start%>',  --%>
		   // value:   new Date(),
//           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
             //startDate:	            '2017/07/10',  // 起始日 
//           //minDate:               '-1970-01-01', // 去除今日(不含)之前
//           //maxDate:                '+1970-01-01'  // 去除今日(不含)之後
	});     
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%-- 	       value: '<%=fest_m_end%>', // value:   new Date(), --%>
		   // value:   new Date(),
//           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
             //startDate:	            '2017/07/10',  // 起始日 
//           //minDate:               '-1970-01-01', // 去除今日(不含)之前
//           //maxDate:                '+1970-01-01'  // 去除今日(不含)之後
	}); 
     
        $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%-- 	       value: '<%=fest_m_send%> --%>
// 	', // value:   new Date(),
	// value:   new Date(),
	//           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日 
	//           //minDate:               '-1970-01-01', // 去除今日(不含)之前
	//           //maxDate:                '+1970-01-01'  // 去除今日(不含)之後
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