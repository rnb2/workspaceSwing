package com.azovstal.core.example2;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Main {
  public static void main(String args[]) {
    JFrame frame = new JFrame("JTreeSample");

    
    List<Station> stations = new ArrayList<Station>();
	stations.add(new Station("Sort", "Sortirovka"));
	stations.add(new Station("Bunk", "Bunkernaya"));
	stations.add(new Station("Prim", "Primorska"));
	stations.add(new Station("Sev", "Severnaya"));
	stations.add(new Station("St1", "First station"));

	List<Front> fronts = new ArrayList<Front>();
	fronts.add(new Front(stations.get(0), "front 1", "fr 1"));
	fronts.add(new Front(stations.get(0), "front 2", "fr 2"));
	fronts.add(new Front(stations.get(0), "front 3", "fr 3"));
	fronts.add(new Front(new Station("Bunk", "Bunkernaya"), "front bunk", "fr 3"));
//	fronts.add(new Front(new Station("Sort", "Sortirovka"), "front 1", "fr 1"));
//	fronts.add(new Front(new Station("Sort", "Sortirovka"), "front 2", "fr 2"));
//	fronts.add(new Front(new Station("Sort", "Sortirovka"), "front 3", "fr 3"));

	
	//fronts.add(new Front(stations.get(1), "front 2.1", "fr 2.1"));
	//fronts.add(new Front(stations.get(1), "front 2.2", "fr 2.2"));
	
	
    List<Path> paths = new ArrayList<Path>();
    paths.add(new Path(fronts.get(1), "put 1", "p1"));
    paths.add(new Path(fronts.get(2), "put 2", "p2"));

System.out.println("station=" + stations);
System.out.println("front=" + fronts);
System.out.println("path=" + paths);

	DefaultMutableTreeNode root =    new DefaultMutableTreeNode("Root");
	    DefaultMutableTreeNode child;
	    DefaultMutableTreeNode grandChild = null;
	    DefaultMutableTreeNode grandChild2;
	    for (Station station : stations) {
	    	System.out.println("station=" + station);
		      child = new DefaultMutableTreeNode(station);
		      root.add(child);
		      for (Front front : fronts) {
		    	  System.out.println("--front=" + front);
		    	  System.out.println("--front.getStation()=" + front.getStation() + " <-> station" +station);
		        
		    	if(front.getStation().getFullName().equals(station.getFullName())){
		          System.out.println("--front=" + front);
		    	  grandChild =  new DefaultMutableTreeNode(front);
		    	   if (child !=null) child.add(grandChild);
				        for (Path path : paths) {
							if(path.getFront().getFullName().equals(front.getFullName())){
								System.out.println("---path=" + path);						
						    	grandChild2 =   new DefaultMutableTreeNode(path);
						    	 if (grandChild != null) grandChild.add(grandChild2);
							}
							
						}//path
		    	}
			}//front
		}//station


	    JTree tree1 = new JTree(root);
	    tree1.expandRow(0); // Expand children to illustrate leaf icons

	
    
    Vector oneVector = new NamedVector("One", args);
    Vector twoVector = new NamedVector("Two", new String[] { "Mercury",
        "Venus", "Mars" });
    Vector threeVector = new NamedVector("Three");
    threeVector.add(System.getProperties());
    threeVector.add(twoVector);
    
    Object rootNodes[] = { oneVector, twoVector, threeVector };
    Vector rootVector = new NamedVector("Root", rootNodes);
    
    
    JTree tree = new JTree(rootVector);
    JScrollPane scrollPane = new JScrollPane(tree1);
    
    
    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    //    frame.getContentPane().add(tree, BorderLayout.CENTER);
    frame.setSize(300, 300);
    frame.setVisible(true);
  }
}

class NamedVector extends Vector {
  String name;

  public NamedVector(String name) {
    this.name = name;
  }

  public NamedVector(String name, List<UnloadPlace> unloadPlaces){
	  this.name = name;
	  for (UnloadPlace unloadPlace : unloadPlaces) {
		add(unloadPlace);
	}
  }
  public NamedVector(String name, Object elements[]) {
    this.name = name;
    for (int i = 0, n = elements.length; i < n; i++) {
      add(elements[i]);
    }
  }

  public String toString() {
    return "[" + name + "]";
  }
}
