package android.com.ad.model;

import java.sql.Timestamp;

public class AdVO {
	private String ad_ID;
	private String ad_status;
	private Timestamp ad_start;
	private Timestamp ad_end;
	private String ad_type;
	private String ad_title;
	private byte[] ad_pic;
	private String ad_con;
	private String food_sup_ID;
	public String getAd_ID() {
		return ad_ID;
	}
	public void setAd_ID(String ad_ID) {
		this.ad_ID = ad_ID;
	}
	public String getAd_status() {
		return ad_status;
	}
	public void setAd_status(String ad_status) {
		this.ad_status = ad_status;
	}
	public Timestamp getAd_start() {
		return ad_start;
	}
	public void setAd_start(Timestamp ad_start) {
		this.ad_start = ad_start;
	}
	public Timestamp getAd_end() {
		return ad_end;
	}
	public void setAd_end(Timestamp ad_end) {
		this.ad_end = ad_end;
	}
	public String getAd_type() {
		return ad_type;
	}
	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}
	public String getAd_title() {
		return ad_title;
	}
	public void setAd_title(String ad_title) {
		this.ad_title = ad_title;
	}
	public byte[] getAd_pic() {
		return ad_pic;
	}
	public void setAd_pic(byte[] ad_pic) {
		this.ad_pic = ad_pic;
	}
	public String getAd_con() {
		return ad_con;
	}
	public void setAd_con(String ad_con) {
		this.ad_con = ad_con;
	}
	public String getFood_sup_ID() {
		return food_sup_ID;
	}
	public void setFood_sup_ID(String food_sup_ID) {
		this.food_sup_ID = food_sup_ID;
	}
	
	
	
	
}
