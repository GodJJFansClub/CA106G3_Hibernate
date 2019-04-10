<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.festOrderDetail.model.*"%>

<%
	FestOrderDetailVO festOrderDetailVO = (FestOrderDetailVO) request.getAttribute("festOrderDetailVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>節慶主題料理訂單明細修改 - update_festOrderDetail_input.jsp</title>

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
				<h3>節慶主題料理訂單修改 - update_festOrder_input.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/festOrderDetail/select_page.jsp"> 回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
    <h3>節慶主題料理訂單明細修改:</h3>
 
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
		ACTION="<%=request.getContextPath()%>/festOrderDetail/festOrderDetail.do"
		name="form1">
		<table>
			<tr>
				<td>節慶主題料理訂單編號:</td>
				<td><input type="TEXT" name="fest_or_ID" size="45"
					value="<%=festOrderDetailVO.getFest_or_ID()%>" /></td>
			</tr>
			<tr>
				<td>節慶主題料理編號:</td>
				<td><input type="TEXT" name="fest_m_ID" size="45"
					value="<%=festOrderDetailVO.getFest_m_ID()%>" /></td>
			</tr>
			<tr>
				<td>訂單評價:</td>
				<td><input type="TEXT" name="fest_or_rate" size="45"
					value="<%=festOrderDetailVO.getFest_or_rate()%>" /></td>
			</tr>

			<tr>
				<td>訂單評價留言:</td>
				<td><input type="TEXT" name="fest_or_msg" size="45"
					value="<%=festOrderDetailVO.getFest_or_msg()%>" /></td>
			</tr>
			<tr>
				<td>訂單數量</td>
				<td><input type="TEXT" name="fest_or_qty" size="45"
					value="<%=festOrderDetailVO.getFest_or_qty()%>" /></td>
			</tr>
			<tr>
				<td>訂單小計:</td>
				<td><input type="TEXT" name="fest_or_stotal" size="45"
					value="<%=festOrderDetailVO.getFest_or_stotal()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="fest_or_ID"
			value="<%=festOrderDetailVO.getFest_or_ID()%>"> <input
			type="submit" value="送出修改">
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