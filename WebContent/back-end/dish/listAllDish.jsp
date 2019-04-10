<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dish.model.*"%>

<jsp:useBean id="dishSvc" scope="page" class="com.dish.model.DishService"/>

<%

    List<DishVO> list = dishSvc.getAll();
    pageContext.setAttribute("list",list);
%>




<html>
<head>
<title>下架菜色</title>


</head>
<body>
	<div id="main-wrapper" data-navbarbg="skin6" data-theme="light"
		data-layout="vertical" data-sidebartype="full"
		data-boxed-layout="full">
		<jsp:include page="/back-endTemplate/header.jsp" flush="true" />
		<jsp:include page="/back-end/sideBar/dishFoodMana.jsp" flush="true" />
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<%--=================================工作區================================================--%>
				
 	<div class="alert alert-secondary text-center" role="alert" ><font style="font-weight:bold;font-size:26px;">修改菜色狀態</font></div>
	<hr class="border:0;height: 1px;background-image: linear-gradient(to right, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));"/>

				<%-- 錯誤表列 --%>
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
                                            <th scope="col">菜色名稱:</th>
											<th scope="col">菜色照片:</th>
											<th scope="col">菜色介紹:</th>
											<th scope="col">菜色價格:</th>
											<th scope="col">審核:</th>
                                        </tr>
                                    </thead>
                                     <c:forEach var="dishVO" items="${list}">
											<c:if test="${dishVO.dish_status eq 'D0'}">
                                    <tr>
                                   
                                        
                                            <th scope="row">${dishVO.dish_name}</th>
                                            
                                        	
                                            <th scope="row">
                                            <c:if test="${not empty dishVO.dish_pic}">
											<img src="<%=request.getContextPath()%>/dish/dish.do?dish_ID=${dishVO.dish_ID}" width="300" height="200"></c:if> 
											<c:if test="${empty dishVO.dish_pic}"><img src="<%=request.getContextPath()%>/images/null2.jpg" width="300" height="200"></c:if>
													
											</th>
                                           
                                        
                                            <th scope="row" width="30%">${dishVO.dish_resume}</th>
                                           
                                        
                                            <th scope="row">${dishVO.dish_price}</th>
                                           
                                       
                                            <th scope="row"><FORM METHOD="post" ACTION="<%=request.getContextPath() %>/dish/dish.do">
													<input type="submit" class="btn btn-secondary" value="審核">
													<input type="hidden" name="dish_ID" value="${dishVO.dish_ID}">
													<input type="hidden" name="action" value="getOne_For_Update">
												</FORM>
												</th>
                          			      </tr>
                                           </c:if>
									</c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                


		

            </div>
        </div>
    </div>

</body>
</html>