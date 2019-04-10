<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodOrder.model.*"%>
<%@ page import="com.foodOrDetail.model.FoodOrDetailVO" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="foodOrderSvc" class="com.foodOrder.model.FoodOrderService"/>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<%
	
	List<FoodOrderVO> list = foodOrderSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title></title>
</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true"/>
		<jsp:include page="/back-end/sideBar/custMana.jsp" flush="true"/>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
<%--=================================工作區================================================--%>
	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">食材商城訂單</font></div>
	<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>
				<%-- 錯誤列表 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<div class="col">
					<div class="card">

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th scope="col">訂單編號</th>
										<th scope="col">收件人姓名</th>
										<th scope="col">訂單狀態</th>
										<th scope="col">下訂日期</th>
										<th scope="col">出貨日期</th>
										<th scope="col">到貨日期</th>
										<th scope="col">完成日期</th>
										<th scope="col">總計</th>
									</tr>
								</thead>


								<%@ include file="/file/page1.file"%>
								<!-- 利用whichpage通知當前頁面, controller接到req再轉傳時, 是同一個req -->
								<c:forEach var="foodORVO" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
									<tr class="foodOrder"
										${(foodORVO.food_or_ID==foodOrderVO.food_or_ID) ? 'bgcolor=#CCCCFF':''}>
										 <th scope="row">${foodORVO.food_or_ID}</th>
										<th scope="row">${foodORVO.food_or_name}</th>
										<th scope="row">
											<c:if test="${foodORVO.food_or_status eq 'o1'}">
												<span class="label label-danger label-rounded">
													${mallOrStatusMap[foodORVO.food_or_status]}
												</span>
											</c:if>
											<c:if test="${not (foodORVO.food_or_status eq 'o1')}">
												${mallOrStatusMap[foodORVO.food_or_status]}
											</c:if>
										</th>
										<th scope="row">${foodORVO.food_or_start}</th>
										<th scope="row">${foodORVO.food_or_send}</th>
										<th scope="row">${foodORVO.food_or_rcv}</th>
										<th scope="row">${foodORVO.food_or_end}</th>
										<th scope="row"><c:set var="total" value="0" /> <c:forEach
												var="foodOrDetailVO"
												items="${foodOrderSvc.getFoodOrDetailsByFood_or_ID(foodORVO.food_or_ID)}">
												<c:set var="total"
													value="${total+foodOrDetailVO.food_od_stotal}" />
											</c:forEach> ${total}</th>
										<th scope="row">
											<FORM METHOD="post"
												action="<%=request.getContextPath()%>/foodOrder/foodOrder.do">
												<button class="btn btn-secondary" id="update" type="button">更新訂單狀態</button>
												<input type="hidden" id="food_or_ID" name="food_or_ID"
													value="${foodORVO.food_or_ID}"> <input
													type="hidden" name="whichPage" value="<%=whichPage%>">
												<input type="hidden" id="action" name="action">
											</FORM>
										</th>
									</tr>

								</c:forEach>
							</table>
							</div>
							</div>
							</div>
							<%@ include file="/file/page2.file"%>
						</div>
						<c:if test="${openModal!=null}">
							<div class="modal fade" id="basicModal" tabindex="-1"
								role="dialog" aria-labelledby="basicModal" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<h3 class="modal-title" id="myModalLabel">
												${foodOrderVO.food_or_ID}</h3>
										</div>

										<div class="modal-body">
											<form style="display: none" id="changeOrHid" method="post"
												action="<%=request.getContextPath()%>/foodOrder/foodOrder.do">
												<input type="hidden" name="action" value="bkChStatSend">
												<input type="hidden" id="food_or_ID" name="food_or_ID"
													value="${foodOrderVO.food_or_ID}"> <input
													type="hidden" name="whichPage" value="<%=whichPage%>">
											</form>
											<jsp:include
												page="/back-end/foodOrder/listFoodOrDetails_ByFood_or_ID.jsp" />
										</div>
										<div class="modal-footer" id="btnDivPos">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
											<c:choose>
												<c:when
													test="${(foodOrderVO.food_or_status eq 'o0')||(foodOrderVO.food_or_status eq 'o1')}">
			                                      ${canSend?'<button id="fORCheck" type="button"  class="btn btn-primary">送出貨物</button>':mallOrStatusMap[foodOrderVO.food_or_status]}
			                            </c:when>
												<c:otherwise>
													<h4>${mallOrStatusMap[foodOrderVO.food_or_status]}</h4>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>

							<script>
							
							
			    		$("#basicModal").modal({show: true});
			    		$(document).ready(function(){
				    		$(".checkToSend").click(function(event){
				    		let btnID = event.target.id;
				    		$.ajax({
				    			type:"POST",
				    				url:"<%=request.getContextPath()%>/foodOrDetail/foodOrDetail.do",
				    				data:$(this).parent("form").serialize(),
				    				dataType:"json",
				    				success: function(data){
					    				if(data.food_od_status === "d2"){
					    					$("#"+btnID).attr('disabled','true');
					    					$("#"+btnID).text("確認到貨");
					    				}
					    				console.log(data);
				    					if(data.canSend === true){
				    						let tempBtnA = document.createElement("button");
				    						tempBtnA.setAttribute("id","fORCheck");
				    						tempBtnA.setAttribute("type","button");
				    						tempBtnA.setAttribute("class","btn btn-primary");
				    						tempBtnA.setAttribute("data-dismiss","modal");
				    						tempBtnA.innerText = "送出貨物";
				    						tempBtnA.addEventListener(
				    							"click",function(){
				    								$("#changeOrHid").submit();
				    							}, false);
				    						$("#btnDivPos").html(tempBtnA);
				    					}
				    				},
				    				error: function(errdata){
				    					alert("ajax 錯誤" + errdata);
				    					console.log(errdata);
				    				}
				    			});
				    		});
				    				
				    		$("#fORCheck").click(function(){
				    			$("#changeOrHid").submit();
				    					
				    		});
			    		});
			    		</script>
						</c:if>

					
					</div>
				</div>
		
	<script>
		$(document).ready(function(){
			$(".foodOrder").click(function(evt){
				let foodOrForm = $(this).find("form");
				if(evt.target.id != "update") { 
					foodOrForm.children("#action").val("getOne_For_Display");
				}else{
					foodOrForm.children("#action").val("getOne_For_Udate");	
				}
				foodOrForm.submit();
			});
			
		});
		
        $.datetimepicker.setLocale('zh');
        $('#foodOrStart_date').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '',              // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
	</script>
</body>
</html>