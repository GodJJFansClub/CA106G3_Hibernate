package com.chef.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.chef.model.ChefService;
import com.chef.model.ChefVO;
import com.cust.model.*;

import com.foodSup.model.FoodSupVO;
import com.menu.model.MenuService;
import com.menu.model.MenuVO;
import com.menuOrder.model.MenuOrderService;
import com.menuOrder.model.MenuOrderVO;
import com.testuse.SendEmail;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ChefServlet extends HttpServlet {

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
		
		}catch(NullPointerException e){
			errorMsgs.add("a");
		}
	}
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// 新增主廚
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.姓名
				String cust_name = request.getParameter("cust_name");
				String cust_nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cust_name == null || cust_name.trim().length() == 0) {
					errorMsgs.add("主廚姓名: 請勿空白");
				} else if (!cust_name.trim().matches(cust_nameReg)) {
					errorMsgs.add(cust_name);
				}
				// "主廚姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間"
				
				// 2.帳號
				String cust_acc = request.getParameter("cust_acc");
				String cust_accReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (cust_acc == null || cust_acc.trim().length() == 0) {
					errorMsgs.add("主廚帳號: 請勿空白");
				} else if (!cust_acc.trim().matches(cust_accReg)) {
					errorMsgs.add("主廚帳號: 只能是英文字母開頭, 且長度必需在5到15之間");
				}
				
				// 3.密碼
				String cust_pwd = request.getParameter("cust_pwd");
				String cust_pwdReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if (cust_pwd == null || cust_pwd.trim().length() == 0) {
					errorMsgs.add("主廚密碼: 請勿空白");
				} else if (!cust_pwd.trim().matches(cust_pwdReg)) {
					errorMsgs.add(cust_pwd);
				}

				// "主廚密碼: 至少有一個數字, 至少有一個大寫或小寫英文字母 , 且長度必需在6到15之間"

				// 4.性別
				String cust_sex = request.getParameter("cust_sex");
				if (cust_sex == null || cust_sex.length() == 0) {
					errorMsgs.add("性別請勿空白");
				}

				// 5.電話
				String cust_tel =request.getParameter("cust_tel").trim();
				if (cust_tel == null || cust_tel.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				// 6.地址
				String cust_addr =request.getParameter("cust_addr").trim();
				if (cust_addr == null || cust_addr.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				// 7.身分證字號
				String cust_pid =request.getParameter("cust_pid").trim();
				if (cust_pid == null || cust_pid.trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}

				// 8.e-mail
				String cust_mail =request.getParameter("cust_mail").trim();
				if (cust_mail == null || cust_mail.trim().length() == 0) {
					errorMsgs.add("e-mail請勿空白");
				}

				// 9.生日
				java.sql.Date cust_brd = null;
				try {
					cust_brd = java.sql.Date.valueOf(request.getParameter("cust_brd").trim());
				} catch (IllegalArgumentException e) {
					cust_brd = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				// 10.註冊日
				SimpleDateFormat setDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				String xx = setDateFormat.format(Calendar.getInstance().getTime());
				java.sql.Date cust_reg = java.sql.Date.valueOf(xx);

				// 11.狀態
				String cust_status = "a0";

				// 12.暱稱
				String cust_niname =request.getParameter("cust_niname").trim();
				if (cust_niname == null || cust_niname.trim().length() == 0) {
					errorMsgs.add("暱稱請勿空白");
				}

				// 13.圖片
				Part part= request.getPart("cust_pic");
				InputStream in = part.getInputStream();
				byte[] cust_pic = new byte[in.available()];
				in.read(cust_pic);
				in.close();
				
				//14.服務地區
				String chef_area = request.getParameter("chef_area");

				//15.簡介
				String chef_resume = request.getParameter("chef_resume");
				if (chef_resume == null || chef_resume.trim().length() == 0) {
					errorMsgs.add("簡介請勿空白");
				}

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
				
				ChefVO chefVO = new ChefVO();
				chefVO.setChef_area(chef_area);
				chefVO.setChef_resume(chef_resume);
				// 如果以上格式有錯
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("custVO", custVO);// 以下練習正則(規)表示式(regular-expression)
					request.setAttribute("chefVO", chefVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/login/addChef.jsp");

					failureView.forward(request, response);
					return;
				}
				//將資料加入資料庫
				ChefService chefSvc = new ChefService();
				chefVO = chefSvc.insertChef(cust_acc, cust_pwd, cust_name, cust_sex, cust_tel, cust_addr, cust_pid, cust_mail, cust_brd, cust_reg, cust_pic, cust_status, cust_niname, chef_area, chef_resume);
				
				String subject = "主廚驗證";
				String passRandom = request.getScheme()+"://"+ request.getServerName()+":" + request.getServerPort() + request.getContextPath() + "/front-end/cust/loginStatus.jsp";
				String messageText = "Hello! " + cust_name + " 請點此連結登入: " + passRandom + "\n" + " (已經啟用)";

				SendEmail mailService = new SendEmail();
				mailService.sendMail(cust_mail, subject, messageText);
				
				
				
				
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/login/addSuccess.jsp");
				successView.forward(request, response);
				//除錯
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/login/addChef.jsp");
				failureView.forward(request, response);
			}
		}
		if("getOneForEditChefResume".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{			
				String chef_ID = request.getParameter("chef_ID");
				
				ChefService chefSvc = new ChefService();
				ChefVO chefVO = chefSvc.getOneByChefID(chef_ID);
				
				request.setAttribute("chefVO", chefVO);
				
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/chef/updateChefResume.jsp");
				successView.forward(request, response);
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/front-end/chef/chef_profile.jsp");
				errView.forward(request, response);
			}
		}
		if("updateResume".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{			
				String chef_ID = request.getParameter("chef_ID");
				String chef_resume = request.getParameter("chef_resume");
				
				ChefService chefSvc = new ChefService();
				ChefVO chefVO = new ChefVO();
				chefVO.setChef_ID(chef_ID);
				chefVO.setChef_resume(chef_resume);				
				chefVO = chefSvc.updateChefResume(chef_ID, chef_resume);
				chefVO = chefSvc.getOneByChefID(chef_ID);
				CustService custSvc = new CustService();
				CustVO custVO = new CustVO();
				custVO = custSvc.getOneCust(chef_ID);
				request.setAttribute("custVO", custVO);
				request.setAttribute("chefVO", chefVO);
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/chef/chef_profile.jsp");
				successView.forward(request, response);
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/front-end/chef/updateChefResume.jsp");
				errView.forward(request, response);
			}
		}
		if ("getOneForDisplay".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{			
				String chef_ID = request.getParameter("chef_ID");
				
				ChefService chefSvc = new ChefService();
				ChefVO chefVO = chefSvc.getOneByChefID(chef_ID);
				
				request.setAttribute("chefVO", chefVO);
				
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/chef/updateChef.jsp");
				successView.forward(request, response);
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/back-end/chef/listOneByChefID.jsp");
				errView.forward(request, response);
			}
		}
		if("getOneForUpdate".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{			
				String chef_ID = request.getParameter("chef_ID");
				String chef_status = request.getParameter("chef_status");
				String chef_area = request.getParameter("chef_area");
				String chef_channel = request.getParameter("chef_channel");
				String chef_resume = request.getParameter("chef_resume");
				
				ChefService chefSvc = new ChefService();
				ChefVO chefVO = chefSvc.updateChef(chef_ID, chef_status, chef_area, chef_channel, chef_resume);
				chefVO = chefSvc.getOneByChefID(chef_ID);
				
				request.setAttribute("chefVO", chefVO);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/chef/listOneByChefID.jsp");
				successView.forward(request, response);
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/back-end/chef/updateChef.jsp");
				errView.forward(request, response);
			}
		}
		if("listAllByChefArea".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {				
				//1.接收請求參數，並做錯誤判斷				
				String menu_ID = request.getParameter("menu_ID");
				String chef_area = request.getParameter("chef_area");
				//2.開始查詢資料
				ChefService chefSvc = new ChefService();
				ChefVO chefVO = new ChefVO();
				//3.查詢完成，準備轉交
//				request.setAttribute("menuOrderVO", menuOrderVO);
				RequestDispatcher sucessView = request.getRequestDispatcher("/menuOrder/updateMenuOrder.jsp");
				sucessView.forward(request, response);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher errView = request.getRequestDispatcher("/menuOrder/listAllMenuOrder.jsp");
				errView.forward(request, response);
			}
		}
	}
}