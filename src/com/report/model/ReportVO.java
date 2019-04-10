package com.report.model;

import java.sql.Timestamp;

public class ReportVO {
	private String report_ID;
	private String report_title;
	private String report_sort;
	private Timestamp report_start;
	private String report_status;
	private String report_con;
	private String cust_ID;
	private String forum_art_ID;
	public String getReport_ID() {
		return report_ID;
	}
	public void setReport_ID(String report_ID) {
		this.report_ID = report_ID;
	}
	public String getReport_title() {
		return report_title;
	}
	public void setReport_title(String report_title) {
		this.report_title = report_title;
	}
	public String getReport_sort() {
		return report_sort;
	}
	public void setReport_sort(String report_sort) {
		this.report_sort = report_sort;
	}
	public Timestamp getReport_start() {
		return report_start;
	}
	public void setReport_start(Timestamp report_start) {
		this.report_start = report_start;
	}
	public String getReport_status() {
		return report_status;
	}
	public void setReport_status(String report_status) {
		this.report_status = report_status;
	}
	public String getReport_con() {
		return report_con;
	}
	public void setReport_con(String report_con) {
		this.report_con = report_con;
	}
	public String getCust_ID() {
		return cust_ID;
	}
	public void setCust_ID(String cust_ID) {
		this.cust_ID = cust_ID;
	}
	public String getForum_art_ID() {
		return forum_art_ID;
	}
	public void setForum_art_ID(String forum_art_ID) {
		this.forum_art_ID = forum_art_ID;
	}

}
