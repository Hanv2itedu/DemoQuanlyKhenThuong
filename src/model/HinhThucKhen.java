package model;

public class HinhThucKhen {
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String nameHT;

	public String getNameHT() {
		return nameHT;
	}

	public void setNameHT(String nameHT) {
		this.nameHT = nameHT;
	}

	public HinhThucKhen(String nameHT) {
		super();
		this.nameHT = nameHT;
	}

	public HinhThucKhen() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
