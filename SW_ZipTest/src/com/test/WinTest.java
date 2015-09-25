package com.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

//import javax.persistence.Column;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.sun.rowset.internal.Row;



public class WinTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
}

class MainFrame extends JFrame {
	public Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension dimension = toolkit.getScreenSize();
	private int srcWidth = dimension.width - 300;
	private int srcHeight =dimension.height - 300;
	private Container container = getContentPane();
	
	protected Locale locale;
	final sortFilterModel filterModel; 
	private JTable table;

	public static ImageIcon COLUMN_UP = new ImageIcon("SortUp.gif");
	public static ImageIcon COLUMN_DOWN = new ImageIcon("SortDown.gif");
	private Boolean m_sortAsc = true;
	
	public MainFrame() throws HeadlessException {
		super();
		setTitle("Test");
		setSize(srcWidth, srcHeight);
		setLocationRelativeTo(null);
		locale.setDefault(new Locale("ru","RU"));
	
		//TableModel model = new TablModel(30,5,10);
		final DefaultTableModel model = new DefaultTableModel(cels, columnNames);
		
		filterModel = new  sortFilterModel(model);
		
		table = new JTable(filterModel);
		
		//	обработчик двойного щелчка на заголовке
		table.getTableHeader().addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent arg0) {
				//проверка на двойной клик
				if (arg0.getClickCount() < 2) return;
				//ищем столбец
				int tablecolumn = table.columnAtPoint(arg0.getPoint());
				//	преобраз столбца в индекс модели и сортировка
				
				int modelColumn = table.convertColumnIndexToModel(tablecolumn);
				filterModel.sort(modelColumn);
				
//				m_sortAsc = !m_sortAsc;
//				TableColumnModel colModel = table.getColumnModel();
//				TableColumn column = colModel.getColumn(0);
//				int index = column.getModelIndex();
//				JLabel rerender = (JLabel) column.getHeaderRenderer();
//				rerender.setIcon(filterModel.getColumnIcon(0));
//				
//				table.getTableHeader().repaint();

			}
			
		});
		JProgressBar bar = new JProgressBar();
		bar.setStringPainted(true);
		container.add(new JScrollPane(table),BorderLayout.CENTER);
		container.add(bar, BorderLayout.SOUTH);
	}
	
	private Object[][] cels= {
			{"Mercury",240.0,0,false, Color.yellow	},
			{"Venus",60501.1,0,false, Color.yellow	},
			{"Mars",3397.1,1,false, Color.red	}
	};	
	private String[] columnNames = {
			"Планета","Радиус","Спутники","Газы","Цвет"
	};
	
	class TablModel extends AbstractTableModel{

		private int yaers;
		private int minR;
		private int maxR;
		
		private static final double balanse =	10000.0;
		
		public TablModel(int y, int mr, int maxr) {
			
			yaers = y;
			minR = mr;
			maxR = maxr;
		}

		public int getColumnCount() {
			return maxR-minR+1;
		}

		public int getRowCount() {
			return yaers;
		}

		public Object getValueAt(int r, int c) {
			double rate = (c + minR) / 100.0;
			int nperiods = r;
			double futureBalance = balanse 	*	Math.pow(1+rate, nperiods) ;
			
			return String.format("%.2f", futureBalance);
		}

		@Override
		public String getColumnName(int arg0) {
			
			return (arg0 + minR) + "%";
		}
		
	}
	
	//	model сортировки таблицы
	class sortFilterModel extends AbstractTableModel{
		private TableModel model;
		private int sortColumn;
		private Row[] rows;

		
		
		public sortFilterModel(TableModel m) {
			model = m;
			rows = new Row[model.getRowCount()];
			for (int i = 0; i < rows.length; i++) {
				rows[i] = new Row();
				rows[i].index = i;
			}
		}
		
		//SORTIROVKA
		public void sort(int c){
			sortColumn = c;
			Arrays.sort(rows);
			fireTableDataChanged();
		}
		
		public int getColumnCount() {
			return model.getColumnCount();
		}
		public int getRowCount() {
			return model.getRowCount();
		}
		
		
		@Override
		public String getColumnName(int arg0) {
			return model.getColumnName(arg0);
		}
		
		@Override
		public Class<?> getColumnClass(int arg0) {
			return model.getColumnClass(arg0);
		}

		@Override
		public boolean isCellEditable(int arg0, int arg1) {
			return model.isCellEditable(rows[arg0].index, arg1);
		}

		public Object getValueAt(int arg0, int arg1) {
			return model.getValueAt(rows[arg0].index, arg1);
		}
		
		@Override
		public void setValueAt(Object arg0, int arg1, int arg2) {
			model.setValueAt(arg0, rows[arg1].index, arg2);
		}

		public Icon getColumnIcon(int column) {
			//if (column==modelColumn)
			//	return m_sortAsc ? COLUMN_UP : COLUMN_DOWN;
			return COLUMN_UP;

		}
		
		private class Row implements Comparable<Row>{
			public  int index;
			
			public int compareTo(Row other) {
				Object a =  model.getValueAt(index, sortColumn);
				Object b =  model.getValueAt(other.index, sortColumn);
				m_sortAsc = !m_sortAsc;
				if (a instanceof Comparable)
					return ((Comparable)a).compareTo(b) ;
				else
					return a.toString().compareTo(b.toString());
				
			}
			
		}
	}

	

}