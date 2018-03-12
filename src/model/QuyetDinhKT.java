package model;

import java.util.Date;

public class QuyetDinhKT {
	private String msQD;
	private String nameQD;
	private Date ngayQD;
	private String referLink; // lưu thông báo
	public String getMsQD() {
		return msQD;
	}
	public void setMsQD(String msQD) {
		this.msQD = msQD;
	}
	public String getNameQD() {
		return nameQD;
	}
	public void setNameQD(String nameQD) {
		this.nameQD = nameQD;
	}
	public Date getNgayQD() {
		return ngayQD;
	}
	public void setNgayQD(Date ngayQD) {
		this.ngayQD = ngayQD;
	}
	public String getReferLink() {
		return referLink;
	}
	public void setReferLink(String referLink) {
		this.referLink = referLink;
	}
	public QuyetDinhKT(String msQD, String nameQD, Date ngayQD, String referLink) {
		super();
		this.msQD = msQD;
		this.nameQD = nameQD;
		this.ngayQD = ngayQD;
		this.referLink = referLink;
	}
	public QuyetDinhKT() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
