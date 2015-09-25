package com.azovstal.test.applet;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.logging.ConsoleHandler;

public class Counter1 extends JApplet {
	private int[] p = new int[1700*2300];
	 
	private class SeparateSubTask extends Thread {
		
		private int count = 0;
	    private boolean runFlag = true;
	    SeparateSubTask() { 
	    	start(); 
	    }
	    void invertFlag() { runFlag = !runFlag; }
	    public void run() {
	      while (true) {
	       try {
	        sleep(100);
	      } catch(InterruptedException e) {
	        System.err.println("Interrupted");
	      }
	       if(runFlag) 
	         t.setText(Integer.toString(count++));
	      }
	    }
	  } 
	
	  private SeparateSubTask sp = null;
	  private JTextField t = new JTextField(10);
	  private JButton 
	    start = new JButton("Start"),
	    onOff = new JButton("Toggle");
	 
	  class StartL implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      if(sp == null)
	        sp = new SeparateSubTask();
	    }
	  }
	  class OnOffL implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      if(sp != null)
	        sp.invertFlag();
	    }
	  }
	  public void init() {
	    Container cp = getContentPane();
	    cp.setLayout(new FlowLayout());
	    cp.add(t);
	    start.addActionListener(new StartL());
	    cp.add(start);
	    onOff.addActionListener(new OnOffL());
	    cp.add(onOff);
	    String s=  ("p="+p.length);
	    cp.add(new JTextField(s));
	  }
	  public static void main(String[] args) {
		  new Counter1 ();
	  }
	
}
