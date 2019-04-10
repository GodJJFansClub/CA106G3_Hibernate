<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>

</head>
<body>
		<aside class="left-sidebar" data-sidebarbg="skin5">
            <br><br><!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    
                    <ul id="sidebarnav">
                    <span class="hide-menu">食材</span>
						<li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/food/listAllFood.jsp" aria-expanded="false">
                                <i class="mdi mdi-av-timer"></i>
                                <span class="hide-menu">列出所有食材</span>
                            </a>
                        </li>                      
                    
                    <span class="hide-menu">菜色</span>
                    	<li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/dish/addDish.jsp" aria-expanded="false">
                                <i class="mdi mdi-av-timer"></i>
                                <span class="hide-menu">新增菜色</span>
                            </a>
                        </li>
                         <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/dish/listAllDish.jsp" aria-expanded="false" aria-expanded="false">
                                <i class="mdi mdi-account-network"></i>
                                <span class="hide-menu">修改菜色狀態</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/dish/Obtainedish.jsp" aria-expanded="false" aria-expanded="false">
                                <i class="mdi mdi-account-network"></i>
                                <span class="hide-menu">菜色下架</span>
                            </a>
                        </li>
                       
                        
                        <span class="hide-menu">套餐</span>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/menu/addMenu.jsp" aria-expanded="false">
                                <i class="mdi mdi-av-timer"></i>
                                <span class="hide-menu">新增套餐</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/menu/listAllMenu.jsp" aria-expanded="false">
                                <i class="mdi mdi-av-timer"></i>
                                <span class="hide-menu">所有套餐</span>
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