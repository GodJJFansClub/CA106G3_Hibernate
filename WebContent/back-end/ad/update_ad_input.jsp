<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad.model.*"%>

<%
	AdVO adVO = (AdVO) request.getAttribute("adVO"); //AdServlet.java (Concroller) 存入req的adVO物件 (包括幫忙取出的adVO, 也包括輸入資料錯誤時的adVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>廣告資料修改</title>
</head>
<body bgcolor='white'>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/adMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>

			
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ad/ad.do"
					name="form1" enctype="multipart/form-data">
					<table>
						<tr>
							<td>廣告編號:<font color=red><b>*</b></font></td>
							<td><%=adVO.getAd_ID()%></td>
						</tr>
						<tr>
							<td>廣告標題:</td>
							<td><%=adVO.getAd_title()%></td>
						</tr>


						<tr>
							<td>廣告圖片:</td>
							<td>
							<c:if test="${not empty adVO.ad_pic}">
								<img src="<%=request.getContextPath()%>/ad/ad.do?ad_ID=${adVO.ad_ID}"  width="300" height="200" />
							</c:if>
							<c:if test="${empty adVO.ad_pic}">
								<img id="preview_progressbarTW_img" src="<%=request.getContextPath()%>/images/null2.jpg"  width="300" height="200"/>
							</c:if><br>
							</td>
						</tr>

						<tr>
							<td>廣告內文:</td>
							<td><%=adVO.getAd_con()%></td>
						</tr>

						<tr>
							<td>廣告上架日期:</td>
							<td>${adVO.ad_start}</td>
						</tr>

						<tr>
							<td>廣告下架日期:</td>
							<td>${adVO.ad_end}</td>
						</tr>

						
						<tr>
							<td>廣告類別:</td>
							<td>${adTypeMap[adVO.ad_type]}</td>
						</tr>
						<tr>
							<td>狀態:</td>
							<td><select size="1" name="ad_status">
									<c:forEach var="adStatus" items="${adStatusMap}">
										<option value="${adStatus.key}"
											${(adVO.ad_status == ad.key)?'selected':''}>${adStatus.value}
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>食材供應商ID:</td>
							<td><%=adVO.getFood_sup_ID()%></td>
						</tr>




					</table>
					<div id="localImag">
						<img id="preview" width=-1 height=-1 style="diplay: none" />
					</div>
					<br> <input type="hidden" name="action" value="updateBack">
					<input type="hidden" name="ad_ID" value="<%=adVO.getAd_ID()%>">
					<input type="submit" value="送出修改">
				</FORM>
				
			</div>
		</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>

<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker:true,
		step : 60, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
		value : '${adVO.ad_start}', // value:   new Date(),
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
<script>

$("#progressbarTWInput").change(function(){

  readURL(this);

});

 

function readURL(input){

  if(input.files && input.files[0]){

    var reader = new FileReader();

    reader.onload = function (e) {

       $("#preview_progressbarTW_img").attr('src', e.target.result);

    }

    reader.readAsDataURL(input.files[0]);

  }

}

</script>
</html>