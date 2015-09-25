package com.azovstal.test.other;

import java.awt.*; 
import java.awt.event.*; 
import java.beans.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SheetTest extends Object 
	implements PropertyChangeListener {

	JOptionPane optionPane;
	SheetableJFrame frame;
	JTextArea area;

	public static void main (String[] args) {
		new SheetTest( );
	}

	public SheetTest ( ) {
		frame = new SheetableJFrame ("Sheet test");
		// put an image in the frame's content pane
		//ImageIcon icon = new ImageIcon ("/com/azovstal/test/other/l.gif");
		area = new JTextArea();
		area.setEditable(false);
		JScrollPane areaScrollPane = new JScrollPane(area);
		
		frame.getContentPane( ).add(areaScrollPane);
		try {

		/*	//FileReader fr = new FileReader("test.txt");
			
			File file = new File("test.txt");
			FileReader reader = new FileReader(file);
			//FileWriter fileWriter = new FileWriter(file);
			//area.rewrite(fileWriter);
			//area.read(reader, reader.getEncoding());
			BufferedReader bufferedReader = new BufferedReader(reader);
	        String line = bufferedReader.readLine();
	        while(line != null){
	          area.append(line + "\n");
	          line = bufferedReader.readLine();
	        }
	        
	        
	        */
	        
			 area.read(new InputStreamReader(
	                    getClass().getResourceAsStream("test.txt")),
	                    null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(getClass().getResource("/com/azovstal/core/l.gif"));
		JLabel label = new JLabel (icon);
		//frame.getContentPane( ).add(label);
		// build JOptionPane dialog and hold onto it
		optionPane = new JOptionPane ("Do you want to save?",
						JOptionPane.QUESTION_MESSAGE, 
						JOptionPane.YES_NO_OPTION);
	//frame.pack( );
		frame.setVisible(true);
		optionPane.addPropertyChangeListener (this);
		// pause for effect, then show the sheet
		try {Thread.sleep(3000);}
		catch (InterruptedException ie) {}
		JDialog dialog =
			optionPane.createDialog (frame, "irrelevant");
		frame.showJDialogAsSheet (dialog);
	}

	public void propertyChange (PropertyChangeEvent pce) { 
	   if (pce.getPropertyName( ).equals (JOptionPane.VALUE_PROPERTY)) {
			System.out.println ("Selected option " +
					pce.getNewValue( ));
			frame.hideSheet( ); 
	   } 
	} 
}

