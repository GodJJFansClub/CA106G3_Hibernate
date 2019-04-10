package com.festOrderDetail.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.festMenu.model.FestMenuService;
import com.festMenu.model.FestMenuVO;
import com.festOrder.model.FestOrderService;
import com.festOrderDetail.model.FestOrderDetailService;
import com.festOrderDetail.model.FestOrderDetailVO;

public class FestOrderDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { 
//			System.out.println("檢查點成功");
			int i = 1;
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String fest_or_ID = req.getParameter("fest_or_ID");
//				System.out.println("檢查點a35" +(i++));
				if (fest_or_ID == null || (fest_or_ID.trim()).length() == 0) {
					errorMsgs.add("節慶主題料理訂單編號");
//					System.out.println("檢查點b" +(i++));
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
//					System.out.println("檢查點c" +(i++));
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/festOrderDetail/select_page.jsp");
					failureView.forward(req, res);
//					System.out.println("檢查點d" +(i++));
					return;
				}
				
				FestOrderDetailService festOrderDetailSvc = new FestOrderDetailService();
//				System.out.println("檢查點e" +(i++));
				FestOrderDetailVO festOrderDetailVO = festOrderDetailSvc.getOneFestOrderDetail(fest_or_ID);
				System.out.println(festOrderDetailVO.getFest_or_ID());
//				System.out.println("檢查點f" +(i++));
				if (fest_or_ID == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/festOrderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("festOrderDetailVO", festOrderDetailVO); //資料庫取出的empVO物件，存入req
				String url = "/front-end/festOrderDetail/listOneFestOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println("A");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("<%=request.getContextPath()%>/festOrderDetail/select_page.jsp");
				
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display_Back".equals(action)) { 
			System.out.println("getOne_For_Display_Back檢查點成功");
			int i = 1;
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String fest_or_ID = req.getParameter("fest_or_ID");
				System.out.println("getOne_For_Display_Back90");
				if (fest_or_ID == null || (fest_or_ID.trim()).length() == 0) {
					errorMsgs.add("節慶主題料理訂單編號");
					System.out.println("getOne_For_Display_Back93");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("getOne_For_Display_Back97");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/festOrderDetail/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("getOne_For_Display_Back101");
					return;
				}
				
				FestOrderDetailService festOrderDetailSvc = new FestOrderDetailService();
				System.out.println("getOne_For_Display_Back106");
				FestOrderDetailVO festOrderDetailVO = festOrderDetailSvc.getOneFestOrderDetail(fest_or_ID);
				System.out.println(festOrderDetailVO.getFest_or_ID());
				System.out.println("getOne_For_Display_Back109");
				if (fest_or_ID == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/festOrderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("festOrderDetailVO", festOrderDetailVO); //資料庫取出的empVO物件，存入req
				String url = "/back-end/festOrderDetail/listOneFestOrderDetail.jsp";
				System.out.println("getOne_For_Display_Back122");
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println("A");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/festOrderDetail/select_page.jsp");
				
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { //來自listAllReport.jsp的請求
            System.out.println("84行");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {	
				/***************************1.接收請求參數***************************************/
				String fest_or_ID = req.getParameter("fest_or_ID");
				
				/***************************2.開始查詢資料***************************************/
				FestOrderDetailService festOrderDetailSvc = new FestOrderDetailService();
				FestOrderDetailVO festOrderDetailVO = festOrderDetailSvc.getOneFestOrderDetail(fest_or_ID);				
				/***********3.查詢完成，準備轉交(Send the Success view)********/
				req.setAttribute("festOrderDetailVO", festOrderDetailVO);   //資料庫取得的reportVO物件，存入req  
				String url = "/back-end/festOrderDetail/update_festOrderDetail_input.jsp";		
	
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/festOrderDetail/listAllFestOrderDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
		
			List<String> errorMsgs = new LinkedList<String>();//來自update_emp_input.jsp的請求
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
System.out.println("檢查點1");		
			try {
				/**********************1.接收請求參數-輸入格式的錯務處理*********************/
				String fest_or_ID = req.getParameter("fest_or_ID").trim();
				
				String fest_m_ID = req.getParameter("fest_m_ID");
System.out.println("檢查點2");				
				Integer fest_or_rate = null;
				try {
					fest_or_rate = new Integer(req.getParameter("fest_or_rate").trim());
				} catch (NumberFormatException e) {
					fest_or_rate = 0;
					errorMsgs.add("訂單評價請填數字.");
System.out.println("檢查點3");					
				}
				System.out.println("update"+"133");
				String fest_or_msg = req.getParameter("fest_or_msg").trim();
				if (fest_or_msg == null || fest_or_msg.trim().length() == 0) {
					errorMsgs.add("訂單評價請留言");
				}
System.out.println("檢查點4");                
				Integer fest_or_qty = null;
				try {
					fest_or_qty = new Integer(req.getParameter("fest_or_qty").trim());
				} catch (NumberFormatException e) {
					fest_or_rate = 0;
					errorMsgs.add("訂單數量請填數字.");
				}
				Integer fest_or_stotal = null;
				try {
					fest_or_stotal = new Integer(req.getParameter("fest_or_stotal").trim());
				} catch (NumberFormatException e) {
					fest_or_stotal = 0;
					errorMsgs.add("訂單小計.");
				}
				FestOrderDetailVO festOrderDetailVO=new FestOrderDetailVO();
				festOrderDetailVO.setFest_or_ID(fest_or_ID);
				festOrderDetailVO.setFest_m_ID(fest_m_ID);
				festOrderDetailVO.setFest_or_rate(fest_or_rate);
				festOrderDetailVO.setFest_or_msg(fest_or_msg);
				festOrderDetailVO.setFest_or_qty(fest_or_qty);
				festOrderDetailVO.setFest_or_stotal(fest_or_stotal);
				System.out.println("update"+"162");
                //Send the use back to the form,if there were errors
                if (!errorMsgs.isEmpty()) {
					req.setAttribute("festOrderDetailVO", festOrderDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/festOrderDetail/update_festOrderDetail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}

				/***************************2.開始修改資料*****************************************/
                FestOrderDetailService festOrderDetailSvc = new FestOrderDetailService();
				festOrderDetailVO = festOrderDetailSvc.updateFestOrderDetail
						(fest_or_ID, fest_m_ID, fest_or_rate, fest_or_msg, fest_or_qty, 
								fest_or_stotal);
				System.out.println("update"+"177");
				System.out.println(festOrderDetailVO);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("festOrderDetailVO", festOrderDetailVO); // 資料庫update成功後,正確的的empVO物件,存入req

				System.out.println(festOrderDetailVO+"221");
				String url = "/back-end/festOrderDetail/listOneFestOrderDetail.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
System.out.println("檢查點9");				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/festOrderDetail/update_festOrderDetail_input.jsp");
				failureView.forward(req, res);
			}
System.out.println("檢查點10");			
		}
				
        if ("insert".equals(action)) { //來自addReport.jsp的請求 
			System.out.println("檢查點256");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				int i=1;
				/***********************1.接收請求參數-輸入格式的錯誤處理*************************/
				String fest_or_ID=req.getParameter("fest_or_ID").trim();
				if (fest_or_ID == null || fest_or_ID.trim().length() == 0) {
					errorMsgs.add("節慶主題料理訂單編號");
				}
				String fest_m_ID = req.getParameter("fest_m_ID").trim();
				if (fest_m_ID == null || fest_m_ID.trim().length() == 0) {
					errorMsgs.add("節慶料理編號請勿空白");
				}
				Integer fest_or_rate = null;
				try {
					fest_or_rate = new Integer(req.getParameter("fest_or_rate").trim());
				}catch(NumberFormatException e) {
					fest_or_rate=0;
					errorMsgs.add("請輸入訂單評價：");
				}
				String fest_or_msg = req.getParameter("fest_or_msg").trim();
				if (fest_or_msg == null || fest_or_msg.trim().length() == 0) {
					errorMsgs.add("訂單評價留言請勿空白");
				}

				Integer fest_or_qty = null;
				try {
					fest_or_qty = new Integer(req.getParameter("fest_or_qty").trim());
				}catch(NumberFormatException e) {
					fest_or_qty=0;
					errorMsgs.add("請輸入訂單數量：");
				}

				Integer fest_or_stotal = null;
				try {
					fest_or_stotal = new Integer(req.getParameter("fest_or_stotal").trim());
				}catch(NumberFormatException e) {
					fest_or_stotal=0;
					errorMsgs.add("請輸入訂單小計：");
				}

				FestOrderDetailVO festOrderDetailVO = new FestOrderDetailVO();
				festOrderDetailVO.setFest_or_ID(fest_or_ID);
				festOrderDetailVO.setFest_m_ID(fest_m_ID);
				festOrderDetailVO.setFest_or_rate(fest_or_rate);
				festOrderDetailVO.setFest_or_msg(fest_or_msg);
				festOrderDetailVO.setFest_or_qty(fest_or_qty);
				festOrderDetailVO.setFest_or_stotal(fest_or_stotal);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("festOrderDetailVO", festOrderDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/festOrderDetail/addFestOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料**************************************/
				FestOrderDetailService festOrderDetailSvc = new FestOrderDetailService();
				festOrderDetailVO = festOrderDetailSvc.addFestOrderDetail
				(fest_or_ID,fest_m_ID,fest_or_rate,fest_or_msg,fest_or_qty,fest_or_stotal);
				
				FestMenuService festMenuSvc = new FestMenuService();
				FestMenuVO festMenuVO = festMenuSvc.getOneFestMenu(fest_m_ID);
				int	festMenu_qty = festMenuVO.getFest_m_qty();
				int final_qty= festMenu_qty-fest_or_qty;   //fest_or_qty是指訂購數量、festMneu_qty：資料庫的數量、final_qty：最後的數量
				festMenuSvc.update2_FestMenu(fest_m_ID, final_qty);
				
				System.out.print(festOrderDetailVO);
				/***************************3.新增完成，準備提交(Send the Success view**********/
				String url = "/front-end/festOrderDetail/listOneFestOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //
				successView.forward(req, res);				
				
				/***************************其它可能的錯誤處理*********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/festOrderDetail/addFestOrderDetail.jsp");
				failureView.forward(req, res);
			}

        }
	
		if ("delete".equals(action)) { 
			System.out.println("  " +"檢查點4");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	        
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數***************************************/
				String fest_or_ID = req.getParameter("fest_or_ID");
				System.out.println("接收請求參數"+ fest_or_ID);
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();  //老師的
//				EmpVO empVO = empSvc.getOneEmp(empno);
//				empSvc.deleteEmp(empno);
				
				/***************************2.開始刪除資料***************************************/
				FestOrderDetailService festOrderDetailSvc = new FestOrderDetailService();
				FestOrderDetailVO festOrderDetailVO = festOrderDetailSvc.getOneFestOrderDetail(fest_or_ID);
				festOrderDetailSvc.deleteFestOrderDetail(fest_or_ID);
					
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/  老師的
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // 資料庫取出的list物件,存入request
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				FestOrderService festOrderSvc = new FestOrderService();
				if(requestURL.equals("/front-end/festOrder/listFestOrderDetail_ByFest_or_ID.jsp") || requestURL.equals("/front-end/festOrder/listAllFestOrder.jsp"))
					req.setAttribute("listFestOrderDetail_ByFest_or_ID",festOrderSvc.getFestOrderDetailByFest_or_ID(festOrderDetailVO.getFest_or_ID())); // 資料庫取出的list物件,存入request
				
				String url = "/back-end/festOrderDetail/listAllFestOrderDetail.jsp";
//				String url = "/front-end/festOrderDetail/listAllFestOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				System.out.println(" "+"檢查點7");
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/festOrderDetail/listAllFestOrderDetail.jsp");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/festOrderDetail/listAllFestOrderDetail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
