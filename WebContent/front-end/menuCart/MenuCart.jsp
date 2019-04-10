<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.menu.model.*" %>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>MenuCart.jsp</title>
</head>
<body bgcolor="#FFFFFF">

<%Vector<MenuVO> list = (Vector<MenuVO>) session.getAttribute("menuCartList");%>
<%if (list != null && (list.size() > 0)) {%>

<font size="+3">目前您購物車的內容如下：</font><p>

<table border="1">
	<tr bgcolor="#999999">
		<th width="200">套餐名稱</th>
		<th width="100">套餐價格</th>
		<th width="100">套餐數量</th>
	</tr>

	<%
	 for (int index = 0; index < list.size(); index++) {
		MenuVO order = list.get(index);
	%>
	<tr>
		<td width="200"><div align="center"><b><%=order.getMenu_ID()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getMenu_price()%></b></div></td>
		<td width="100">
			<div align="center">
          		<form name="deleteForm" action="Shopping.html" method="POST">
              		<input type="hidden" name="action" value="delete">
             		<input type="hidden" name="del" value="<%= index %>">
              		<input type="submit" value="刪除">
          		</form>
          	</div>
        </td>
     </tr>
	<%}%>
</table>
<p>
          <form name="checkoutForm" action="Shopping.html" method="POST">
              <input type="hidden" name="action" value="CHECKOUT"> 
              <input type="submit" value="付款結帳">
          </form>
<%}%>
</body>
</html>