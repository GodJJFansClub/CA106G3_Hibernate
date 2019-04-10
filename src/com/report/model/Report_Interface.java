package com.report.model;

import java.util.List;

public interface Report_Interface{
	void insert(ReportVO reportVO);
	void update(ReportVO reportVO);
	void delete(String report_ID);
	ReportVO findByPrimaryKey(String report_ID);
	List<ReportVO> getAll();
}
