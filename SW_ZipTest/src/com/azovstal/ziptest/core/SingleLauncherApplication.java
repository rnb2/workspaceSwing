package com.azovstal.ziptest.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SingleLauncherApplication implements Runnable, Serializable {
	public static final int PORT = 38629;
	public JLabel label;
	public ServerSocket server;
	public JFrame frame;
	public void launch(String[] args) {
		try {
			server = new ServerSocket(PORT);
			new Thread(this).start();
			firstMain(args);

		} catch (IOException ioex) {
			System.out.println("already running!");
			//relaunch(args);

		}
	}

	public void firstMain(String[] args) {
		System.out.println("firstMain");
		frame = new JFrame("Single Launch Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String word = "";
		if(args.length >= 1) {

			word = args[0];
		}
		label = new JLabel("The word of the day is: " + word);
		frame.getContentPane().add(label);
		frame.pack();
		frame.show();
		
	}

	public void run() {
		System.out.println("waiting for a connection");
		//while(true) {
			try {
				// wait for a socket connection
				System.out.println("before write object");
				Socket sock = server.accept();
				
				// read the contents into a string buffer
//				InputStreamReader in = new InputStreamReader(
//				sock.getInputStream());    
//				StringBuffer sb = new StringBuffer(); 
//				char[] buf = new char[256];
//				while(true) {
//				int n = in.read(buf);
//				if(n < 0) { break; }
//				sb.append(buf,0,n);
//				}
				
				System.out.println("write object");
				ObjectOutputStream oc = new ObjectOutputStream(sock.getOutputStream());
				oc.writeObject(frame);
				oc.close();
				// split the string buffer into strings
//				String[] results = sb.toString().split("\\n");
				// call other main
//				otherMain(results);

			} catch (IOException ex) {
				System.out.println("ex: " + ex);
				ex.printStackTrace();

			}
		//}
	}

	public void otherMain(final String[] args) {
		if(args.length >= 1) {
			SwingUtilities.invokeLater(new Runnable() {


	         public void run() { 
				label.setText("The word of the day is: " + args[0]);
			}
		});
		}
	}
	
	public void relaunch(String[] args) {
		try {
		// open a socket to the original instance
		Socket sock = new Socket("localhost",PORT);
	ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
	//System.out.println(inputStream.readObject());
	JFrame f = (JFrame) inputStream.readObject();
	if (f!=null){
		f.setVisible(true);
	}
		// write the args to the output stream 
//		OutputStreamWriter out = new OutputStreamWriter(
//			sock.getOutputStream());
//		

//		
//		for(int i=0; i<args.length; i++) {
//			out.write(args[i]+"\n");
//			out.write("wrote: " + args[i]);
//			System.out.println(args[i]);
//		}
//		// cleanup
//		out.flush();
//		out.close();
		
	} catch (Exception ex) {
		System.out.println("ex: " + ex);
		ex.printStackTrace();

	}
	}
	
	public static void main(String[] args) {
		try {
			Socket sock = new Socket("localhost",PORT);
				ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
				//System.out.println(inputStream.readObject());
				JFrame f = (JFrame) inputStream.readObject();
				if (f!=null){
					f.setVisible(true);
				}
		}
		catch (Exception e) {
			System.err.println("error in main");
			new SingleLauncherApplication().launch(args);		}
	}	
}

