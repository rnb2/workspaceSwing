package com.azovstal.core.treeExmpl3;

//Создание дерева (JTree) из карты (HashMap)
//Используется специфическая карта Map< String, Vector>


import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.*;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
public class Example3 {

	
	static Vector v = new Vector();
	static Vector v1 = new Vector();
	static Map< String, Vector> map = new HashMap< String, Vector>();
	static Map< String, Vector> submap=new HashMap< String, Vector>();
	static DefaultTreeModel tm;
	static DefaultTreeModel tm1;
	public static void main(String[] args) {
	        v = new Vector();
	        v.add("b");
	        v.add("c");
	        map.put("a",v);
	        //--добавление детских узлов к "a"
	        v = new Vector();
	        v.add("b1");
	        v.add("b2");
	        map.put("b",v);
	        //-добавление детских узлов к "b"
	        v = new Vector();
	        v.add("c1");
	        v.add("c2");
	        v.add("c3");
	        map.put("c",v);
	        //---добавление детских узлов к "c"
	        v = new Vector();
	        v.add("c11");
	        v.add("c12");
	        v.add("c13");
	        v.add("c14");
	        map.put("c1",v);
	          //---добавление детских узлов к "c1"
	        v = new Vector();
	        v.add("c21");
	        v.add("c22");
	        v.add("c23");
	        map.put("c2",v);
	         //---добавление детских узлов к "c2"
	        v = new Vector();
	        v.add("c221");
	        v.add("c222");
	        v.add("c223");
	        map.put("c22",v);
	        //---добавление детских узлов к "c22"
	        JTree tree;
	        DefaultMutableTreeNode root = new DefaultMutableTreeNode("a");
	        tm = new DefaultTreeModel(root);
	        v=map.get("a");
	         DefaultMutableTreeNode node0 = new DefaultMutableTreeNode(v.get(0));
	      addNewNodes(root,v);
	      
	      v1=map.get(node0.toString());
	      System.out.println("v1 "+v1);
	      addNewNodes(node0,v1);
	    
	    tree =new JTree(tm);
	    JFrame jf=new JFrame();
	    JPanel jp=new JPanel();
	    jf.setSize(333,333);
	    jf.getContentPane().add(tree);
	    jf.setVisible(true);
	    jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
	    
	    System.out.println("submap "+submap);
	}
	static void addNewNodes(DefaultMutableTreeNode currNode,Vector vec){
	 DefaultMutableTreeNode node0=null;
	 DefaultMutableTreeNode node1=null;
	 System.out.println("currNode "+currNode);
	 for(int i=0;i< vec.size();i++){
	     DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(vec.get(i));
	        tm.insertNodeInto(childNode, currNode, currNode.getChildCount());
	        if(map.get(childNode.toString())!=null){
	            v1=map.get(childNode.toString());
	      addNewNodes(childNode,v1);
	        }  
	    }
	   
	}
	static void submap(String key,Vector v){
	        if(map.containsKey(key)){
	            submap.put(key, v);
	            for(int i=0;i< v.size();i++){
	                String key1=v.get(i).toString();
	                System.out.println("!!! "+key1);
	               submap(key1,map.get(key1));
	            }
	        }
	}



}
