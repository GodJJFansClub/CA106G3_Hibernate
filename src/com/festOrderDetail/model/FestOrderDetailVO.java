package com.festOrderDetail.model;

import java.io.Serializable;

public class FestOrderDetailVO implements Serializable {

	private static final long serialVersionUID = 10L;
	private String fest_or_ID;
	private String fest_m_ID;
	private Integer fest_or_rate;
	private String fest_or_msg;
	private Integer fest_or_qty;
	private Integer fest_or_stotal;
	
	public String getFest_or_ID() {
		return fest_or_ID;
	}
	public void setFest_or_ID(String fest_or_ID) {
		this.fest_or_ID = fest_or_ID;
	}
	public String getFest_m_ID() {
		return fest_m_ID;
	}
	public void setFest_m_ID(String fest_m_ID) {
		this.fest_m_ID = fest_m_ID;
	}
	public Integer getFest_or_rate() {
		return fest_or_rate;
	}
	public void setFest_or_rate(Integer fest_or_rate) {
		this.fest_or_rate = fest_or_rate;
	}
	public String getFest_or_msg() {
		return fest_or_msg;
	}
	public void setFest_or_msg(String fest_or_msg) {
		this.fest_or_msg = fest_or_msg;
	}
	public Integer getFest_or_qty() {
		return fest_or_qty;
	}
	public void setFest_or_qty(Integer fest_or_qty) {
		this.fest_or_qty = fest_or_qty;
	}
	public Integer getFest_or_stotal() {
		return fest_or_stotal;
	}
	public void setFest_or_stotal(Integer fest_or_stotal) {
		this.fest_or_stotal = fest_or_stotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fest_m_ID == null) ? 0 : fest_m_ID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FestOrderDetailVO other = (FestOrderDetailVO) obj;
		if (fest_m_ID == null) {
			if (other.fest_m_ID != null)
				return false;
		} else if (!fest_m_ID.equals(other.fest_m_ID))
			return false;
		return true;
	}

}
