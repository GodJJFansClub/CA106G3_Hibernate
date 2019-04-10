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
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link"
                            href="<%=request.getContextPath()%>/back-end/foodMall/listAllFoodMall.jsp" aria-expanded="false">
                                <i class="mdi mdi-av-timer"></i>
                                <span class="hide-menu">管理食材商城商品</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" 
                            href="<%=request.getContextPath()%>/back-end/festMenu/listAllFestMenu.jsp" aria-expanded="false">
                                <i class="mdi mdi-account-network"></i>
                                <span class="hide-menu">管理節慶主題料理</span>
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