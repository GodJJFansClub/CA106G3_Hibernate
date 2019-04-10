<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>

</head>
<body>
		
 		<aside class="left-sidebar" data-sidebarbg="skin5">
            <!-- Sidebar scroll-->
            <br><br><!-- 挑出節 -->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="<%=request.getContextPath()%>/back-end/ad/listAllAd.jsp" aria-expanded="false"> <i
								class="mdi mdi-av-timer"></i>
								<span class="hide-menu">廣告資料管理</span>
						</a></li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="<%=request.getContextPath()%>/back-end/ad/select_page.jsp" aria-expanded="false"> <i
								class="mdi mdi-av-timer"></i>
								<span class="hide-menu">廣告查詢</span>
						</a></li>
                        
                    </ul>
                </nav>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll-->
        </aside>
    
</body>
</html>