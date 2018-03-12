package controller;

import java.text.MessageFormat;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Print {

public void Print(JTable table,String head) {
	MessageFormat header = new MessageFormat(head);
	MessageFormat footer = new MessageFormat("Page {0,number,integer}");
	try {
		table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
	} catch (java.awt.print.PrinterException ep) {
		JOptionPane.showMessageDialog(null, "Không in được "+head.toLowerCase()+"!");
	}
}
}
