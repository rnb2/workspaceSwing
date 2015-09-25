package com.azovstal.test.jtable;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 


import javax.swing.JTable; 
import javax.swing.table.DefaultTableModel; 


/**
 * @author ranjith
 */ 
public class MoveListener1 implements ActionListener  {  
 public static final int MOVE_UP = -1; 
 public static final int MOVE_DOWN = 1; 
 /**
	 * @param table
	 */ 
 public MoveListener1 ( JTable table, int direction )   {  
   myTable = table; 
   this.direction = direction; 
  }  


 JTable myTable = null; 
 int direction = MOVE_UP; 
  
 /**
	 * The actual move action. based on the direciton, calculate the destination
	 * row and simply call the moveRow method. then call a method to set the row
	 * as currently selected row.
	 */ 
 public void actionPerformed ( ActionEvent arg0 )   {  
   int selRow = myTable.getSelectedRow (  ) ; 
   // find the destination row.
   int destRow = selRow + direction; 
   // check if the destination row is within table limits
   if  ( destRow  >= 0 && destRow  <  myTable.getRowCount (  )  )   {  
     DefaultTableModel dtm =  ( DefaultTableModel )  myTable.getModel (  ) ; 
     dtm.moveRow ( selRow, selRow, destRow ) ; 
     // Move is done, now set selection.
     myTable.setColumnSelectionAllowed ( false ) ; 
     myTable.setRowSelectionAllowed ( true ) ; 
     myTable.setRowSelectionInterval ( destRow, destRow ) ; 
     // make the change visible in UI.
     myTable.repaint (  ) ; 
    }  
  }  


}  
