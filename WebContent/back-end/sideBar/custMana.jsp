<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>

</head>
<body>
	<aside class="left-sidebar" data-sidebarbg="skin5">
	<br><br>
	<!-- Sidebar scroll-->
	<div class="scroll-sidebar">
		<!-- Sidebar navigation-->
		<nav class="sidebar-nav">
			<ul id="sidebarnav">
				<li class="sidebar-item"><a
					class="sidebar-link waves-effect waves-dark sidebar-link"
					href="<%=request.getContextPath()%>/back-end/cust/listAllCust.jsp" aria-expanded="false"> <i
						class="mdi mdi-av-timer"></i> <span class="hide-menu">所有顧客資料</span>
				</a></li>
				<li class="sidebar-item"><a
					class="sidebar-link waves-effect waves-dark sidebar-link"
					href="<%=request.getContextPath()%>/back-end/cust/select_page.jsp" aria-expanded="false"> <i
						class="mdi mdi-av-timer"></i> <span class="hide-menu">查詢顧客資料</span>
				</a></li>
				
				<li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/foodOrder/listAllFoodOrder.jsp" aria-expanded="false">
                                <i class="mdi mdi-av-timer"></i>
                                <span class="hide-menu">食材商城訂單</span>
                            </a>
                        </li>
                 <li class="sidebar-item">
                     <a class="sidebar-link waves-effect waves-dark sidebar-link"
                            	href="<%=request.getContextPath()%>/back-end/menuOrder/listAllMenuOrder.jsp" aria-expanded="false">
                           <i class="mdi mdi-account-network"></i>
                           <span class="hide-menu">套餐訂單</span>
                     </a>
                 </li>

                 <li class="sidebar-item">
                     <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/festOrder/listAllFestOrder.jsp" aria-expanded="false">
                        <i class="mdi mdi-arrange-bring-forward"></i>
                        <span class="hide-menu">節慶主題料理訂單</span>
                     </a>
                 </li>
			</ul>
		</nav>
		<!-- End Sidebar navigation -->
	</div>
	<!-- End Sidebar scroll-->
	</aside>

</body>
</html>