package model;

public class FilterSearch {
	
	private int idDV;
	private int idDanhHieu;
	private int idHinhThuc;
	private String idQD;
	private int nam;
	private String orderBy;
	private String orderStatus;
	
	
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getIdDV() {
		return idDV;
	}
	public void setIdDV(int idDV) {
		this.idDV = idDV;
	
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
	public String getIdQD() {
		return idQD;
	}
	public void setIdQD(String idQD) {
		this.idQD = idQD;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
	}
	@Override
	public String toString() {
		return "FilterSearch [idDV=" + idDV + ", idDanhHieu=" + idDanhHieu + ", idHinhThuc=" + idHinhThuc + ", idQD="
				+ idQD + ", nam=" + nam + "]";
	}
	
	
}
