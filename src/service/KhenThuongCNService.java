package service;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.FilterSearch;
import model.HinhThucKhen;
import model.KTCaNhan;
import projectDao.KhenThuongCNDao;

public class KhenThuongCNService {
	KhenThuongCNDao ktcnDao = new KhenThuongCNDao();
	public void showAllKTCN(JTable jTable){
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
		model.addColumn("ID Khen Thuong");
		model.addColumn("Ma So");
		model.addColumn("Ca Nhan");
		model.addColumn("Danh Hieu");
		model.addColumn("Hinh Thuc");
		model.addColumn("Ngay Khen Thuong");
		model.addColumn("Quyet Dinh");
		List<KTCaNhan> listKTCN = ktcnDao.listAllKTCN();
		int row = 0;
		for(KTCaNhan ktcn : listKTCN){
			model.addRow(new Object[0]);
			model.setValueAt(false, row, 0);
			model.setValueAt(ktcn.getId(), row, 1);
			model.setValueAt(ktcn.getIdCaNhan(), row, 2);
			model.setValueAt(ktcn.getTenCaNhan(), row, 3);
			model.setValueAt(ktcn.getTenDanhHieu(), row, 4);
			model.setValueAt(ktcn.getTenHinhThuc(), row, 5);
			model.setValueAt(ktcn.getNgayKT(), row, 6);
			model.setValueAt(ktcn.getTenQD(), row, 7);
			row++;
		}
	}
	public void showAllKTCNByDV(JTable jTable, int idDV){
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
		model.addColumn("ID Khen Thuong");
		model.addColumn("Ca Nhan");
		model.addColumn("Danh Hieu");
		model.addColumn("Hinh Thuc");
		model.addColumn("Ngay Khen Thuong");
		model.addColumn("Quyet Dinh");
		List<KTCaNhan> listKTCN = ktcnDao.listAllKTCNByDonVi(idDV);
		int row = 0;
		for(KTCaNhan ktcn : listKTCN){
			model.addRow(new Object[0]);
			model.setValueAt(false, row, 0);
			model.setValueAt(ktcn.getId(), row, 1);
			model.setValueAt(ktcn.getTenCaNhan(), row, 2);
			model.setValueAt(ktcn.getTenDanhHieu(), row, 3);
			model.setValueAt(ktcn.getTenHinhThuc(), row, 4);
			model.setValueAt(ktcn.getNgayKT(), row, 5);
			model.setValueAt(ktcn.getTenQD(), row, 6);
			row++;
		}
	}
	public void showAllKTCNByTenNhanVien(JTable jTable, String tenNhanVien){
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
		List<KTCaNhan> listKTCN = ktcnDao.listAllKTCNByTenCaNhan(tenNhanVien);
		if(listKTCN != null){
			jTable.setModel(model);
			model.addColumn("Select");
			model.addColumn("ID Khen Thuong");
			model.addColumn("Ca Nhan");
			model.addColumn("Danh Hieu");
			model.addColumn("Hinh Thuc");
			model.addColumn("Ngay Khen Thuong");
			model.addColumn("Quyet Dinh");
			int row = 0;
			for(KTCaNhan ktcn : listKTCN){
				model.addRow(new Object[0]);
				model.setValueAt(false, row, 0);
				model.setValueAt(ktcn.getId(), row, 1);
				model.setValueAt(ktcn.getTenCaNhan(), row, 2);
				model.setValueAt(ktcn.getTenDanhHieu(), row, 3);
				model.setValueAt(ktcn.getTenHinhThuc(), row, 4);
				model.setValueAt(ktcn.getNgayKT(), row, 5);
				model.setValueAt(ktcn.getTenQD(), row, 6);
				row++;
			}
		}else{
			JOptionPane.showMessageDialog(null, "Không có nhân viên nào được khen thưởng");
		}
		
	}
	public List<KTCaNhan> listAllKTCN(){
		return ktcnDao.listAllKTCN();
	}
	public void showAllKTCNByIdTTCha(JTable jTable, int idTTKT) {
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
		model.addColumn("ID Khen Thuong");
		model.addColumn("Ca Nhan");
		model.addColumn("Danh Hieu");
		model.addColumn("Hinh Thuc");
		model.addColumn("Ngay Khen Thuong");
		model.addColumn("Quyet Dinh");
		List<KTCaNhan> listKTCN = ktcnDao.showAllKTCNByIdTTCha(idTTKT);
		int row = 0;
		for(KTCaNhan ktcn : listKTCN){
			model.addRow(new Object[0]);
			model.setValueAt(false, row, 0);
			model.setValueAt(ktcn.getId(), row, 1);
			model.setValueAt(ktcn.getTenCaNhan(), row, 2);
			model.setValueAt(ktcn.getTenDanhHieu(), row, 3);
			model.setValueAt(ktcn.getTenHinhThuc(), row, 4);
			model.setValueAt(ktcn.getNgayKT(), row, 5);
			model.setValueAt(ktcn.getTenQD(), row, 6);
			row++;
		}
	}
	public void addAKTCN(KTCaNhan ktCNForm) {
		ktcnDao.addAKTCN(ktCNForm);
		
	}
	public void updateAKTCN(KTCaNhan ktCNForm, int idKTCN) {
		ktcnDao.updateAKTCN(ktCNForm, idKTCN);
	}
	public void deleteAKTCN( int idKTCN) {
		ktcnDao.deleteAKTCN( idKTCN);
	}
	public void showAllThongKeByFilters(JTable jTable, FilterSearch filterSearchOb) {
		DefaultTableModel model = new DefaultTableModel();
		jTable.setModel(model);
		model.addColumn("STT");
		model.addColumn("ID Ca Nhan");
		model.addColumn("Ca Nhan");
		model.addColumn("Danh Hieu");
		model.addColumn("Hinh Thuc");
		model.addColumn("Ngay Khen Thuong");
		model.addColumn("Quyet Dinh");
		model.addColumn("Ten Don Vi");
		List<KTCaNhan> listKTCN = ktcnDao.showAllThongKeByFilters(filterSearchOb);
		int row = 0;
		for(KTCaNhan ktcn : listKTCN){
			int i = 0;
			model.addRow(new Object[0]);
			model.setValueAt(row+1, row, i++);
			model.setValueAt(ktcn.getIdCaNhan(), row, i++);
			model.setValueAt(ktcn.getTenCaNhan(), row, i++);
			model.setValueAt(ktcn.getTenDanhHieu(), row, i++);
			model.setValueAt(ktcn.getTenHinhThuc(), row, i++);
			model.setValueAt(ktcn.getNgayKT(), row, i++);
			model.setValueAt(ktcn.getTenQD(), row, i++);
			model.setValueAt(ktcn.getTenDV(), row, i++);
			row++;
		}
		
	}
	public void showAllThongKeCNUT(JTable jTableThongKeSoNamLT, int soNam) {
		DefaultTableModel model = new DefaultTableModel();
		jTableThongKeSoNamLT.setModel(model);
		model.addColumn("STT");
		model.addColumn("ID Ca Nhan");
		model.addColumn("Ca Nhan");
		model.addColumn("Ten Don Vi");
		List<KTCaNhan> listKTCN = ktcnDao.showAllThongKeCNUT(soNam);
		int row = 0;
		for(KTCaNhan ktcn : listKTCN){
			int i = 0;
			model.addRow(new Object[0]);
			model.setValueAt(row+1, row, i++);
			model.setValueAt(ktcn.getIdCaNhan(), row, i++);
			model.setValueAt(ktcn.getTenCaNhan(), row, i++);
			model.setValueAt(ktcn.getTenDV(), row, i++);
			row++;
		}
		
	}
	public List<KTCaNhan> listAllKTCNByTenCaNhan(String tenNhanVien) {		
		return ktcnDao.listAllKTCNByTenCaNhan(tenNhanVien);
	}
	
	
}
