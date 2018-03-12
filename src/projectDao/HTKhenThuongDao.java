package projectDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.DanhHieu;
import model.HinhThucKhen;

public class HTKhenThuongDao extends GenerationDao {
	public HTKhenThuongDao(){
		super();
	}
	public List<HinhThucKhen> findAllDanhHieu() {
		List<HinhThucKhen> listHTKT = new ArrayList<>();
		sql = "Select * from hinh_thuc ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				HinhThucKhen hinhThucKhen = new HinhThucKhen();
				hinhThucKhen.setId(resultSet.getInt(1));
				hinhThucKhen.setNameHT(resultSet.getString("hinh_thuc"));
				listHTKT.add(hinhThucKhen);
			}		
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}		
		return listHTKT;
	}

	public void addAHTKT(HinhThucKhen hinhThucKhen) {
		sql = "INSERT INTO hinh_thuc(hinh_thuc) VALUE(?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, hinhThucKhen.getNameHT());			
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Thêm xong 1 hình thức khen thưởng!");
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi");
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
			e.printStackTrace();
		}
	}

	

	public void updateAHTKT(HinhThucKhen hinhThucKhen, String hTKTname) {
		sql = "UPDATE hinh_thuc SET hinh_thuc = ? Where hinh_thuc = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, hinhThucKhen.getNameHT());	
			preparedStatement.setString(2, hTKTname);
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Update xong 1 hình thức khen thưởng!");
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi");
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
			e.printStackTrace();
		}
	}

	public HinhThucKhen findOneHTByName(String nameHT) {
		HinhThucKhen htKhen = new HinhThucKhen();
		sql = "SELECT * FROM hinh_thuc WHERE hinh_thuc = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nameHT);			
			resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if(resultSet.getRow() == 0){
				htKhen = null;
			}else{
				htKhen.setId(resultSet.getInt(1));
				htKhen.setNameHT(resultSet.getString("hinh_thuc"));
			}			
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi");
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
			e.printStackTrace();
		}
		return htKhen;
	}

	public void deleteAHT(String hTName) {
		sql = "DELETE FROM hinh_thuc WHERE hinh_thuc = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, hTName);
			preparedStatement.executeUpdate();
			
		}catch(SQLException sqlEX){
			JOptionPane.showMessageDialog(null, "Lỗi SQl! Nhập lại! ");
			sqlEX.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
		}
	}

	

	public String findANameHinhThucById(int idHinhThuc) {
		String name= "";
		sql = "SELECT hinh_thuc FROM hinh_thuc WHERE id = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idHinhThuc);			
			resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if(resultSet.getRow() != 0){
				name = resultSet.getString("hinh_thuc");				
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

	public List<String> getAllNameHinhThuc() {
		List<String> listName = new ArrayList<>();
		sql = "SELECT hinh_thuc FROM hinh_thuc ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				listName.add(resultSet.getString("hinh_thuc"));				
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


