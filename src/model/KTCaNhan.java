package model;

import java.util.Date;

import projectDao.DanhHieuDao;
import projectDao.HTKhenThuongDao;
import projectDao.NhanVienDao;
import projectDao.QDKTDao;

public class KTCaNhan {
	private int id;
	private String idCaNhan;
	private String tenCaNhan;
	private int idDanhHieu;
	private String tenDanhHieu;
	private int idHinhThuc;
	private String tenHinhThuc;
	private Date ngayKT;
	private String idQD;
	private String tenQD;
	private String tenDV;
	
	public String getTenDV() {
		return tenDV;
	}
	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}
	private NhanVienDao nhanVienDao = new NhanVienDao();
	private DanhHieuDao danhHieuDao = new DanhHieuDao();
	private HTKhenThuongDao htktDao = new HTKhenThuongDao();
	private QDKTDao qdktdao = new QDKTDao();
	public void idCaNhanToTen(){
		this.tenCaNhan = nhanVienDao.findANameNhanVienById(this.idCaNhan);
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
	public String getTenCaNhan() {
		return tenCaNhan;
	}
	public void setTenCaNhan(String tenCaNhan) {
		this.tenCaNhan = tenCaNhan;
	}
	public String getTenDanhHieu() {
		return tenDanhHieu;
	}
	public void setTenDanhHieu(String tenDanhHieu) {
		this.tenDanhHieu = tenDanhHieu;
	}
	public String getTenHinhThuc() {
		return tenHinhThuc;
	}
	public void setTenHinhThuc(String tenHinhThuc) {
		this.tenHinhThuc = tenHinhThuc;
	}
	public String getTenQD() {
		return tenQD;
	}
	public void setTenQD(String tenQD) {
		this.tenQD = tenQD;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdCaNhan() {
		return idCaNhan;
	}
	public void setIdCaNhan(String idCaNhan) {
		this.idCaNhan = idCaNhan;
	}
	public int getIdDanhHieu() {
		return idDanhHieu;
	}
	public void setIdDanhHieu(int idDanhHieu) {
		this.idDanhHieu = idDanhHieu;
	}
	public int getIdHinhThuc() {
		return idHinhThuc;
	}
	public void setIdHinhThuc(int idHinhThuc) {
		this.idHinhThuc = idHinhThuc;
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
	public KTCaNhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "KTCaNhan [id=" + id + ", " + (idCaNhan != null ? "isCaNhan=" + idCaNhan + ", " : "") + "idDanhHieu="
				+ idDanhHieu + ", idHinhThuc=" + idHinhThuc + ", " + (ngayKT != null ? "ngayKT=" + ngayKT + ", " : "")
				+ (idQD != null ? "idQD=" + idQD : "") + "]";
	}
	
	
	
}
