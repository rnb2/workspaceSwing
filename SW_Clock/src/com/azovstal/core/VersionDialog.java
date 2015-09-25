/**
 * 
 */
package com.azovstal.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author budukh-rn
 *
 */
public class VersionDialog extends JDialog {
	
	private static final String fileName = "test.txt";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea area;
	private JScrollPane scrollPane;

	public VersionDialog(){
		setTitle("About");
	    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

	    add(Box.createRigidArea(new Dimension(0, 10)));

	    area = new JTextArea();
		area.setEditable(false);
		scrollPane = new JScrollPane(area);
		readFile();
	    add(scrollPane, BorderLayout.CENTER);

	    add(Box.createRigidArea(new Dimension(0, 10)));

	    JButton close = new JButton("Close");
	    close.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	        dispose();
	      }
	    });

	    close.setAlignmentX(0.5f);
	    add(close, BorderLayout.SOUTH);
	    setModalityType(ModalityType.APPLICATION_MODAL);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setSize(450, 350);
	}
	
	private void readFile() {
		try {
			InputStreamReader in = new InputStreamReader(getClass().getResourceAsStream(fileName));
			area.read(in,null);
		}catch (NullPointerException npe){
			JOptionPane.showMessageDialog(rootPane, "Ошибка, Не найден файл!!!");	
		}catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "Ошибка, не удается прочитать файл!!! " + e.getLocalizedMessage());
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "Ошибка !!! " + e.getLocalizedMessage());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VersionDialog  dialog = new VersionDialog();
		dialog.setVisible(true);
		
	}

}
