/**
 * 
 */
package com.rnb.shutdown;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author roman.budukh
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame();
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setVisible(true);
			}
		});
	}

	
}
