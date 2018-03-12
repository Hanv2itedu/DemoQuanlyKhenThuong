package service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.FilterSearch;
import model.KTCaNhan;
import model.KTTapThe;
import projectDao.KhenThuongTTDao;

public class KhenThuongTTService {
	KhenThuongTTDao ktttDao = new KhenThuongTTDao();
	DonViService donViService = new DonViService();
	public void showAllKTTTByDV(JTable jTable, int idTTKT) {
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
		model.addColumn("Tap The");
		model.addColumn("Danh Hieu");
		model.addColumn("Hinh Thuc");
		model.addColumn("Ngay Khen Thuong");
		model.addColumn("Quyet Dinh");
		List<KTTapThe> listKTTT = ktttDao.listAllKTTTByIdTT(idTTKT);
		int row = 0;
		for(KTTapThe kttt : listKTTT){
			model.addRow(new Object[0]);
			model.setValueAt(false, row, 0);
			model.setValueAt(kttt.getId(), row, 1);
			model.setValueAt(kttt.getTenTapThe(), row, 2);
			model.setValueAt(kttt.getTenDanhHieu(), row, 3);
			model.setValueAt(kttt.getTenHinhThuc(), row, 4);
			model.setValueAt(kttt.getNgayKT(), row, 5);
			model.setValueAt(kttt.getTenQD(), row, 6);
			row++;
		}
	}

	

	public void addAKTTT(KTTapThe ktTTForm) {
		ktttDao.addAKTTT(ktTTForm);
		
	}



	public void updateAKTTT(KTTapThe ktTTForm, String idKhenThuongTT) {
		ktttDao.updateAKTTT(ktTTForm ,idKhenThuongTT );
		
	}



	public void deleteAKTTT(String ktDel) {
		ktttDao.deleteAKTTT(ktDel);
	}



	public void showAllThongKeByFilters(JTable jTable, FilterSearch filterSearchOb) {
		DefaultTableModel model = new DefaultTableModel();
		jTable.setModel(model);
		model.addColumn("STT");
		model.addColumn("Ma So Khen Thuong");
		model.addColumn("Ten Tap The");
		model.addColumn("Danh Hieu");
		model.addColumn("Hinh Thuc");
		model.addColumn("Ngay Khen Thuong");
		model.addColumn("Quyet Dinh");
		model.addColumn("Ten Don Vi");
		List<KTTapThe> listKTT = ktttDao.showAllThongKeByFilters(jTable,filterSearchOb);
		int row = 0;
		for(KTTapThe kttt : listKTT){
			int i = 0;
			model.addRow(new Object[0]);
			model.setValueAt(row+1, row, i++);
			model.setValueAt(kttt.getId(), row, i++);
			model.setValueAt(kttt.getTenTapThe(), row, i++);
			model.setValueAt(kttt.getTenDanhHieu(), row, i++);
			model.setValueAt(kttt.getTenHinhThuc(), row, i++);
			model.setValueAt(kttt.getNgayKT(), row, i++);
			model.setValueAt(kttt.getTenQD(), row, i++);
			model.setValueAt(kttt.getTenDonViCha(), row, i++);
			row++;
		}
		
	}



	public void showAllThongKeTTUT(JTable jTableThongKeSoNamLT, int soNam) {
		DefaultTableModel model = new DefaultTableModel();
		jTableThongKeSoNamLT.setModel(model);
		model.addColumn("STT");
		model.addColumn("Đơn Vị");
		model.addColumn("Trực thuộc đơn vị");
		List<KTTapThe> listKTTT = ktttDao.showAllThongKeTTUT(soNam)  ;
		int row = 0;
		for(KTTapThe ktcn : listKTTT){
			int i = 0;
			model.addRow(new Object[0]);
			model.setValueAt(row+1, row, i++);
			model.setValueAt(ktcn.getIdTapThe(), row, i++);
			model.setValueAt(ktcn.getTenDonViCha(), row, i++);
			row++;
		}
		
	}

}
