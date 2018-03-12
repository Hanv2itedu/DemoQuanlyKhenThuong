package service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.TapThe;
import projectDao.DonViDao;

public class DonViService {
	DonViDao donViDao = new DonViDao();
	
	public void showTableTruong(JTable jTableDonVi, Boolean check, int idTTCha, String orderByStr, String orderStatus){
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
		//creat a table
		jTableDonVi.setModel(model);
		//add collumn
		model.addColumn("Select");
		model.addColumn("ID");
		model.addColumn("Ký Hiệu");
		model.addColumn("Tên");
		model.addColumn("Trực Thuộc Đơn Vị");
		
		List<TapThe> listTapThe = donViDao.listAllDonViTheoDVCha(idTTCha, orderByStr, orderStatus);
		
		int row = 0;
		for(TapThe tapThe : listTapThe){
			TapThe tapTheTemp =new TapThe(tapThe.getId(), tapThe.getKyHieuTT(), tapThe.getTenTT(), tapThe.getIdTTCha());
			model.addRow(new Object[0]);
			model.setValueAt(check, row, 0);
			model.setValueAt(tapTheTemp.getId(), row, 1);
			model.setValueAt(tapTheTemp.getKyHieuTT(), row, 2);
			model.setValueAt(tapTheTemp.getTenTT(), row, 3);
			model.setValueAt(tapTheTemp.getTenTTCha(), row, 4);
			row++;
		}
	}
	public List<TapThe> getAllTapThe(int idTT){
		return donViDao.listAllDonViTheoDVCha(idTT, "id", "ASC");
	}
	public void addADonVi(TapThe tt){
		donViDao.addADonVi(tt);
	}
	public ArrayList<String> getAllNameOfTruong(){
		List<TapThe> listTT = donViDao.listAllDonViTheoDVCha(100, "id", "ASC");		
		ArrayList<String> listName = new ArrayList<>();
		for(TapThe tt : listTT){
			listName.add(tt.getTenTT());
		}		
		return listName;
		
	}
	public List<String> getAllNameOfDVTheoCha(List<TapThe> listTT){	
		
		List<String> listName = new ArrayList<>();
		for(TapThe tt : listTT){
			listName.add(tt.getTenTT());
		}		
		return listName;
		
	}
	public TapThe findOneTTById(int idTT){
		return donViDao.findOneTTById(idTT);
	}
	public int convertTenTTToId(String tenDonVi, int idTTCha){
		List<TapThe> listTT = donViDao.listAllDonViTheoDVCha(idTTCha, "id", "ASC");
		int result = 0;
		for(TapThe tt : listTT){
			if(tenDonVi.compareTo(tt.getTenTT()) == 0){
				result = tt.getId();
				break;
			}
		}
		return result;
		
	}
	public int convertTenDVConToId(String tenDonViCha, List<TapThe> listTT){		
		int result = 0;
		for(TapThe tt : listTT){
			if(tenDonViCha.compareTo(tt.getTenTT()) == 0){
				result = tt.getId();
				break;
			}
		}
		return result;
		
	}
	public void updateATT(TapThe tt, String kyHieuTT) {
		donViDao.updateATT(tt,kyHieuTT);
	}
	public void deleteATT(String kyHieuTT){
		donViDao.deleteATT(kyHieuTT);
	}
	public TapThe findOneByName(String tenTT) {
		return donViDao.findOneByName(tenTT);
	}
	public List<String> getAllNameLikeName(String text) {
		
		return donViDao.getAllNameLikeName(text);
	}
}
