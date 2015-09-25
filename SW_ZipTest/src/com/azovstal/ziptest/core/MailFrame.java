package com.azovstal.ziptest.core;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class MailFrame extends JDialog {
	
	private JTextField from;
	private JComboBox to;
	private JTextField smtp;
	private JTextField subj;
	private JTextArea area;
	private JTextArea communication;
	private JButton send; 
	private PrintWriter out;
	private BufferedReader in;
	private static final String valFrom = "@azovstal.com.ua";
	private JPanel panel;

	public MailFrame(JFrame owner, String title, String username){
		super(owner,"Mail panel",true);
		setSize(owner.getWidth()-10,owner.getHeight()-75);
		Container container = getContentPane();
		setLocationRelativeTo(owner);
		//setPreferredSize(new Dimension(owner.getWidth(),owner.getHeight()));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		
		
		panel = new JPanel(new GridBagLayout());
		
		
		//panel.setLayout(new DialogLayout());
		
		Border etch = BorderFactory.createEmptyBorder(5, 5, 5, 5);//LineBorder(Color.darkGray, 1);
		panel.setBorder(etch);

		gbc.weightx = 0;
		addtoGrid(new JLabel("From:"), gbc, 0, 0, 1, 1) ;
		//panel.add(new JLabel("From: "),gbc);
		from = new JTextField(username+valFrom,20);
		gbc.weightx = 100;
		
		//panel.add(from,gbc);
		addtoGrid(from, gbc, 1, 0, 1, 1) ;
		
		gbc.weightx = 0;
		//panel.add(new JLabel("To: "));
		addtoGrid(new JLabel("To: ") , gbc, 0, 1, 1, 1) ;
		gbc.weightx = 100;

		to = new JComboBox();
		to.addItem(username+valFrom);
		to.addItem("reshetnikov-sn"+valFrom);
		to.addItem("daduev-ad"+valFrom);
		to.addItem("albulov-an"+valFrom);
		to.addItem("burlachenko-ev"+valFrom);
		to.addItem("budukh-rn@mail.ru");
	
		//panel.add(to);
		addtoGrid(to, gbc, 1, 1, 1, 1) ;
		gbc.weightx = 0;
		
		//panel.add(new JLabel("SMTP server: "));
		addtoGrid(new JLabel("SMTP server:"), gbc, 0, 2, 1, 1);
		gbc.weightx = 100;
		smtp = new JTextField("vmail1",20);
		smtp.setEditable(false);
		
		//panel.add(smtp);
		addtoGrid(smtp, gbc, 1, 2, 1, 1);
		
		gbc.weightx = 0;
		//panel.add(new JLabel("Subject: "));
		addtoGrid(new JLabel("Subject: "), gbc, 0, 3, 1, 1);
		subj = new JTextField("Ѕез темы 1",20);
		
		gbc.weightx = 100;
		//panel.add(subj);
		addtoGrid(subj, gbc, 1, 3, 1, 1);
		
		
	
		//panel.add(new JLabel("Message text: "));
		gbc.weighty = 15;
		addtoGrid(new JLabel("Message text: "), gbc, 0, 4, 1, 1);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 100;
		String host;
		InetAddress[] addresses=null;
		try {
			host = InetAddress.getLocalHost().getHostAddress();
			addresses = InetAddress.getAllByName(host);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		area = new JTextArea(addresses.toString());
		area.setMinimumSize(new Dimension(30,30));
		area.setMaximumSize(new Dimension(300,150));
		area.setPreferredSize(area.getMaximumSize());
		addtoGrid(new JScrollPane(area), gbc, 0, 5, 2, 1);
		
		//panel.add(new JScrollPane(area));
		
		
		//panel.add(new JLabel("Socket string: "));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 15;
		addtoGrid(new JLabel("Socket string: "), gbc, 0, 6, 1, 1);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 100;
		communication = new JTextArea();
		communication.setMinimumSize(new Dimension(30,30));
		communication.setMaximumSize(new Dimension(300,50));
		communication.setPreferredSize(communication.getMaximumSize());

		addtoGrid(new JScrollPane(communication), gbc, 0, 7, 2, 1);
		gbc.weighty = 0;
		
//	Action send mail		
		send =new JButton("Send");
		send.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				new Thread (){
					public void run(){
						sendMail(valFrom);
					}
				}.start();
			}
			
		});
//	----
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(send);
		addtoGrid(buttonPanel, gbc, 0, 8, 2, 1);
		
		container.add(panel,"Center");

}
	
	//	Gridbag добавление
	private void addtoGrid(Component c, GridBagConstraints gbc,
			int x, int y, int w, int h)
			{
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.gridwidth = w;
			gbc.gridheight = h;
			panel.add(c, gbc);
		}
//
//	получение строки от сокета
	public void receive() throws IOException{
		String line = in.readLine();
		
		if(line !=null){
			communication.append(line);
			communication.append("\n");
		}
	}
//	/----
	
//	передача строки сокету и эхо ответ
	public void send(String s) throws IOException{
		communication.append(s);
		communication.append("\n");
		out.print(s);
		out.print("\r\n");
		out.flush();
	}
//	/----
	
//	Metod sendMail
	public void sendMail(String rekvizit){

			try {
				Socket s = new Socket(smtp.getText(),25);
				s.setSoTimeout(10000);
			if (s!=null){	
				out = new PrintWriter(s.getOutputStream());
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
				String hostName = InetAddress.getLocalHost().getHostName();
				
				receive();
				send("HELO " + hostName);
				receive() ;
				send("MAIL FROM: <" + from.getText() + ">");
				receive();
				send("RCPT TO: <" + (String)to.getSelectedItem() + ">");
				receive();
				send("SUBJECT: <"+subj.getText()+">");
				receive();
				send("DATA");
				receive();
				StringTokenizer tokenizer = new StringTokenizer(area.getText(), "\n");
						while (tokenizer.hasMoreTokens())
								send(tokenizer.nextToken());
								send(".");
								receive();
								s.close();
								
			}					
			} catch (UnknownHostException e) {
				communication.append("Error1: " + e);
			} catch (IOException e) {
				communication.append("Error2: " + e);
			}
		
	} 
	
	
}
