package model;

public class DanhHieu {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String name;
	private byte isCaNhan;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte getIsCaNhan() {
		return isCaNhan;
	}
	public void setIsCaNhan(byte isCaNhan) {
		this.isCaNhan = isCaNhan;
	}
	public DanhHieu(String name, byte isCaNhan) {
		super();
		this.name = name;
		this.isCaNhan = isCaNhan;
	}
	public DanhHieu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
