package com.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//import org.hibernate.transform.ToListResultTransformer;

//import ugdt.core.entity.CarriageType;
//import ugdt.core.test.parent.WinParent;

/*
public class Winspr1 extends WinParent {

	protected Person[] m_employees = {
			new Person("John", "Smith", "111-1111"),
			new Person("Silvia", "Glenn", "222-2222"),
			new Person("Captain", "Kirk", "333-3333"),
			new Person("Duke", "Nukem", "444-4444"),
			new Person("James", "Bond", "000-7777")
		};
	protected JList      m_list;
	protected JTextField m_firstTxt = new JTextField(20);
	protected JTextField m_lastTxt = new JTextField(20);
	protected JTextField m_phoneTxt = new JTextField(20);
	protected JTextField combo_Out = new JTextField(20);
	private GridBagConstraints constraints = new GridBagConstraints();
	
	private JComboBox comboBox = new JComboBox();
	ActionListener lstCombo = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			comboBox.getSelectedItem();
		}
	};

	
	class Person {
		public String m_firstName;
		public String m_lastName;
		public String m_phone;
		
		public Person(String firstName, String lastName, String phone) {
			m_firstName = firstName;
			m_lastName = lastName;
			m_phone = phone;
		}
		
		public String toString() {
			String str = m_firstName+" "+m_lastName;
			if (m_phone.length() > 0)
				str += " ("+m_phone+")";
			return str.trim();
		}
	}

	public Person getSelectedPerson() {
		return (Person)m_list.getSelectedValue();
	}


	
	public Winspr1(MainWin owner) {
		super("Справочник 1", owner);
		//JPanel content = new JPanel();
		//content.setLayout(new GridBagLayout());
	
		JPanel p4 = new JPanel();
	    JPanel p4c = new JPanel();
		    p4c.setLayout(new GridLayout(1, 2, 5, 5));
		        

		    JButton b1 = new JButton("Search");
		    b1.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					m_list.add(m_firstTxt.getText(),m_list);
					m_list.repaint();
					
				}
		    	
		    });
		    p4c.add(b1);
		        
		        
		    JButton b3 = new JButton("Exit");
		    p4c.add(b3);

		    p4.add(p4c);
		   // getContentPane().add(p4, BorderLayout.EAST);

		    
		JPanel p2 = new JPanel(new BorderLayout());
		    p2.setBorder(new TitledBorder(new EtchedBorder(), 
		      "Table Name"));
		    m_list = new JList(m_employees);
		    JScrollPane ps = new JScrollPane(m_list);
		    p2.add(ps, BorderLayout.CENTER);

		 
		getContentPane().add(p2, BorderLayout.CENTER);
		
		 
		 
		JPanel p1 = new JPanel();
		    p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));

		    JPanel content = new JPanel();
		    content.setBorder(new EmptyBorder(10, 10, 10, 10));
		    content.setLayout(new GridLayout(4, 2, 3, 3));
		    
		setSize(owner.getWidth()-10, owner.getHeight()-55);

		content.add(new JLabel("Имя:"), constraints);
		content.add(m_firstTxt, constraints);

		content.add(new JLabel("Фамилия:"), constraints);
		content.add(m_lastTxt, constraints);

		content.add(new JLabel("Телефон:"), constraints);
		content.add(m_phoneTxt, constraints);

		content.add(new JLabel("Тип подвижного средства"), constraints);

		comboBox.addItem("Item 1");
		comboBox.addItem("Item 2");
		comboBox.addItem("Item 3");
		
		for(Person t : m_employees){
			comboBox.addItem(t);
		}
		comboBox.setPrototypeDisplayValue(new String("XX"));
		comboBox.addActionListener(lstCombo);
		content.add(comboBox, constraints);
		
		
		
		p1.add(content,BorderLayout.CENTER);
	    p1.add(p4, BorderLayout.SOUTH);

	 //Панель стилей
	    ButtPanel  buttPanel = new ButtPanel();
	    buttPanel.setBorder(new TitledBorder(new EtchedBorder(),"Панель стилей"));
	    p1.add(buttPanel);
	//------    
	    getContentPane().add(p1, BorderLayout.SOUTH);
	    	
		
	//Split
	final JSplitPane spHor = new JSplitPane(
			      JSplitPane.VERTICAL_SPLIT, p1, p2);
			spHor.setDividerSize(8);
			//spHor.setDividerLocation((owner.getHeight()+70)/2);
			spHor.setDividerLocation(120);
			spHor.setContinuousLayout(true);
			spHor.setOneTouchExpandable(true);
	  getContentPane().add(spHor, BorderLayout.CENTER);
	//---- 
	  
	//Лисенер на лист 
	m_list.addListSelectionListener(new ListChange());
		
		//show();
	setVisible(true);
	}
	
//Лисенер на лист
	class ListChange implements ListSelectionListener{

		public void valueChanged(ListSelectionEvent arg0) {
			Person ps = getSelectedPerson();
			
			m_firstTxt.setText(ps.m_firstName);
			m_firstTxt.grabFocus();			
			m_lastTxt.setText(ps.m_lastName);
			m_phoneTxt.setText(ps.m_phone);
			
		}
	}
//	-----

//Панель стилей	
	class ButtPanel extends JPanel{

		public ButtPanel() {
			UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
			for (UIManager.LookAndFeelInfo info: infos)
				makeButton(info.getName(), info.getClassName());
		}
		
		void makeButton(String name, final String plafName){
			 JButton button = new JButton(name);
			 add(button);
			 
			 button.addActionListener(new ActionListener(){
	
				public void actionPerformed(ActionEvent arg0) {
					try{
						UIManager.setLookAndFeel(plafName);
						
						//Обновляем панель
						SwingUtilities.updateComponentTreeUI(ButtPanel.this);
					}catch(Exception e){}
				}
				 
			 });
		}
	}
//-------
	
}
*/