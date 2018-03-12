package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ExcelExporter {
	
	public ExcelExporter(){}
	
	public void exporterTable(JTable table, File file) throws IOException{		
		TableModel model = table.getModel();
//		FileWriter fw = new FileWriter(file);
		FileOutputStream fw = new FileOutputStream(file.getPath()+".xls");
		try{
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fw, "utf-8"));
			for(int i = 0; i< model.getColumnCount() ;i++){
				bw.write(model.getColumnName(i) + "\t");
			}
			bw.newLine();
			for(int i = 0; i< model.getRowCount() ;i++){
				for(int j = 0; j< model.getColumnCount() ;j++){
					bw.write(model.getValueAt(i, j) + "\t");
				}
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(Exception e){
			
		}
		
		
	}
}
