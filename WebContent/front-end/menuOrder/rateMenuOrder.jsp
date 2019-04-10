<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menuOrder.model.*"%>

<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>updateMenuOrder.jsp</title>
</head>
<body>

	<%--Error Message --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red; font-size: 20px;">Error:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="container justify-content-center">
		<div class="row">
			<div class="col-12">
				<form method="post" action="menuOrder.do" name="insertMenuOrderForm">
					<jsp:useBean id="menuOrderSvc" scope="page"
						class="com.menuOrder.model.MenuOrderService" />

					<div class="form-group">
						<label>訂單編號</label> <input type="text" readonly
							class="form-control" name="menu_od_ID"
							value="${menuOrderVO.menu_od_ID}" />
					</div>
					
					<div class="form-group">
						<label>主廚編號</label> 
						<input type="text" readonly name="chef_ID" class="form-control" value="${menuOrderVO.chef_ID}" />
					</div>

					<div class="form-group">
						<label>套餐編號</label> 
						<input type="text" readonly name="menu_ID" class="form-control" value="${menuOrderVO.menu_ID}" />
					</div>
					
					<div class="form-group">
						<label>預約日期</label> 
						<input name="menu_od_book" readonly class="form-control" type="text" value="${menuOrderVO.menu_od_book}" />${errMsgs.menu_od_book}
					</div>

					<div class="form-group">
						<label>完成日期</label> 
						<input type="text" readonly name="menu_od_end" class="form-control" value="${menuOrderVO.menu_od_end}" />${errMsgs.menu_od_end}
					</div>
					<%	
							String rate[] = new String [5];
							rate[0]="1";
							rate[1]="2";
							rate[2]="3";
							rate[3]="4";
							rate[4]="5";
							request.setAttribute("myrate", rate);
					%>
					<div class="form-group">
						<label>訂單評價</label> <select size="1" name="menu_od_rate"
							class="form-control">
							<c:forEach var="myrate" items="${myrate}">
								<option value="${myrate}"
									${(menuOrderVO.menu_od_rate==myrate)? 'selected':'' }>${myrate}
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<label>訂單留言</label>
						<textarea rows="3" cols="100" name="menu_od_msg"
							class="form-control">${menuOrderVO.menu_od_msg}</textarea>
					</div>
					<input type="hidden" name="menu_od_status" value="${menuOrderVO.menu_od_status}">
					<input type="hidden" name="action" value="getOneForRating"> 
					<input type="submit" class="btn btn-success btn-lg btn-block" value="送出">
				</form>
			</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous">
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous">
	</script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous">
	</script>
</body>
</html>