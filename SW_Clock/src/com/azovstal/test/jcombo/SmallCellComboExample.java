package com.azovstal.test.jcombo;

/* (swing1.1) */
//package jp.gr.java_conf.tame.swing.examples;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
//import jp.gr.java_conf.tame.swing.combobox.*;

/**
 * @version 1.0  3/06/99
 */
public class SmallCellComboExample extends JFrame {

  public SmallCellComboExample() {
    super( "SmallCell Combo Example" );
        
    DefaultTableModel dm = new DefaultTableModel(4,10) {
      public void setValueAt(Object obj, int row, int col) {
        if (obj != null) {
          String str;
          if (obj instanceof String) {
            str = ((String)obj).substring(0,2);
          } else {
            str = obj.toString();
          }
          super.setValueAt(str, row, col);
        }
      }
    };
    JTable table = new JTable( dm );
    
    String[] str = {
      "010 - To Time",
      "020 - Vacation",	  
      "030 - Feel Bad"  
    };
    
    SteppedComboBox combo = new SteppedComboBox(str) {
      public void contentsChanged(ListDataEvent e) {
        selectedItemReminder = null;
        super.contentsChanged(e);
      }
    };
    
    Dimension d = combo.getPreferredSize();
    combo.setPopupWidth(d.width);
    
    DefaultCellEditor editor = new DefaultCellEditor(combo);
    table.setDefaultEditor(Object.class, editor);
    

    JScrollPane scroll = new JScrollPane( table );
    getContentPane().add(scroll, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    SmallCellComboExample frame = new SmallCellComboExample();
    frame.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent e ) {
	System.exit(0);
      }
    });
    frame.setSize( 300, 120 );
    frame.setVisible(true);
  }
}

