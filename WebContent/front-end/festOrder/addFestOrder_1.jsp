<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.festOrder.model.*"%>
<%@ page import="com.festOrderDetail.model.*"%>

<%
	FestOrderVO festOrderVO = (FestOrderVO) request.getAttribute("festOrderVO");
%>

<%
	FestOrderDetailVO festOrderDetailVO = (FestOrderDetailVO) request.getAttribute("festOrderDetailVO");
%>
<%
	
	   java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String formatDate = df.format(new java.util.Date());
	   out.println(formatDate); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>節慶主題料理訂單新增 - addFestOrder.jsp</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/froTempl/temp/img/core-img/favicon.ico">

<!-- Stylesheet -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/froTempl/temp/style.css">
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

<jsp:include page="/froTempl/header.jsp" flush="true" />
<!-- ##### Contact Area Start #####-->
<jsp:include page="/froTempl/headerMall.jsp" flush="true" />

	<table id="table-1">
		<tr>
			<td>
				<h3>節慶主題料理訂單新增 - addFestOrder.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/festOrder/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/festOrder/festOrder.do" name="form1">
		<table>
			<tr>
				<td>訂單狀態:</td>
				<td><input type="TEXT" name="fest_or_status" size="45" value="<%=(festOrderVO == null) ? "3" : festOrderVO.getFest_or_status()%>" /></td>
			</tr>

			<tr>
				<td>價格:</td>
				<td><input type="TEXT" name="fest_or_price" size="45" value="<%=(festOrderVO == null) ? "2000" : festOrderVO.getFest_or_price()%>" /></td>
			</tr>

			<tr>
				<td>訂單成立日期:</td>
				<td><input type="date" name="fest_or_start" value="<%=(festOrderVO == null) ? "2000" : festOrderVO.getFest_or_start()%>" /></td>
			</tr>

			<tr>
				<td>出貨日期:</td>
				<td><input type="date" name="fest_or_send" value="<%=(festOrderVO == null) ? "2000" : festOrderVO.getFest_or_send()%>" /></td>
			</tr>

			<tr>
				<td>訂單結束日期:</td>
				<td><input type="date" name="fest_or_end" value="<%=(festOrderVO == null) ? "2000" : festOrderVO.getFest_or_end()%>" /></td>
			</tr>

			<tr>
				<td>折扣:</td>
				<td><input type="TEXT" name="fest_or_disc" size="45" value="<%=(festOrderVO == null) ? "0.8" : festOrderVO.getFest_or_disc()%>" /></td>
			</tr>

			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="cust_ID" size="45" value="<%=(festOrderVO == null) ? "C00001" : festOrderVO.getCust_ID()%>" /></td>
			</tr>
		</table>

		<table>

			<tr>
				<td>節慶料理編號:</td>
				<td><input type="TEXT" name="fest_m_ID" size="45" value="<%=(festOrderDetailVO == null) ? "FM0002" : festOrderDetailVO.getFest_m_ID()%>" /></td>
			</tr>

			<tr>
				<td>訂單數量:</td>
				<td><input type="TEXT" name="fest_or_qty" value="<%=(festOrderDetailVO == null) ? "45" : festOrderDetailVO.getFest_or_qty()%>" /></td>
			</tr>


		</table> <br> <br> 
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
	</FORM>
</section>

<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date fest_or_start = null;
	try {
		fest_or_start = festOrderVO.getFest_or_start();
	} catch (Exception e) {
		fest_or_start = new java.sql.Date(System.currentTimeMillis());
	}

	java.sql.Date fest_or_send = null;
	try {
		fest_or_send = festOrderVO.getFest_or_send();
	} catch (Exception e) {
		fest_or_send = new java.sql.Date(System.currentTimeMillis());
	}

	java.sql.Date fest_or_end = null;
	try {
		fest_or_end = festOrderVO.getFest_or_end();
	} catch (Exception e) {
		fest_or_end = new java.sql.Date(System.currentTimeMillis());
	}
%>
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
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	       value: '<%=fest_or_start%>' 
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
</html>