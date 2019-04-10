<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.report.model.*"%>

<%
  ReportVO reportVO = (ReportVO) request.getAttribute("reportVO");
%>
<% 
    java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formatDate = df.format(new java.util.Date());
    out.println(formatDate);
    
  %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>檢舉文章資料新增 - addReport.jsp</title>

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
	<tr><td>
		 <h3>檢舉文章資料新增 - addReport.jsp</h3></td><td>
		 
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>
<p class="mt-3">您可以利用下列表單與我們交流檢舉文章的內容,請留下資料，我們會儘快與您聯絡！</p>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/report/report.do" name="form1">
<table>
  
	<tr>
		<td>檢舉編題:</td>
		<td><input type="TEXT" name="report_title" size="45" class="form-control"
			 value="<%= (reportVO==null)? "請輸入檢舉文章標題" :reportVO.getReport_title() %>" /></td>
	</tr>

	<tr>
		<td>檢舉分類:</td>
		<td><input type="TEXT" name="report_sort" size="45" 
			 value="<%= (reportVO==null)? "1" :reportVO.getReport_sort()%>" /></td>
	</tr>
	
	<tr>
		<td>檢舉時間:</td>
		<td><input type="TEXT" name="report_start" id="f_date1" 
			 value="<%= (reportVO==null)? "請選擇檢舉文章時間" :reportVO.getReport_start()%>" /></td>
	</tr>
	
	<tr>
		<td>檢舉狀態:</td>
		<td><input type="TEXT" name="report_status" size="45" 
			 value="<%= (reportVO==null)? "1" : reportVO.getReport_status()%>" /></td>
	</tr>
	
	<tr>
		<td>檢舉內容:</td>
		<td><input type="TEXT" name="report_con" size="45" 
			 value="<%= (reportVO==null)? "請填入檢舉文章內容" : reportVO.getReport_con()%>" /></td>
	</tr>
	
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="cust_ID" size="45" 
			 value="<%= (reportVO==null)? "C00001" :reportVO.getCust_ID()%>" /></td>
	</tr>
	
	<tr>
		<td>文章編號:</td>
		<td><input type="TEXT" name="forum_art_ID" size="45" 
			 value="<%= (reportVO==null)? "A00001" : reportVO.getForum_art_ID()%>" /></td>
	</tr>
</table>
<br>
 <div>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
<button type="reset" class="btn btn-primary float-right send-btn">重設</button>
 </div>
</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

 <% 
   java.sql.Timestamp report_start = null;
   try {
	    report_start = reportVO.getReport_start();
    } catch (Exception e) {
	    report_start = new java.sql.Timestamp(System.currentTimeMillis());
    }
  %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=report_start%>', 
		   // value:   new Date(),
//           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
             //startDate:	            '2017/07/10',  // 起始日 
//           //minDate:               '-1970-01-01', // 去除今日(不含)之前
//           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
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