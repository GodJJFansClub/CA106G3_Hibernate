<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="custSvc" class="com.cust.model.CustService"/>
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
				<div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">訂單詳細資訊</h4>
                    </div>
                </div>
            </div>
            <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
				    <c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			 <div class="container-fluid">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <!-- Row -->
                <div class="row">
                	<!-- Column -->
                    <div class="col-lg-4">
                        <div class="card">
                            <div class="card-body">
                                
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
                                             <h4>${foodOrderVO.food_or_send}</h4>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">到貨日期</label>
                                        <div class="col-md-12">
                                            <h4>${foodOrderVO.food_or_rcv}</h4>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">完成日期</label>
                                        <div class="col-md-12">
                                           <h4>${foodOrderVO.food_or_end}</h4>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-12">訂單狀態</label>                                       
                                        <div class="col-sm-12">
                                        	<h4>${mallOrStatusMap[foodOrderVO.food_or_status]}</h4>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-12">總計</label>                                       
                                        <div class="col-sm-12">
                                        	<h4>${total}</h4>
                                        </div>
                                    </div>

                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                    <!-- Column -->
                    <div class="col-lg-8">
                        <div class="card">
                            <div class="card-body">
                              <jsp:include page="/back-end/foodOrder/listFoodODs.jsp" flush="true" />
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                </div>

			</div>
		</div>
	</div>
</body>
</html>