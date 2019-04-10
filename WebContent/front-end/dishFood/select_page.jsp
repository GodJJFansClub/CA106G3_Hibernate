<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM DishFood: Home</title>

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
   <tr><td><h3>IBM DishFood: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM DishFood: Home</p>

<h3>食材查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllDishFood.jsp'>查詢全部文章</a> <br><br></li>

  <jsp:useBean id="dishFoodSvc" scope="page" class="com.dishFood.model.DishFoodService" />
   
  <li>
     <FORM METHOD="post" ACTION="dishFood.do" >
       <b>選擇菜色編號:</b>
       <select size="1" name="dish_ID">
         <c:forEach var="forumArtVO" items="${forumArtSvc.all}" > 
          <option value="${forumArtVO.forum_art_ID}">${forumArtVO.forum_art_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="forumArt.do" >
       <b>選擇文章:</b>
       <select size="1" name="forum_art_ID">
         <c:forEach var="dishVO" items="${forumArtSvc.all}" > 
          <option value="${forumArtVO.forum_art_ID}">${forumArtVO.forum_art_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>菜色新增</h3>

<ul>
  <li><a href='addDishFood.jsp'>Add</a> a new ForunArt.</li>
</ul>

</body>
</html>