package com.test;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class ExpenseReport extends JFrame
{
	protected JTable m_table;
	protected ExpenseReportData m_data;
	protected JLabel m_tolatLbl;
	protected JLabel m_approvedLbl;
	protected NumberFormat m_moneyFormat =
				NumberFormat.getCurrencyInstance(Locale.US);

	static {
		UIManager.put("ComboBox.foreground",
			UIManager.getColor("Table.foreground"));
		UIManager.put("ComboBox.background",
			UIManager.getColor("Table.background"));
		UIManager.put("ComboBox.selectionForeground",
			UIManager.getColor("Table.selectionForeground"));
		UIManager.put("ComboBox.selectionBackground",
			UIManager.getColor("Table.selectionBackground"));
		UIManager.put("ComboBox.font",
			UIManager.getFont("Table.font"));
	}
/**
 * comboBox в Таблице
 *
 */
//	Collection rs = manage.executeNamedQuery("findAllCarriage");
//	for(RollingStock t : (Collection<RollingStock>)rs){
//		rsCombo.addItem(t);
//	}
//	rsCombo.addActionListener(new rsComboAction());
//	rsCombo.setRequestFocusEnabled(false);
//
//	rsCombo.setRenderer(documentRenderer);
//	TableCellEditor cellEditor = new DefaultCellEditor(rsCombo);
//	TableColumnModel columnModel = tableRs.getColumnModel();
//	TableColumn column2  = columnModel.getColumn(1);
//	
//	column2.setCellEditor(cellEditor);

	public ExpenseReport() {
		super("Expense Report");
		setSize(600, 300);

		m_data = new ExpenseReportData(this);

		m_table = new JTable();
		m_table.setAutoCreateColumnsFromModel(false);
		m_table.setModel(m_data);
		m_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		for (int k = 0; k < m_data.getColumnCount(); k++) {
			TableCellRenderer renderer = null;
			TableCellEditor editor = null;
			switch (k) {
			case ExpenseReportData.COL_DATE:
				SimpleDateFormat dateFormat =
					new SimpleDateFormat("MM/dd/yy");
				renderer = new FormattedCellRenderer(dateFormat);
				editor = new FormattedCellEditor(
					new JFormattedTextField(dateFormat));
				break;

			case ExpenseReportData.COL_AMOUNT:
				renderer = new FormattedCellRenderer(m_moneyFormat);
				editor = new FormattedCellEditor(
					new JFormattedTextField(m_moneyFormat));
				break;

			case ExpenseReportData.COL_CATEGORY:
				renderer = new DefaultTableCellRenderer();
				JComboBox combo = new JComboBox(
					ExpenseReportData.CATEGORIES);
				combo.setRequestFocusEnabled(false);
				editor = new DefaultCellEditor(combo);
				break;

			case ExpenseReportData.COL_APPROVED:
				renderer = new CheckCellRenderer();
				JCheckBox chBox = new JCheckBox();
				chBox.setHorizontalAlignment(JCheckBox.CENTER);
				chBox.setBackground(m_table.getBackground());
				editor = new DefaultCellEditor(chBox);
				break;

			case ExpenseReportData.COL_DESCRIPTION:
				renderer = new DefaultTableCellRenderer();
				JTextField txt = new JTextField();
				txt.setBorder(null);
				editor = new DefaultCellEditor(txt);
				break;
			}
			if (renderer instanceof JLabel)
				((JLabel)renderer).setHorizontalAlignment(
					ExpenseReportData.m_columns[k].m_alignment);
			if (editor instanceof DefaultCellEditor)
				((DefaultCellEditor)editor).setClickCountToStart(2);

			TableColumn column = new TableColumn(k,
				ExpenseReportData.m_columns[k].m_width,
				renderer, editor);
			m_table.addColumn(column);
		}

		JTableHeader header = m_table.getTableHeader();
		header.setUpdateTableInRealTime(false);

		JScrollPane ps = new JScrollPane();
		ps.getViewport().setBackground(m_table.getBackground());
		ps.setSize(550, 150);
		ps.getViewport().add(m_table);
		getContentPane().add(ps, BorderLayout.CENTER);

		JToolBar tb = createToolbar();
		getContentPane().add(tb, BorderLayout.NORTH);

		JPanel p = new JPanel(new GridLayout(1, 2, 5, 5));

		m_tolatLbl = new JLabel("Total: ");
		m_tolatLbl.setFont(new Font("Helvetica", Font.PLAIN, 14));
		m_tolatLbl.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		p.add(m_tolatLbl);

		m_approvedLbl = new JLabel("Approved: ");
		m_approvedLbl.setFont(new Font("Helvetica", Font.PLAIN, 14));
		m_approvedLbl.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		p.add(m_approvedLbl);

		getContentPane().add(p, BorderLayout.SOUTH);

		calcTotal();
	}

	public void calcTotal() {
		double total = 0;
		double approved = 0;
		for (int k=0; k<m_data.getRowCount(); k++) {
			Double amount = (Double)m_data.getValueAt(k,
				ExpenseReportData.COL_AMOUNT);
			total += amount.doubleValue();

			Boolean flag = (Boolean)m_data.getValueAt(k,
				ExpenseReportData.COL_APPROVED);
			if (flag.booleanValue())
				approved += amount.doubleValue();
		}
		m_tolatLbl.setText("Total: "+m_moneyFormat.format(total));
		m_approvedLbl.setText("Approved: "+m_moneyFormat.format(approved));
	}

	protected JToolBar createToolbar() {
		JToolBar tb = new JToolBar();
		tb.setFloatable(false);

		JButton bt = new JButton(new ImageIcon("Insert24.gif"));
		bt.setToolTipText("Insert Row");
		bt.setRequestFocusEnabled(false);
		ActionListener lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nRow = m_table.getSelectedRow()+1;
				m_data.insert(nRow);

				m_table.tableChanged(new TableModelEvent(
					m_data, nRow, nRow, TableModelEvent.ALL_COLUMNS,
					TableModelEvent.INSERT));
				m_table.setRowSelectionInterval(nRow, nRow);
			}
		};
		bt.addActionListener(lst);
		tb.add(bt);

		bt = new JButton(new ImageIcon("Delete24.gif"));
		bt.setToolTipText("Delete Row");
		bt.setRequestFocusEnabled(false);
		lst = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nRow = m_table.getSelectedRow();
				if (m_data.delete(nRow)) {
					m_table.tableChanged(new TableModelEvent(
						m_data, nRow, nRow, TableModelEvent.ALL_COLUMNS,
						TableModelEvent.DELETE));
					m_table.clearSelection();
					calcTotal();
				}
			}
		};
		bt.addActionListener(lst);
		tb.add(bt);

		return tb;
	}

	public static void main(String argv[]) {
		ExpenseReport frame = new ExpenseReport();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class CheckCellRenderer extends JCheckBox implements TableCellRenderer
{
	protected static Border m_noFocusBorder = new EmptyBorder(1, 1, 1, 1);
	protected static Border m_focusBorder = UIManager.getBorder(
			"Table.focusCellHighlightBorder");

	public CheckCellRenderer() {
		super();
		setOpaque(true);
		setBorderPainted(true);	// Important
		setBorder(m_noFocusBorder);
		setHorizontalAlignment(JCheckBox.CENTER);
	}

	public Component getTableCellRendererComponent(JTable table,
		Object value, boolean isSelected, boolean hasFocus,
		int nRow, int nCol)
	{
		if (value instanceof Boolean) {
			Boolean b = (Boolean)value;
			setSelected(b.booleanValue());
		}

		setBackground(isSelected && !hasFocus ?
			table.getSelectionBackground() : table.getBackground());
		setForeground(isSelected && !hasFocus ?
			table.getSelectionForeground() : table.getForeground());
		setFont(table.getFont());
		setBorder(hasFocus ? m_focusBorder : m_noFocusBorder);

		return this;
	}
}

class FormattedCellRenderer extends DefaultTableCellRenderer
{
	protected Format m_format;

	public FormattedCellRenderer(Format format) {
		m_format = format;
	}

	public Component getTableCellRendererComponent(JTable table,
		Object value, boolean isSelected, boolean hasFocus,
		int nRow, int nCol)
	{
		return super.getTableCellRendererComponent(table,
			value==null ? null : m_format.format(value),
			isSelected, hasFocus, nRow, nCol);
	}
}

class FormattedCellEditor extends DefaultCellEditor
{
	public FormattedCellEditor(final JFormattedTextField
			formattedTextField) {
		super(formattedTextField);
System.out.println(delegate);
		formattedTextField.removeActionListener(delegate);
		delegate = new EditorDelegate() {
			public void setValue(Object value) {
				formattedTextField.setValue(value);
			}

			public Object getCellEditorValue() {
				return formattedTextField.getValue();
			}
		};
		formattedTextField.addActionListener(delegate);
		formattedTextField.setBorder(null);
	}
}

class ExpenseData
{
	public Date		m_date;
	public Double	m_amount;
	public Integer m_category;
	public Boolean m_approved;
	public String	m_description;

	public ExpenseData() {
		m_date = new Date();
		m_amount = new Double(0);
		m_category = new Integer(1);
		m_approved = new Boolean(false);
		m_description = "";
	}

	public ExpenseData(Date date, double amount, int category,
		boolean approved, String description)
	{
		m_date = date;
		m_amount = new Double(amount);
		m_category = new Integer(category);
		m_approved = new Boolean(approved);
		m_description = description;
	}
}

class ColumnData
{
	public String	m_tolatLbl;
	int m_width;
	int m_alignment;

	public ColumnData(String title, int width, int alignment) {
		m_tolatLbl = title;
		m_width = width;
		m_alignment = alignment;
	}
}

class ExpenseReportData extends AbstractTableModel
{
	public static final ColumnData m_columns[] = {
		new ColumnData( "Date", 80, JLabel.LEFT ),
		new ColumnData( "Amount", 80, JLabel.RIGHT ),
		new ColumnData( "Category", 130, JLabel.LEFT ),
		new ColumnData( "Approved", 80, JLabel.CENTER ),
		new ColumnData( "Description", 180, JLabel.LEFT )
	};

	public static final int COL_DATE = 0;
	public static final int COL_AMOUNT = 1;
	public static final int COL_CATEGORY = 2;
	public static final int COL_APPROVED = 3;
	public static final int COL_DESCRIPTION = 4;

	public static final String[] CATEGORIES = {
		"Fares", "Logging", "Business meals", "Others"
	};

	protected ExpenseReport m_parent;
	protected Vector m_vector;

	public ExpenseReportData(ExpenseReport parent) {
		m_parent = parent;
		m_vector = new Vector();
		setDefaultData();
	}

	public void setDefaultData() {
		m_vector = new Vector();
		try {
			SimpleDateFormat f = new SimpleDateFormat("MM/dd/yy");
			m_vector.addElement(new ExpenseData(
				f.parse("11/24/01"), 200, 0, true,
				"Airline tickets to San Jose from Cork via London and New York"));
			m_vector.addElement(new ExpenseData(
				f.parse("11/24/01"), 50, 2, false,
				"Lunch with Pavel to discuss 2nd edition plans and deadlines"));
			m_vector.addElement(new ExpenseData(
				f.parse("11/24/01"), 120, 1, true,
				"Hotel"));
		}
		catch (java.text.ParseException ex) {}
	}

	public int getRowCount() {
		return m_vector==null ? 0 : m_vector.size();
	}

	public int getColumnCount() {
		return m_columns.length;
	}

	public String getColumnName(int nCol) {
		return m_columns[nCol].m_tolatLbl;
	}

	public boolean isCellEditable(int nRow, int nCol) {
		return true;
	}

	public Object getValueAt(int nRow, int nCol) {
		if (nRow < 0 || nRow>=getRowCount())
			return "";
		ExpenseData row = (ExpenseData)m_vector.elementAt(nRow);
		switch (nCol) {
			case COL_DATE:
				return row.m_date;
			case COL_AMOUNT:
				return row.m_amount;
			case COL_CATEGORY:
				return CATEGORIES[row.m_category.intValue()];
			case COL_APPROVED:
				return row.m_approved;
			case COL_DESCRIPTION:
				return row.m_description;
		}
		return "";
	}

	public void setValueAt(Object value, int nRow, int nCol) {
		if (nRow < 0 || nRow>=getRowCount() || value == null)
			return;
		ExpenseData row = (ExpenseData)m_vector.elementAt(nRow);
		String svalue = value.toString();

		switch (nCol) {
			case COL_DATE:
				row.m_date = (Date)value;
				break;
			case COL_AMOUNT:
				if (value instanceof Double)
					row.m_amount = (Double)value;
				else
					row.m_amount = new Double(((Number)value).doubleValue());
				m_parent.calcTotal();
				break;
			case COL_CATEGORY:
				for (int k=0; k<CATEGORIES.length; k++)
					if (svalue.equals(CATEGORIES[k])) {
						row.m_category = new Integer(k);
						break;
					}
				break;
			case COL_APPROVED:
				row.m_approved = (Boolean)value;
				m_parent.calcTotal();
				break;
			case COL_DESCRIPTION:
				row.m_description = svalue;
				break;
		}
	}

	public void insert(int nRow) {
		if (nRow < 0)
			nRow = 0;
		if (nRow > m_vector.size())
			nRow = m_vector.size();
		m_vector.insertElementAt(new ExpenseData(), nRow);
	}

	public boolean delete(int nRow) {
		if (nRow < 0 || nRow >= m_vector.size())
			return false;
		m_vector.remove(nRow);
		return true;
	}
}
