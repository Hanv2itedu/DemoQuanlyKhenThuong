package service;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.DanhHieu;
import model.HinhThucKhen;
import projectDao.DanhHieuDao;
import projectDao.HTKhenThuongDao;

public class HTKhenThuongService {
	HTKhenThuongDao hTKhenThuongDao = new HTKhenThuongDao();
	
	public void showAllHTKT(JTable jTable){
		DefaultTableModel model = new DefaultTableModel() {

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		jTable.setModel(model);
		model.addColumn("Select");
		model.addColumn("Thứ tự");
		model.addColumn("Tên hình thức khen thưởng");
		List<HinhThucKhen> listHTKT = hTKhenThuongDao.findAllDanhHieu();
		int row = 0;
		for(HinhThucKhen hTKT : listHTKT){
			model.addRow(new Object[0]);
			model.setValueAt(false, row, 0);
			model.setValueAt(row+1, row, 1);
			model.setValueAt(hTKT.getNameHT(), row, 2);
			row++;
		}
	}
	public void addAHTKT(HinhThucKhen hinhThucKhen){
		hTKhenThuongDao.addAHTKT(hinhThucKhen);
	}
	public HinhThucKhen findOneHTByName(String nameHT){
		return hTKhenThuongDao.findOneHTByName(nameHT);
	}
	public void updateAHTKT(HinhThucKhen hinhThucKhen, String HTKTname) {
		hTKhenThuongDao.updateAHTKT(hinhThucKhen,  HTKTname);
		
	}
	public void deleteAHT(String HTName){
		hTKhenThuongDao.deleteAHT(HTName);
	}
	public List<HinhThucKhen> getAllHT() {
		return hTKhenThuongDao.findAllDanhHieu();
	}
	public List<String> getAllTenHT() {
		return hTKhenThuongDao.getAllNameHinhThuc();
	}
	public int convertTenToId(String ten) {
		int id = 0;
		List<HinhThucKhen> listHt = hTKhenThuongDao.findAllDanhHieu();
		for(HinhThucKhen ht : listHt){
			if(ten.equals(ht.getNameHT()) )
				id = ht.getId();
		}
		return id;
	}
}
