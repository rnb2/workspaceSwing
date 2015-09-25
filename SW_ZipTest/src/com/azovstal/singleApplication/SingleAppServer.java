package com.azovstal.singleApplication;

import java.io.IOException;
import java.net.ServerSocket;

public class SingleAppServer {
	public  ServerSocket server;
	public static final int PORT = 38629;

	public SingleAppServer(final MainFrame frame) {
		try {
			server = new ServerSocket(PORT);
		} catch (IOException e) {
			System.err.println("--"+e);
		}
		
		new Thread(new Runnable(){
			public void run() {
				try {
					server.accept();
					System.out.println("signal!!!!!");
					frame.setVisible(true);
					frame.toFront();
				} catch (IOException e) {
				}
			}}).start();
	}
}
