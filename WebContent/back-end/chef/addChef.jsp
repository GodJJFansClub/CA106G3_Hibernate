<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cust.model.*"%>
<%@ page import="com.chef.model.*"%>

<%
	CustVO custVO = (CustVO) request.getAttribute("custVO");
	ChefVO chefVO = (ChefVO) request.getAttribute("chefVO");
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>主廚資料新增 - addCust.jsp</title>
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
	width: 450px;
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

<body bgcolor='white'>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<aside class="left-sidebar" data-sidebarbg="skin5">
			<%--==============<jsp:include page="/back-end/XXXX/sidebar.jsp" flush="true" />=================================--%>

		</aside>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>
				<h3>資料新增:</h3>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/chef/chef.do" name="form1"
					enctype="multipart/form-data">

					<table>
						<tr>
							<td>主廚帳號:</td>
							<td><input type="TEXT" id="cust_acc" name="cust_acc"
								size="45"
								value="<%=(custVO == null) ? "Aa158556" : custVO.getCust_acc()%>" />
							<p id="answer"></p></td>
						</tr>

						<tr>
							<td>主廚密碼:</td>
							<td><input type="password" name="cust_pwd" size="45"
								value="<%=(custVO == null) ? "Aa123456" : custVO.getCust_pwd()%>" /></td>
						</tr>

						<tr>
							<td>主廚姓名:</td>
							<td><input type="TEXT" name="cust_name" size="45"
								value="<%=(custVO == null) ? "teddy" : custVO.getCust_name()%>" /></td>
						</tr>

						<tr>
							<td>身分證字號:</td>
							<td><input type="TEXT" name="cust_pid" size="45"
								value="<%=(custVO == null) ? "a987654321" : custVO.getCust_pid()%>" /></td>
						</tr>

						<tr>
							<td>主廚性別:</td>
							<td><input type="radio" name="cust_sex" size="10" value="M"
								${custVO.cust_sex=='M'||custVO ==null ? 'checked':'' } />男 <input
								type="radio" name="cust_sex" size="10" value="F"
								${custVO.cust_sex=='F'? 'checked':'' } />女</td>
						</tr>

						<tr>
							<td>主廚生日:</td>
							<td><input type="TEXT" name="cust_brd" id="f_date1"
								size="45" /></td>
						</tr>

						<tr>
							<td>主廚電話:</td>
							<td><input type="TEXT" name="cust_tel" size="45"
								value="<%=(custVO == null) ? "09090909" : custVO.getCust_tel()%>" /></td>
						</tr>

						<tr>
							<td>主廚地址:</td>
							<td><input type="TEXT" name="cust_addr" size="45"
								value="<%=(custVO == null) ? "09090909" : custVO.getCust_addr()%>" />
							</td>
						</tr>

						<tr>
							<td>主廚信箱:</td>
							<td><input type="TEXT" name="cust_mail" size="45"
								value="<%=(custVO == null) ? "gggccc@yahoo" : custVO.getCust_mail()%>" /></td>
						</tr>

						<tr>
							<td>主廚暱稱:</td>
							<td><input type="TEXT" name="cust_niname" size="45"
								value="<%=(custVO == null) ? "555" : custVO.getCust_niname()%>" /></td>
						</tr>

						<tr>
							<td>主廚大頭照:</td>
							<td><input type="file" name="cust_pic" size="45" id="doc"
								onchange="javascript:setImagePreview();" /></td>
						</tr>
						<tr>
							<td>主廚服務地區:</td>
							<td><input type="radio" name="chef_area" size="10" value="0"
								${chefVO.chef_area=='0'||chefVO ==null ? 'checked':'' } />北<br>
								<input type="radio" name="chef_area" size="10" value="1"
								${chefVO.chef_area=='1'? 'checked':'' } />中<br> <input
								type="radio" name="chef_area" size="10" value="2"
								${chefVO.chef_area=='2'? 'checked':'' } />南<br> <input
								type="radio" name="chef_area" size="10" value="3"
								${chefVO.chef_area=='3'? 'checked':'' } />東<br></td>
						</tr>
						<tr>
							<td>主廚簡介:</td>
							<td><input type="text" name="chef_resume" size="50"
								value="${chefVO==null? '請輸入簡介...':chefVO.chef_resume}">
							</td>
						</tr>
					</table>
					<div id="localImag">
						<img id="preview" width=-1 height=-1 style="display: none" />
					</div>
					<br> <input type="hidden" name="action" value="insert">
					<input type="submit" value="送出新增">

				</FORM>

			
			</div>
		</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>
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
 	       timepicker:true,       //timepicker:true,
 	       step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '${custVO.cust_brd}', // value:   new Date(),
			
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
				type: "POST",
				data: { action: 'ask', cust_acc: $('#cust_acc').val() },
				//dataType: 'json',
				success: function(res){
					console.log(res);
					$('#answer').text(res);
					
				},
				error: function(res){
					console.log(res);
					$('#answer').text('Error! Could not reach the API. ');
				}
			});
		}
		
		function _debounce(callback, time){
			if(timer)
				 clearTimeout(timer);
			timer = setTimeout(function(){
				callback();
			}, time);
		}
	</script>




</html>