<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM ForumMsg: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>IBM MenuDish: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for IBM MenuDish: Home</p>

	<h3>菜色查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllMenuDish.jsp'>List</a> all Dish. <br>
		<br></li>

		<jsp:useBean id="menuDishSvc" scope="page"
			class="com.menuDish.model.MenuDishService" />

		<li>
			<FORM METHOD="post" ACTION="menuDish.do">
				<b>選擇菜色:</b> <select size="1" name="forum_msg_ID">
					<c:forEach var="forumMsgVO" items="${forumMsgSvc.all}">
						<option value="${forumMsgVO.forum_msg_ID}">${forumMsgVO.forum_msg_ID}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="forumMsg.do">
				<b>選擇員工姓名:</b> <select size="1" name="forum_msg_ID">
					<c:forEach var="dishVO" items="${forumMsgSvc.all}">
						<option value="${forumMsgVO.forum_msg_ID}">${forumMsgVO.forum_art_name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>

</body>
</html>