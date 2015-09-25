package com.azovstal.ziptest.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.azovstal.ziptest.layout.DialogLayout;
import com.test.ExpenseReport;




public class MainFrame extends JFrame {
	public Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension dimension = toolkit.getScreenSize();
	private int srcWidth = dimension.width - 300;
	private int srcHeight =dimension.height - 300;
	
	private Container container = getContentPane();
	private Date currDate;

	protected ResourceBundle bundle = ResourceBundle.getBundle("com.azovstal.ziptest.property.language"); 
	protected Locale locale;
	protected GregorianCalendar m_calendar;
	protected SimpleDateFormat m_dateFormat;
	
	
	private String zipname;
	private JComboBox fileCombo;
	private JTextArea textArea;
	private JTextField field = new JTextField("test.zip");
		
	
	private JComboBox comboBox =new JComboBox();
	private JButton moveButt;
	private JButton zipButt;
	
	private JLabel userName = new JLabel("User name: "+System.getProperty("user.name"));
	private static final String valUserName = System.getProperty("user.name");
	
	private MailFrame mailFrame;
	private JButton mailButt;
	
	private ExpenseReport report;
	private JButton but;
	
	//Constr
	public MainFrame(){
			setTitle("ZIP / UNZIP Test");
			setSize(srcWidth, srcHeight);
			setLocationRelativeTo(null);
			locale.setDefault(new Locale("ru","RU"));
			
			 m_calendar = new GregorianCalendar();
			 currDate = new Date();
			 m_calendar.setTime(currDate);
		     m_dateFormat = new SimpleDateFormat("dd.MM.yyyy");

			
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu menu = new JMenu(bundle.getString("m_file"));
			menuBar.add(menu);
			

			JMenuItem item1 = new JMenuItem(bundle.getString("m_open"));
			item1.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File("."));
					
					MyFileFilter filter = new MyFileFilter();
						filter.addExtentions(".zip");
						filter.addExtentions(".rar");
						filter.addExtentions(".jar");
						filter.setDescription("Файлы архивов zip,rar,jar");
					
					chooser.setFileFilter(filter);
					int r = chooser.showOpenDialog(MainFrame.this);
					
					if(r == JFileChooser.APPROVE_OPTION){
						zipname = chooser.getSelectedFile().getPath();
						zipButt.setEnabled(true);
						moveButt.setEnabled(true);
						scanZipFile();
					}
				}
				
			});
			
			
			JMenuItem item = new JMenuItem(bundle.getString("m_exit"));
			item.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			
			menu.add(item1);
			menu.addSeparator();
			menu.add(item);
			menuBar.add(menu);
			
			
//	Panel  CENTER
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
			panel.setBorder(new CompoundBorder(
							new EtchedBorder(EtchedBorder.RAISED),
							new EmptyBorder(5,5,5,5)));
			
			
			//Панель для комбо чтоб он не был растянут как panel
			JPanel panel3 = new JPanel(new GridLayout(2,1));
			fileCombo = new JComboBox();
			fileCombo.setPreferredSize(new Dimension(200,22));
			fileCombo.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					loadZipFile((String)fileCombo.getSelectedItem());

					
				}
				
			});
			moveButt = new JButton("Move");
			moveButt.setEnabled(false);
			moveButt.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					comboBox.addItem((String)fileCombo.getSelectedItem());
					fileCombo.removeItem((String)fileCombo.getSelectedItem());
					
				}
				
			});
			
			mailButt = new JButton("Mail");
			mailButt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(mailFrame == null)
						mailFrame = new MailFrame(MainFrame.this, "Title",valUserName);
						
						mailFrame.setVisible(true);
						
				}
			});	
			
			but = new JButton("Table test");
			but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(report == null)
					report = new ExpenseReport();
						
				report.setVisible(true);
						
				}
			});	
			JPanel panel3_ = new JPanel();
			panel3_.add(fileCombo);
			panel3_.add(moveButt);
			panel3_.add(mailButt);
			panel3_.add(but);
			panel3.add(panel3_,"North");
			panel.add(panel3,"East");
			//
			
			//Panel 4
			JPanel panel4 = new JPanel();
			panel4.setLayout(new BoxLayout(panel4,BoxLayout.X_AXIS));
			panel.setBorder(new CompoundBorder(
					new EtchedBorder(EtchedBorder.RAISED),
					new EmptyBorder(2,2,2,2)));
			
			comboBox.setPreferredSize(new Dimension(200,22));
			
			zipButt = new JButton("Zip");
			zipButt.setEnabled(false);
			zipButt.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent arg0) {
						if (field.getText()!="")	
						SaveZipFile(comboBox, field.getText());
					}
					
				});
			
			JPanel panel4_ = new JPanel(new DialogLayout());
			JLabel labelF = new JLabel("File name:");
									
			panel4_.add(labelF);
			panel4_.add(field);
			
			panel4_.add(zipButt);
			panel4_.add(comboBox);
			
			panel4.add(panel4_,"North");
			panel3.add(panel4, "South");
			//	---
			
			textArea = new JTextArea("User name: "+userName.getText());
			panel.add(textArea,"West");
			
//	PANEL BOTOM
			JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			panel2.setBorder(new CompoundBorder(
							new EtchedBorder(EtchedBorder.RAISED),
							new EmptyBorder(1,1,1,1)));
			
			JLabel label =new JLabel(bundle.getString("date")+": ");
				label.setForeground(new Color(0,0,200));
			panel2.add(label);
			panel2.add(new JLabel(m_dateFormat.format(currDate)));
			
			panel2.add(userName);
			
			
			container.add(panel,"Center");
			container.add(panel2,"South");
			
			
	 }//End Constr
	
		
//	Загружает файл из архива в текстовую область
		public void loadZipFile(String name){
			try {
				ZipInputStream zipInput = new ZipInputStream( new FileInputStream(zipname));
				ZipEntry zipEntry;// = new ZipEntry(zipname);
				textArea.setText("");
				
				
				while ( (zipEntry = zipInput.getNextEntry()) !=null) {
				if (zipEntry.getName().equals(name)){	
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(zipInput));
					String line;
					
					while ((line = reader.readLine()) != null) {
						textArea.append(line);
						textArea.append("\n");
					}
				  }
					zipInput.closeEntry();
				}	
				zipInput.close();
			} catch (Exception e) {	}
			
		}
// /---

//	Заполняем combo файлами из архива
		public void scanZipFile(){
			fileCombo.removeAllItems();
			try {
				
				ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zipname));
				ZipEntry zipEntry;
				while ((zipEntry= zipInput.getNextEntry()) !=null){
					fileCombo.addItem(zipEntry.getName());
					zipInput.closeEntry();
				}
				zipInput.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
//	/---
//	Запись выбранных файлов в архив
	public void SaveZipFile(JComboBox list, String filename){
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ZipOutputStream zipOut = new ZipOutputStream(fileOut);
			
			for (int i = 0; i < list.getItemCount(); i++) {
				ZipEntry entry = new ZipEntry(list.getItemAt(i).toString());
				zipOut.putNextEntry(entry);
				zipOut.closeEntry();
			}
		 zipOut.close();		
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
 

}//End.


