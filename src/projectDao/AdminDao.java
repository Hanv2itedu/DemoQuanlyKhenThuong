package projectDao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class AdminDao extends GenerationDao {
	
	public boolean login_admin(String userName, String pass){
		boolean check =false;
		sql = "SELECT * FROM admin_ql WHERE admin_name=? AND password = ? ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, pass);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				check = true;
			}
		}catch(SQLException sqlEx){
			JOptionPane.showMessageDialog(null, " LỖI :" + sqlEx.getMessage());
		}
		return check;
	}
}
