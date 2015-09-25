package com.test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridBagExample extends JFrame
{
   public GridBagExample()
   {
      super( "Grid Bag Layout Examples" );
      setSize( 640, 480 );
      Container cn;
      GridBagConstraints c;
      JDesktopPane desktop = new JDesktopPane();
      getContentPane().add( desktop );

      // EXAMPLE 1
      // Demonstrates the default behaviour of a GridBagLayout
      // without specifying any constraints
      JInternalFrame fr1 = new JInternalFrame( "Example 1", true, true );
      fr1.setBounds( 5, 5, 270, 100 );
      cn = fr1.getContentPane();
      cn.setLayout( new GridBagLayout() );
      cn.add( new JButton( "Wonderful" ) );
      cn.add( new JButton( "World" ) );
      cn.add( new JButton( "Of" ) );
      cn.add( new JButton( "Swing !!!" ) );
      desktop.add( fr1, 0 );
      fr1.show();

      // EXAMPLE 2
      // Demonstrates the use of gridx, gridy,
      // ipadx, ipady and insets constraints
      JInternalFrame fr2 = new JInternalFrame( "Example 2", true, true );
      fr2.setBounds( 5, 110, 270, 140 );
      cn = fr2.getContentPane();
      cn.setLayout( new GridBagLayout() );
      c = new GridBagConstraints(); // creates a constraints object
      c.insets = new Insets( 2, 2, 2, 2 ); // insets for all my components
      c.gridx = 0; // column 0
      c.gridy = 0; // row 0
      c.ipadx = 5; // increases my components width by 10 pixels
      c.ipady = 5; // increases my components height by 10 pixels
      cn.add( new JButton( "Wonderful" ), c ); // constraints passed in 
      c.gridx = 1; // column 1
      //c.gridy = 0; // I don't have to do this since I'm reusing the obj
      c.ipadx = 3; // resets the pad to 0
      c.ipady = 0;
      cn.add( new JButton( "World" ), c );                               
      c.gridx = 0; // column 0 
      c.gridy = 1; // row 1 
      cn.add( new JButton( "Of" ), c );                                  
      c.gridx = 1; // column 1 
      cn.add( new JButton( "Swing !!!" ), c );                           
      desktop.add( fr2, 0 );
      fr2.show();

      // EXAMPLE 3a
      /*
      JInternalFrame fr3a = new JInternalFrame( "Example 3a", true, true );
      fr3a.setBounds( 5, 255, 270, 140 );
      cn = fr3a.getContentPane();
      cn.setLayout( new GridBagLayout() );
      c = new GridBagConstraints();
      c.weightx = 1.0;
      c.gridx = 0;
      c.gridy = 0;
      cn.add( new JButton( "A" ), c );
      c.weightx = 0.0;
      c.gridx = 1;
      cn.add( new JButton( "B" ), c );
      desktop.add( fr3a, 0 );
      fr3a.show();
      */

      // EXAMPLE 3
      // Demonstrates the use of weightx, weighty constraints
      JInternalFrame fr3 = new JInternalFrame( "Example 3", true, true );
      fr3.setBounds( 5, 255, 270, 140 );
      cn = fr3.getContentPane();
      cn.setLayout( new GridBagLayout() );
      c = new GridBagConstraints();
      c.insets = new Insets( 2, 2, 2, 2 );
      c.weighty = 1.0;
      c.weightx = 1.0;
      c.gridx = 0;
      c.gridy = 0;
      cn.add( new JButton( "Wonderful" ), c );
      c.gridx = 1;
      cn.add( new JButton( "World" ), c );
      c.gridx = 0;
      c.gridy = 1;
      cn.add( new JButton( "Of" ), c );
      c.gridx = 1;
      cn.add( new JButton( "Swing !!!" ), c );
      desktop.add( fr3, 0 );
      fr3.show();

      // EXAMPLE 4
      // Demonstrates the use of gridwidth, gridheight constraints
      JInternalFrame fr4 = new JInternalFrame( "Example 4", true, true );
      fr4.setBounds( 280, 5, 270, 140 );                                 
      cn = fr4.getContentPane();                                         
      cn.setLayout( new GridBagLayout() );                               
      c = new GridBagConstraints();                                      
      c.insets = new Insets( 2, 2, 2, 2 );                               
      c.weighty = 1.0;                                                   
      c.weightx = 1.0;                                                   
      c.gridx = 0;                                                       
      c.gridy = 0;                                                       
      c.gridheight = 2; // span across 2 rows 
      cn.add( new JButton( "Wonderful" ), c );                           
      c.gridx = 1;                                                       
      c.gridheight = 1; // remember to set back to 1 row 
      c.gridwidth = 2; // span across 2 columns 
      cn.add( new JButton( "World" ), c );                               
      c.gridy = 1;                                                       
      c.gridwidth = 1; // remember to set back to 1 column 
      cn.add( new JButton( "Of" ), c );                                  
      c.gridx = 2;                                                       
      cn.add( new JButton( "Swing !!!" ), c );                           
      desktop.add( fr4, 0 );
      fr4.show();

      // EXAMPLE 5
      // Demonstrates the use of anchor constraints
      JInternalFrame fr5 = new JInternalFrame( "Example 5", true, true );
      fr5.setBounds( 280, 150, 270, 140 );                               
      cn = fr5.getContentPane();                                         
      cn.setLayout( new GridBagLayout() );                               
      c = new GridBagConstraints();                                      
      c.insets = new Insets( 2, 2, 2, 2 );                               
      c.weighty = 1.0;                                                   
      c.weightx = 1.0;                                                   
      c.gridx = 0;                                                       
      c.gridy = 0;                                                       
      c.gridheight = 2;                                                  
      c.anchor = GridBagConstraints.NORTH; // place component on the North 
      cn.add( new JButton( "Wonderful" ), c );                           
      c.gridx = 1;                                                       
      c.gridheight = 1;                                                  
      c.gridwidth = 2;                                                   
      c.anchor = GridBagConstraints.SOUTHWEST; 
      cn.add( new JButton( "World" ), c );                               
      c.gridy = 1;                                                       
      c.gridwidth = 1;                                                   
      c.anchor = GridBagConstraints.CENTER; // remember to rest to center 
      cn.add( new JButton( "Of" ), c );                                  
      c.gridx = 2;                                                       
      cn.add( new JButton( "Swing !!!" ), c );                           
      desktop.add( fr5, 0 );
      fr5.show();

      // EXAMPLE 6
      // Demonstrates the use of fill constraints
      JInternalFrame fr6 = new JInternalFrame( "Example 6", true, true );
      fr6.setBounds( 280, 295, 270, 140 );                               
      cn = fr6.getContentPane();                                         
      cn.setLayout( new GridBagLayout() );                               
      c = new GridBagConstraints();                                      
      c.insets = new Insets( 2, 2, 2, 2 );                               
      c.weighty = 1.0;                                                   
      c.weightx = 1.0;                                                   
      c.gridx = 0;                                                       
      c.gridy = 0;                                                       
      c.gridheight = 2;                                                  
      c.fill = GridBagConstraints.BOTH; // Use both horizontal & vertical 
      cn.add( new JButton( "Wonderful" ), c );                           
      c.gridx = 1;                                                       
      c.gridheight = 1;                                                  
      c.gridwidth = 2;                                                   
      c.fill = GridBagConstraints.HORIZONTAL; // Horizontal only 
      cn.add( new JButton( "World" ), c );                               
      c.gridy = 1;                                                       
      c.gridwidth = 1;                                                   
      c.fill = GridBagConstraints.NONE; // Remember to reset to none
      cn.add( new JButton( "Of" ), c );                                  
      c.gridx = 2;                                                       
      c.fill = GridBagConstraints.VERTICAL; // Vertical only 
      cn.add( new JButton( "Swing !!!" ), c );                           
      desktop.add( fr6, 0 );
      fr6.show();

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setVisible( true );
   }
   
   public static void main( String[] args )
   {
      new GridBagExample();
   }

}
