package projectDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.FilterSearch;
import model.KTCaNhan;
import model.KTTapThe;

public class KhenThuongTTDao extends GenerationDao{
	DonViDao dvDao = new DonViDao();
	
	public KhenThuongTTDao(){
		super();
	}
	public List<KTTapThe> listAllKTTTByIdTT(int idTT) {
		List<KTTapThe> listKTTT = new ArrayList<>();
		String sqlTemp1 = " where tt.id =?";
		String sqlTemp2 = " where tt.id =? or tt.id in(select id from tap_the where id_tap_the_cha = ?)";
		sql = " select kt.*, tt.ten_tap_the, dh.danh_hieu, ht.hinh_thuc, qd.ten_qd from khen_thuong_tap_the kt"
			+	" inner join tap_the tt on tt.id = kt.id_tap_the" 
			+	" left join danh_hieu dh on dh.id = kt.id_danh_hieu"
			+	" left join hinh_thuc ht on ht.id = kt.id_hinh_thuc"
			+	" inner join qd_khen_thuong qd on qd.ma_so_kt = kt.id_quyet_dinh ";
		if(idTT!=0){
			if( dvDao.findOneTTById(idTT).getIdTTCha() == 100){
				sql += sqlTemp2;
			}else{
				sql += sqlTemp1;
			}
		}
		try{
			preparedStatement = connection.prepareStatement(sql);
			if(idTT!=0){
				if( dvDao.findOneTTById(idTT).getIdTTCha() == 100){
					preparedStatement.setInt(1, idTT);
					preparedStatement.setInt(2, idTT);
				}else{
					preparedStatement.setInt(1, idTT);
				}
			}
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				KTTapThe kttt = new KTTapThe();
				kttt.setId(resultSet.getString(1));
				kttt.setIdTapThe(resultSet.getInt(2));
				kttt.setIdDanhHieu(resultSet.getInt(3));
				kttt.setIdHinhThuc(resultSet.getInt(4));
				kttt.setNgayKT(resultSet.getDate(5));
				kttt.setIdQD(resultSet.getString(6));
				kttt.setTenTapThe(resultSet.getString(7));
				kttt.setTenDanhHieu(resultSet.getString(8));
				kttt.setTenHinhThuc(resultSet.getString(9));
				kttt.setTenQD(resultSet.getString(10));
				listKTTT.add(kttt);
			}
		}catch(SQLException sql){
			JOptionPane.showConfirmDialog(null, "Lỗi" + sql.getMessage());
			sql.printStackTrace();
		}			
		return listKTTT; 
	}

	public void addAKTTT(KTTapThe ktTTForm) {
		sql = "INSERT INTO khen_thuong_tap_the VALUES (?, ?, ?, ?, ?, ?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ktTTForm.getId());
			preparedStatement.setInt(2, ktTTForm.getIdTapThe());
			if(ktTTForm.getIdDanhHieu() == 0)
				preparedStatement.setNull(3, java.sql.Types.INTEGER);
			else
				preparedStatement.setInt(3,  ktTTForm.getIdDanhHieu() );
			if(ktTTForm.getIdHinhThuc() == 0)
				preparedStatement.setNull(4, java.sql.Types.INTEGER);
			else
				preparedStatement.setInt(4, ktTTForm.getIdHinhThuc());
			preparedStatement.setDate(5,new java.sql.Date(ktTTForm.getNgayKT().getTime()));
			preparedStatement.setString(6, ktTTForm.getIdQD());
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Thêm xong 1 danh hiệu");
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi"+ sql.getMessage());
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi");
			e.printStackTrace();
		}
	}

	public void updateAKTTT(KTTapThe ktTTForm, String idKhenThuongTT) {
		sql = "UPDATE khen_thuong_tap_the SET id = ?, id_tap_the = ? ,id_danh_hieu = ?, id_hinh_thuc= ?, ngay_khen_thuong =? ,id_quyet_dinh = ? Where id = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ktTTForm.getId());
			preparedStatement.setInt(2, ktTTForm.getIdTapThe());
			if(ktTTForm.getIdDanhHieu() != 0)
				preparedStatement.setInt(3, ktTTForm.getIdDanhHieu());
			else 
				preparedStatement.setNull(3, java.sql.Types.INTEGER);
			if(ktTTForm.getIdHinhThuc() != 0)
				preparedStatement.setInt(4, ktTTForm.getIdHinhThuc() );
			else 
				preparedStatement.setNull(4, java.sql.Types.INTEGER);
			preparedStatement.setDate(5,new java.sql.Date(ktTTForm.getNgayKT().getTime()));
			preparedStatement.setString(6, ktTTForm.getIdQD());
			preparedStatement.setString(7, idKhenThuongTT);
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Update xong khen thưởng !");
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi  " + sql.getMessage());
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi  "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void deleteAKTTT(String ktDel) {
		sql = "DELETE FROM khen_thuong_tap_the where id=? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ktDel);
			preparedStatement.executeUpdate();
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi  " + sql.getMessage());
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi  "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public List<KTTapThe> showAllThongKeByFilters(JTable jTable, FilterSearch filterSearchOb) {
		List<KTTapThe> listKTTT = new ArrayList<>();
		sql = "SELECT ktcn.*, dh.danh_hieu, ht.hinh_thuc, qd.ten_qd, tt.ten_tap_the,ttc.ten_tap_the"
			+ " FROM khen_thuong_tap_the ktcn"
			+ " inner join qd_khen_thuong qd on qd.ma_so_kt = ktcn.id_quyet_dinh"
			+ " inner join tap_the tt on ktcn.id_tap_the = tt.id"
			+ " inner join tap_the ttc on tt.id_tap_the_cha = ttc.id ";
		String sqlDHInner = " inner join danh_hieu dh on dh.id = ktcn.id_danh_hieu";
		String sqlDHLeft  = " left join danh_hieu dh on dh.id = ktcn.id_danh_hieu";	
		String sqlHTInner = " inner join hinh_thuc ht on ht.id = ktcn.id_hinh_thuc";
		String sqlHTLeft  = " left join hinh_thuc ht on ht.id = ktcn.id_hinh_thuc";
		if(filterSearchOb.getIdDanhHieu()!= 0)
			sql+= sqlDHInner;
		else
			sql+= sqlDHLeft;
		if(filterSearchOb.getIdHinhThuc()!= 0)
			sql+= sqlHTInner;
		else
			sql+= sqlHTLeft;
		if(filterSearchOb.getIdDV() != 0 || filterSearchOb.getIdDanhHieu()!= 0 || filterSearchOb.getIdHinhThuc()!= 0 || filterSearchOb.getIdQD()!= null || filterSearchOb.getNam()>0) 
			sql+= " WHERE ";
		String sqlDv = " (tt.id = ? or ttc.id = ?) ";
		String sqlDh = " dh.id = ? ";
		String sqlHt = " ht.id = ? ";
		String sqlQd = " qd.ma_so_kt = ? ";
		String sqlNam = " year(ktcn.ngay_khen_thuong) = ?";
		boolean check = true;
		if(filterSearchOb.getIdDV() != 0){
			if(check == true){
				sql+= sqlDv;
				check = false;
			}
			else
				sql += " and" + sqlDv;
		}
		if(filterSearchOb.getIdDanhHieu()!= 0 ){
			if(check == true){
				sql+= sqlDh;
				check = false;
			}
			else
				sql += " and" + sqlDh;
		}
		if(filterSearchOb.getIdHinhThuc()!= 0 ){
			if(check == true){
				sql+= sqlHt;
				check = false;
			}
			else
				sql += " and" + sqlHt;
		}
		if(filterSearchOb.getIdQD()!= null ){
			if(check == true){
				sql+= sqlQd;
				check = false;
			}
			else
				sql += " and" + sqlQd;
		}
		if(filterSearchOb.getNam()>0){
			if(check == true){
				sql+= sqlNam;
				check = false;
			}
			else
				sql += " and" + sqlNam;
		}
		if(filterSearchOb.getOrderBy() != null){
			sql+= " order by "+ filterSearchOb.getOrderBy() + filterSearchOb.getOrderStatus();
		}
		try{
			preparedStatement = connection.prepareStatement(sql);
			int i = 1;
			if(filterSearchOb.getIdDV() != 0){
				preparedStatement.setInt(i, filterSearchOb.getIdDV());
				i++;
				preparedStatement.setInt(i, filterSearchOb.getIdDV());
				i++;
			}
			if(filterSearchOb.getIdDanhHieu()!= 0 ){
				preparedStatement.setInt(i, filterSearchOb.getIdDanhHieu());
				i++;
			}
			if(filterSearchOb.getIdHinhThuc()!= 0 ){
				preparedStatement.setInt(i, filterSearchOb.getIdHinhThuc());
				i++;
			}
			if(filterSearchOb.getIdQD()!= null ){
				preparedStatement.setString(i, filterSearchOb.getIdQD());
				i++;
			}				
			if(filterSearchOb.getNam()>0){
				preparedStatement.setInt(i, filterSearchOb.getNam());
				i++;
			}
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				KTTapThe ktcn = new KTTapThe();
				ktcn.setId(resultSet.getString(1));
				ktcn.setTenTapThe(resultSet.getString(10));
				ktcn.setTenDanhHieu(resultSet.getString(7));
				ktcn.setTenHinhThuc(resultSet.getString(8));
				ktcn.setNgayKT(resultSet.getDate(5));
				ktcn.setTenQD(resultSet.getString(9));
				ktcn.setTenDonViCha(resultSet.getString(11));
				listKTTT.add(ktcn);
			}
				
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return listKTTT;
	}

	public List<KTTapThe> showAllThongKeTTUT(int soNam) {
		List<KTTapThe> listKTTT = new ArrayList<>();
		sql =" select kttt.* , tt.ten_tap_the, ttc.ten_tap_the from khen_thuong_tap_the kttt"
			+"	inner join tap_the tt on kttt.id_tap_the = tt.id" 
			+"	inner join tap_the ttc on tt.id_tap_the_cha = ttc.id"
			+"	where year(kttt.ngay_khen_thuong) = year(curtime()) and  kttt.id_tap_the"
			+"	in(select id_tap_the from khen_thuong_tap_the  where year(ngay_khen_thuong) = year(curtime())-1";
		if(soNam ==3){
			sql+=" and id_tap_the in(select id_tap_the from khen_thuong_tap_the  where year(ngay_khen_thuong) = year(curtime())-2) ) group by id_tap_the";
		}else
			sql+=") group by id_tap_the";
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				KTTapThe ktcn = new KTTapThe();
				ktcn.setIdTapThe(resultSet.getInt(2));
				ktcn.setTenTapThe(resultSet.getString(7));
				ktcn.setTenDonViCha(resultSet.getString(8));
				listKTTT.add(ktcn);
			}
				
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return listKTTT;
	}

}
