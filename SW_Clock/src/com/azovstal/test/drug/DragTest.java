package com.azovstal.test.drug;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class DragTest extends JFrame {

  JList jl;

  String[] items = { "Java", "C", "C++", "Lisp", "Perl", "Python" };

  public DragTest() {
    super("Drag Test 1.4");
    setSize(200, 150);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    jl = new JList(items);
    jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jl.setDragEnabled(true);

    getContentPane().add(new JScrollPane(jl), BorderLayout.CENTER);
    setVisible(true);
  }

  public static void main(String args[]) {
    int[] p = new int[1700*2300];
	System.out.println("budukh-rn-----03.07.2008-----"+p); 
    new DragTest();
  }
}
