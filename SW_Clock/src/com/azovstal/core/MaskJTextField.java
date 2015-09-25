package com.azovstal.core;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import javax.swing.text.*;

public class MaskJTextField
extends JTextField {

  private String mask;
  private Pattern regPattern;

  public void setMask(String mask) {
    this.mask = mask;
    this.setColumns(mask.length() + 1);
    String regExp = null;
    if (Pattern.matches("#{1,}", mask)) {
      regExp = "([-+]){0,1}\\d{1," + mask.length() + "}";
    }
    else if (Pattern.matches("#{1,}.#{1,}", mask)) {
      regExp = "([-+]){0,1}(\\d{1," + mask.indexOf('.') + "}" +
      "([.,]\\d{0," + (mask.length() - mask.indexOf('.') - 1) +
      "}){0,1})"
      ;
    }
    else {
      System.out.println("Mask=\"" + mask + "\" is not correct");
      this.regPattern = null;
      return;
    }
    this.regPattern = Pattern.compile(regExp);
  }

  protected Document createDefaultModel() {
    FieldDocument fieldDocument = new FieldDocument();
    return fieldDocument;
  }

  protected class FieldDocument
  extends PlainDocument {
    public void remove(int offs, int len) throws BadLocationException {
      if (regPattern != null) {
        StringBuffer stringBuffer = new StringBuffer(MaskJTextField.this.
        getText());
        stringBuffer.delete(offs, offs + len);
        if (!regPattern.matcher(stringBuffer).matches()) {
          Toolkit.getDefaultToolkit().beep();
          return;
        }
      }
      super.remove(offs, len);
    }

    public void insertString(int offs, String str, AttributeSet a) throws
    BadLocationException {
      if (str == null) {
        return;
      }
      StringBuffer strInsert = new StringBuffer(str);
      if (strInsert.length() > 0) {
        if (regPattern != null) {
          StringBuffer stringBuffer = new StringBuffer(MaskJTextField.this.
          getText());
          stringBuffer.insert(offs, strInsert.toString());
          if (!regPattern.matcher(stringBuffer).matches()) {
            Toolkit.getDefaultToolkit().beep();
            return;
          }
        }
        super.insertString(offs, strInsert.toString(), a);
      }
    }
  }

  public static void main(String[] args) {
    JFrame frame1 = new JFrame();
    frame1.setSize(400, 60);
    frame1.setTitle("Демонстрация MaskJTextField");
    frame1.getContentPane().setLayout(new FlowLayout());
    frame1.getContentPane().add(new JLabel("Маска ввода"));
    final JTextField maskString = new JTextField("###.##");
    maskString.setColumns(10);
    frame1.getContentPane().add(maskString);
    frame1.getContentPane().add(new JLabel("Поле ввода"));
    final MaskJTextField maskJtextField = new MaskJTextField();
    maskJtextField.setMask("###.##");
    frame1.getContentPane().add(maskJtextField);
    frame1.setVisible(true);
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    maskString.addFocusListener(new FocusAdapter() {
      public void focusLost(FocusEvent e) {
        System.out.println("Try to set mask=\"" + maskString.getText() + "\"");
        maskJtextField.setMask(maskString.getText());
      }
    });
  }
}