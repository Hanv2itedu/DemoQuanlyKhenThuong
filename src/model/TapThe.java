package model;

import projectDao.DonViDao;

public class TapThe {
	private int id;	
	private String kyHieuTT ;
	private String tenTT;
	private int idTTCha;
	private String tenTTCha;
	
	
	public int getIdTTCha() {
		return idTTCha;
	}
	public void setIdTTCha(int idTTCha) {
		this.idTTCha = idTTCha;
	}
	public String getTenTTCha() {
		return tenTTCha;
	}
	public void setTenTTCha(String tenTTCha) {
		this.tenTTCha = tenTTCha;
	}
	public void setTenTTCha(int idTTCha) {
		DonViDao dvDao = new DonViDao();
		TapThe tt = dvDao.findOneTTById(idTTCha);
		this.tenTTCha = tt.getTenTT();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKyHieuTT() {
		return kyHieuTT;
	}
	public void setKyHieuTT(String kyHieuTT) {
		this.kyHieuTT = kyHieuTT;
	}
	public String getTenTT() {
		return tenTT;
	}
	public void setTenTT(String tenTT) {
		this.tenTT = tenTT;
	}
	
	
	public TapThe(int id, String kyHieuTT, String tenTT, int idTTCha) {
		super();
		this.id = id;
		this.kyHieuTT = kyHieuTT;
		this.tenTT = tenTT;
		this.idTTCha = idTTCha;
		DonViDao dvDao = new DonViDao();
		TapThe tt = dvDao.findOneTTById(this.idTTCha);
		this.tenTTCha = tt.getTenTT();
	}
	public TapThe() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TapThe [id=" + id + ", kyHieuTT=" + kyHieuTT + ", tenTT=" + tenTT + ", idTTCha=" + idTTCha + "]";
	}
	
	
}
