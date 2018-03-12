package model;

import java.util.Date;

public class NhanVien {
	private String id;
	private String tenNhanVien;
	private int idTT; // id tập thể
	private String tenTapthe;
//	private int soDT;
//	private byte gioiTinh;
//	private String chucVu1;
//	private String chucVu2;
//	private Date ngaySinh;
//	private String mail;
	
	
	public String getTenTapthe() {
		return tenTapthe;
	}
	public void setTenTapthe(String tenTapthe) {
		this.tenTapthe = tenTapthe;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public int getIdTT() {
		return idTT;
	}
	public void setIdTT(int idTT) {
		this.idTT = idTT;
	}	
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
