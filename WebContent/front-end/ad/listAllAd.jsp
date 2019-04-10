<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.foodSup.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	FoodSupVO foodSupVO = (FoodSupVO) session.getAttribute("foodSupVO");
	AdService adSvc = new AdService();
	List<AdVO> list = adSvc.getAll();
	;pageContext.setAttribute("list", list);
	
	
%>


<!DOCTYPE html>
<html>
<head>
<title>所有廣告資料 </title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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

<style>
table {
	width: 500px;
	background-color: white;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 10px;
	text-align: center;
}
</style>
<style>






.single_gallery_item {
  display: inline-block;
  float: left;
  position: relative;
  z-index: 2;
  width: 33.3333334%;
  overflow: hidden;
  -webkit-transition-duration: 500ms;
  -o-transition-duration: 500ms;
  transition-duration: 500ms; }
  @media only screen and (max-width: 767px) {
    .single_gallery_item {
      width: 100%; } }
  .single_gallery_item img {
    width: 100%;
    -webkit-transition-duration: 2000ms;
    -o-transition-duration: 2000ms;
    transition-duration: 2000ms; }
  .single_gallery_item .hover-content1 {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(92, 92, 92, 0.7);
    opacity: 0;
    visibility: hidden;
    -webkit-transition-duration: 500ms;
    -o-transition-duration: 500ms;
    transition-duration: 500ms; }
    .single_gallery_item .hover-content1 .zoom-img {
      display: inline-block;
      width: 50px;
      height: 50px;
      border-radius: 50%;
      border: 2px solid #ffffff;
      font-size: 20px;
      margin-bottom: 30px;
      color: #ffffff;
      line-height: 46px;
      margin-top: -15px;
      opacity: 0;
      -webkit-transition-duration: 500ms;
      -o-transition-duration: 500ms;
      transition-duration: 500ms; }
    .single_gallery_item .hover-content1 h4 {
      margin-bottom: 0;
      color: #ffffff; }
      @media only screen and (min-width: 992px) and (max-width: 1199px) {
        .single_gallery_item .hover-content1 h4 {
          font-size: 20px; } }
      @media only screen and (min-width: 768px) and (max-width: 991px) {
        .single_gallery_item .hover-content1 h4 {
          font-size: 16px; } }
      @media only screen and (max-width: 767px) {
        .single_gallery_item .hover-content1 h4 {
          font-size: 16px; } }
  .single_gallery_item:hover img {
    -webkit-transform: scale(1.1);
    -ms-transform: scale(1.1);
    transform: scale(1.1); }
  .single_gallery_item:hover .hover-content1 {
    opacity: 1;
    visibility: visible; }
    .single_gallery_item:hover .hover-content1 .zoom-img {
      margin-top: 0;
      opacity: 1; }          

</style>

</head>
<body>
	<jsp:include page="/froTempl/header.jsp" flush="true" />
	<jsp:include page="/froTempl/headerFoodSup.jsp" flush="true" />
	<!-- ##### Breadcrumb Area Start ##### -->

    <!-- ##### Breadcrumb Area End ##### -->

	<!-- ##### Portfolio Area Start ###### -->
    <div class="pixel-portfolio-area section-padding-100-0">
    <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Section Heading -->
                    <div class="section-heading text-center wow fadeInUp" data-wow-delay="100ms">
                        <h2>所有廣告</h2>
                        <img src="<%=request.getContextPath()%>/froTempl/temp/img/core-img/logo.gif" alt="">
                    </div>
                </div>
            </div>
        </div>
    
    
    
       <div class="pixel-portfolio">
        
        <c:forEach var="adVO" items="${list}" >
			
			<c:if test="${adVO.food_sup_ID == foodSupVO.getFood_sup_ID()}">
			
			<div class="single_gallery_item visual wow fadeInUp" data-wow-delay="0.2s">
                <img src="<%=request.getContextPath()%>/ad/ad.do?ad_ID=${adVO.ad_ID}" width="800px" height="600px" alt="">
                <div class="hover-content1 text-center d-flex align-items-center justify-content-center">
                    <div class="hover-text">
                        
                        <h3 style="color:#ff8000">${adVO.ad_title}</h3>
                     		<h4 >上架時間</h4>
				<a style="color:#ff8000">${adVO.ad_start}</a><br>
							<h4>審核狀態</h4>
				<a style="color:#ff8000">${adStatusMap[adVO.ad_status]}</a>
			
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/ad/ad.do"
						style="margin-bottom: 0px;">
						<button type="submit" class="btn btn-info"
												data-wow-delay="300ms">修改內容</button><input type="hidden"
							name="ad_ID" value="${adVO.ad_ID}">
							 <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				
                    </div>
                </div>
            </div>
			
			</c:if>
		</c:forEach>
	</table>


        </div>
    </div>


    <!-- ##### Contact Area End #####-->

	<jsp:include page="/froTempl/footer.jsp" flush="true" />
<%--=================================jQuery===============================================--%>
			</div>
		</div>
	</div>

</body>
</html>