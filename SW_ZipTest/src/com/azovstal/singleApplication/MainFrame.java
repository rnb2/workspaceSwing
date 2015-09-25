package com.azovstal.singleApplication;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainFrame extends JFrame implements Serializable {
	public Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension dimension = toolkit.getScreenSize();
	private int srcWidth = 300;
	private int srcHeight =300;
	private Container container = getContentPane();
	protected Locale locale;
	private JLabel label = new JLabel("Тест");
	
	public MainFrame(){
		setTitle("Single loading application ***Test***");
		setSize(srcWidth, srcHeight);
		setLocationRelativeTo(null);
		locale.setDefault(new Locale("ru","RU"));
	
		JPanel mainPanel = new JPanel(new FlowLayout());
		
		mainPanel.add(label);
		
		container.add(mainPanel);
	}
}
