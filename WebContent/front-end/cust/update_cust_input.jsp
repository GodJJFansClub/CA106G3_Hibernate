<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cust.model.*"%>

<%
	CustVO custVO = (CustVO) session.getAttribute("custVO"); //CustServlet.java (Concroller) 存入req的custVO物件 (包括幫忙取出的custVO, 也包括輸入資料錯誤時的custVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>顧客資料修改</title>
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
	padding: 1px;
}
</style>


</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />

	<!-- ##### Breadcrumb Area Start ##### -->
	<jsp:include page="/froTempl/headerCust.jsp" flush="true" />
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

							<h2 style="font-weight:bold">顧客資料修改</h2>
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
											ACTION="<%=request.getContextPath()%>/cust/cust.do"
											name="form1" enctype="multipart/form-data">
											<table>
												<tr>
													<td>顧客編號:<font color=red><b>*</b></font></td>
													<td><%=custVO.getCust_ID()%></td>
												</tr>
												<tr>
													<td>顧客帳號:</td>
													<td><input type="TEXT" name="cust_acc" size="45"
														value="<%=custVO.getCust_acc()%>" /></td>
												</tr>

												<tr>
													<td>顧客密碼:</td>
													<td><input type="TEXT" name="cust_pwd" size="45"
														value="<%=custVO.getCust_pwd()%>" /></td>
												</tr>

												<tr>
													<td>顧客姓名:</td>
													<td><input type="TEXT" name="cust_name" size="45"
														value="<%=custVO.getCust_name()%>" /></td>
												</tr>

												<tr>
													<td>顧客身分證字號:</td>
													<td><input type="TEXT" name="cust_pid" size="45"
														value="<%=custVO.getCust_pid()%>" /></td>
												</tr>

												<tr>
													<td>顧客性別:</td>
													<c:if test="${custVO.cust_sex.equals('M')}" var="true">
														<td><input type="radio" name="cust_sex" size="10"
															value="M" checked style="width:20px;height:20px"/>男 
															<input type="radio"
															name="cust_sex" size="10" value="F" style="width:20px;height:20px"/>女</td>
													</c:if>

													<c:if test="${custVO.cust_sex.equals('F')}" var="true">
														<td><input type="radio" name="cust_sex" size="10"
															value="M" style="width:20px;height:20px"/>男 <input type="radio"
															name="cust_sex" size="10" value="F" checked style="width:20px;height:20px"/>女</td>
													</c:if>

												</tr>

												<tr>
													<td>顧客生日:</td>
													<td><input type="text" name="cust_brd" id="f_date1"
														size="45" value="<%=custVO.getCust_brd()%>"></td>
												</tr>

												<tr>
													<td>顧客電話:</td>
													<td><input type="TEXT" name="cust_tel" size="45"
														value="<%=custVO.getCust_tel()%>" /></td>
												</tr>

												<tr>
													<td>顧客地址:</td>
													<td><input type="TEXT" name="cust_addr" size="45"
														value="<%=custVO.getCust_addr()%>" /></td>
												</tr>

												<tr>
													<td>顧客信箱:</td>
													<td><input type="TEXT" name="cust_mail" size="45"
														value="<%=custVO.getCust_mail()%>" /></td>
												</tr>

												
												<tr>
													<td>顧客暱稱:</td>
													<td><input type="TEXT" name="cust_niname" size="45"
														value="<%=custVO.getCust_niname()%>" /></td>
												</tr>

												<tr>
													<td>顧客大頭照:</td>
													<td><input type="file" name="cust_pic" size="45"
														id="doc" onchange="javascript:setImagePreview();" /></td>
												</tr>

													<input type="hidden" name="cust_status"
														size="45" value="<%=custVO.getCust_status()%>" />
											</table>
											<div id="localImag">
												<img id="preview" width=-1 height=-1 style="diplay: none" />
											</div>
											<br> <input type="hidden" name="action" value="update">
											<input type="hidden" name="cust_ID"
												value="<%=custVO.getCust_ID()%>">
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
 	       timepicker:true,       //timepicker:true,
 	       step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=custVO.getCust_brd()%>'
 		   //value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
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