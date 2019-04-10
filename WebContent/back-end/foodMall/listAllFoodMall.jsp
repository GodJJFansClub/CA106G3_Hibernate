<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodMall.model.*" %>
<jsp:useBean id="foodMallSvc" scope="page" class="com.foodMall.model.FoodMallService"/>
<jsp:useBean id="foodSupSvc" class="com.foodSup.model.FoodSupService"/>
<jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService"/>

<%
	List<FoodMallVO> list = foodMallSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
</head>
<body>
    <div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
        data-layout="vertical" data-sidebartype="full"
        data-boxed-layout="full">
        <jsp:include page="/back-endTemplate/header.jsp" flush="true" />
        <jsp:include page="/back-end/sideBar/mallMana.jsp" flush="true" />
        <div class="page-wrapper">
            <div class="page-breadcrumb">
                <%--=================================工作區================================================--%>
 	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">管理食材商城商品</font></div>
	<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>
                
                
                 <div class="col">
                        <div class="card">
                            
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col">食材供應商名稱:</th>
                                            <th scope="col">食材名稱:</th>
                                            <th scope="col">食材介紹:</th>
                                            <th scope="col">商品狀態:</th>
                                            <th scope="col">價格:</th>
                                            <th scope="col">單位:</th>
                                            <th scope="col">產地:</th>
                                            
                                          
                                        </tr>
                                    </thead>
                
				
					<%@ include file="/back-end/file/page1.file" %>
					<c:forEach var="foodMallVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<th scope="row">${foodSupSvc.getOneFoodSup(foodMallVO.food_sup_ID).food_sup_name}</th>
							<th scope="row">${foodSvc.getOneFood(foodMallVO.food_ID).food_name}</th>
							<th scope="row">${foodMallVO.food_m_name}</th>
							<th scope="row">
								<c:choose>
									<c:when test="${foodMallVO.food_m_status eq 'p2'}">
										<span class="label label-danger label-rounded">
											${mallStatusMap[foodMallVO.food_m_status]}
										</span>
									</c:when>
									<c:otherwise>${mallStatusMap[foodMallVO.food_m_status]}</c:otherwise>
								</c:choose>
							</th>
							<th scope="row">${foodMallVO.food_m_price}</th>
							<th scope="row">${foodUnitMap[foodMallVO.food_m_unit]}</th>
							<th scope="row">${foodMallVO.food_m_place}</th>
						
							<th scope="row">
								<form method="post" action="<%=request.getContextPath()%>/foodMall/foodMall.do">
									<input type="submit" value="審核" class="btn btn-secondary">
									<input type="hidden" name="food_ID" value="${foodMallVO.food_ID}">
									<input type="hidden" name="food_sup_ID" value="${foodMallVO.food_sup_ID}">
									<input type="hidden" name="action" value="getOne_For_Update">
								</form>
							</th>
						</tr>
					</c:forEach>
				</table>
				<div>
				<%@ include file="/back-end/file/page2.file" %>
				</div>
			</div>
           </div>
        </div>
				
				<c:if test="${openModal!=null}">

				<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">			
							<div class="modal-body">
				<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
				               <jsp:include page="/back-end/foodMall/update_foodMall_input.jsp" />
				<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
							</div>
							
							<div class="modal-footer">
				                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				                <button id="upStatus" type="button" class="btn btn-primary">Save changes</button>
				            </div>
						
						</div>
					</div>
				</div>

		        <script>
		    		 $("#basicModal").modal({show: true});
		    		 $(document).ready(function(){
		    			 $("#upStatus").click(function(){
		    				 $("#upStatusForm").submit(); 
		    			 });
		    		 });
		        </script>
 				</c:if>


			</div>
		</div>
	</div>
</body>
</html>