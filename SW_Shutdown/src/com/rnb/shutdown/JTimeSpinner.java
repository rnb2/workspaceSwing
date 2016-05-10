/**
 * 
 */
package com.rnb.shutdown;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DateFormatter;

/**
 * @author budukh-rn, 02 окт. 2015 г.
 *
 */
public class JTimeSpinner extends JSpinner {

	private static SimpleDateFormat defaultTimeFormat = new SimpleDateFormat("HH:mm");
	public static final Date ARTIFICIAL_NULL = new Date(32503672800000l);
	private static final long serialVersionUID = 1L;

	private KeyListener keyListener = new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
			final JFormattedTextField textField = (JFormattedTextField) e.getSource();

			if (textField.getCaretPosition() != 0) {
				return;
			}
			if ('2' != e.getKeyChar()) {
				return;
			}

			int second_digit = 0;
			try {
				second_digit = Integer.parseInt(textField.getText(1, 1));
			} catch (NumberFormatException e2) {

			} catch (BadLocationException e2) {

			}

			if (second_digit > 3) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							textField.getDocument().insertString(0, "23", null);
							textField.setCaretPosition(1);
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}
				});
				e.consume();
			}
		}
	};

	/**
	 * 
	 * 2 окт. 2015 г.
	 * 
	 * @param date
	 */
	public JTimeSpinner(Date date) {
		this(defaultTimeFormat, date);
	}

	/**
	 * 
	 */
	public JTimeSpinner() {
		this(defaultTimeFormat, ARTIFICIAL_NULL);
	}

	/**
	 * 
	 * 2 окт. 2015 г.
	 * 
	 * @param formatTime
	 * @param date
	 */
	public JTimeSpinner(SimpleDateFormat formatTime, Date date) {
		setModel(new SpinnerDateModel(date, null, null, Calendar.MINUTE));

		setPreferredSize(new Dimension(80, 25));
		setMinimumSize(this.getPreferredSize());
		setMaximumSize(this.getPreferredSize());

		setEditor(new JSpinner.DateEditor(this, formatTime.toPattern()));

		JFormattedTextField textField = ((JSpinner.DefaultEditor) this.getEditor()).getTextField();

		// --!
		DateFormatter formatter = ((DateFormatter) textField.getFormatter());
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		((DateFormat) formatter.getFormat()).setLenient(false);
		// --

		textField.addKeyListener(keyListener);
	}

}