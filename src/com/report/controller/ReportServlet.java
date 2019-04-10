package com.report.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.report.model.ReportService;
import com.report.model.ReportVO;

@MultipartConfig
public class ReportServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		if ("getOne_For_Display".equals(action)) { 
			System.out.println("30");
			int i = 1;
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String report_ID = req.getParameter("report_ID");
				System.out.println("檢查點a35" +(i++));
				if (report_ID == null || (report_ID.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
					System.out.println("檢查點b" +(i++));
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("檢查點c" +(i++));
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_page.jsp");
					failureView.forward(req, res);
					System.out.println("檢查點d" +(i++));
					return;
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				ReportService reportSvc = new ReportService();
				System.out.println("檢查點e" +(i++));
				ReportVO reportVO = reportSvc.getOneReport(report_ID);
				System.out.println(reportVO.getReport_ID());
//				System.out.println("檢查點f" +(i++));
				if (report_ID == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("reportVO", reportVO); //資料庫取出的empVO物件，存入req
				String url = "/back-end/report/listOneReport.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
//				System.out.println("A");

			} catch (Exception e) {
				System.out.println("A");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/select_page.jsp");
				failureView.forward(req, res);
			}
		}


		if ("getOne_For_Update".equals(action)) { //來自listAllReport.jsp的請求
            System.out.println(" "+ "檢查點2");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	//		System.out.println("errorMsgs" + errorMsgs);

//			try {
				System.out.println("  " +"檢查點3");
				/***************************1.接收請求參數***************************************/
				String report_ID = req.getParameter("report_ID");

				/***************************2.開始查詢資料***************************************/
				ReportService reportSvc = new ReportService();
				ReportVO reportVO = reportSvc.getOneReport(report_ID);

				/***************************3.查詢完成，準備轉交(Send the Success view)********/
				req.setAttribute("reportVO", reportVO);   //資料庫取得的reportVO物件，存入req   
				System.out.println(report_ID);
				String url = "/front-end/report/update_report_input.jsp";
				System.out.println("  " +"檢查點4");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("  " +"檢查點5");

//			} catch (Exception e) {
//				System.out.println("  " +"檢查點6");
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/report/listAllReport.jsp");
//				failureView.forward(req, res);
//			}
		}


		if ("update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();//來自update_emp_input.jsp的請求
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/**********************1.接收請求參數-輸入格式的錯務處理*********************/
				String report_ID = req.getParameter("report_ID").trim();

				String report_title = req.getParameter("report_title");
				String report_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (report_title == null || report_title.trim().length() == 0) {
//					errorMsgs.add("檢舉編號: 請勿空白");
//				} else if(!report_title.trim().matches(report_titleReg)) { 
//					errorMsgs.add("檢舉編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
				String report_sort = req.getParameter("report_sort").trim();
				if (report_sort == null || report_sort.trim().length() == 0) {
					errorMsgs.add("請填入檢舉分類");
				}	

//				System.out.println("  " +"檢查點9");
			    Timestamp report_start = null;
			    System.out.println(req.getParameter("report_start").trim());
					try {
						report_start =java.sql.Timestamp.valueOf(req.getParameter("report_start").trim());
				} catch (IllegalArgumentException e) {
						report_start=new java.sql.Timestamp(System.currentTimeMillis());
						errorMsgs.add("請輸入檢舉日期!");
				}
//				System.out.println("  " +"檢查點11");

				String report_status = req.getParameter("report_status").trim();
				if (report_sort == null || report_sort.trim().length() == 0) {
					errorMsgs.add("請填入檢舉狀態");
				}

				String report_con = req.getParameter("report_con").trim();
				if (report_sort == null || report_sort.trim().length() == 0) {
					errorMsgs.add("請填入檢舉內容");
				}

				String cust_ID= req.getParameter("cust_ID").trim();
				if (cust_ID == null || cust_ID.trim().length() == 0) {
					errorMsgs.add("請填入會員編號");
				}

				String forum_art_ID = null;
				try {
					forum_art_ID = req.getParameter("forum_art_ID").trim();
				} catch (NumberFormatException e) {
					forum_art_ID = null;
					errorMsgs.add("文章編號請填數字.");
				}
//				System.out.println("  " +"檢查點12");

				ReportVO reportVO=new ReportVO();
				reportVO.setReport_ID(report_ID);
				reportVO.setReport_title(report_title);
                reportVO.setReport_sort(report_sort);
                reportVO.setReport_start(report_start);
                reportVO.setReport_status(report_status);
                reportVO.setReport_con(report_con);
                reportVO.setCust_ID(cust_ID);
                reportVO.setForum_art_ID(forum_art_ID);
//                System.out.println("  " +"檢查點13");

                //Send the use back to the form,if there were errors
                if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportVO", reportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/update_report_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}

//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				/***************************2.開始修改資料*****************************************/
				ReportService reportSvc = new ReportService();
				reportVO = reportSvc.updateReport(report_ID,report_title, 
				report_sort, report_start, report_status, report_con, cust_ID,forum_art_ID);
//				System.out.println("  " +"檢查點15");
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("reportVO", reportVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/report/listAllReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
//				System.out.println("  " +"檢查點16");

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/update_report_input.jsp");
				failureView.forward(req, res);
			}

		}

        if ("insert".equals(action)) { //來自addReport.jsp的請求 
            System.out.println("237");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數-輸入格式的錯誤處理*************************/
				String report_title = req.getParameter("report_title").trim();
				if (report_title == null || report_title.trim().length() == 0) {
					errorMsgs.add("檢舉標題請勿空白");
				}

				String report_sort = req.getParameter("report_sort").trim();
				if (report_sort == null || report_sort.trim().length() == 0) {
					errorMsgs.add("檢舉分類請勿空白");
				}

				Timestamp report_start = null;
				try {
					report_start =java.sql.Timestamp.valueOf(req.getParameter("report_start").trim());
				} catch (IllegalArgumentException e) {
					report_start=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String report_status = req.getParameter("report_status").trim();
				if (report_status == null || report_status.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉狀態");
				}

				String report_con = req.getParameter("report_con").trim();
				if (report_con == null || report_con.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉內容");
				}

				String cust_ID = req.getParameter("cust_ID").trim();
				if (cust_ID == null || cust_ID.trim().length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				String forum_art_ID = null;
				try {
					forum_art_ID = req.getParameter("forum_art_ID").trim();
				} catch (NumberFormatException e) {
					forum_art_ID =null;
					errorMsgs.add("請輸入文章編號");
				}

				ReportVO reportVO = new ReportVO();
				reportVO.setReport_title(report_title);
				reportVO.setReport_sort(report_sort);
				reportVO.setReport_start(report_start);
				reportVO.setReport_status(report_status);
				reportVO.setReport_con(report_con);
				reportVO.setCust_ID(cust_ID);
				reportVO.setForum_art_ID(forum_art_ID);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportVO", reportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/addReport.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.開始新增資料**************************************/
				ReportService reportSvc = new ReportService();
				reportVO = reportSvc.addReport(report_title, report_sort, report_start, report_status, report_con, cust_ID, forum_art_ID);
//				reportVO = reportSvc.addReport(report_title,report_sort,report_start,
//						report_status,report_con,cust_ID,forum_art_ID);

				/***************************3.新增完成，準備提交(Send the Success view**********/
				String url = "/back-end/report/listAllReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				

				/***************************其它可能的錯誤處理*********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/addReport.jsp");
				failureView.forward(req, res);
			}
		}


		if ("delete".equals(action)) { 
	//		System.out.println("  " +"檢查點4");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數***************************************/
				String report_ID = req.getParameter("report_ID");

				ReportService reportSvc = new ReportService();
				reportSvc.deleteReport(report_ID);

				String url = "/back-end/report/listAllReport.jsp";
		//		System.out.println("  " +"檢查點5");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		//		System.out.println("  " +"檢查點6");

			} catch (Exception e) {
				System.out.println(" "+"檢查點7");
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/listAllReport.jsp");
				failureView.forward(req, res);
			}
		}
	}
}