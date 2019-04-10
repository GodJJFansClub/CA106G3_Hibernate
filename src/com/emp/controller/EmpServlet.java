package com.emp.controller;


	import java.io.*;

	import java.util.*;

	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.ServletOutputStream;
	import javax.servlet.http.*;
	import javax.servlet.annotation.MultipartConfig;

import com.emp.model.*;


	@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
	public class EmpServlet extends HttpServlet {

		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();
			List<String> errorMsgs = new LinkedList<String>();

			String s = req.getParameter("emp_ID");
			try {
				EmpService ds = new EmpService();
				EmpVO dao = (EmpVO) ds.getOneEmp(s);
				byte[] sb = dao.getEmp_pic();
				out.write(sb);
			
			}catch(NullPointerException e){
				errorMsgs.add("a");
				
			}
			
			doPost(req, res);
		}

		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");

			// 新增
			if ("insert".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {

					// 1.姓名
					String emp_name = req.getParameter("emp_name");
					String emp_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (emp_name == null || emp_name.trim().length() == 0) {
						errorMsgs.add("員工姓名: 請勿空白");
					} else if (!emp_name.trim().matches(emp_nameReg)) {
						errorMsgs.add(emp_name);
					}
					// "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間"

					// 2.密碼
					String emp_pwd = req.getParameter("emp_pwd");
					String emp_pwdReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
					if (emp_pwd == null || emp_pwd.trim().length() == 0) {
						errorMsgs.add("員工密碼: 請勿空白");
					} else if (!emp_pwd.trim().matches(emp_pwdReg)) {
						errorMsgs.add(emp_pwd);
					}

					// "員工密碼: 至少有一個數字, 至少有一個大寫或小寫英文字母 , 且長度必需在6到15之間"

					// 3.帳號
					String emp_acc = req.getParameter("emp_acc");
					String emp_accReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
					if (emp_acc == null || emp_acc.trim().length() == 0) {
						errorMsgs.add("員工帳號: 請勿空白");
					} else if (!emp_acc.trim().matches(emp_accReg)) {
						errorMsgs.add("員工帳號: 只能是英文字母開頭, 且長度必需在5到15之間");
					}

					
					// 13.圖片
//					byte[] emp_pic = null;
					Part part= req.getPart("emp_pic");
					InputStream in = part.getInputStream();
					byte[] emp_pic = new byte[in.available()];
					in.read(emp_pic);
					in.close();
					
					

					// set
					EmpVO empVO = new EmpVO();
					empVO.setEmp_acc(emp_acc);
					empVO.setEmp_name(emp_name);
					empVO.setEmp_pwd(emp_pwd);
					empVO.setEmp_pic(emp_pic);
					

					// 如果以上格式有錯
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("empVO", empVO);// 以下練習正則(規)表示式(regular-expression)
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");

						failureView.forward(req, res);
						return;
					}

					//將資料加入資料庫
					EmpService empSvc = new EmpService();

					empVO = empSvc.addEmp(emp_acc, emp_pwd, emp_name, emp_pic);
					String url = "/back-end/emp/listAllEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
					//除錯
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failureView.forward(req, res);
				}
			}
			
			// 查詢-單一
			if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
					String str = req.getParameter("emp_ID");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入員工帳號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					String emp_ID = null;
					try {
						emp_ID = new String(str);
					} catch (Exception e) {
						errorMsgs.add("員工編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					/*************************** 2.開始查詢資料 *****************************************/
					EmpService empSvc = new EmpService();
					EmpVO empVO = empSvc.getOneEmp(emp_ID);
					if (empVO == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
					req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
					String url = "/back-end/emp/listOneEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 *************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
				}
			}

			// 查詢-全部
			if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 ****************************************/
					String emp_ID = new String(req.getParameter("emp_ID"));

					/*************************** 2.開始查詢資料 ****************************************/
					EmpService empSvc = new EmpService();
					EmpVO empVO = empSvc.getOneEmp(emp_ID);

					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
					String url = "/back-end/emp/update_emp_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
					failureView.forward(req, res);
				}
			}

			// 修改
			if ("update".equals(action)) {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					
					//0.id
					String emp_ID = new String(req.getParameter("emp_ID").trim());
					// 1.姓名
					String emp_name = req.getParameter("emp_name");
					String emp_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (emp_name == null || emp_name.trim().length() == 0) {
						errorMsgs.add("員工姓名: 請勿空白");
					} else if (!emp_name.trim().matches(emp_nameReg)) {
						errorMsgs.add(emp_name);
					}
					// "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間"

					// 2.密碼
					String emp_pwd = req.getParameter("emp_pwd");
					String emp_pwdReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
					if (emp_pwd == null || emp_pwd.trim().length() == 0) {
						errorMsgs.add("員工密碼: 請勿空白");
					} else if (!emp_pwd.trim().matches(emp_pwdReg)) {
						errorMsgs.add(emp_pwd);
					}

					// "員工密碼: 至少有一個數字, 至少有一個大寫或小寫英文字母 , 且長度必需在6到15之間"

					// 3.帳號
					String emp_acc = req.getParameter("emp_acc");
					String emp_accReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
					if (emp_acc == null || emp_acc.trim().length() == 0) {
						errorMsgs.add("員工帳號: 請勿空白");
					} else if (!emp_acc.trim().matches(emp_accReg)) {
						errorMsgs.add("員工帳號: 只能是英文字母開頭, 且長度必需在5到15之間");
					}

					
					// 13.圖片
//					byte[] emp_pic = null;
					Part part= req.getPart("emp_pic");
					InputStream in = part.getInputStream();
					byte[] emp_pic = new byte[in.available()];
					in.read(emp_pic);
					in.close();

					// set
					EmpVO empVO = new EmpVO();
					empVO.setEmp_acc(emp_acc);
					empVO.setEmp_name(emp_name);
					empVO.setEmp_pwd(emp_pwd);
					empVO.setEmp_pic(emp_pic);
					
					// 如果以上格式有錯
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("empVO", empVO);// 以下練習正則(規)表示式(regular-expression)
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");

						failureView.forward(req, res);
						return;
					}

//					/***************************2.開始修改資料*****************************************/
					EmpService empSvc = new EmpService();

					empVO = empSvc.updateEmp(emp_acc, emp_pwd, emp_name,emp_pic);
					
//					empVO = empSvc.updateEmp("C0055", "dddd", emp_name, "f", "050505", "8888", "H123456789", "@54564", emp_brd, emp_reg,by , "c","ff" );
					/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
					HttpSession session = req.getSession();
					session.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
					String url = "/back-end/emp/listOneEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					/*************************** 其他可能的錯誤處理 *************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
					failureView.forward(req, res);

				}
			}

			// 刪除
			if ("delete".equals(action)) { // 來自listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 ***************************************/
					String emp_ID = new String(req.getParameter("emp_ID"));

					/*************************** 2.開始刪除資料 ***************************************/
					EmpService empSvc = new EmpService();
					empSvc.deleteEmp(emp_ID);

					/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
					String url = "/back-end/emp/listAllEmp.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
					failureView.forward(req, res);
				}
			}

		}

		public String getRandomPassword() {
		    int z;
		    StringBuilder sb = new StringBuilder();
		    int i;
		    for (i = 0; i < 6; i++) {
		      z = (int) ((Math.random() * 7) % 3);
		 
		      if (z == 1) { // 放數字
		        sb.append((int) ((Math.random() * 10) + 48));
		      } else if (z == 2) { // 放大寫英文
		        sb.append((char) (((Math.random() * 26) + 65)));
		      } else {// 放小寫英文
		        sb.append(((char) ((Math.random() * 26) + 97)));
		      }
		    }
		    return sb.toString();
		  }

}
