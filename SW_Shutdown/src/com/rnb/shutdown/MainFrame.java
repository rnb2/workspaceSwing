/**
 * 
 */
package com.rnb.shutdown;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author roman.budukh
 *
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSpinner spinner1, spinner2;

	public MainFrame() {
		init();
	}

	private void init() {
		setTitle("Выключатель");
		setSize(300, 250);
		setLocationRelativeTo(null);
		setResizable(false);

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));

		Dimension dimension = new Dimension(100, 25);
		spinner1 = new JSpinner();
		spinner2 = new JSpinner();

		spinner1.setModel(new SpinnerNumberModel(0, 0, 4, 1));
		spinner1.setPreferredSize(dimension);

		spinner2.setModel(new SpinnerNumberModel(0, 0, 60, 15));
		spinner2.setPreferredSize(dimension);

		setDisableInput(spinner1);
		setDisableInput(spinner2);

		JButton btnOk = new JButton("OK");
		JButton btnCnl = new JButton("Cancel");

		btnOk.addActionListener(actionOk());
		btnCnl.addActionListener(actionCnl());

		panel.add(getSpinnerPanel("Часы:", spinner1));
		panel.add(getSpinnerPanel("Минуты:", spinner2));
		panel.add(btnOk);
		panel.add(btnCnl);

		getContentPane().add(panel, SwingConstants.CENTER);
		pack();
	}

	private void setDisableInput(JSpinner spinner) {
		JSpinner.DefaultEditor editor = (DefaultEditor) spinner.getEditor();
		JFormattedTextField textField = editor.getTextField();
		textField.setEditable(false);
	}

	private ActionListener actionCnl() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
	}

	private ActionListener actionOk() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer h = (Integer) spinner1.getValue();
				Integer m = (Integer) spinner2.getValue();

				Long m1 = h * 60L;
				Long m2 = m + m1;
				Long s = m2 * 60;

				String command = "cmd /c start cmd.exe /c \"shutdown /s /t " + s + "\"";
				try {
					Runtime.getRuntime().exec(command);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		};
	}

	private Box getSpinnerPanel(String text, JSpinner spinner) {
		Box panel = Box.createHorizontalBox();
		panel.add(new JLabel(text));
		panel.add(Box.createHorizontalStrut(2));
		panel.add(spinner);

		return panel;
	}

}
