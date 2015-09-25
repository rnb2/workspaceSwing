/**
 * 
 */
package com.rnb2.swApp;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel;

import com.rnb2.swApp.resource.FactoryImage;
import com.rnb2.swApp.utils.SwingUtil;

/**
 * 
 * @author budukh-rn
 *
 */
public class SwAppWindow {

	private JFrame frame;
	

	/**Название*/
	private JTextField titleMusicField =  new JTextField();

	/**Краткое описание - Картинка*/
	private JTextField shortNewsMusicField =  new JTextField();
	/**Исполнитель*/
	private JTextField artistField =  new JTextField();
	/**Дата*/
	private JTextField dataField =  new JTextField();
	/**Жанр*/
	private JTextField geneField =  new JTextField();
	private Map<String,String> bitRateMap = new HashMap<String, String>();
	/**Качество Mp3 /256 kbps / 44.1KHz*/
	private JTextField bitRateField =  new JTextField();
	/**Качество Mp3 /256 kbps / 44.1KHz*/
	private JComboBox bitRateComboField;
	/**Трэки*/
	private JTextField trackField =  new JTextField();
	/**Размер*/
	private JTextField sizeField =  new JTextField();

	/**Плей лист*/
	private JTextArea playListTxtArea;
	/**Ссылки на Rapid*/
	private JTextArea mirrorRapidTextArea;
	/**Зеркало*/
	private JTextField mirrorField1 =  new JTextField();
	/**Зеркало*/
	private JTextField mirrorField2 =  new JTextField();
	/**Зеркало*/
	private JTextField mirrorField3 =  new JTextField();
	
	/**Краткое описание - Картинка*/
	private JTextField shortNewsMagazineField =  new JTextField();
	/**Название*/
	private JTextField titleMagazineField = new JTextField();
	/**Формат*/
	private JTextField formatField = new JTextField();
	/**Страниц*/
	private JTextField pageField = new JTextField();
	/**Качество*/
	private JTextField qualityField = new JTextField();
	/**Размер*/
	private JTextField sizeMagazineField =  new JTextField();
	/**Язык*/
	private JTextField langField = new JTextField();
	
	/**Зеркало*/
	private JTextField mirrorMagazineField1 =  new JTextField();
	/**Зеркало*/
	private JTextField mirrorMagazineField2 =  new JTextField();
	/**Зеркало*/
	private JTextField mirrorMagazineField3 =  new JTextField();

	
	
	/**Результат*/
	private JTextArea resultArea;
	
	private JTextField[] jTextFields;
	private JTextField[] jTextMagazineFields;
	
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtil.setupSubstance(new SubstanceMarinerLookAndFeel());
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwAppWindow window = new SwAppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwAppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Епидем шаблонизатор 1.0");
		frame.setBounds(100, 100, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initVar();
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setRollover(true);
		
		actionClear.putValue(Action.SHORT_DESCRIPTION, "Очистить форму");
		actionGoShort.putValue(Action.SHORT_DESCRIPTION, "Формировать для 'Краткое описание'");
		actionGo.putValue(Action.SHORT_DESCRIPTION, "Формировать для 'Полное описание'");
		actionCopyToClipBoard.putValue(Action.SHORT_DESCRIPTION, "Копировать в буфер");
		toolBar.add(actionClear);
		toolBar.add(actionGoShort);
		toolBar.add(actionGo);
		toolBar.addSeparator();
		toolBar.add(actionCopyToClipBoard);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel verticalPanel = SwingUtil.createVerticalPanel();
		verticalPanel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
		JPanel titlePanel = createNameFieldPanel("Название:",titleMusicField);
		JPanel shortNewsPanel = createNameFieldPanel("Картинка (URL):",shortNewsMusicField);
		JPanel artistPanel = createNameFieldPanel("Исполнитель:",artistField);
		JPanel dataPanel = createNameFieldPanel("Дата:",dataField);
		JPanel genePanel = createNameFieldPanel("Жанр:",geneField);
		JPanel bitPanel = createNameFieldPanel("Качество:",bitRateComboField);
		JPanel trackPanel = createNameFieldPanel("Трэки:",trackField);
		JPanel sizePanel = createNameFieldPanel("Размер:",sizeField);
		
		JPanel playListPanel = SwingUtil.createHorizontalPanel();
		JLabel playListLabel = new JLabel("Плейлист:");
		playListPanel.add(playListLabel);
		playListPanel.add(Box.createHorizontalStrut(6));
		playListTxtArea = new JTextArea();
		playListTxtArea.setRows(10);
		JScrollPane scrPlayList = new JScrollPane(playListTxtArea);
		playListPanel.add(scrPlayList);
		
		JPanel mirrorPanelRapid = SwingUtil.createHorizontalPanel();
		JLabel mirrorRapidLabel = new JLabel("Рапида:");
		mirrorPanelRapid.add(mirrorRapidLabel);
		mirrorPanelRapid.add(Box.createHorizontalStrut(12));
		mirrorRapidTextArea = new JTextArea();
		mirrorRapidTextArea.setRows(5);
		JScrollPane scrmirrorRapid = new JScrollPane(mirrorRapidTextArea);
		mirrorPanelRapid.add(scrmirrorRapid);

		JPanel mirrorPanel1 = createNameFieldPanel("Зеркало:",mirrorField1);
		JPanel mirrorPanel2 = createNameFieldPanel("Зеркало:",mirrorField2);
		JPanel mirrorPanel3 = createNameFieldPanel("Зеркало:",mirrorField3);
		
		JPanel resultPanel = SwingUtil.createHorizontalPanel();
		JLabel resultLabel = new JLabel("Рез-т:");
		resultPanel.add(resultLabel);
		resultPanel.add(Box.createHorizontalStrut(12));
		resultPanel.add(resultArea);
		
		verticalPanel.add(titlePanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(shortNewsPanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(new JSeparator(JSeparator.HORIZONTAL));
		verticalPanel.add(Box.createVerticalStrut(12));
		verticalPanel.add(artistPanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(dataPanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(genePanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(bitPanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(trackPanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(sizePanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(playListPanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(mirrorPanelRapid);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(mirrorPanel1);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(mirrorPanel2);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(mirrorPanel3);
		//verticalPanel.add(Box.createVerticalStrut(12));
		//verticalPanel.add(resultPanel);
		
		SwingUtil.setGroupAlignmentX(new JComponent[] { titlePanel, shortNewsPanel, artistPanel, dataPanel, genePanel, bitPanel, trackPanel, sizePanel, 
													playListPanel, mirrorPanelRapid, mirrorPanel1, mirrorPanel2, mirrorPanel3,  resultPanel }, Component.LEFT_ALIGNMENT);
		jTextFields = new JTextField[]{titleMusicField, shortNewsMusicField, artistField, dataField, geneField, trackField, sizeField, mirrorField1, mirrorField2, mirrorField3};
		setSameSize(jTextFields);
		
		tabbedPane.addTab("Музыка", verticalPanel);
		tabbedPane.addTab("Журналы", createMagazinePanel());
		tabbedPane.addChangeListener(tabbChangeListener());
		
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		SwingUtil.setFocusByEnterKey(frame);
		SwingUtil.setToolBarComponentsFocusDisable(toolBar);
	}



	private void initVar(){
		resultArea = new JTextArea();
		bitRateComboField = new JComboBox(Bitrate.values());
	}

	private JPanel createMagazinePanel(){
		JPanel verticalPanel = SwingUtil.createVerticalPanel();
		//verticalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		verticalPanel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
		JPanel titlePanel = createNameFieldPanel("Название:",titleMagazineField);
		JPanel shortNewsPanel = createNameFieldPanel("Картинка (URL):",shortNewsMagazineField);
		JPanel pagePanel = createNameFieldPanel("Страниц:",pageField);
		JPanel formatPanel = createNameFieldPanel("Формат:",formatField);
		JPanel qualityPanel = createNameFieldPanel("Качество:",qualityField);
		JPanel sizePanel = createNameFieldPanel("Размер файла:",sizeMagazineField);
		JPanel langPanel = createNameFieldPanel("Язык:",langField);
		
		JPanel mirrorPanel1 = createNameFieldPanel("Зеркало:",mirrorMagazineField1);
		JPanel mirrorPanel2 = createNameFieldPanel("Зеркало2:",mirrorMagazineField2);
		JPanel mirrorPanel3 = createNameFieldPanel("Зеркало3:",mirrorMagazineField3);
		
		verticalPanel.add(titlePanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(shortNewsPanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(new JSeparator(JSeparator.HORIZONTAL));
		verticalPanel.add(Box.createVerticalStrut(12));
		verticalPanel.add(pagePanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(formatPanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(qualityPanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(sizePanel);
		verticalPanel.add(Box.createVerticalStrut(6));
		verticalPanel.add(langPanel);
		verticalPanel.add(Box.createVerticalStrut(12));
		verticalPanel.add(mirrorPanel1);
		verticalPanel.add(Box.createVerticalStrut(12));
		verticalPanel.add(mirrorPanel2);
		verticalPanel.add(Box.createVerticalStrut(12));
		verticalPanel.add(mirrorPanel3);
		
		SwingUtil.setGroupAlignmentX(new JComponent[] { titlePanel, shortNewsPanel, pagePanel, formatPanel, qualityPanel, sizePanel, langPanel, mirrorPanel1, mirrorPanel2, mirrorPanel3}, Component.LEFT_ALIGNMENT);
		jTextMagazineFields = new JTextField[]{titleMagazineField, shortNewsMagazineField, pageField, formatField, qualityField, sizeMagazineField, langField, mirrorMagazineField1, mirrorMagazineField2, mirrorMagazineField3};
		setSameSize(jTextMagazineFields);
		return verticalPanel;
	}
	
	private void setSameSize(JTextField[] fields){
		for (JTextField jTextField : fields) {
			SwingUtil.fixTextFieldSize(jTextField);
		}
	}

	private ChangeListener tabbChangeListener() {
		ChangeListener listener = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				  System.out.println("Tab: " + tabbedPane.getSelectedIndex());
			}
		};//// TODO Auto-generated method stub
		
		return listener;
	}

	final Action actionPasteFromClipBoard = new AbstractAction("", FactoryImage.getInstance().ICON_PASTE_16){
		public void actionPerformed(ActionEvent e) {
			String fromClipBoard = SwingUtil.getClipboard();
		}
	};
	
	final Action actionCopyToClipBoard = new AbstractAction("", FactoryImage.getInstance().ICON_COPY_16){
		public void actionPerformed(ActionEvent e) {
			SwingUtil.setClipboard(resultArea.getText());
		}
	};
	
	final Action actionGoShort = new AbstractAction("", FactoryImage.getInstance().ICON_GOTOOBJ_16) {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			
			StringBuffer sb = new StringBuffer();
			sb.append("[center][img]");
			if(tabbedPane.getSelectedIndex()==0){
				sb.append(shortNewsMusicField.getText());
			}else if(tabbedPane.getSelectedIndex()==1){
				sb.append(shortNewsMagazineField.getText());
			}
			sb.append("[/img][/center]");
			
			resultArea.setText(sb.toString());
			resultArea.selectAll();
			resultArea.grabFocus();
			
		}
	};
	final Action actionGo = new AbstractAction("", FactoryImage.getInstance().ICON_COPY_R_CO_16){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			StringBuffer sb = new StringBuffer();
			sb.append("[center][img]");
			sb.append(shortNewsMusicField.getText());
			sb.append("[/img][/center]");

			sb.append("<br>");
			sb.append("[b]Исполнитель[/b]: ");
			sb.append(artistField.getText());

			sb.append("<br>");
			sb.append("[b]Дата[/b]: "+dataField.getText());
			
			sb.append("<br>");
			sb.append("[b]Жанр[/b]: "+geneField.getText());
			
			sb.append("<br>");
			sb.append("[b]Качество[/b]: "+bitRateComboField.getSelectedItem().toString());
			
			if(trackField.getText()!=null && trackField.getText().length()>0){
				sb.append("<br>");
				sb.append("[b]Трэки[/b]: " + trackField.getText());
			}
			
			sb.append("<br>");
			sb.append("[b]Размер[/b]: "+sizeField.getText()+" Mb");
			
			sb.append("<br>");
			sb.append("[spoiler]");
			sb.append("<br>");
			sb.append("<br>");
			sb.append(playListTxtArea.getText());
			sb.append("<br>");
			sb.append("[/spoiler]");
			
			sb.append("<br>");
			sb.append("[quote][b]Скачать "+titleMusicField.getText() + "[/b]");
			//Rapida
			sb.append("<br>");
			sb.append("[b]");
			sb.append(mirrorRapidTextArea.getText());
			sb.append("[/b]");

			//Mirrors			
			addMirrors(sb, mirrorField1);
			addMirrors(sb, mirrorField2);
			addMirrors(sb, mirrorField3);
			
			sb.append("[/quote]");
			
			resultArea.setText(sb.toString());
			resultArea.selectAll();
			resultArea.grabFocus();
		}
	};
	final Action actionClear = new AbstractAction("", FactoryImage.getInstance().ICON_CLEAR_16){
		private static final long serialVersionUID = 280095686770366458L;

		public void actionPerformed(ActionEvent e) {
			if(tabbedPane.getSelectedIndex()==0){
				for(JTextField field: jTextFields){
					field.setText("");
				}
				resultArea.setText("");
				titleMusicField.grabFocus();
			}else if(tabbedPane.getSelectedIndex()==1){
				for(JTextField field: jTextMagazineFields){
					field.setText("");
				}
				resultArea.setText("");
				titleMagazineField.grabFocus();
			}
		}
	};





	private StringBuffer addMirrors(StringBuffer sb, JTextField field){
			if(field.getText()!=null && field.getText().length()>0){
				sb.append("<br>");
				sb.append("[b]Зеркало[/b]");
				sb.append("<br>");
				sb.append(field.getText());
			}
		return sb;
	}
	public JPanel createNameFieldPanel(String labelName, JComponent textField, Dimension preferedSize){
		JPanel panel = SwingUtil.createHorizontalPanel();
		JLabel nameLabel = new JLabel(labelName);
		panel.add(nameLabel);
		panel.add(Box.createHorizontalStrut(20));
		textField.setPreferredSize(preferedSize);
		panel.add(textField);
		return panel;
	}
	public JPanel createNameFieldPanel(String labelName, JComponent textField){
		JPanel panel = SwingUtil.createHorizontalPanel();
		JLabel nameLabel = new JLabel(labelName);
		panel.add(nameLabel);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(textField);
		return panel;
	}
	
	

}
