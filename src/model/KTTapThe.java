package model;

import java.util.Date;

import projectDao.DanhHieuDao;
import projectDao.DonViDao;
import projectDao.HTKhenThuongDao;
import projectDao.QDKTDao;

public class KTTapThe {
	private String id;
	private int idTapThe;
	private String tenTapThe;
	private int idDanhHieu;
	private String tenDanhHieu;
	private int idHinhThuc;
	private String tenHinhThuc;
	private Date ngayKT;
	private String idQD;
	private String tenQD;
	private String tenDonViCha;
	
	
	
	public String getTenDonViCha() {
		return tenDonViCha;
	}
	public void setTenDonViCha(String tenDonViCha) {
		this.tenDonViCha = tenDonViCha;
	}
	private DonViDao donViDao = new DonViDao();
	private DanhHieuDao danhHieuDao = new DanhHieuDao();
	private HTKhenThuongDao htktDao = new HTKhenThuongDao();
	private QDKTDao qdktdao = new QDKTDao();
	
	public void idTaptheToTen(){
		this.tenTapThe = donViDao.findANameTTById(this.idTapThe);
	}
	public void idDanhHieuToTen(){
		this.tenDanhHieu = danhHieuDao.findANameDanhHieuById(this.idDanhHieu);
	}
	public void idHinhThucToTen(){
		this.tenHinhThuc = htktDao.findANameHinhThucById(idHinhThuc);
	}
	public void idQDToName(){
		this.tenQD = qdktdao.findANameQDById(this.idQD);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIdTapThe() {
		return idTapThe;
	}
	public void setIdTapThe(int idTapThe) {
		this.idTapThe = idTapThe;
	}
	public String getTenTapThe() {
		return tenTapThe;
	}
	public void setTenTapThe(String tenTapThe) {
		this.tenTapThe = tenTapThe;
	}
	public int getIdDanhHieu() {
		return idDanhHieu;
	}
	public void setIdDanhHieu(int idDanhHieu) {
		this.idDanhHieu = idDanhHieu;
	}
	public String getTenDanhHieu() {
		return tenDanhHieu;
	}
	public void setTenDanhHieu(String tenDanhHieu) {
		this.tenDanhHieu = tenDanhHieu;
	}
	public int getIdHinhThuc() {
		return idHinhThuc;
	}
	public void setIdHinhThuc(int idHinhThuc) {
		this.idHinhThuc = idHinhThuc;
	}
	public String getTenHinhThuc() {
		return tenHinhThuc;
	}
	public void setTenHinhThuc(String tenHinhThuc) {
		this.tenHinhThuc = tenHinhThuc;
	}
	public Date getNgayKT() {
		return ngayKT;
	}
	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}
	public String getIdQD() {
		return idQD;
	}
	public void setIdQD(String idQD) {
		this.idQD = idQD;
	}
	public String getTenQD() {
		return tenQD;
	}
	public void setTenQD(String tenQD) {
		this.tenQD = tenQD;
	}
	
}
