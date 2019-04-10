<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodOrder.model.*" %>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
<html>
<head>
<title>修改食材商城訂單</title>
<link href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />

	 <!-- ##### Contact Area Start #####-->
    <section class="contact-area section-padding-100">
            <%-- 錯誤列表 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <!-- Row -->
                <div class="row">
                	<!-- Column -->
                    <div class="col-lg-8 col-xlg-9 col-md-7">
                        <div class="card">
                            <div class="card-body">
                                <form class="form-horizontal form-material">
                                    <div class="form-group">
                                        <label class="col-md-12">訂單編號</label>
                                        <div class="col-md-12">
                                            <h4>${foodOrderVO.food_or_ID}</h4>
                                        </div>
                                    </div>      
                                    <div class="form-group">
                                        <label class="col-md-12">顧客姓名</label>
                                        <div class="col-md-12">
                                            <h4>${custSvc.getOneCust(foodOrderVO.cust_ID).cust_name}</h4>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">收件人姓名</label>
                                        <div class="col-md-12">
                                            <h4>${foodOrderVO.food_or_name}</h4>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">收件地址</label>
                                        <div class="col-md-12">
                                            <h4>${foodOrderVO.food_or_addr}</h4>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">收件人電話</label>
                                        <div class="col-md-12">
                                            <h4>${foodOrderVO.food_or_tel}</h4>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">下訂日期</label>
                                        <div class="col-md-12">
                                            <h4>${foodOrderVO.food_or_start}</h4>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">出貨日期</label>
                                        <div class="col-md-12">
                                            <input name="food_or_send" id="f_date1" type="text" value="${foodOrderVO.food_or_send}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">到貨日期</label>
                                        <div class="col-md-12">
                                            <input name="food_or_rcv" id="f_date1" type="text" value="${foodOrderVO.food_or_rcv}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">完成日期</label>
                                        <div class="col-md-12">
                                            <input name="food_or_end" id="f_date1" type="text" value="${foodOrderVO.food_or_end}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-12">訂單狀態</label>                                       
                                        <div class="col-sm-12">
                                        	<select name="food_or_status" class="form-control form-control-line">
                                        	<c:forEach var="mallOrStatus" items="${mallOrStatusMap}">                
                                            	<option value="${mallOrStatus.key}"
                                            	 ${(foodOrder.food_or_status == mallOrStatus.key)? 'selected':''}>${mallOrStatus.value}</option>
                                            </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <input type="submit" value="送出" class="btn btn-success">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                    <!-- Column -->
                    <div class="col-lg-4 col-xlg-3 col-md-5">
                        <div class="card">
                            <div class="card-body">
                              
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                </div>
                </div>
	</section>
    <!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
</body>
</html>