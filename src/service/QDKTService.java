package service;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import model.DanhHieu;
import model.QuyetDinhKT;
import program_form.ButtonEditor;
import program_form.ButtonRenderer;
import projectDao.QDKTDao;

public class QDKTService {
	QDKTDao qdktDao = new QDKTDao();
	public void showAllQD(JTable jTableQDKT) {
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
		jTableQDKT.setModel(model);
		model.addColumn("Select");
		model.addColumn("Mã Quyết Định");
		model.addColumn("Tên Quyết Định");
		model.addColumn("Văn Bản Đính Kèm");
		model.addColumn("Ngày ra quyết định");
		List<QuyetDinhKT> listQD = qdktDao.findAllQD();
		int row = 0;
		for(QuyetDinhKT qdkt : listQD){
			model.addRow(new Object[0]);
			model.setValueAt(false, row, 0);
			model.setValueAt(qdkt.getMsQD(), row, 1);
			model.setValueAt(qdkt.getNameQD(), row, 2);
			model.setValueAt(qdkt.getReferLink(), row, 3);
			model.setValueAt(qdkt.getNgayQD(), row, 4);
			row++;
		}
//		jTableQDKT.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());;
//		jTableQDKT.getColumnModel().getColumn(1).setCellEditor((TableCellEditor) new ButtonEditor(new JTextField()));
	}
	public QuyetDinhKT findOneQDByMaQD(String maSoQD) {		
		return qdktDao.findOneQDByMaQD( maSoQD);
	}
	public void addAQD(QuyetDinhKT qdForm) {
		qdktDao.addAQD(qdForm);
	}
	public void updateAQD(QuyetDinhKT qdkt ,String maSoQD) {
		qdktDao.updateAQD(qdkt, maSoQD);
	}
	public void deleteAQD(String qdDel) {
		qdktDao.deleteAQD(qdDel);
		
	}
	public List<QuyetDinhKT> getAllQD() {
		return qdktDao.findAllQD();
	}
	public List<String> getAllNameQD() {
		return qdktDao.getAllNameQD();
	}
	public String convertTenToId(String ten) {
		String mskt = null;
		List<QuyetDinhKT> listQd = qdktDao.findAllQD();
		for(QuyetDinhKT qd : listQd){
			if(qd.getNameQD().equals(ten))
				mskt = qd.getMsQD();
		}
		return mskt;
	}
	public List<String> getAllNameLikeNameOrMSKT(String text) {		
		return qdktDao.getAllNameLikeNameOrMSKT(text);
	}
	

}
