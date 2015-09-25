package com.azovstal.core;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ShowErrors {

	public ShowErrors() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShowErrors(Exception ex,JPanel panel, String message){
		ex.printStackTrace();
		JOptionPane.showMessageDialog(panel,
			message, "Error",
			JOptionPane.WARNING_MESSAGE);
	}
	
	public ShowErrors(Exception ex,JPanel panel, String title, String message){
		ex.printStackTrace();
		JOptionPane.showMessageDialog(panel,
			message, title,
			JOptionPane.WARNING_MESSAGE);
	}
}
