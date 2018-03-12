package projectDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.DBUtil;
import model.TapThe;

public class DonViDao extends GenerationDao{
	
	public DonViDao(){
		super();
	}
	
	public List<TapThe> listAllDonViTheoDVCha(int idTaptheCha, String orderByStr, String orderStatus){
		List<TapThe> listTT = new ArrayList<>();		
		String sql = "SELECT * FROM tap_the where id_tap_the_cha = ? order by "+ orderByStr + " " + orderStatus;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idTaptheCha);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next() && resultSet !=null){
				TapThe tapThe = new TapThe();
				tapThe.setId(resultSet.getInt("id"));
				tapThe.setKyHieuTT(resultSet.getString("ky_hieu"));
				tapThe.setTenTT(resultSet.getString("ten_tap_the"));
				tapThe.setIdTTCha(resultSet.getInt("id_tap_the_cha"));
				listTT.add(tapThe);
			}			
		}catch(SQLException sqe){
			sqe.printStackTrace();
			JOptionPane.showMessageDialog(null, "loi ket noi" + sqe.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "loi ket noi");
		}
		return listTT;
	}
	public TapThe findOneTTById(int idTaptheCha){
		String sql = "SELECT * FROM tap_the where id = ?  ";
		TapThe tapThe = new TapThe();
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idTaptheCha);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				tapThe.setId(resultSet.getInt("id"));
				tapThe.setKyHieuTT(resultSet.getString("ky_hieu"));
				tapThe.setTenTT(resultSet.getString("ten_tap_the"));
				tapThe.setIdTTCha(resultSet.getInt("id_tap_the_cha"));
			}else{
				tapThe = null;
			}
	
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "loi ket noi");
		}
		return tapThe;
	}
//	public List<TapThe> listAllDonViTheoDVCha(int idTaptheCha){
//		List<TapThe> listTT = new ArrayList<>();		
//		String sql = "SELECT * FROM qlkhenthuong.tap_the where id_tap_the_cha = ?";
//		try{
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, idTaptheCha);
//			resultSet = preparedStatement.executeQuery();
//			while(resultSet.next() && resultSet !=null){
//				TapThe tapThe = new TapThe();
//				tapThe.setId(resultSet.getInt("id"));
//				tapThe.setKyHieuTT(resultSet.getString("ky_hieu"));
//				tapThe.setTenTT(resultSet.getString("ten_tap_the"));
//				tapThe.setIdTTCha(resultSet.getInt("id_tap_the_cha"));
//				listTT.add(tapThe);
//			}			
//		}catch(Exception e){
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, "loi ket noi");
//		}
//		return listTT;
//	}
	
	
	// 
	
	public void addADonVi(TapThe tapThe) {
		String sql = "INSERT INTO tap_the (ky_hieu , ten_tap_the , id_tap_the_cha) VALUES(? , ? , ?) ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tapThe.getKyHieuTT());
			preparedStatement.setString(2, tapThe.getTenTT());
			System.out.println(tapThe.getIdTTCha());
			preparedStatement.setInt(3, tapThe.getIdTTCha());
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void updateATT(TapThe tt, String kyHieuTT) {
		sql = "UPDATE tap_the SET ky_hieu = ? ,ten_tap_the = ?, id_tap_the_cha =? WHERE ky_hieu =?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tt.getKyHieuTT());	
			preparedStatement.setString(2, tt.getTenTT());
			preparedStatement.setInt(3, tt.getIdTTCha());
			preparedStatement.setString(4, kyHieuTT);
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Update xong 1 Tập Thể!");
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi");
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
			e.printStackTrace();
		}
	}
	public void deleteATT(String kyHieuTT) {
		sql = "DELETE FROM tap_the WHERE ky_hieu = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, kyHieuTT);
			preparedStatement.executeUpdate();
			
		}catch(SQLException sqlEX){
			JOptionPane.showMessageDialog(null, "không thể xóa vì nó liên quan đến những dữ liệu ở bảng khác! ");
			sqlEX.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
		}
	}
	public TapThe findOneByName(String tenTT) {
		String sql = "SELECT * FROM tap_the WHERE ky_hieu = ?  ";
		TapThe tapThe = new TapThe();
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tenTT);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				tapThe.setId(resultSet.getInt("id"));
				tapThe.setKyHieuTT(resultSet.getString("ky_hieu"));
				tapThe.setTenTT(resultSet.getString("ten_tap_the"));
				tapThe.setIdTTCha(resultSet.getInt("id_tap_the_cha"));
			}else{
				tapThe = null;
			}
	
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "loi ket noi");
		}
		return tapThe;
	}
	public String findANameTTById(int idTapThe) {
		String name= "";
		sql = "SELECT ten_tap_the FROM tap_the WHERE id = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idTapThe);			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				name = resultSet.getString(1);				
			}			
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi"+ sql.getMessage());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi"+e.getMessage());
		}
		return name;
	}
	public List<String> getAllNameLikeName(String text) {
		sql = "select tt.ten_tap_the, ttc.ten_tap_the from tap_the tt "
				+ "inner join tap_the ttc on ttc.id = tt.id_tap_the_cha "
				+ "where tt.ten_tap_the like '%"+ text +"%' ";
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
