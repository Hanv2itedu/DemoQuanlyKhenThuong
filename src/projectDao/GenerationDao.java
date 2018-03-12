package projectDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.DBUtil;


public class GenerationDao {
	
	protected Connection connection;
	protected PreparedStatement preparedStatement;
	protected ResultSet resultSet; 
	String sql;
	
	public GenerationDao(){
		connection = null;
		try{
			connection = DBUtil.getConnection();
			
		}catch(Exception sqlEx){
			JOptionPane.showMessageDialog(null, "Không có kết nối với cơ sở dữ liệu");
			sqlEx.printStackTrace();
		}
	}
//	public void openConnection(){
//		if(connection == null){
//			try{
//				connection = DBUtil.getConnection();
//				
//			}catch(Exception sqlEx){
//				JOptionPane.showMessageDialog(null, "Không có kết nối với cơ sở dữ liệu");
//				sqlEx.printStackTrace();
//			}
//		}
//	}
//	public void closeConnect(){
//		if(connection !=null){
//			 try {
//				 connection.close();
//		        } catch (SQLException e) { e.printStackTrace();}
//		}
//		if (resultSet!= null) {
//	        try {
//	            resultSet.close();
//	        } catch (SQLException e) { e.printStackTrace();}
//	    }
//	    if (preparedStatement != null) {
//	        try {
//	            preparedStatement.close();
//	        } catch (SQLException e) { e.printStackTrace();}
//	    }	    
//	}
}
