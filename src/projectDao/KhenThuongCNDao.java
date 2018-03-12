package projectDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.FilterSearch;
import model.KTCaNhan;

public class KhenThuongCNDao extends GenerationDao {
	public KhenThuongCNDao(){
		super();
	}
	public List<KTCaNhan> listAllKTCN(){
		List<KTCaNhan> listAllKTCN = new ArrayList<>();
		sql = "SELECT * FROM khen_thuong_ca_nhan";
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				KTCaNhan ktcn = new KTCaNhan();
				ktcn.setId(resultSet.getInt(1));
				ktcn.setIdCaNhan(resultSet.getString(2));
				ktcn.setIdDanhHieu(resultSet.getInt(3));
				ktcn.setIdHinhThuc(resultSet.getInt(4));
				ktcn.setNgayKT(resultSet.getDate(5));
				ktcn.setIdQD(resultSet.getString(6));
				ktcn.idCaNhanToTen();
				ktcn.idDanhHieuToTen();
				ktcn.idHinhThucToTen();
				ktcn.idQDToName();
				listAllKTCN.add(ktcn);
			}		
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listAllKTCN;
	}
	
	public List<KTCaNhan> listAllKTCNByDonVi(int idDV){
		List<KTCaNhan> listAllKTCN = new ArrayList<>();
		sql = "SELECT kt.id, nv.ten, kt.id_danh_hieu, kt.id_hinh_thuc, kt.ngay_kt, kt.id_quyet_dinh "+
				"FROM khen_thuong_ca_nhan kt inner join nhan_vien nv on kt.id_ca_nhan = nv.id ";
		if(idDV != 0)		 
				sql += " where nv.id_tap_the in(Select id from tap_the where id = ?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			if(idDV != 0) preparedStatement.setInt(1, idDV);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				KTCaNhan ktcn = new KTCaNhan();
				ktcn.setId(resultSet.getInt(1));
				ktcn.setTenCaNhan(resultSet.getString(2));
				ktcn.setIdDanhHieu(resultSet.getInt(3));
				ktcn.setIdHinhThuc(resultSet.getInt(4));
				ktcn.setNgayKT(resultSet.getDate(5));
				ktcn.setIdQD(resultSet.getString(6));
//				ktcn.idCaNhanToTen();
				ktcn.idDanhHieuToTen();
				ktcn.idHinhThucToTen();
				ktcn.idQDToName();
				listAllKTCN.add(ktcn);
			}		
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listAllKTCN;
	}
	
	public List<KTCaNhan> listAllKTCNByTenCaNhan(String tenNhanVien){
		List<KTCaNhan> listAllKTCN = new ArrayList<>();
		sql = "SELECT kt.id, nv.ten, kt.id_danh_hieu, kt.id_hinh_thuc, kt.ngay_kt, kt.id_quyet_dinh "+
				"FROM khen_thuong_ca_nhan kt inner join nhan_vien nv on kt.id_ca_nhan = nv.id ";
		if(tenNhanVien != null)		 
				sql += " where nv.ten = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			if(tenNhanVien != null) preparedStatement.setString(1, tenNhanVien);
			resultSet = preparedStatement.executeQuery();
			
				while(resultSet.next()){
					KTCaNhan ktcn = new KTCaNhan();
					ktcn.setId(resultSet.getInt(1));
					ktcn.setTenCaNhan(resultSet.getString(2));
					ktcn.setIdDanhHieu(resultSet.getInt(3));
					ktcn.setIdHinhThuc(resultSet.getInt(4));
					ktcn.setNgayKT(resultSet.getDate(5));
					ktcn.setIdQD(resultSet.getString(6));
	//				ktcn.idCaNhanToTen();
					ktcn.idDanhHieuToTen();
					ktcn.idHinhThucToTen();
					ktcn.idQDToName();
					listAllKTCN.add(ktcn);
				}		
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listAllKTCN;
	}
	
	public List<KTCaNhan> showAllKTCNByIdTTCha(int idTTKT) {
		List<KTCaNhan> listAllKTCN = new ArrayList<>();
		sql = "SELECT kt.id, nv.ten, kt.id_danh_hieu, kt.id_hinh_thuc, kt.ngay_kt, kt.id_quyet_dinh "+
				"FROM khen_thuong_ca_nhan kt inner join nhan_vien nv on kt.id_ca_nhan = nv.id ";
		if(idTTKT != 0)		 
				sql += "WHERE nv.id_tap_the in (select id from tap_the where id_tap_the_cha = ?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			if(idTTKT != 0) preparedStatement.setInt(1, idTTKT);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				KTCaNhan ktcn = new KTCaNhan();
				ktcn.setId(resultSet.getInt(1));
				ktcn.setTenCaNhan(resultSet.getString(2));
				ktcn.setIdDanhHieu(resultSet.getInt(3));
				ktcn.setIdHinhThuc(resultSet.getInt(4));
				ktcn.setNgayKT(resultSet.getDate(5));
				ktcn.setIdQD(resultSet.getString(6));
//				ktcn.idCaNhanToTen();
				ktcn.idDanhHieuToTen();
				ktcn.idHinhThucToTen();
				ktcn.idQDToName();
				listAllKTCN.add(ktcn);
			}		
			
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listAllKTCN;
	}

	public void addAKTCN(KTCaNhan ktCNForm) {
		sql = "INSERT INTO khen_thuong_ca_nhan (id_ca_nhan, id_danh_hieu, id_hinh_thuc, ngay_kt , id_quyet_dinh) VALUE(? , ?, ? ,? ,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ktCNForm.getIdCaNhan());
			if(ktCNForm.getIdDanhHieu() == 0)
				preparedStatement.setNull(2, java.sql.Types.INTEGER);
			else
				preparedStatement.setInt(2,  ktCNForm.getIdDanhHieu() );
			if(ktCNForm.getIdHinhThuc() == 0)
				preparedStatement.setNull(3, java.sql.Types.INTEGER);
			else
				preparedStatement.setInt(3, ktCNForm.getIdHinhThuc());
			preparedStatement.setDate(4,new java.sql.Date(ktCNForm.getNgayKT().getTime()));
			preparedStatement.setString(5, ktCNForm.getIdQD());
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

	public void updateAKTCN(KTCaNhan ktCNForm, int idKTCN) {
		sql = "UPDATE khen_thuong_ca_nhan SET id_ca_nhan = ?, id_danh_hieu = ?, id_hinh_thuc= ?, ngay_kt =? ,id_quyet_dinh = ? Where id = ?";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ktCNForm.getIdCaNhan());
			if(ktCNForm.getIdDanhHieu() != 0)
				preparedStatement.setInt(2, ktCNForm.getIdDanhHieu());
			else 
				preparedStatement.setNull(2, java.sql.Types.INTEGER);
			if(ktCNForm.getIdHinhThuc() != 0)
				preparedStatement.setInt(3, ktCNForm.getIdHinhThuc() );
			else 
				preparedStatement.setNull(3, java.sql.Types.INTEGER);
			preparedStatement.setDate(4,new java.sql.Date(ktCNForm.getNgayKT().getTime()));
			preparedStatement.setString(5, ktCNForm.getIdQD());
			preparedStatement.setInt(6, idKTCN);
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
	public void deleteAKTCN(int idKT){
		sql = "DELETE FROM khen_thuong_ca_nhan where id=? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idKT);
			preparedStatement.executeUpdate();
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Lỗi  " + sql.getMessage());
			sql.printStackTrace();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Lỗi  "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public List<KTCaNhan> showAllThongKeByFilters(FilterSearch filterSearchOb) {
		List<KTCaNhan> listCaNhan = new ArrayList<>();
		sql = "SELECT ktcn.*, nv.ten, dh.danh_hieu, ht.hinh_thuc, qd.ten_qd, tt.ten_tap_the,ttc.ten_tap_the"
			+ " FROM khen_thuong_ca_nhan ktcn"
			+ " inner join nhan_vien nv on nv.id = ktcn.id_ca_nhan"			
			+ " inner join qd_khen_thuong qd on qd.ma_so_kt = ktcn.id_quyet_dinh"
			+ " inner join tap_the tt on nv.id_tap_the = tt.id"
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
		String sqlNam = " year(ktcn.ngay_kt) = ? ";
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
				KTCaNhan ktcn = new KTCaNhan();
				ktcn.setIdCaNhan(resultSet.getString(2));
				ktcn.setTenCaNhan(resultSet.getString(7));
				ktcn.setTenDanhHieu(resultSet.getString(8));
				ktcn.setTenHinhThuc(resultSet.getString(9));
				ktcn.setNgayKT(resultSet.getDate(5));
				ktcn.setTenQD(resultSet.getString(10));
				ktcn.setTenDV(resultSet.getString(11)+"/"+resultSet.getString(12));
				listCaNhan.add(ktcn);
			}
				
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return listCaNhan;
	}

	public List<KTCaNhan> showAllThongKeCNUT( int soNam) {
		List<KTCaNhan> listCaNhan =new ArrayList<>();
		sql = "select khen_thuong_ca_nhan.*, nhan_vien.ten, tt.ten_tap_the ,ttc.ten_tap_the "
			+"from khen_thuong_ca_nhan"
			+" inner join nhan_vien on nhan_vien.id = khen_thuong_ca_nhan.id_ca_nhan "
			+"inner join tap_the tt on nhan_vien.id_tap_the = tt.id "
			+"inner join tap_the ttc on tt.id_tap_the_cha = ttc.id "
			+ "where year(ngay_kt) = year(curtime()) and  id_ca_nhan "
			+ "in(select id_ca_nhan from khen_thuong_ca_nhan  where year(ngay_kt) = year(curtime())-1";
		if(soNam ==3){
			sql+=" and id_ca_nhan in(select id_ca_nhan from khen_thuong_ca_nhan  where year(ngay_kt) = year(curtime())-2) ) group by id_ca_nhan";
		}else
			sql+=") group by id_ca_nhan";
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				KTCaNhan ktcn = new KTCaNhan();
				ktcn.setIdCaNhan(resultSet.getString(2));
				ktcn.setTenCaNhan(resultSet.getString(7));
				ktcn.setTenDV(resultSet.getString(8)+"/"+resultSet.getString(9));
				listCaNhan.add(ktcn);
			}
				
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return listCaNhan;
	} 

	
}
