/**
 * 
 */
package com.rnb2.swApp.utils;

import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel;

/**
 * @author budukh-rn
 * 
 */
public class SwingUtil {

	/**
	 * ��������� lookAndFeel
	 */
	public static void setupSubstance(final SubstanceLookAndFeel lookAndFeel) {
		// SubstanceLookAndFeel lookAndFeel = new SubstanceMarinerLookAndFeel();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
			UIManager
					.put(SubstanceLookAndFeel.SHOW_EXTRA_WIDGETS, Boolean.TRUE);
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);

		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������� ������ �� VK_ENTER
	 * 
	 * @param frame
	 */
	public static void setFocusByEnterKey(final JFrame frame) {
		Set forwardKeys = frame
				.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
		Set newForwardKeys = new HashSet(forwardKeys);
		newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		frame.setFocusTraversalKeys(
				KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardKeys);
	}

	/**
	 * ������� ����� � JButton �� toolBare
	 * 
	 * @param toolBar
	 */
	public static void setToolBarComponentsFocusDisable(final JToolBar toolBar) {
		for (int i = 0; i < toolBar.getComponentCount(); i++)
			if (toolBar.getComponent(i) instanceof JButton)
				((JButton) toolBar.getComponent(i)).setFocusable(false);
	}

	/** ���������� ������ � ������������� ������������ ������� ������������� */
	public static JPanel createVerticalPanel() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		return p;
	}

	public static JPanel createHorizontalPanel() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		return p;
	}

	/** ��������� ��������� ���������� � �������� ���������� ���� JTextField */
	public static void fixTextFieldSize(JTextField field, int size) {
		Dimension sizeD = field.getPreferredSize();
		// ����� ��������� ���� ��-�������� ����� ����������� ���� ������ �
		// �����
		sizeD.width = size;
		// ������ ��������� ���� �� ������ ���� ����� ����������� ������
		field.setMaximumSize(sizeD);
	}
	
	/** ��������� ��������� ���������� � �������� ���������� ���� JTextField */
	public static void fixTextFieldSize(JTextField field) {
		Dimension size = field.getPreferredSize();
		// ����� ��������� ���� ��-�������� ����� ����������� ���� ������ �
		// �����
		size.width = field.getMaximumSize().width;
		// ������ ��������� ���� �� ������ ���� ����� ����������� ������
		field.setMaximumSize(size);
	}

	/** ������ ������ ������������ �� ��� X ��� ������ ����������� */
	public static void setGroupAlignmentX(JComponent[] cs, float alignment) {
		for (int i = 0; i < cs.length; i++) {
			cs[i].setAlignmentX(alignment);
		}
	}

	public static void setClipboard(String str) {
		StringSelection ss = new StringSelection(str);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	}

	public static String getClipboard() {
		Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
				.getContents(null);

		try {
			if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				String text = (String) t
						.getTransferData(DataFlavor.stringFlavor);
				return text;
			}
		} catch (UnsupportedFlavorException e) {
		} catch (IOException e) {
		}
		return null;
	}

}
