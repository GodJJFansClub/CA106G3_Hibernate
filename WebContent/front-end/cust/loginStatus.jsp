
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="com.cust.model.*"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 
</head>

<body>
 <script src="<%=request.getContextPath()%>/back-endTemplate/assets/libs/jquery/dist/jquery.min.js"></script>
 	
	<FORM METHOD="post" id="sub" ACTION="<%=request.getContextPath()%>/cust/cust.do"
		 name="form1" >
		
		<input type="hidden" name="action" value="updateCust_status">
		<input type="hidden" name="cust_ID" value="${custVO.cust_ID}">
		
		
	</FORM>

	
	<script >
		$(document).ready(function() {
			
			$("#sub").submit();
		});
	</script>

</body>
</html>