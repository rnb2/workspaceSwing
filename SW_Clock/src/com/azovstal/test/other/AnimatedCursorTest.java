package com.azovstal.test.other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AnimatedCursorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Animated Cursor Hack");
		JButton button = new JButton("Start Animation");
		button.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					new AnimatedCursor(frame,true);
			}
		});
			

		frame.getContentPane().add(button);
		frame.pack();
		
		frame.setVisible(true);


	}

}
