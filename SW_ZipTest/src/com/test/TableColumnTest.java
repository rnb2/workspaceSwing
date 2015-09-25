package com.test;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class TableColumnTest {

	final static Object[][] TABLE_DATA = {
			{ new Integer(1), "ONJava", "http://www.onjava.com/" },
			{ new Integer(2), "Joshy's Site", "http://www.joshy.org/" },
			{ new Integer(3), "Anime Weekend Atlanta",
					"http://www.awa-con.com/" },
			{ new Integer(4), "QTJ book",
					"http://www.oreilly.com/catalog/quicktimejvaadn/" },
			{ new Integer(5), "bokk",
					"ht", "fdf jhjhf gfgrcvdfg bhfhgdgcbv" }};

	final static String[] COLUMN_NAMES = { "Count", "Name", "URL","Name of Collumn" };

	public static void main(String[] args) {
		// 142 mac l&f has a header bug - force metal for today
		try {

			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		DefaultTableModel mod = new DefaultTableModel(TABLE_DATA, COLUMN_NAMES);
		JTable table = new JTable(mod);
		JScrollPane pane =

		new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JFrame frame = new JFrame("JTable Column Widths");
		frame.getContentPane().add(pane);
		frame.pack();
		frame.setVisible(true);

		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// now get smart about col widths
		final JTable fTable = table;
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				ColumnResizer.adjustColumnPreferredWidths(fTable);
				fTable.revalidate();
			}
		});
	}

}

class ColumnResizer {
	public static void adjustColumnPreferredWidths(JTable table) {
		TableColumnModel columnModel = table.getColumnModel();
		for (int col = 0; col < table.getColumnCount(); col++) {

			int maxwidth = 0;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer rend = table.getCellRenderer(row, col);
				Object value = table.getValueAt(row, col);
				Component comp = rend.getTableCellRendererComponent(table,
						value, false, false, row, col);
				maxwidth = Math.max(comp.getPreferredSize().width, maxwidth);
			} // for row
			TableColumn column = columnModel.getColumn(col);
			column.setPreferredWidth(maxwidth);
		} // for col
	}
}
