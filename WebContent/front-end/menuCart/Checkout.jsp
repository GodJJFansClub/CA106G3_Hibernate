<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*" %>

<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Mode II 範例程式 - Checkout.jsp</title>
</head>
<body bgcolor="#FFFFFF">
<img src="images/tomcat.gif"> <font size="+3">網路書店 - 結帳 </font>
<hr><p>

<table border="1">
	<tr bgcolor="#999999">
		<th width="200">名</th>
		<th width="100">價格</th>
		<th width="100">數量</th>
	</tr>
	
	<%
		Vector<MenuVO> buylist = (Vector<MenuVO>) session.getAttribute("menuCartList");
		String amount =  (String) request.getAttribute("amount");
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
			MenuVO order = buylist.get(i);
			String menu_name = order.getMenu_ID();
			int menu_price = order.getMenu_price();
	%>
	<tr>
		<td width="200"><div align="center"><b><%=menu_name%></b></div></td>
		<td width="100"><div align="center"><b><%=menu_price%></b></div></td>
	</tr>
	<%}%>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td><div align="center"><font color="red"><b>總金額：</b></font></div></td>
		<td></td>
		<td> <font color="red"><b>$<%=amount%></b></font> </td>
		<td></td>
	</tr>
</table>
<p><a href="EShop.jsp">是否繼續購物</a>
</body>
</html>