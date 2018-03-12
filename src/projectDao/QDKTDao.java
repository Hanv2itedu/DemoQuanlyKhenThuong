package projectDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import jdk.nashorn.internal.scripts.JO;
import model.HinhThucKhen;
import model.QuyetDinhKT;

public class QDKTDao extends GenerationDao {
	public QDKTDao(){
		super();
	}
	
	public List<QuyetDinhKT> findAllQD() {
		List<QuyetDinhKT> listQD = new ArrayList<>();
		sql = "Select * from qd_khen_thuong ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				QuyetDinhKT quyetDinhKT = new QuyetDinhKT();
				quyetDinhKT.setNameQD(resultSet.getString("ten_qd"));
				quyetDinhKT.setMsQD(resultSet.getString("ma_so_kt"));
				quyetDinhKT.setReferLink(resultSet.getString("reference_link"));
				quyetDinhKT.setNgayQD((Date) resultSet.getDate("ngay"));
				listQD.add(quyetDinhKT);
			}		
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}		
		return listQD;
	}

	public QuyetDinhKT findOneQDByMaQD(String maSoQD) {
		QuyetDinhKT quyetDinhKT = new QuyetDinhKT();
		sql = "SELECT * FROM qd_khen_thuong WHERE ma_so_kt = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maSoQD);
			resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if(resultSet.getRow() == 0){
				quyetDinhKT = null;
			}else{
				quyetDinhKT.setNameQD(resultSet.getString("ten_qd"));
				quyetDinhKT.setMsQD(resultSet.getString("ma_so_kt"));
				quyetDinhKT.setReferLink(resultSet.getString("reference_link"));
				quyetDinhKT.setNgayQD((Date) resultSet.getDate("ngay"));
			}					
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}		
		return quyetDinhKT;
	}
	
	public void addAQD(QuyetDinhKT qdkt){
		sql = "INSERT INTO qd_khen_thuong values( ? , ? , ? , ?)";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, qdkt.getMsQD());
			preparedStatement.setString(2, qdkt.getNameQD());
			preparedStatement.setString(3, qdkt.getReferLink());
			preparedStatement.setDate(4,new java.sql.Date(qdkt.getNgayQD().getTime()));
			preparedStatement.executeUpdate();
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	public void updateAQD(QuyetDinhKT qdkt, String maSoQD){
		// , reference_link ? thêm sau
		sql = "UPDATE qd_khen_thuong SET ma_so_kt = ? , ten_qd = ? , ngay = ? WHERE ma_so_kt = ? ";
		try{			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, qdkt.getMsQD());
			preparedStatement.setString(2, qdkt.getNameQD());
//			preparedStatement.setString(3, qdkt.getReferLink());
			preparedStatement.setDate(3,new java.sql.Date(qdkt.getNgayQD().getTime()));
			preparedStatement.setString(4, maSoQD);
			preparedStatement.executeUpdate();
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	public void deleteAQD(String maSoQD){
		sql = "DELETE FROM qd_khen_thuong WHERE ma_so_kt = ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maSoQD);
			preparedStatement.executeUpdate();
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String findANameQDById(String idQD) {
		String name= "";
		sql = "SELECT ten_qd FROM qd_khen_thuong WHERE ma_so_kt = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, idQD);			
			resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if(resultSet.getRow() != 0){
				name = resultSet.getString("ten_qd");				
			}			
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi");
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
			e.printStackTrace();
		}
		return name;
	}

	public List<String> getAllNameQD() {
		List<String> listName = new ArrayList<>();
		sql = "SELECT ten_qd FROM qd_khen_thuong";
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				listName.add(resultSet.getString("ten_qd"));				
			}			
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi");
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
			e.printStackTrace();
		}
		return listName;
	
	}

	public List<String> getAllNameLikeNameOrMSKT(String text) {
		sql = "SELECT ten_qd, ma_so_kt FROM qd_khen_thuong "
				+ "where ten_qd like '%"+text+"%' or ma_so_kt like '%"+text+"%'";
		List<String> listTen = new ArrayList<>();
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet!=null && resultSet.next()){
				listTen.add(resultSet.getString(1) + "/" + resultSet.getString(2) );
			}
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return listTen;
	}

}
