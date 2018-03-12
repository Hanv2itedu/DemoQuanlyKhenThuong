package projectDao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.DBUtil;
import model.NhanVien;

public class NhanVienDao extends GenerationDao {
	
	public NhanVienDao(){
		super();
	}
	// tìm tất cả Nhân viên theo tung don vi

	public List<NhanVien> findAllNhanVienTheoDV(int idTapThe, String oderBy, String orderStatus) {
		String sqlTemp = "";
		if(idTapThe != 0)
			sqlTemp += "WHERE nv.id_tap_the = ?";
		
		List<NhanVien> listNhanVien = new ArrayList<>();
		sql = "SELECT nv.id, nv.ten , nv.id_tap_the, tt.ten_tap_the FROM nhan_vien nv inner join tap_the tt on nv.id_tap_the = tt.id " + sqlTemp +" order by "
				+ oderBy + " " + orderStatus;
		try {
			preparedStatement = connection.prepareStatement(sql);
			if(idTapThe != 0)
				preparedStatement.setInt(1, idTapThe);
			resultSet = preparedStatement.executeQuery();
			while (resultSet != null && resultSet.next()) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.setId(resultSet.getString(1));
				nhanVien.setTenNhanVien(resultSet.getString(2));				
				nhanVien.setIdTT(resultSet.getInt(3));
				nhanVien.setTenTapthe(resultSet.getString(4));
				listNhanVien.add(nhanVien);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Sự Cố Kết Nối Cơ Sở Dữ Liệu");
			e.printStackTrace();
		} catch(Exception e2){
			e2.printStackTrace();
		}

		return listNhanVien;
	}
	public String findANameNhanVienById(String idNV){
		sql = "Select ten from nhan_vien where id =?";
		String name = "";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, idNV);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next() && resultSet != null){
				name = resultSet.getString(1);
			}
		}catch(SQLException sqlEx){
			JOptionPane.showMessageDialog(null, "Lỗi Xóa Nhân Viên!");
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return name;
	}

	public void addANhanVien(NhanVien nhanVien) {
		sql = "INSERT INTO nhan_vien (id , ten, id_tap_the)"
				+ "VALUES(?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, nhanVien.getId());
			preparedStatement.setString(2, nhanVien.getTenNhanVien());
			preparedStatement.setInt(3, nhanVien.getIdTT());

			preparedStatement.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Cập Nhật Xong Nhan Vien  ");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Mời Bạn Nhập Lại");
			e.printStackTrace();
		}

	}
	
	public void updateANhanVien(NhanVien nhanVien, String idNeedUpdate){
		sql = "UPDATE nhan_vien SET id= ? , ten= ?, id_tap_the= ?  WHERE id= ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, nhanVien.getId());	
			preparedStatement.setString(2, nhanVien.getTenNhanVien());
			preparedStatement.setInt(3, nhanVien.getIdTT());
			preparedStatement.setString(4, idNeedUpdate);
			
			preparedStatement.executeUpdate();
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi Mời Thử Lại");
		}
		
	}
	
	public void deleteANhanVien(String idNhanVien){
		
		sql = "DELETE FROM nhan_vien WHERE id=? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, idNhanVien);
			preparedStatement.executeUpdate();
			
		}catch(SQLException sqlEx){
			JOptionPane.showMessageDialog(null, "Lỗi Xóa Nhân Viên!");
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<String> getAllTenNhanViensLikeName(String tenNhanVien) {
		sql = " select nv.ten, tt.ten_tap_the, ttc.ten_tap_the from nhan_vien nv "
				+ "inner join tap_the tt on nv.id_tap_the = tt.id "
				+ "inner join tap_the ttc on tt.id_tap_the_cha = ttc.id"
				+ " WHERE nv.ten like '%"+ tenNhanVien +"%' ";
		List<String> listTen = new ArrayList<>();
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet!=null && resultSet.next()){
				listTen.add(resultSet.getString(1) + "/" + resultSet.getString(2) +"/" + resultSet.getString(3));
			}
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return listTen;
	}
	public List<NhanVien> getAllNhanVien() {
		List<NhanVien> listNhanVien = new ArrayList<>();
		sql = "SELECT * FROM nhan_vien";
				
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet != null && resultSet.next()) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.setId(resultSet.getString("id"));
				nhanVien.setTenNhanVien(resultSet.getString("ten"));				
				nhanVien.setIdTT(resultSet.getInt("id_tap_the"));
				listNhanVien.add(nhanVien);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Sự Cố Kết Nối Cơ Sở Dữ Liệu");
			e.printStackTrace();
		}

		return listNhanVien;
	}
	
	
	
	
	
	
	

}
