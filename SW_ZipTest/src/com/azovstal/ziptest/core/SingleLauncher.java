package com.azovstal.ziptest.core;

import java.awt.Frame;
import java.awt.Window;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class SingleLauncher extends Window {
	public static final int PORT = 38629;
	public ServerSocket server;
	private	MainFrame mainFrame = null;	
	
	public SingleLauncher() {
		super(new Frame());
		try {
			server = new ServerSocket(PORT);
			mainFrame = new MainFrame();
			SingleStatic.mmm(mainFrame);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setVisible(true);

		} catch (IOException ioex) {
			System.out.println("already running!" + SingleStatic.frame);
			JOptionPane.showMessageDialog(null, "Дура!!!!!!! уже открыто");
//				mainFrame = SingleStatic.frame;
//				mainFrame.requestFocus();
//				mainFrame.setVisible(true);
			
			try {
				Runtime.getRuntime().exec("cmd.exe /c start");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new SingleLauncher();
	}
	
}
