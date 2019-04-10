package com.report.model;

import java.sql.Timestamp;
import java.util.List;

public class ReportService {

	private Report_Interface dao;

	public  ReportService() {
		
		dao = new ReportDAO();
	}

	public ReportVO addReport(String report_title,String report_sort,Timestamp report_start,
			String report_status,String report_con,String cust_ID,String forum_art_ID)
	{

		ReportVO reportVO = new ReportVO();

		reportVO.setReport_title(report_title);
		reportVO.setReport_sort(report_sort);
		reportVO.setReport_start(report_start);
		reportVO.setReport_status(report_status);
		reportVO.setReport_con(report_con);
		reportVO.setCust_ID(cust_ID);
		reportVO.setForum_art_ID(forum_art_ID);
		dao.insert(reportVO);
        
		return reportVO;
	}

	public ReportVO updateReport(String report_ID,String report_title,String report_sort,Timestamp report_start,
			String report_status,String report_con,String cust_ID,String forum_art_ID) {
		int i = 0;
		ReportVO reportVO = new ReportVO();
//		System.out.println("檢查點s" +(i++));
		reportVO.setReport_ID(report_ID);
		reportVO.setReport_title(report_title);
		reportVO.setReport_sort(report_sort);
		reportVO.setReport_start(report_start);
		reportVO.setReport_status(report_status);
		reportVO.setReport_con(report_con);
		reportVO.setCust_ID(cust_ID);
		reportVO.setForum_art_ID(forum_art_ID);
		dao.update(reportVO);

		return reportVO;
	}

	public void deleteReport(String report_ID) {
		dao.delete(report_ID);
	}

	public ReportVO getOneReport(String report_ID) {
		return dao.findByPrimaryKey(report_ID);
	}

	public List<ReportVO> getAll() {
		return dao.getAll();
	}
}
