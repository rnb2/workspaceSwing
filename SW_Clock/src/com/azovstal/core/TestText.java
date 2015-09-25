package com.azovstal.core;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ProgressMonitor;
import javax.swing.SpinnerListModel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import com.azovstal.core.button.AddButton;
import com.azovstal.test.other.InfiniteProgressPanel;

public class TestText extends AddButton {

	public TestText() {
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class MainFrame extends JFrame implements Serializable {
	private InfiniteProgressPanel infiniteProgressPanel = new InfiniteProgressPanel();
	public Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dimension = toolkit.getScreenSize();
	private int srcWidth = dimension.width - 300;
	private int srcHeight = dimension.height - 300;
	private JPanel panel = new JPanel(new BorderLayout(2, 2));
	private JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel panelBot = new JPanel();// new JPanel(new
	// FlowLayout(FlowLayout.LEFT));

	JTextField fieldMin, fieldHour, fieldSek;
	DocumentListener listener = new TextListener();
	ClockPanel clock;
	Font arialfont;
	JFormattedTextField textField;
	JCheckBox checkBox;
	private int FONT_SIZE = 14;

	private Date now = new Date();
	private JProgressBar m_progress;
	private SimplThread simplThread;
	private Timer timerProgress;
	private ProgressMonitor progressMonitor;
	
private int activ;
private JButton vesButton = new JButton("Вес");
private JTextField vesText;
private final static String[] inputStrings = new String[]{"0","1","2","3","4","5","6","7","8","9","."};


	/*
	 * private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy
	 * HH.mm"); private SimpleDateFormat hmFormat = new
	 * SimpleDateFormat("HH.mm.ss"); private JButton button = new
	 * JButton(dateFormat.format(date).toString());
	 */
	private JComboBox comboBox = new JComboBox();
	ActionListener lstCombo = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			textField.setText((String) comboBox.getSelectedItem());
			textField.setFont(new Font((String) comboBox.getSelectedItem(),
					Font.PLAIN, FONT_SIZE));
		}
	};

	public MainFrame() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		setTitle("Clock");
		UIManager.setLookAndFeel(UIManager
				.getCrossPlatformLookAndFeelClassName());
		setSize(srcWidth, srcHeight);
		setLocationRelativeTo(null);

		// разворачивает на весь экран
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		
		arialfont = new Font("Arial", Font.BOLD, FONT_SIZE);
		panel.setBorder(new EtchedBorder(BevelBorder.LOWERED));
		panelTop.setBorder(new EtchedBorder(BevelBorder.LOWERED));

		panelBot.setBorder(new CompoundBorder(new TitledBorder(
				new EtchedBorder(), "Personal Data"), new EmptyBorder(1, 5, 3,
				5)));
		// panelBot.setLayout(new DialogLayout());

		clock = new ClockPanel();
		String HH = new SimpleDateFormat("HH").format(now.getTime());
		String mm = new SimpleDateFormat("mm").format(now.getTime());
		String ss = new SimpleDateFormat("ss").format(now.getTime());
		int hours = Integer.parseInt(HH);
		int minutes = Integer.parseInt(mm);
		int sek = Integer.parseInt(mm);

		setClock(hours, minutes, sek);

		fieldHour = new JTextField(10);
		//fieldHour.putClientProperty(SwingUtilities2.AA_TEXT_PROPERTY_KEY, true);
		fieldHour.getDocument().addDocumentListener(listener);
		fieldHour.setFont(arialfont);
		fieldHour.setText(HH);
		panelTop.add(fieldHour);

		fieldMin = new JTextField(mm, 10);
		//fieldMin.putClientProperty(SwingUtilities2.AA_TEXT_PROPERTY_KEY, true);
		fieldMin.getDocument().addDocumentListener(listener);
		fieldMin.setFont(arialfont);
		panelTop.add(fieldMin);

		fieldSek = new JTextField(ss, 10);
		//fieldSek.putClientProperty(SwingUtilities2.AA_TEXT_PROPERTY_KEY, true);
		fieldSek.getDocument().addDocumentListener(listener);
		fieldSek.setFont(arialfont);
		panelTop.add(fieldSek);

		panel.add(panelTop, "North");
		panel.add(clock, "Center");

/*----------------*/
		Box inputVesBox = Box.createHorizontalBox();

		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));//
		//inputPanel.setPreferredSize(new Dimension(140,27));
		//inputPanel.setMaximumSize(getPreferredSize());
		inputPanel.setBackground(Color.GREEN);

		MaskFormatter rollNumberMask=null;
		try {
			rollNumberMask = new MaskFormatter("###.###");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rollNumberMask.setValidCharacters("0123456789");
		rollNumberMask.setPlaceholderCharacter('0');

//		NumberFormat rollNumberMask = NumberFormat.getInstance();
//		rollNumberMask.setMinimumIntegerDigits(1);
//		rollNumberMask.setMaximumIntegerDigits(3);
//		rollNumberMask.setMinimumFractionDigits(1);
//		rollNumberMask.setMaximumFractionDigits(3);
		
		
		 vesText =  new JTextField(7);//new JFormattedTextField(rollNumberMask);
		 vesText.setPreferredSize(new Dimension(55,25));
		 vesText.setMaximumSize(getPreferredSize());
		 vesText.setText("000.000");
		 vesText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		 
		 
		 final ArrayList<Integer> arrKeCode = new ArrayList<Integer>();
		 arrKeCode.add(97);
		 arrKeCode.add(98);
		 //Integer[] arrKeCode = new Integer[]{97,98};
		
		 vesText.addFocusListener(new FocusAdapter() {
			 public void focusGained(FocusEvent e) {
				 vesText.selectAll();
			 }
		 });
		 vesText.addKeyListener(new KeyListener(){
			 public void keyPressed(KeyEvent e) {
				System.out.println("keyPressed ");
				vesText.setForeground(Color.BLACK);
				 if (e.getKeyCode()==110 && e.getKeyChar()==','){
					 System.err.println("keyPressed c="+vesText.getText());
					 	vesText.setText(vesText.getText()+'.');
				}
				
				if(e.getKeyCode()==10){
					vesButton.doClick();
				}
			}
			 public void keyReleased(KeyEvent e) {
				 System.out.println("keyReleased");				
			}
			 public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()==',' || !getInputCharacter(inputStrings).contains(e.getKeyChar())){
					System.out.println("keyTyped");
					e.consume();
				}
			}
		 });
		inputPanel.add(vesButton);
		inputPanel.add(Box.createHorizontalStrut(1));
		inputPanel.add(vesText);	
		inputVesBox.add(inputPanel);
		
		vesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(vesText.getText()!= "" || vesText.getText()!=null){
					System.err.println("text=" + vesText.getText());
					Double d = Double.valueOf(vesText.getText());
					if (d<=120.00){
						JOptionPane.showMessageDialog(null,d);
					}else{
						vesText.setForeground(Color.RED);
					}
					
				}
				
			}
		});

/*----------------*/		
		checkBox = new JCheckBox();

		checkBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				textField.setEnabled(checkBox.isSelected());
				textField.grabFocus();
			}
		});
		panelBot.add(checkBox);

		panelBot.add(new JLabel("Name:"));

		textField = new JFormattedTextField(new fieldNameFormat());
		textField.setFont(arialfont);
		textField.setColumns(15);
		textField.setEnabled(false);
		panelBot.add(textField);

		comboBox.addItem("Serif");
		comboBox.addItem("SansSerif");
		comboBox.addItem("Monospaced");
		comboBox.addItem("Dialog");
		comboBox.addItem("DialogInput");

		comboBox.addActionListener(lstCombo);
		panelBot.add(comboBox);

		String[] font = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();
		JSpinner spinner = new JSpinner(new SpinnerListModel(font));

		spinner.setMinimumSize(new Dimension(200, 22));
		spinner.setPreferredSize(new Dimension(200, 22));
		panelBot.add(spinner);

		Box box = Box.createHorizontalBox();
		box.setBorder(new TitledBorder("HorizontalBox"));
		box.add(new JLabel("labelBox"));
		box.add(Box.createGlue());
		box.add(new JTextField("text2", 15));
		panelBot.add(box, "East");
		
		panelBot.add(inputVesBox);
		// 04.10.2007
		m_progress = new JProgressBar();
		m_progress.setStringPainted(true);
		m_progress.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.white,
				Color.gray));
		m_progress.setMinimum(0);
		JPanel p1 = new JPanel(new BorderLayout());
		p1.setToolTipText("Test Progress Bar");
		p1.setBorder(new TitledBorder("Test Progress Bar"));

		p1.add(m_progress, BorderLayout.CENTER);
		ImageIcon im = new ImageIcon(getClass().getResource("/com/azovstal/core/loading.gif"));
		final JButton button = new JButton("Start",im);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m_progress.setMaximum(200);
				m_progress.setIndeterminate(true);
				simplThread = new SimplThread(1000);
				simplThread.start();
				progressMonitor = new ProgressMonitor(MainFrame.this,
						"Waiting for Simulated Activity", null, 0, simplThread
								.getTarget());
				progressMonitor.setMinimum(0);
				progressMonitor.setMaximum(200);
				timerProgress.start();
			  // setCursor(new Cursor(Cursor.WAIT_CURSOR));
				createCustomCurs();
			}
		});
		p1.add(button, BorderLayout.EAST);
		
		final JTextField testText = new JTextField("56");
		//testText.putClientProperty(SwingUtilities2.AA_TEXT_PROPERTY_KEY, true);
		JButton button2 = new JButton("<html><i>Test</i>");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				progressMonitor = new ProgressMonitor(MainFrame.this,
//						"Waiting for Simulated Activity", null, 0,activ);
//				progressMonitor.setMinimum(0);
//				progressMonitor.setMaximum(1000);
				
//				button.doClick();
//				setGlassPane(infiniteProgressPanel);
//				SplashUgdt splashUgdt= new SplashUgdt(MainFrame.this);
//				splashUgdt.start();
				
				
				button.doClick();
				setGlassPane(infiniteProgressPanel);
				infiniteProgressPanel.start(getWidth(), getHeight());
				//infiniteProgressPanel.start();
				//final String s = testText.getText().trim(); 
				//checkNumWaggon(s);
			}
		});
		//p1.add(button2);
		
		timerProgress = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int curr = simplThread.getCurrent();
				m_progress.setValue(curr);
				progressMonitor.setProgress(curr);

				if (curr == simplThread.getTarget()) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					timerProgress.stop();
					infiniteProgressPanel.stop(getWidth(), getHeight());
					
				}
				if (curr == simplThread.getCurrent()
						&& progressMonitor.isCanceled()) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					timerProgress.stop();
					infiniteProgressPanel.stop(getWidth(), getHeight());
					
				}
			}
		});
		panelBot.add(p1);
		panelBot.add(testText);
		panelTop.add(button2);
		// ---/

		panel.add(panelBot, "South");

		// --- PopUpMenu
		final JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem item = new JMenuItem("Cut");
		item.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				textField.grabFocus();
				textField.getSelectedText();
			}

		});

		final Action copyAction = new AbstractAction("Copy") {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("-------copyAction");
			}

		};

		JMenuItem item2 = new JMenuItem("Paste");
		item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_MASK));

		popupMenu.add(item);
		popupMenu.add(copyAction);
		popupMenu.add(item2);

		panel.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent arg0) {
				if (arg0.isPopupTrigger()) {
					popupMenu.show(arg0.getComponent(), arg0.getX(), arg0
							.getY());
				}
			}
		});
		// ---

		// Панель стилей
		ButtPanel buttPanel = new ButtPanel();
		buttPanel.setBorder(new TitledBorder(new EtchedBorder(),
				"Панель стилей"));
		getContentPane().add(buttPanel, "South");
		// ------
		getContentPane().add(panel, "Center");

		Timer timer = new Timer(1000, new TimerAction());
		timer.start();

	}
	private ArrayList<Character> getInputCharacter(String...parameters){
		ArrayList<Character> rslt = new ArrayList<Character>();
			for(String s: parameters){
				rslt.add(new Character(s.charAt(0)));
			}
	 return rslt;	
	}
	private boolean checkNumWaggon(String numWaggon){
		boolean retval = false;
			if(numWaggon.length()==2){
				String validS = "00"+numWaggon;
				if (validS.equals("0056")){
					System.out.println("budukh-rn-----27.03.2008-----2 "+numWaggon + " = 0056");
				}
			}else if(numWaggon.length()==3){
				String validS = "0"+numWaggon;
				if (validS.equals("0056")){
					System.out.println("budukh-rn-----27.03.2008-----3 "+numWaggon + " = 0056");
				}
			}
		return retval;
	}
	private void createCustomCurs(){
		Cursor c;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.getImage(getClass().getResource("/com/azovstal/core/loading.gif"));
		ImageIcon im  = new ImageIcon(getClass().getResource("/com/azovstal/core/l.gif"));//"C:\\Windows\\Cursors\\horse.ani"
		
		try {
			File file = new File(getClass().getResource("/com/azovstal/core/loading.gif").getFile());
			//BufferedImage imB = ImageIO.read(getClass().getResourceAsStream("loading.gif"));
			//BufferedImage imB = ImageIO.read(getClass().getResource("loading.gif"));
			BufferedImage imB = ImageIO.read(file);

			c = tk.createCustomCursor(imB, new Point(0,0),"cursorName");

			setCursor(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// ---Show Error
	public void showError(Exception ex, String title, String message) {
		ex.printStackTrace();
		JOptionPane.showMessageDialog(this, message, title,
				JOptionPane.WARNING_MESSAGE);
	}

	// ---

	public void setClock(int hours, int minutes, int sek) {
		try {
			clock.setTime(hours, minutes, sek);

		} catch (NumberFormatException e) {
			new ShowErrors(e, panel, "Error", "Введите правильно значение");
			// showError(e,"Error", "Введите правильно значение");
			// JOptionPane.showConfirmDialog(panel,
			// "Введите правильно значение",
			// "Ошибка",
			// JOptionPane.PLAIN_MESSAGE,
			// JOptionPane.ERROR_MESSAGE);
		}
	}

//-------- Панель стилей
	class ButtPanel extends JPanel {

		public ButtPanel() {
			UIManager.LookAndFeelInfo[] infos = UIManager
					.getInstalledLookAndFeels();
	
		boolean found= false; 
		for (int i = 0; i < infos.length; i++) {
				if (infos[i].getName().equals("JGoodies Plastic 3D") || infos[i].getName().equals("JGoodies Plastic XP")) {
					found = true;
				}
		}
		if (!found) {
			UIManager.installLookAndFeel("JGoodies Plastic 3D",
					"com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
			UIManager.installLookAndFeel("JGoodies Plastic",
			"com.jgoodies.looks.plastic.PlasticLookAndFeel");
			UIManager.installLookAndFeel("JGoodies Plastic XP",
					"com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
		}

		try {
			UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}
		 infos = UIManager
			.getInstalledLookAndFeels();
		 

		 for (UIManager.LookAndFeelInfo info : infos){
			makeButton(info.getName(), info.getClassName());
			System.out.println("info.getName()="+info.getName() +" info.getClassName()=" + info.getClassName());
		 };

	}

		void makeButton(String name, final String plafName) {
			
			   new AddButton(name,ButtPanel.this, new ActionListener() 
			   {
				public void actionPerformed(ActionEvent arg0) {
					try {
						UIManager.setLookAndFeel(plafName);

						// Обновляем панель
						SwingUtilities.updateComponentTreeUI(ButtPanel.this);
						SwingUtilities.updateComponentTreeUI(panel);
					} catch (Exception e) {
					}
				}

			});
		}
	}

// ------- Пнаель стилей

	class TimerAction implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			Date now = new Date();
			int hours = Integer.parseInt(new SimpleDateFormat("HH").format(now
					.getTime()));
			int minutes = Integer.parseInt(new SimpleDateFormat("mm")
					.format(now.getTime()));
			int sek = Integer.parseInt(new SimpleDateFormat("ss").format(now
					.getTime()));
			String HH = new SimpleDateFormat("HH").format(now.getTime());
			String mm = new SimpleDateFormat("mm").format(now.getTime());
			String ss = new SimpleDateFormat("ss").format(now.getTime());
			setClock(hours, minutes, sek);
			fieldHour.setText(HH);
			fieldMin.setText(mm);
			fieldSek.setText(ss);
		}
	}

	private class TextListener implements DocumentListener {

		public void changedUpdate(DocumentEvent arg0) {
			// setClock();
		}

		public void insertUpdate(DocumentEvent arg0) {
			// setClock();
		}

		public void removeUpdate(DocumentEvent arg0) {

		}

	}

	class ClockPanel extends JPanel implements Serializable {

		private double radius = 100;
		private double minutes = 0;
		private double sek = 0;
		private double hourHandLenght = 0.6 * radius;
		private double minHandLenght = 0.8 * radius;
		private double sekHandLenght = 0.8 * radius;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			Ellipse2D ellipse = new Ellipse2D.Double(10, 10, 2 * radius,
					2 * radius);

			g2.setColor(new Color(0, 175, 0));
			g2.draw(ellipse);

			g2.setColor(Color.blue);
			double hourA = Math.toRadians(90 - 360 * minutes / (12 * 60));
			drawStrelki(g2, hourA, hourHandLenght);

			double minA = Math.toRadians(90 - 360 * minutes / 60);
			drawStrelki(g2, minA, minHandLenght);

			g2.setColor(Color.red);

			double sekA = Math.toRadians(90 - 360 * sek / 60);
			drawStrelki(g2, sekA, sekHandLenght);

		}

		/*
		 * @param h hours @param m minutes
		 */
		public void setTime(int h, int m, int s) {
			minutes = h * 60 + m;
			sek = s;
			repaint();
		}

		public void drawStrelki(Graphics2D g2, double angle, double lenghtHand) {
			Point2D end = new Point2D.Double(radius + lenghtHand
					* Math.cos(angle), radius - lenghtHand * Math.sin(angle));
			Point2D center = new Point2D.Double(radius, radius);
			Line2D line = new Line2D.Double(center, end);

			g2.draw(line);
		}
	}

	class SplashUgdt extends JDialog{
		InfiniteProgressPanel pane;
		public SplashUgdt(JFrame owner){
			
			super(owner);
			ImageIcon im = new ImageIcon(getClass().getResource("/com/azovstal/core/loading.gif"));
			Toolkit tk = Toolkit.getDefaultToolkit();
			//Image im = tk.getImage(getClass().getResource("/com/azovstal/core/loading.gif"));
			
			JPanel mainPanel = new JPanel();
			JLabel label = new JLabel(im);
			//mainPanel.add(label);

			 pane = new InfiniteProgressPanel("Wait...");
			//owner.setGlassPane(pane);
			getContentPane().add(pane, BorderLayout.CENTER);
			
			setSize(200, 200);	
			
			setLocationRelativeTo(owner);
			//setModal(true);
			//setResizable(false);
			setVisible(true);
		}
		public void start(){
			pane.start(getWidth(), getHeight());
		}
		public void stop(){
			pane.stop(getWidth(), getHeight());
		}
	}
	// Форматирование имен фамил (Перв букв ПРОПИСНАЯ)
	class fieldNameFormat extends Format {

		@Override
		public StringBuffer format(Object object, StringBuffer buffer,
				FieldPosition fieldPosition) {
			fieldPosition.setBeginIndex(buffer.length());
			String str = object.toString();
			char prevchar = ' ';
			for (int i = 0; i < str.length(); i++) {
				char nextchar = str.charAt(i);
				if (Character.isLetter(nextchar) && prevchar == ' ')
					nextchar = Character.toTitleCase(nextchar);

				buffer.append(nextchar);
				prevchar = nextchar;

			}
			return buffer;
		}

		@Override
		public Object parseObject(String text, ParsePosition position) {
			position.setIndex(position.getIndex() + text.length());
			return text;
		}

	}

	// -----

	// Поток имитируемого действия
	class SimplThread extends Thread {
		private int current;
		private int target;

		public SimplThread(int i) {
			current = 0;
			target = i;
		}

		public void run() {
			try {
				while (current < target && !interrupted()) {
					// while (fieldMin.getText()!="00" && !interrupted() ){
					sleep(50);
					current++;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public int getCurrent() {
			return current;
		}

		public int getTarget() {
			return target;
		}
	}
	// ---/

	public int getActiv() {
		return activ;
	}

	public void setActiv(int activ) {
		this.activ = activ;
	}
}
