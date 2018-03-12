package service;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.DanhHieu;
import model.NhanVien;
import projectDao.DanhHieuDao;

public class DanhHieuService {
	DanhHieuDao danhHieuDao = new DanhHieuDao();
	
	public void showAllDanhHieu(JTable jTableDanhhieu, String filter){
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
		jTableDanhhieu.setModel(model);
		model.addColumn("Select");
		model.addColumn("Thứ tự");
		model.addColumn("Tên Danh Hiệu");
		List<DanhHieu> listDanhHieu = danhHieuDao.findAllDanhHieu(filter);
		int row = 0;
		for(DanhHieu danhHieu : listDanhHieu){
			model.addRow(new Object[0]);
			model.setValueAt(false, row, 0);
			model.setValueAt(row+1, row, 1);
			model.setValueAt(danhHieu.getName(), row, 2);
			row++;
		}
	}
	public void addADanhHieu(DanhHieu danhHieu){
		danhHieuDao.addADanhHieu(danhHieu);
	}
	public DanhHieu findOneDanhHieuByName(String nameDh){
		return danhHieuDao.findOneDanhHieuByName(nameDh);
	}
	public void updateANhanVien(DanhHieu danhHieu, String danhHieuName) {
		danhHieuDao.updateANhanVien(danhHieu,  danhHieuName);
		
	}
	public void deleteADanhHieu(String danhHieuName){
		danhHieuDao.deleteADanhHieu(danhHieuName);
	}
	public List<DanhHieu> getAllDHByIsCaNhan(String filter) {
		return danhHieuDao.findAllDanhHieu(filter);
	}
	public List<String> getAllNameDHByIsCaNhan(String isCaNhan) {
		return danhHieuDao.findAllNameDanhHieu(isCaNhan);
	}
	public int convertTenToId(String ten, String filter) {
		int id = 0;
		System.out.println(ten);
		List<DanhHieu> listDH = danhHieuDao.findAllDanhHieu(filter);
		for(DanhHieu dh : listDH){
			System.out.println(dh.getName());
			if(ten.equals(dh.getName()) == true)
				id = dh.getId();
		}
		return id;
	}
}
