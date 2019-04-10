<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>

</head>
<body>
		<aside class="left-sidebar" data-sidebarbg="skin5">
           <br><br> <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/foodSup/listAllFoodSup.jsp" aria-expanded="false">
                                <i class="mdi mdi-account-network"></i>
                                <span class="hide-menu">食材供應商資料</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/chef/listAllChef.jsp" aria-expanded="false">
                                <i class="mdi mdi-account-network"></i>
                                <span class="hide-menu">主廚資料</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/chef/searchChef.jsp" aria-expanded="false">
                                <i class="mdi mdi-account-network"></i>
                                <span class="hide-menu">查詢主廚</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<%=request.getContextPath()%>/back-end/chefDish/listAllChefDishNotCheck.jsp" aria-expanded="false">
                                <i class="mdi mdi-account-network"></i>
                                <span class="hide-menu">主廚新菜色申請</span>
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