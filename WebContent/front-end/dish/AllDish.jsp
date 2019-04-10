<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dish.model.*"%>

<%-- 此測試頁，練習採用 EL 的寫法取值 --%>

<jsp:useBean id="dishDAO"  scope="page" class="com.dish.model.DishDAO" />
<jsp:useBean id="dishSvc"  scope="page" class="com.dish.model.DishDAO" />
<%
	List<DishVO> list = dishSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<html>
<head>
<title>所有菜色資料</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h3 {
    font: 20px Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;
    font-weight:bold;
    color: black;
    display: block;
    margin: 5px;
  }
  h4 {
    font: 18px Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;
    font-weight:bold;
    color: blue;
    display: inline;
    margin: 5px;
  }
</style>

<style>
  table {
	width: 1000px;
	background-color: white;
	margin: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
</style>

<style>
.myTable{
  width: 100%;
}
.myTable *{
  text-align: center;
}
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3><font color=red>全部<font color=blue>菜色資料</font></h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/dish/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>
	<h2><font color=red>菜色狀態:D0=下架 D1=上架</font></h2>
<table>
	<tr>
		<th>菜色編號:</th>
		<th>菜色名稱:</th>
		<th>菜色照片:</th>
		<th>菜色介紹:</th>
		<th>菜色狀態</th>
		<th>菜色價格</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="dishVO" items="${list}" varStatus="s" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
		<tr>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dish/dish.do" style="margin-bottom: 0px;">
			<A href="<%=request.getContextPath()%>/dish/dish.do?dish_ID=${dishVO.dish_ID}&action=getAllDish">${dishVO.dish_ID}</a>
			<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			<input type="hidden" name="whichPage"	value="<%=whichPage%>">
			</FORM>	
		</td>        
			<td>${dishVO.dish_name}</td>
			<td><img src ="<%=request.getContextPath()%>/dish/dish.do?dish_ID=${dishVO.dish_ID}"  width="300" height="200"></td>
			<td >${dishVO.dish_resume}</td>
			<td>${dishVO.dish_status}</td>
			<td>${dishVO.dish_price}</td>
			
		</tr>
	</c:forEach>
</table> 
<%@ include file="page2.file" %>

<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
				
			<div class="modal-header">
                
                <h3 class="modal-title" id="myModalLabel">食神來了</h3>
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="/back-end/dish/listOneDish.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>      
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
        </script>
 </c:if>
</body>
</html>