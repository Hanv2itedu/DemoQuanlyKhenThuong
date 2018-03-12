package service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.NhanVien;
import model.TapThe;
import projectDao.DonViDao;
import projectDao.NhanVienDao;

public class NhanVienService {
	
	NhanVienDao nhanVienDao = new NhanVienDao();
	DonViDao donViDao = new DonViDao();
	
	public void showAllDonViTheoTenTTCha(JTable jTableNhanVien, Boolean check, String group, String sort,int idTapThe){
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
		jTableNhanVien.setModel(model);
		model.addColumn("Select");
		model.addColumn("ID");
		model.addColumn("Tên");		
		model.addColumn("Thuộc Đơn Vị");
		List<NhanVien> listNhanVien = nhanVienDao.findAllNhanVienTheoDV(idTapThe, group, sort);
		int row = 0;
		for(NhanVien nhanVien: listNhanVien){
			model.addRow(new Object[0]);
			model.setValueAt(check, row, 0);
			model.setValueAt(nhanVien.getId()         , row, 1);
			model.setValueAt(nhanVien.getTenNhanVien(), row, 2);			
			model.setValueAt(nhanVien.getTenTapthe()  , row, 3);
			row++;
		}
	}
	public void addANhanVien(NhanVien nhanVien){
		List<NhanVien> listNhanVienTemp = nhanVienDao.findAllNhanVienTheoDV(nhanVien.getIdTT(), "ten", "ASC");
		boolean check = true;
		for(NhanVien nhanVienCompare : listNhanVienTemp){
			if(nhanVien.getId().equals(nhanVienCompare.getId())){
				check = false;
				break;
			}
		}
		if(check ==true)
			nhanVienDao.addANhanVien(nhanVien);
		else
			JOptionPane.showMessageDialog(null, "Mời nhập lại do trùng ID NHAN VIEN");
	}
	public void updateANhanVien(NhanVien nhanVien, String idNeedUpdate ){
		
			nhanVienDao.updateANhanVien(nhanVien, idNeedUpdate);
		
	}
	public void deleteANhanVien( String idNeedDel ){
		
		nhanVienDao.deleteANhanVien(idNeedDel);
	
}
	public void showAllNVTheoTruong(JTable jTableNhanVien, boolean check, String group, String sort, int idTTCha) {
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
		jTableNhanVien.setModel(model);
		model.addColumn("Select");
		model.addColumn("ID");
		model.addColumn("Tên");		
		model.addColumn("Thuộc Đơn Vị");
		List<TapThe> listTT = donViDao.listAllDonViTheoDVCha(idTTCha, "id", "ASC");
		List<NhanVien> listNhanVien =  new ArrayList<>();
		for(TapThe tt: listTT){			
			listNhanVien.addAll(nhanVienDao.findAllNhanVienTheoDV(tt.getId(), group, sort));
		}
		int row = 0;
		for(NhanVien nhanVien: listNhanVien){
			model.addRow(new Object[0]);
			model.setValueAt(check, row, 0);
			model.setValueAt(nhanVien.getId()         , row, 1);
			model.setValueAt(nhanVien.getTenNhanVien(), row, 2);
			model.setValueAt(nhanVien.getTenTapthe()  , row, 3);
			row++;
		}
	}
	
	public List<String> getAllTenNhanViensLikeName(String text) {
		return nhanVienDao.getAllTenNhanViensLikeName(text);
		
	}
	public String convertTenToId(String ten) {
		List<NhanVien> listNhanVien = nhanVienDao.getAllNhanVien();
		String id = null;
		for(NhanVien nhanVien: listNhanVien){
			if(nhanVien.getTenNhanVien().equals(ten))
				id = nhanVien.getId();
		}
		return id;
	}
	
}
