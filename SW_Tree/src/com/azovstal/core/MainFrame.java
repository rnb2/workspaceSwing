package com.azovstal.core;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;





public class MainFrame extends JFrame {
	public Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension dimension = toolkit.getScreenSize();
	private int srcWidth = dimension.width - 300;
	private int srcHeight =dimension.height - 300;
	
	private Container container = getContentPane();

//	protected ResourceBundle bundle = ResourceBundle.getBundle("com.azovstal.ziptest.property.language"); 
	protected Locale locale;
	
	private String zipname;
	
	//Constr
	public MainFrame(){
			setTitle("ZIP / UNZIP Test");
			setSize(srcWidth, srcHeight);
			setLocationRelativeTo(null);
			locale.setDefault(new Locale("ru","RU"));
			
			
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu menu = new JMenu("File");
			menuBar.add(menu);
			

			menu.addSeparator();
			
			JMenuItem item = new JMenuItem("Exit");
			item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			
			menu.add(item);
			
			menuBar.add(menu);
			
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
		DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
		root.add(country);
		
		DefaultMutableTreeNode state = new DefaultMutableTreeNode("Calichfornia");
		country.add(state);
		
		DefaultMutableTreeNode city = new DefaultMutableTreeNode("City1");
		state.add(city);
		
		city = new DefaultMutableTreeNode("City 2");
		state.add(city);
		
		country = new DefaultMutableTreeNode("Ukraine");
		root.add(country);
		
		state = new DefaultMutableTreeNode("Donetska");
		country.add(state);
		
		city = new DefaultMutableTreeNode("Mariupol");
		state.add(city);
		
		JTree tree = new JTree(root);
		tree.setShowsRootHandles(true);
		container.add(new JScrollPane(tree), BorderLayout.CENTER);
		
		
			
 
	}
}//End.


