package com.festMenu.model;

import java.sql.Date;

public class FestMenuVO {

	private String fest_m_ID;
	private String fest_m_name;
	private Integer fest_m_qty;
	private Date fest_m_start;
	private Date fest_m_end;
	private byte[] fest_m_pic;
	private String fest_m_resume;
	private Date fest_m_send;
	private String fest_m_status;
	private String fest_m_kind;
	private Integer fest_m_price;
	private String chef_ID;

	public String getFest_m_ID() {
		return fest_m_ID;
	}

	public void setFest_m_ID(String fest_m_ID) {
		this.fest_m_ID = fest_m_ID;
	}

	public String getFest_m_name() {
		return fest_m_name;
	}

	public void setFest_m_name(String fest_m_name) {
		this.fest_m_name = fest_m_name;
	}

	public Integer getFest_m_qty() {
		return fest_m_qty;
	}

	public void setFest_m_qty(Integer fest_m_qty) {
		this.fest_m_qty = fest_m_qty;
	}

	public Date getFest_m_start() {
		return fest_m_start;
	}

	public void setFest_m_start(Date fest_m_start) {
		this.fest_m_start = fest_m_start;
	}

	public Date getFest_m_end() {
		return fest_m_end;
	}

	public void setFest_m_end(Date fest_m_end) {
		this.fest_m_end = fest_m_end;
	}

	public byte[] getFest_m_pic() {
		return fest_m_pic;
	}

	public void setFest_m_pic(byte[] fest_m_pic) {
		this.fest_m_pic = fest_m_pic;
	}

	public String getFest_m_resume() {
		return fest_m_resume;
	}

	public void setFest_m_resume(String fest_m_resume) {
		this.fest_m_resume = fest_m_resume;
	}

	public Date getFest_m_send() {
		return fest_m_send;
	}

	public void setFest_m_send(Date fest_m_send) {
		this.fest_m_send = fest_m_send;
	}

	public String getFest_m_status() {
		return fest_m_status;
	}

	public void setFest_m_status(String fest_m_status) {
		this.fest_m_status = fest_m_status;
	}

	public String getFest_m_kind() {
		return fest_m_kind;
	}

	public void setFest_m_kind(String fest_m_kind) {
		this.fest_m_kind = fest_m_kind;
	}

	public Integer getFest_m_price() {
		return fest_m_price;
	}

	public void setFest_m_price(Integer fest_m_price) {
		this.fest_m_price = fest_m_price;
	}

	public String getChef_ID() {
		return chef_ID;
	}

	public void setChef_ID(String chef_ID) {
		this.chef_ID = chef_ID;
	}

}
