<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<%@ page import="com.chefSch.model.*"%>

<%	
	ChefSchService chefSchSvc = new ChefSchService();
	ChefVO chefVO = (ChefVO)session.getAttribute("chefVO"); 
	String chef_ID = chefVO.getChef_ID();
	List<ChefSchVO> listAll = chefSchSvc.getAllChefSchByID(chef_ID);
	pageContext.setAttribute("listAll", listAll);
%>

<html>
<head>
<title>主廚排程管理</title>
</head>
<style>
.table-hidden tbody{
  overflow-y: auto;/*設定卷軸 auto 是有超過我的高度的時候才會出現卷軸*/
    height: 400px;/*自己設定*/
    display: block;
}
.table-hidden tr {
	height:50px;
    width: 100%;
    display: inline-table;
}
/*因為 display: block 打壞了 tbody 的結構，
  所以 thead 的 tr 跟 tbody 的 td 寬度拆開了，
  所以必須重新設定表格的寬度，
  但是 tbody 有包括卷軸 針對bootstrap 去修正卷軸
*/
.table-hidden thead th[data-th="其他"]{ width:400px;}
/*因為 tbody 多了卷軸 尺寸多了 17px*/
.table-hidden tbody td[data-th="其他"]{ width:383px;}

.table-hidden td[data-th="chefSchDate"],.table-hidden th[data-th="chefSchDate"]{
	width:33%; 
}
.table-hidden td[data-th="chefSchStatus"],.table-hidden th[data-th="chefSchStatus"]{ 
	width:33%;
}
.table-hidden td[data-th="checkChefSch"],.table-hidden th[data-th="checkChefSch"]{ 
	width:33%;
}

</style>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerChef.jsp" flush="true" />
	<!-- #####  Start ##### -->
		<div class="container-fliud">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-3" style="font-family:Microsoft JhengHei;">
					<form method="post" action="<%=request.getContextPath() %>/chefSch/chefSch.do">
						<div class="form-group">
							<label style="font-size:14px;font-weight:bolder;font-family: Microsoft JhengHei;">請選擇可接單日期</label> 
							<input name="chef_sch_date" class="form-control" id="sch_date" type="text" autocomplete="off" />${errMsgs.chef_sch_date}
						</div>
						<input type="hidden" name="chef_ID" value="${chefVO.chef_ID}"> 
						<input type="hidden" name="action" value="insert"> 
						<input type="submit" class="btn btn-success btn-lg btn-block" value="新增排程">
					</form>
					<%--Error Message --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red; font-size: 20px;">Error:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
				<div class="col-7" style="font-family:Microsoft JhengHei;">
					<table class="table table-striped table-bordered table-hidden">
						<thead>
							<tr>
							　　<th data-th="chefSchDate">排程日期</th>
							　　<th data-th="chefSchStatus">當天狀態</th>
							　　<th data-th="checkChefSch">排程管理</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="chefSchVO" items="${listAll}">
								<tr>
									<td data-th="chefSchDate">${chefSchVO.chef_sch_date}</td>
									<td data-th="chefSchStatus">
										<c:if test="${chefSchVO.chef_sch_status=='c0'}">閒置</c:if>
										<c:if test="${chefSchVO.chef_sch_status=='c1'}">已預定</c:if>
										<c:if test="${chefSchVO.chef_sch_status=='c2'}">新的訂單</c:if>
									</td>
									<td data-th="checkChefSch">
										<c:if test="${chefSchVO.chef_sch_status=='c0'}">
											<form method="post" action="<%=request.getContextPath()%>/chefSch/chefSch.do">
												<input type="hidden" name="chef_ID" value="${chefSchVO.chef_ID}"> 
												<input type="hidden" name="chef_sch_date" value="${chefSchVO.chef_sch_date}"> 
												<input type="hidden" name="action" value="delete">
												<input type="submit" class="btn btn-danger" value="刪除">
											</form>
										</c:if>
										<c:if test="${chefSchVO.chef_sch_status=='c1'}">
											<form method="post" action="<%=request.getContextPath()%>/chefSch/chefSch.do">
												<input type="hidden" name="chef_ID" value="${chefSchVO.chef_ID}"> 
												<input type="hidden" name="chef_sch_date" value="${chefSchVO.chef_sch_date}"> 
												<input type="hidden" name="action" value="delete">
												<input type="submit" class="btn btn-secondary" value="刪除" disabled>
											</form>
										</c:if>
										<c:if test="${chefSchVO.chef_sch_status=='c2'}">
											<a href="<%=request.getContextPath()%>/front-end/menuOrder/unCheckMenuOrder.jsp">
											<input type="button" class="btn btn-warning" value="審核"></a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-1"></div>
			</div>
		</div>
	<!-- #####  End #####-->
	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<% 
  java.sql.Timestamp chef_sch_date = null;
  try {
	  	chef_sch_date = java.sql.Timestamp.valueOf(request.getParameter("chef_sch_date").trim());
   } catch (Exception e) {
	    chef_sch_date = null;
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
        $('#sch_date').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       //step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d' ,         //format:'Y-m-d H:i:s',
		   //value: '<%=chef_sch_date%>' , // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1969-12-25', // 去除今日(不含)之前
           maxDate:               '+1970-03-01'  // 去除今日(不含)之後
        });
</script>
</html>