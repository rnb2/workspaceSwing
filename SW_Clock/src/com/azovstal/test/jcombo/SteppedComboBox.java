/* (swing1.1) */
package com.azovstal.test.jcombo;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import javax.swing.plaf.basic.*;

/**
 * @version 1.0 12/12/98
 */
class SteppedComboBoxUI extends MetalComboBoxUI {
  protected ComboPopup createPopup() {
    BasicComboPopup popup = new BasicComboPopup( comboBox ) {
        
      public void show() {
        Dimension popupSize = ((SteppedComboBox)comboBox).getPopupSize();
        popupSize.setSize( popupSize.width,
          getPopupHeightForRowCount( comboBox.getMaximumRowCount() ) );
        Rectangle popupBounds = computePopupBounds( 0,
          comboBox.getBounds().height, popupSize.width, popupSize.height);
        scroller.setMaximumSize( popupBounds.getSize() );
        scroller.setPreferredSize( popupBounds.getSize() );
        scroller.setMinimumSize( popupBounds.getSize() );
        list.invalidate();            
        int selectedIndex = comboBox.getSelectedIndex();
        if ( selectedIndex == -1 ) {
          list.clearSelection();
        } else {
          list.setSelectedIndex( selectedIndex );
        }            
        list.ensureIndexIsVisible( list.getSelectedIndex() );
        setLightWeightPopupEnabled( comboBox.isLightWeightPopupEnabled() );

        show( comboBox, popupBounds.x, popupBounds.y );
      }
    };
    popup.getAccessibleContext().setAccessibleParent(comboBox);
    return popup;
  }
}
 
 
public class SteppedComboBox extends JComboBox {
  protected int popupWidth;
  
  public SteppedComboBox(ComboBoxModel aModel) {
    super(aModel);
    setUI(new SteppedComboBoxUI());
    popupWidth = 0;
  }

  public SteppedComboBox(final Object[] items) {
    super(items);
    setUI(new SteppedComboBoxUI());
    popupWidth = 0;
  }
  
  public SteppedComboBox(Vector items) {
    super(items);
    setUI(new SteppedComboBoxUI());
    popupWidth = 0;
  }
  
  
  public void setPopupWidth(int width) {
    popupWidth = width;
  }
  
  public Dimension getPopupSize() {
    Dimension size = getSize();
    if (popupWidth < 1) popupWidth = size.width;
    return new Dimension(popupWidth, size.height);
  }
}
  
  
