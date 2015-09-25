package com.azovstal.test.jtable;

//By Ranjith on 2004/04/15 18:49:20  Rate
//Main class ( a jframe, that has a table )  
//and two buttons to move rows up and down 
//the actual code is in MoveListener class. 
import java.awt.BorderLayout; 
import java.awt.GridLayout; 


import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JTable; 
import javax.swing.table.DefaultTableModel; 
/**
 * @author ranjith
 */ 
public class MoveRow extends JFrame  {  
 JTable table = null; 
 public static final String [  ]  names =  { "Artist","Track","In stock","Price" } ; 
 public static Object [  ]  [  ]  data= {  
    { "The Beatles", "No.1", "Out of stock","$3.99" } , 
    { "The Beatles", "Rubber soule", "Available","$3.99" } , 
    { "Eric Clapton", "Cream of clapton", "Available","$3.99" } , 
    { "The doors", "Super hits", "Out of stock","$3.99" } , 
    { "Crosby Still Nash", "Not fool again", "Out of stock","$3.99" } , 
    { "Pink floyd", "Brick in the wall", "Out of stock","$3.99" }          
    } ; 
    
 JButton moveUpButton = new JButton ( "Move Up" ) ; 
 JButton moveDnButton = new JButton ( "Move Down" ) ; 
  
 /**
	 * constructor. Draws controls on the frame
	 */ 
 public MoveRow (  )  {  
   getContentPane (  ) .setLayout ( new BorderLayout (  )  ) ; 
   getContentPane (  ) .add ( getTable (  ) , BorderLayout.CENTER ) ; 
   getContentPane (  ) .add ( getButtonPanel (  ) , BorderLayout.EAST ) ; 
   setSize ( 200,300 ) ; 
   pack (  ) ; 
   setVisible ( true ) ; 
  }   
  
 /**
	 * Returns the button panel with up and down buttons.
	 * 
	 * @return panel
	 */ 
 public JPanel getButtonPanel (  )  {  
   JPanel btnPanel = new JPanel (  ) ; 
   btnPanel.setLayout ( new GridLayout ( 0,1 )  ) ; 
   btnPanel.add ( moveUpButton ) ; 
   moveUpButton.addActionListener ( new MoveListener1 ( table,MoveListener1.MOVE_UP )  ) ; 
   btnPanel.add ( moveDnButton ) ; 
   moveDnButton.addActionListener ( new MoveListener1 ( table, MoveListener1.MOVE_DOWN )  ) ; 
   return btnPanel; 
  }  
  
 /**
	 * Returns the table, created using the data [ ] [ ] .
	 * 
	 * @return
	 */ 
 public JTable getTable (  )  {  
   DefaultTableModel dataModel = new DefaultTableModel (  ) ; 
   for  ( int i = 0; i  <  names.length; i++ )   {  
     dataModel.addColumn ( names [ i ]  ) ; 
    }  
   for ( int i =0 ; i  <  6;i++ )  {  
       dataModel.addRow ( data [ i ]  ) ; 
    }  
   table = new JTable ( dataModel ) ; 
   return table; 
  }  


 public static void main ( String [  ]  args )   {  
   MoveRow mr = new MoveRow (  ) ; 
  }  
}  
// //////////////////////////////////////////
// / MoveListener.java, the actual move code
// //////////////////////////////////////////
/*
 * Created on Oct 23, 2003
 * 
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */ 
 


