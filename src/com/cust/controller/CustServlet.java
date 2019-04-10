package com.cust.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;

import org.json.JSONObject;

import com.cust.model.*;

import com.foodSup.model.FoodSupVO;
import com.testuse.SendEmail;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CustServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		List<String> errorMsgs = new LinkedList<String>();

		String s = req.getParameter("cust_ID");
		try {
			CustService ds = new CustService();
			CustVO dao = (CustVO) ds.getOneCust(s);
			byte[] sb = dao.getCust_pic();
			out.write(sb);

		} catch (NullPointerException e) {
			errorMsgs.add("a");

		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 新增顧客
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				// 1.姓名
				String cust_name = req.getParameter("cust_name");
				String cust_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cust_name == null || cust_name.trim().length() == 0) {
					errorMsgs.add("顧客姓名: 請勿空白");
				} else if (!cust_name.trim().matches(cust_nameReg)) {
					errorMsgs.add(cust_name);
				}
				// "顧客姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間"

				// 2.密碼
				String cust_pwd = req.getParameter("cust_pwd");
				String cust_pwdReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (cust_pwd == null || cust_pwd.trim().length() == 0) {
					errorMsgs.add("顧客密碼: 請勿空白");
				} else if (!cust_pwd.trim().matches(cust_pwdReg)) {
					errorMsgs.add(cust_pwd);
				}

				// "顧客密碼: 至少有一個數字, 至少有一個大寫或小寫英文字母 , 且長度必需在6到15之間"

				// 3.帳號
				String cust_acc = req.getParameter("cust_acc");
				String cust_accReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (cust_acc == null || cust_acc.trim().length() == 0) {
					errorMsgs.add("顧客帳號: 請勿空白");
				} else if (!cust_acc.trim().matches(cust_accReg)) {
					errorMsgs.add("顧客帳號: 只能是英文字母開頭, 且長度必需在5到15之間");
				}

				// 4.性別
				String cust_sex = req.getParameter("cust_sex");
				if (cust_sex == null || cust_sex.length() == 0) {
					errorMsgs.add("性別請勿空白");
				}

				// 5.電話
				String cust_tel = req.getParameter("cust_tel").trim();
				if (cust_tel == null || cust_tel.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				// 6.地址
				
				String cityName = req.getParameter("cityName");
				if("-1".equals(cityName)) {
					errorMsgs.add("請選擇城市");
				}
				String areaName = req.getParameter("areaName");
				if("-1".equals(areaName)) {
					errorMsgs.add("請選擇區域");
				}
				String roadName = req.getParameter("roadName");
				if("-1".equals(roadName)) {
					errorMsgs.add("請選擇路");
				}
				String partAddr = req.getParameter("partAddr");
				if(null != partAddr && partAddr.trim().length() == 0) {
					errorMsgs.add("請輸入全部地址");
				}
				//地址總和
				String cust_addr = req.getParameter("zipCode") + cityName + areaName + roadName + partAddr;
				
				// 7.身分證字號
				String cust_pid = req.getParameter("cust_pid").trim();
				if (cust_pid == null || cust_pid.trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}

				// 8.e-mail
				String cust_mail = req.getParameter("cust_mail").trim();
				if (cust_mail == null || cust_mail.trim().length() == 0) {
					errorMsgs.add("e-mail請勿空白");
				}

				// 9.生日
				java.sql.Date cust_brd = null;
				try {
					cust_brd = java.sql.Date.valueOf(req.getParameter("cust_brd").trim());
				} catch (IllegalArgumentException e) {
					cust_brd = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				// 10.註冊日
				// 註冊日期
				SimpleDateFormat setDateFormat = new SimpleDateFormat("yyyy-MM-dd");

				String xx = setDateFormat.format(Calendar.getInstance().getTime());
				java.sql.Date cust_reg = java.sql.Date.valueOf(xx);

				// 11.狀態
				String cust_status = "a1";

				// 12.暱稱
				String cust_niname = req.getParameter("cust_niname").trim();
				if (cust_niname == null || cust_niname.trim().length() == 0) {
					errorMsgs.add("暱稱請勿空白");
				}

				// 13.圖片
//				byte[] cust_pic = null;
				Part part = req.getPart("cust_pic");
				InputStream in = part.getInputStream();
				byte[] cust_pic = new byte[in.available()];
				in.read(cust_pic);
				in.close();

				
				// set
				CustVO custVO = new CustVO();
				custVO.setCust_acc(cust_acc);
				custVO.setCust_name(cust_name);
				custVO.setCust_pwd(cust_pwd);
				custVO.setCust_sex(cust_sex);
				custVO.setCust_tel(cust_tel);
				custVO.setCust_addr(cust_addr);
				custVO.setCust_pid(cust_pid);
				custVO.setCust_mail(cust_mail);
				custVO.setCust_brd(cust_brd);
				custVO.setCust_reg(cust_reg);
				custVO.setCust_pic(cust_pic);
				custVO.setCust_status(cust_status);
				custVO.setCust_niname(cust_niname);

				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("custVO", custVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/login/addCust.jsp");

					failureView.forward(req, res);
					return;
				}
				

				// 將資料加入資料庫
				CustService custSvc = new CustService();

				custVO = custSvc.addCust(cust_acc, cust_pwd, cust_name, cust_sex, cust_tel, cust_addr, cust_pid,
						cust_mail, cust_brd, cust_reg, cust_pic, cust_status, cust_niname);
				//add成功導向跳轉頁面
				String url = "/front-end/login/addSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				// 寄Email
				String to = cust_mail;

				String subject = "會員驗證";

				String ch_name = cust_name;
				String passRandom =  req.getScheme()+"://"+ req.getServerName()+":" + req.getServerPort() + req.getContextPath()+"/front-end/cust/loginStatus.jsp";
				String messageText = "Hello! " + ch_name + " 請點此連結登入: " + passRandom + "\n" + " (已經啟用)";

				
				try {
					SendEmail mailService = new SendEmail();
					mailService.sendMail(to, subject, messageText);
				} catch (IllegalArgumentException e) {
				
					errorMsgs.add("請輸入E-mail!");
				}catch (Exception e) {
				
					errorMsgs.add("請輸入E-mail!");
					}

				// 除錯
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/login/addCust.jsp");
				failureView.forward(req, res);
			}
			

		}
		// 新增食材供應商
		if ("insert2".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				// 1.姓名
				String cust_name = req.getParameter("cust_name");
				String cust_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cust_name == null || cust_name.trim().length() == 0) {
					errorMsgs.add("顧客姓名: 請勿空白");
				} else if (!cust_name.trim().matches(cust_nameReg)) {
					errorMsgs.add(cust_name);
				}
				// "顧客姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間"

				// 2.密碼
				String cust_pwd = req.getParameter("cust_pwd");
				String cust_pwdReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (cust_pwd == null || cust_pwd.trim().length() == 0) {
					errorMsgs.add("顧客密碼: 請勿空白");
				} else if (!cust_pwd.trim().matches(cust_pwdReg)) {
					errorMsgs.add(cust_pwd);
				}

				// "顧客密碼: 至少有一個數字, 至少有一個大寫或小寫英文字母 , 且長度必需在6到15之間"

				// 3.帳號
				String cust_acc = req.getParameter("cust_acc");
				String cust_accReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (cust_acc == null || cust_acc.trim().length() == 0) {
					errorMsgs.add("顧客帳號: 請勿空白");
				} else if (!cust_acc.trim().matches(cust_accReg)) {
					errorMsgs.add("顧客帳號: 只能是英文字母開頭, 且長度必需在5到15之間");
				}

				// 4.性別
				String cust_sex = req.getParameter("cust_sex");
				if (cust_sex == null || cust_sex.length() == 0) {
					errorMsgs.add("性別請勿空白");
				}

				// 5.電話
				String cust_tel = req.getParameter("cust_tel").trim();
				String cust_telReg = "^[(0-9)]{10,11}$";
				if (cust_tel == null || cust_tel.trim().length() == 0) {
					errorMsgs.add("電話號碼請勿空白");
				} else if (!cust_tel.trim().matches(cust_telReg)) {
					errorMsgs.add("電話號碼: 只能是數字");
				}

				String cityName = req.getParameter("cityName");
				if("-1".equals(cityName)) {
					errorMsgs.add("請選擇城市");
				}
				String areaName = req.getParameter("areaName");
				if("-1".equals(areaName)) {
					errorMsgs.add("請選擇區域");
				}
				String roadName = req.getParameter("roadName");
				if("-1".equals(roadName)) {
					errorMsgs.add("請選擇路");
				}
				String partAddr = req.getParameter("partAddr");
				if(null != partAddr && partAddr.trim().length() == 0) {
					errorMsgs.add("請輸入全部地址");
				}
				//地址總和
				String cust_addr = req.getParameter("zipCode") + cityName + areaName + roadName + partAddr;
				

				// 7.身分證字號
				String cust_pid = req.getParameter("cust_pid").trim();
				if (cust_pid == null || cust_pid.trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}

				// 8.e-mail
				String cust_mail = req.getParameter("cust_mail").trim();
				if (cust_mail == null || cust_mail.trim().length() == 0) {
					errorMsgs.add("e-mail請勿空白");
				}

				// 9.生日
				java.sql.Date cust_brd = null;
				try {
					cust_brd = java.sql.Date.valueOf(req.getParameter("cust_brd").trim());
				} catch (IllegalArgumentException e) {
					cust_brd = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				// 10.註冊日
				// 註冊日期
				SimpleDateFormat setDateFormat = new SimpleDateFormat("yyyy-MM-dd");

				String xx = setDateFormat.format(Calendar.getInstance().getTime());
				java.sql.Date cust_reg = java.sql.Date.valueOf(xx);

				// 11.狀態
				String cust_status = "a1";

				// 12.暱稱
				String cust_niname = req.getParameter("cust_niname").trim();
				if (cust_niname == null || cust_niname.trim().length() == 0) {
					errorMsgs.add("暱稱請勿空白");
				}

				// 13.圖片
//				byte[] cust_pic = null;
				Part part = req.getPart("cust_pic");
				InputStream in = part.getInputStream();
				byte[] cust_pic = new byte[in.available()];
				in.read(cust_pic);
				in.close();

				// 14.食材供應商名稱
				String food_sup_name = req.getParameter("cust_niname");
				String food_sup_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (food_sup_name == null || food_sup_name.trim().length() == 0) {
					errorMsgs.add("食材供應商名稱: 請勿空白");
				} else if (!food_sup_name.trim().matches(food_sup_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("食材供應商名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}

				// 15.食材供應商電話
				String food_sup_tel = req.getParameter("cust_tel").trim();
				String food_sup_telReg = "^[(0-9)]{10,11}$";
				if (food_sup_tel == null || food_sup_tel.trim().length() == 0) {
					errorMsgs.add("電話號碼請勿空白");
				} else if (!food_sup_tel.trim().matches(food_sup_telReg)) {
					errorMsgs.add("電話號碼: 只能是數字");
				}

				// 16.食材供應商狀態
				String food_sup_status = "s0";

				// 17.食材供應商簡歷
				String food_sup_resume = req.getParameter("food_sup_resume");
				
				
				// 寄Email
				String to = cust_mail;

				String subject = "會員驗證";

				String ch_name = cust_name;
				String passRandom = req.getScheme()+"://"+ req.getServerName()+":" + req.getServerPort() + req.getContextPath()+"/front-end/cust/loginStatus.jsp";
				String messageText = "Hello! " + ch_name + " 請點此連結登入: " + passRandom + "\n" + " (已經啟用)";

				SendEmail mailService = new SendEmail();
				mailService.sendMail(to, subject, messageText);

				// set
				CustVO custVO = new CustVO();
				custVO.setCust_acc(cust_acc);
				custVO.setCust_name(cust_name);
				custVO.setCust_pwd(cust_pwd);
				custVO.setCust_sex(cust_sex);
				custVO.setCust_tel(cust_tel);
				custVO.setCust_addr(cust_addr);
				custVO.setCust_pid(cust_pid);
				custVO.setCust_mail(cust_mail);
				custVO.setCust_brd(cust_brd);
				custVO.setCust_reg(cust_reg);
				custVO.setCust_pic(cust_pic);
				custVO.setCust_status(cust_status);
				custVO.setCust_niname(cust_niname);

				List<FoodSupVO> testList = new ArrayList<FoodSupVO>();
				FoodSupVO foodSupVO = new FoodSupVO();

				foodSupVO.setFood_sup_name(food_sup_name);
				foodSupVO.setFood_sup_tel(food_sup_tel);
				foodSupVO.setFood_sup_status(food_sup_status);
				foodSupVO.setFood_sup_resume(food_sup_resume);

				testList.add(foodSupVO);
				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("custVO", custVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/login/addFoodSup.jsp");

					failureView.forward(req, res);
					return;
				}

				// 將資料加入資料庫
				CustService custSvc = new CustService();

				custVO = custSvc.addFoodSup(cust_acc, cust_pwd, cust_name, cust_sex, cust_tel, cust_addr, cust_pid,
						cust_mail, cust_brd, cust_reg, cust_pic, cust_status, cust_niname, food_sup_name, food_sup_tel,
						food_sup_status, food_sup_resume);
				//加入成功，導向跳轉頁
				String url = "/front-end/login/addSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				// 除錯
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/login/addFoodSup.jsp");
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
				String str = req.getParameter("cust_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入顧客編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String cust_ID = null;
				try {
					cust_ID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("顧客編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CustService custSvc = new CustService();
				CustVO custVO = custSvc.getOneCust(cust_ID);
				if (custVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("custVO", custVO); // 資料庫取出的custVO物件,存入req
				String url = "/front-end/cust/listOneCust.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCust.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		// 查詢-單一
				if ("getOne_For_DisplayBack".equals(action)) { // 來自select_page.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						String str = req.getParameter("cust_ID");
						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入顧客編號");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/cust/select_page.jsp");
							failureView.forward(req, res);
							return;// 程式中斷
						}

						String cust_ID = null;
						try {
							cust_ID = new String(str);
						} catch (Exception e) {
							errorMsgs.add("顧客編號格式不正確");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/cust/select_page.jsp");
							failureView.forward(req, res);
							return;// 程式中斷
						}

						/*************************** 2.開始查詢資料 *****************************************/
						CustService custSvc = new CustService();
						CustVO custVO = custSvc.getOneCust(cust_ID);
						if (custVO == null) {
							errorMsgs.add("查無資料");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/cust/select_page.jsp");
							failureView.forward(req, res);
							return;// 程式中斷
						}

						/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
						req.setAttribute("custVO", custVO); // 資料庫取出的custVO物件,存入req
						String url = "/back-end/cust/listOneCust.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCust.jsp
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 *************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/cust/select_page.jsp");
						failureView.forward(req, res);
					}
				}

		// 修改單一
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String cust_ID = new String(req.getParameter("cust_ID"));

				/*************************** 2.開始查詢資料 ****************************************/
				CustService custSvc = new CustService();
				CustVO custVO = custSvc.getOneCust(cust_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("custVO", custVO); // 資料庫取出的custVO物件,存入req
				String url = "/front-end/cust/update_cust_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_cust_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/listAllCust.jsp");
				failureView.forward(req, res);
			}
		}
		// 修改單一
				if ("getOne_For_UpdateBack".equals(action)) { // 來自listAllEmp.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.接收請求參數 ****************************************/
						String cust_ID = new String(req.getParameter("cust_ID"));

						/*************************** 2.開始查詢資料 ****************************************/
						CustService custSvc = new CustService();
						CustVO custVO = custSvc.getOneCust(cust_ID);

						/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("custVO", custVO); // 資料庫取出的custVO物件,存入req
						String url = "/back-end/cust/update_cust_input.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_cust_input.jsp
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/cust/listAllCust.jsp");
						failureView.forward(req, res);
					}
				}

		// 修改
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				// 0.id
				String cust_ID = new String(req.getParameter("cust_ID").trim());
				// 1.姓名
				String cust_name = req.getParameter("cust_name");
				String cust_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cust_name == null || cust_name.trim().length() == 0) {
					errorMsgs.add("顧客姓名: 請勿空白");
				} else if (!cust_name.trim().matches(cust_nameReg)) {
					errorMsgs.add(cust_name);
				}
				// "顧客姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間"

				// 2.密碼
				String cust_pwd = req.getParameter("cust_pwd");
				String cust_pwdReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (cust_pwd == null || cust_pwd.trim().length() == 0) {
					errorMsgs.add("顧客密碼: 請勿空白");
				} else if (!cust_pwd.trim().matches(cust_pwdReg)) {
					errorMsgs.add(cust_pwd);
				}

				// "顧客密碼: 至少有一個數字, 至少有一個大寫或小寫英文字母 , 且長度必需在6到15之間"

				// 3.帳號
				String cust_acc = req.getParameter("cust_acc");
				String cust_accReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (cust_acc == null || cust_acc.trim().length() == 0) {
					errorMsgs.add("顧客帳號: 請勿空白");
				} else if (!cust_acc.trim().matches(cust_accReg)) {
					errorMsgs.add("顧客帳號: 只能是英文字母開頭, 且長度必需在5到15之間");
				}

				// 4.性別
				String cust_sex = req.getParameter("cust_sex");
				if (cust_sex == null || cust_sex.length() == 0) {
					errorMsgs.add("性別請勿空白");
				}

				// 5.電話
				String cust_tel = req.getParameter("cust_tel").trim();
				if (cust_tel == null || cust_tel.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				// 6.地址
				String cust_addr = req.getParameter("cust_addr").trim();
				if (cust_addr == null || cust_addr.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				// 7.身分證字號
				String cust_pid = new String(req.getParameter("cust_pid".trim()));
				if (cust_pid == null || cust_pid.trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}

				// 8.e-mail
				String cust_mail = new String(req.getParameter("cust_mail".trim()));
				if (cust_mail == null || cust_mail.trim().length() == 0) {
					errorMsgs.add("e-mail請勿空白");
				}

				// 9.生日
				java.sql.Date cust_brd = null;
				try {
					cust_brd = java.sql.Date.valueOf(req.getParameter("cust_brd").trim());
				} catch (IllegalArgumentException e) {
					cust_brd = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				// 10.註冊日
				// 註冊日期
				SimpleDateFormat setDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				/*
				 * 時：分：秒 HH:mm:ss HH : 23小時制 (0-23) kk : 24小時制 (1-24) hh : 12小時制 (1-12) KK :
				 * 11小時制 (0-11)
				 */
				String xx = setDateFormat.format(Calendar.getInstance().getTime());
				java.sql.Date cust_reg = java.sql.Date.valueOf(xx);

				// 11.狀態
				String cust_status = new String(req.getParameter("cust_status".trim()));
				if (cust_status == null || cust_status.trim().length() == 0) {
					errorMsgs.add("狀態請勿空白");
				}
				// 12.暱稱
				String cust_niname = new String(req.getParameter("cust_niname".trim()));
				if (cust_niname == null || cust_niname.trim().length() == 0) {
					errorMsgs.add("暱稱請勿空白");
				}

				// 13.圖片
				Part part = req.getPart("cust_pic");
				InputStream in = part.getInputStream();
				byte[] cust_pic = new byte[in.available()];
				in.read(cust_pic);
				in.close();

				// set
				CustVO custVO = new CustVO();
				custVO.setCust_acc(cust_acc);
				custVO.setCust_name(cust_name);
				custVO.setCust_pwd(cust_pwd);
				custVO.setCust_sex(cust_sex);
				custVO.setCust_tel(cust_tel);
				custVO.setCust_addr(cust_addr);
				custVO.setCust_pid(cust_pid);
				custVO.setCust_mail(cust_mail);
				custVO.setCust_brd(cust_brd);
				custVO.setCust_reg(cust_reg);
				custVO.setCust_pic(cust_pic);
				custVO.setCust_status(cust_status);
				custVO.setCust_niname(cust_niname);

				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("custVO", custVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/cust/update_cust_input.jsp");

					failureView.forward(req, res);
					return;
				}

//				/***************************2.開始修改資料*****************************************/
				CustService custSvc = new CustService();

				custVO = custSvc.updateCust(cust_ID, cust_acc, cust_pwd, cust_name, cust_sex, cust_tel, cust_addr,
						cust_pid, cust_mail, cust_brd, cust_reg, cust_pic, cust_status, cust_niname);
//				custVO = custSvc.updateCust("C0055", "dddd", cust_name, "f", "050505", "8888", "H123456789", "@54564", cust_brd, cust_reg,by , "c","ff" );
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				HttpSession session = req.getSession();
				session.setAttribute("custVO", custVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/cust/listOneCust.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/update_cust_input.jsp");
				failureView.forward(req, res);

			}
		}

		// 修改
		if ("updateback".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

		//	try {

				// 0.id
				String cust_ID = new String(req.getParameter("cust_ID").trim());
				// 1.姓名
				String cust_name = req.getParameter("cust_name");
				String cust_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cust_name == null || cust_name.trim().length() == 0) {
					errorMsgs.add("顧客姓名: 請勿空白");
				} else if (!cust_name.trim().matches(cust_nameReg)) {
					errorMsgs.add(cust_name);
				}
				// "顧客姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間"

				// 2.密碼
				String cust_pwd = req.getParameter("cust_pwd");
				String cust_pwdReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (cust_pwd == null || cust_pwd.trim().length() == 0) {
					errorMsgs.add("顧客密碼: 請勿空白");
				} else if (!cust_pwd.trim().matches(cust_pwdReg)) {
					errorMsgs.add(cust_pwd);
				}

				// "顧客密碼: 至少有一個數字, 至少有一個大寫或小寫英文字母 , 且長度必需在6到15之間"

				// 3.帳號
				String cust_acc = req.getParameter("cust_acc");
				String cust_accReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (cust_acc == null || cust_acc.trim().length() == 0) {
					errorMsgs.add("顧客帳號: 請勿空白");
				} else if (!cust_acc.trim().matches(cust_accReg)) {
					errorMsgs.add("顧客帳號: 只能是英文字母開頭, 且長度必需在5到15之間");
				}

				// 4.性別
				String cust_sex = req.getParameter("cust_sex");
				if (cust_sex == null || cust_sex.length() == 0) {
					errorMsgs.add("性別請勿空白");
				}

				// 5.電話
				String cust_tel = req.getParameter("cust_tel").trim();
				if (cust_tel == null || cust_tel.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				// 6.地址
				String cust_addr = req.getParameter("cust_addr").trim();
				if (cust_addr == null || cust_addr.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				// 7.身分證字號
				String cust_pid = new String(req.getParameter("cust_pid".trim()));
				if (cust_pid == null || cust_pid.trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}

				// 8.e-mail
				String cust_mail = new String(req.getParameter("cust_mail".trim()));
				if (cust_mail == null || cust_mail.trim().length() == 0) {
					errorMsgs.add("e-mail請勿空白");
				}

				// 9.生日
				java.sql.Date cust_brd = null;
				try {
					cust_brd = java.sql.Date.valueOf(req.getParameter("cust_brd").trim());
				} catch (IllegalArgumentException e) {
					cust_brd = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				// 10.註冊日
				// 註冊日期
				SimpleDateFormat setDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				/*
				 * 時：分：秒 HH:mm:ss HH : 23小時制 (0-23) kk : 24小時制 (1-24) hh : 12小時制 (1-12) KK :
				 * 11小時制 (0-11)
				 */
				String xx = setDateFormat.format(Calendar.getInstance().getTime());
				java.sql.Date cust_reg = java.sql.Date.valueOf(xx);

				// 11.狀態
				String cust_status = new String(req.getParameter("cust_status".trim()));
				if (cust_status == null || cust_status.trim().length() == 0) {
					errorMsgs.add("狀態請勿空白");
				}
				// 12.暱稱
				String cust_niname = new String(req.getParameter("cust_niname".trim()));
				if (cust_niname == null || cust_niname.trim().length() == 0) {
					errorMsgs.add("暱稱請勿空白");
				}

				// 13.圖片
				Part part = req.getPart("cust_pic");
				InputStream in = part.getInputStream();
				byte[] cust_pic = new byte[in.available()];
				in.read(cust_pic);
				in.close();

				// set
				CustVO custVO = new CustVO();
				custVO.setCust_acc(cust_acc);
				custVO.setCust_name(cust_name);
				custVO.setCust_pwd(cust_pwd);
				custVO.setCust_sex(cust_sex);
				custVO.setCust_tel(cust_tel);
				custVO.setCust_addr(cust_addr);
				custVO.setCust_pid(cust_pid);
				custVO.setCust_mail(cust_mail);
				custVO.setCust_brd(cust_brd);
				custVO.setCust_reg(cust_reg);
				custVO.setCust_pic(cust_pic);
				custVO.setCust_status(cust_status);
				custVO.setCust_niname(cust_niname);

				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("custVO", custVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/cust/update_cust_input.jsp");

					failureView.forward(req, res);
					return;
				}

//				/***************************2.開始修改資料*****************************************/
				CustService custSvc = new CustService();

				custVO = custSvc.updateCust(cust_ID, cust_acc, cust_pwd, cust_name, cust_sex, cust_tel, cust_addr,
						cust_pid, cust_mail, cust_brd, cust_reg, cust_pic, cust_status, cust_niname);
//				custVO = custSvc.updateCust("C0055", "dddd", cust_name, "f", "050505", "8888", "H123456789", "@54564", cust_brd, cust_reg,by , "c","ff" );
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				
				System.out.println(custVO.getCust_ID());
				req.setAttribute("custVO", custVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/cust/listOneCust.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/cust/update_cust_input.jsp");
//				failureView.forward(req, res);
//
//			}
		}
		
		// 刪除
		if ("delete".equals(action)) { // 來自listAllCust.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String cust_ID = new String(req.getParameter("cust_ID"));

				/*************************** 2.開始刪除資料 ***************************************/
				CustService custSvc = new CustService();
				custSvc.deleteCust(cust_ID);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/cust/listAllCust.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/listAllCust.jsp");
				failureView.forward(req, res);
			}
		}

		if("updateCust_status".equals(action))

		{
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				// 0.id
				String cust_ID = new String(req.getParameter("cust_ID").trim());
				
				// set
				CustVO custVO = new CustVO();
				
				
				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("custVO", custVO);// 以下練習正則(規)表示式(regular-expression)
					RequestDispatcher failureView = req.getRequestDispatcher("/cust/update_cust_input.jsp");

					failureView.forward(req, res);
					return;
				}

				/***************************2.開始修改資料*****************************************/
				System.out.println(cust_ID);
				CustService custSvc = new CustService();
				custVO = custSvc.getOneCust(cust_ID);
				custVO = custSvc.updateCust(cust_ID, custVO.getCust_acc(), custVO.getCust_pwd(), custVO.getCust_name(), custVO.getCust_sex(), custVO.getCust_tel(), custVO.getCust_addr(), custVO.getCust_pid(), custVO.getCust_mail(),
						custVO.getCust_brd(), custVO.getCust_reg(), custVO.getCust_pic(), "a0", custVO.getCust_niname());
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("custVO", custVO); // 資料庫update成功後,正確的的empVO物件,存入req
				custVO = custSvc.getOneCust(cust_ID);
				HttpSession session = req.getSession();
				session.setAttribute("custVO", custVO);
				String url = "/front-end/cust/listOneCust.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cust/update_cust_input.jsp");
				failureView.forward(req, res);
			}
		}
		

		// ajax 驗證帳號

		if ("ask".equals(action)) {

			res.setContentType("text/plain; charset=UTF-8");
			String cust_acc = req.getParameter("cust_acc");
			CustService custSvc = new CustService();

			JSONObject obj = new JSONObject();

//			if (custVO.getCust_acc().equals(cust_acc)) {
//				try {
//					obj.accumulate("answer", "帳號重複，請重新輸入");
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			} else {
//				try {
//					obj.accumulate("answer", "此帳號可使用");
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}

			PrintWriter out = res.getWriter();

			if (custSvc.getOneCust_acc(cust_acc) == null) {
				out.write("此帳號可以使用");
			} else {
				out.write("此帳號已有重複");
			}
			out.flush();
			out.close();
		}

	}

	
}