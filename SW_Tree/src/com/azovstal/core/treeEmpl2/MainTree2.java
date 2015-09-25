package com.azovstal.core.treeEmpl2;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
public class MainTree2 extends JFrame
{
    JTree jTree = new JTree();
    public MainTree2() throws HeadlessException
    {
        // ������� ������ ��� ���������� � ������ ���������� addNewItem()
        JButton add_btn = new JButton("Add");
        add_btn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewItem();
            }
        });
        getContentPane().add("North", add_btn);
        // ������� ������ ��� �������� � ������ ���������� removeItem()
        JButton remove_btn = new JButton("Remove");
        remove_btn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeItem();
            }
        });
        getContentPane().add("South", remove_btn);
        // �������� ���� ���� ROOT
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("ROOT");
        jTree = new JTree(top);
        getContentPane().add("Center", new JScrollPane(jTree));
        setBounds(100, 100, 500, 400);
    }
    public void addNewItem()
    {
        // ����� - ������ � ��� ������� ������� ����� ������������ ������ ����� ������ ������.
        // ������ � ���� ������ ������������� ���������� ������ � ����� �������
        // � ��������� ������ ����� ���� ����� ���� �� �����������
        DefaultTreeModel model = (DefaultTreeModel)jTree.getModel();
        Object obj = jTree.getLastSelectedPathComponent();
        if(obj!=null)
        {
            DefaultMutableTreeNode sel = (DefaultMutableTreeNode)obj;
            // ������� ������� ����������� � �������� � ������������ � ���
            if(sel.getLevel()==1) {
                DefaultMutableTreeNode tmp = new DefaultMutableTreeNode("Deep");
                model.insertNodeInto(tmp, sel, sel.getChildCount());
            }
            if(sel.isRoot()) {
                DefaultMutableTreeNode tmp = new DefaultMutableTreeNode("Midle");
                model.insertNodeInto(tmp, sel, sel.getChildCount());
            }
            jTree.expandPath(new TreePath(sel.getPath()));
        }
    }
    public void removeItem()
    {
        // ������ ��������� � addNewItem()
        DefaultTreeModel model = (DefaultTreeModel)jTree.getModel();
        Object obj = jTree.getLastSelectedPathComponent();
        if(obj!=null)
        {
            DefaultMutableTreeNode sel = (DefaultMutableTreeNode)obj;
            // ������ ������� ������
            if(!sel.isRoot())
            {
                if(sel.getChildCount()==0)
                    model.removeNodeFromParent(sel);
                else
                    // ���� ���� "�������" ������� ���������
                    JOptionPane.showMessageDialog(null, "Remove all subnodes");
            }
        }
    }
    public static void main(String[] args) throws HeadlessException
    {
        MainTree2 mainTree2 = new MainTree2();
        mainTree2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainTree2.setVisible(true);
    }
}

