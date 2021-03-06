package com.test;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ComplaintsDialog extends JDialog
{
   public ComplaintsDialog( JFrame frame )
   {
      super( frame, true );
      setTitle( "Simple Complaints Dialog" );
      setSize( 500, 300 );

      // Creates a panel to hold all my components
      JPanel panel = new JPanel( new BorderLayout() );
      panel.setLayout( new GridBagLayout() );
      // give the panel a border gap of 5 pixels
      panel.setBorder( new EmptyBorder( new Insets( 5, 5, 5, 5 ) ) );
      getContentPane().add( BorderLayout.CENTER, panel );
      
      GridBagConstraints c = new GridBagConstraints();

      // Define preferred sizes for my entry fields
      Dimension shortField = new Dimension( 40, 20 );
      Dimension mediumField = new Dimension( 120, 20 );
      Dimension longField = new Dimension( 240, 20 );
      Dimension hugeField = new Dimension( 240, 80 );

      // Spacing between the label and the field
      EmptyBorder border = new EmptyBorder( new Insets( 0, 0, 0, 10 ) );
      EmptyBorder border1 = new EmptyBorder( new Insets( 0, 20, 0, 10 ) );

      // add some space around all my components to avoid cluttering
      c.insets = new Insets( 2, 2, 2, 2 );

      // anchors all my components to the west
      c.anchor = GridBagConstraints.WEST;

      // Short description label and field
      JLabel lbl1 = new JLabel( "Short Description" );
      lbl1.setBorder( border ); // add some space on the right
      panel.add( lbl1, c );
      JTextField txt1 = new JTextField();
      txt1.setPreferredSize( longField );
      c.gridx = 1;
      c.weightx = 1.0; // use all available horizontal space
      c.gridwidth = 3; // spans across 3 columns
      c.fill = GridBagConstraints.HORIZONTAL; // fills up the 3 columns
      panel.add( txt1, c );

      // Description label and field
      JLabel lbl2 = new JLabel( "Description" );
      lbl2.setBorder( border );
      c.gridwidth = 1;
      c.gridx = 0;
      c.gridy = 1;;
      c.weightx = 0.0; // do not use any available horizontal space
      panel.add( lbl2, c );
      JTextArea area1 = new JTextArea();
      JScrollPane scroll = new JScrollPane( area1 );
      scroll.setPreferredSize( hugeField );
      c.gridx = 1;
      c.weightx = 1.0; // use all available horizontal space
      c.weighty = 1.0; // use all available vertical space
      c.gridwidth = 3; // spans across 3 columns
      c.gridheight = 2; // spans across 2 rows
      c.fill = GridBagConstraints.BOTH; // fills up the cols & rows
      panel.add( scroll, c );

      // Severity label and combo box
      JLabel lbl3 = new JLabel( "Severity" );
      lbl3.setBorder( border );
      c.gridx = 0;
      c.gridy = 3;
      c.gridwidth = 1;
      c.gridheight = 1;
      c.weightx = 0.0;
      c.weighty = 0.0;
      c.fill = GridBagConstraints.NONE;
      panel.add( lbl3, c );
      JComboBox combo3 = new JComboBox();
      combo3.addItem( "A" );
      combo3.addItem( "B" );
      combo3.addItem( "C" );
      combo3.addItem( "D" );
      combo3.addItem( "E" );
      combo3.setPreferredSize( shortField );
      c.gridx = 1;
      panel.add( combo3, c );

      // Priority label and combo box
      JLabel lbl4 = new JLabel( "Priority" );
      lbl4.setBorder( border1 );
      c.gridx = 2;
      panel.add( lbl4, c );
      JComboBox combo4 = new JComboBox();
      combo4.addItem( "1" );
      combo4.addItem( "2" );
      combo4.addItem( "3" );
      combo4.addItem( "4" );
      combo4.addItem( "5" );
      combo4.setPreferredSize( shortField );
      c.gridx = 3;
      panel.add( combo4, c );

      // Name label and text field
      JLabel lbl5 = new JLabel( "Name" );
      lbl5.setBorder( border );
      c.gridx = 0;
      c.gridy = 4;
      panel.add( lbl5, c );
      JTextField txt5 = new JTextField();
      txt5.setPreferredSize( longField );
      c.gridx = 1;
      c.gridwidth = 3;
      panel.add( txt5, c );

      // Telephone label and text field
      JLabel lbl6 = new JLabel( "Telephone" );
      lbl6.setBorder( border );
      c.gridx = 0;
      c.gridy = 5;
      panel.add( lbl6, c );
      JTextField txt6 = new JTextField();
      txt6.setPreferredSize( mediumField );
      c.gridx = 1;
      c.gridwidth = 3;
      panel.add( txt6, c );

      // Sex label and radio button
      JLabel lbl7 = new JLabel( "Sex" );
      lbl7.setBorder( border );
      c.gridx = 0;
      c.gridy = 6;
      panel.add( lbl7, c );
      JPanel radioPanel = new JPanel();
      // Creates a FlowLayout layout JPanel with 5 pixel of horizontal gaps
      // and no vertical gaps
      radioPanel.setLayout( new FlowLayout( FlowLayout.LEFT, 5, 0 ) );
      ButtonGroup group = new ButtonGroup();
      JRadioButton radio1 = new JRadioButton( "Male" );
      radio1.setSelected( true );
      group.add( radio1 );
      JRadioButton radio2 = new JRadioButton( "Female" );
      group.add( radio2 );
      radioPanel.add( radio1 );
      radioPanel.add( radio2 );
      c.gridx = 1;
      c.gridwidth = 3;
      panel.add( radioPanel, c);

      // ID Number label and text field
      JLabel lbl8 = new JLabel( "ID Number" );
      lbl8.setBorder( border );
      c.gridx = 0;
      c.gridy = 7;
      c.gridwidth = 1;
      panel.add( lbl8, c );
      JTextField txt8 = new JTextField();
      txt8.setPreferredSize( mediumField );
      c.gridx = 1;
      c.gridwidth = 3;
      panel.add( txt8, c );

      // Okay button
      JButton submitBtn = new JButton( "Submit" );
      c.gridx = 4;
      c.gridy = 0;
      c.gridwidth = 1;
      c.fill = GridBagConstraints.HORIZONTAL;
      panel.add( submitBtn, c );

      // Cancel button
      JButton cancelBtn = new JButton( "Cancel" );
      c.gridy = 1;
      panel.add( cancelBtn, c );

      // Help button
      JButton helpBtn = new JButton( "Help" );
      c.gridy = 2;
      c.anchor = GridBagConstraints.NORTH; // anchor north
      panel.add( helpBtn, c );

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setVisible( true );
   }

   public static void main( String[] args )
   {
      new ComplaintsDialog( new JFrame() );
   }
}
