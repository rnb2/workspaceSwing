package com.azovstal.singleApplication;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



/**
 * Run only single instance of application.
 * @author budukh-rn, daduev-ad
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			Socket sock = new Socket("localhost",SingleAppServer.PORT);
			System.err.println("Дура, уже запущено! ");
			JOptionPane.showMessageDialog(null, "Дура, уже запущено! ");
			
		} catch (IOException e) {
			System.err.println("exceptio in main():"+e);
			runApplication(args);
		}
	}

	public static void runApplication(String[] args){
		MainFrame mainFrame = new MainFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		new SingleAppServer(mainFrame);
	}
	
}
