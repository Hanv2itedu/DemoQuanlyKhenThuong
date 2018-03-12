package program_form;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

	//CONSTRUCTOR
	  public ButtonRenderer() {
	    //SET BUTTON PROPERTIES
	    setOpaque(true);
	  }
	  @Override
	  public Component getTableCellRendererComponent(JTable table, Object obj,
	      boolean selected, boolean focused, int row, int col) {
	    
	    //SET PASSED OBJECT AS BUTTON TEXT
	      setText((obj==null) ? "":obj.toString());
	        
	    return this;
	  }

}
