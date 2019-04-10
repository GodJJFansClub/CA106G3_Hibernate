<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.festMenu.model.*"%>

<%
	FestMenuVO festMenuVO = (FestMenuVO) request.getAttribute("festMenuVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>節慶主題料理修改 - update_festMenu_input.jsp</title>

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

	<table id="table-1">
		<tr>
			<td>
				<h3>節慶主題料理修改 - update_festMenu_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/festMenu/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>節慶主題料理訂單修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
		<font style="color: red"></font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/festMenu/festMenu.do"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>節慶主題料理編號:<font color=red><b>*</b></font></td>
				<td><%=festMenuVO.getFest_m_ID()%></td>
			</tr>

			<tr>
				<td>節慶主題料理名稱 :</td>
				<td><input type="TEXT" name="fest_m_name" size="45"
					value="<%=festMenuVO.getFest_m_name()%>" /></td>
			</tr>
			<tr>
				<td>數量</td>
				<td><input type="TEXT" name="fest_m_qty" size="45"
					value="<%=festMenuVO.getFest_m_qty()%>" /></td>
			</tr>
			<tr>
				<td>開始預購日期</td>
				<td><input type="Date" name="fest_m_start" class="f_date1"
					size="30" value="<%=festMenuVO.getFest_m_start()%>" /></td>
			</tr>

			<tr>
				<td>結束預購日期</td>
				<td><input type="Date" name="fest_m_end" class="f_date2"
					size="30" value="<%=festMenuVO.getFest_m_end()%>" /></td>
			</tr>
			<tr>
				<td>照片:</td>
				<td><input type="file" name="fest_m_pic" /></td>
			</tr>
			<tr>
				<td>介紹:</td>
				<td><input type="TEXT" name=fest_m_resume size="45"
					value="<%=festMenuVO.getFest_m_resume()%>" /></td>
			</tr>
			<tr>
				<td>出貨日期</td>
				<td><input name="fest_m_send" id="f_date3" type="date"></td>
			<tr>
			<tr>
				<td>節慶主題料理狀態 :</td>
				<td><input type="TEXT" name="fest_m_status" size="45"
					value="<%=festMenuVO.getFest_m_status()%>" /></td>
			</tr>
			<tr>
				<td>種類 :</td>
				<td><input type="TEXT" name="fest_m_kind" size="45"
					value="<%=festMenuVO.getFest_m_kind()%>" /></td>
			</tr>
			<tr>
				<td>價格 :</td>
				<td><input type="TEXT" name="fest_m_price" size="45"
					value="<%=festMenuVO.getFest_m_price()%>" /></td>
			</tr>
			<tr>
				<td>主廚編號 :</td>
				<td><input type="TEXT" name="chef_ID" size="45"
					value="<%=festMenuVO.getChef_ID()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="fest_m_ID" value="<%=festMenuVO.getFest_m_ID()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
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
	$.datetimepicker.setLocale('zh');
	$('.f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',

	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	$('.f_date2').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',

	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	$('.f_date3').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',

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
</html>