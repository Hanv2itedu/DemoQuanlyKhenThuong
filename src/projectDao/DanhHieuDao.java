package projectDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.DanhHieu;

public class DanhHieuDao extends GenerationDao {
	public DanhHieuDao(){
		super();
	}
	// if filter  = all , ca nhan, tap the;
	public List<DanhHieu> findAllDanhHieu(String filter){
		List<DanhHieu> listDanhHieu = new ArrayList<>();
		String filterPara = " WHERE is_ca_nhan = ?";
		sql = "SELECT * FROM danh_hieu ";
		
		if(filter.equals("all") == false){
			sql += filterPara;
		}
		try{
			preparedStatement = connection.prepareStatement(sql);
			if(filter.equals("ca nhan")){
				preparedStatement.setInt(1, 1);
			}else if(filter.equals("tap the")){
				preparedStatement.setInt(1, 0);
			}
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				DanhHieu danhHieu = new DanhHieu();
				danhHieu.setId(resultSet.getInt("id"));
				danhHieu.setName(resultSet.getString("danh_hieu"));
				danhHieu.setIsCaNhan((byte) resultSet.getInt("is_ca_nhan"));
				listDanhHieu.add(danhHieu);
			}
			
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}		
		return listDanhHieu;
	}

	public void addADanhHieu(DanhHieu danhHieu) {
		sql = "INSERT INTO danh_hieu (danh_hieu , is_ca_nhan) VALUE(?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, danhHieu.getName());
			preparedStatement.setInt(2, danhHieu.getIsCaNhan());
			
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Thêm xong 1 danh hiệu");
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi");
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
			e.printStackTrace();
		}
		
	}

	public DanhHieu findOneDanhHieuByName(String nameDh) {
		DanhHieu danhHieu = new DanhHieu();
		sql = "SELECT * FROM danh_hieu WHERE danh_hieu = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nameDh);			
			resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if(resultSet.getRow() == 0){
				danhHieu = null;
			}else{
				danhHieu.setId(resultSet.getInt("id"));
				danhHieu.setName(resultSet.getString("danh_hieu"));
				danhHieu.setIsCaNhan((byte) resultSet.getInt("is_ca_nhan"));
			}			
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi");
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
			e.printStackTrace();
		}
		return danhHieu;
	}
	public String findANameDanhHieuById(int idDH) {
		String name= "";
		sql = "SELECT danh_hieu FROM danh_hieu WHERE id = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDH);			
			resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if(resultSet.getRow() != 0){
				name = resultSet.getString("danh_hieu");				
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

	public void updateANhanVien(DanhHieu danhHieu, String danhHieuName) {
		sql = "UPDATE danh_hieu SET danh_hieu = ?, is_ca_nhan = ? WHERE danh_hieu = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, danhHieu.getName());	
			preparedStatement.setInt(2, danhHieu.getIsCaNhan());
			preparedStatement.setString(3, danhHieuName);	
			preparedStatement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Cập Nhật Xong thành "+ danhHieu.getName() +"!");
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi");
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
			e.printStackTrace();
		}
	}

	public void deleteADanhHieu(String danhHieuName) {
		sql = "DELETE FROM danh_hieu WHERE danh_hieu = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, danhHieuName);
			preparedStatement.executeUpdate();
			
		}catch(SQLException sqlEX){
			JOptionPane.showMessageDialog(null, "Lỗi SQl! Nhập lại! ");
			sqlEX.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
		}
	}

	public List<String> findAllNameDanhHieu(String isCaNhan) {
		sql = "SELECT danh_hieu FROM danh_hieu ";
		if(isCaNhan != "all"){
			sql+= "WHERE is_ca_nhan = ?";
		}
		List<String> listName = new ArrayList<>();
		try{
			preparedStatement = connection.prepareStatement(sql);
			if(isCaNhan == "ca nhan")
				preparedStatement.setInt(1, 1);	
			else if(isCaNhan == "tap the")
				preparedStatement.setInt(1, 0);	
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				listName.add(resultSet.getString("danh_hieu"));				
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
	
}
