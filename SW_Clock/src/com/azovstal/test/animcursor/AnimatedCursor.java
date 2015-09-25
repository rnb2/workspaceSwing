/**
 * 
 */
package com.azovstal.test.animcursor;

/**
 * @author budukh-rn
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class AnimatedCursor implements Runnable {
    private boolean animate;
    private Cursor[] cursors;
    private JFrame frame;
    
    private final static AnimatedCursor animCursor = new AnimatedCursor();
    
    public static AnimatedCursor getInstance(){
    	return animCursor;
    }
    
    private AnimatedCursor() {
        animate = false;
        cursors = new Cursor[10];
        ArrayList<Image> imageList= new ArrayList<Image>(10);
        imageList.add(FactoryImage.getInstance().ICON_LOADER001.getImage());
        imageList.add(FactoryImage.getInstance().ICON_LOADER002.getImage());
        imageList.add(FactoryImage.getInstance().ICON_LOADER003.getImage());
        imageList.add(FactoryImage.getInstance().ICON_LOADER004.getImage());
        imageList.add(FactoryImage.getInstance().ICON_LOADER005.getImage());
        imageList.add(FactoryImage.getInstance().ICON_LOADER006.getImage());
        imageList.add(FactoryImage.getInstance().ICON_LOADER007.getImage());
        imageList.add(FactoryImage.getInstance().ICON_LOADER008.getImage());
        imageList.add(FactoryImage.getInstance().ICON_LOADER009.getImage());
        imageList.add(FactoryImage.getInstance().ICON_LOADER010.getImage());
        for (int i = 0; i < imageList.size(); i++) {
			cursors[i] = Toolkit.getDefaultToolkit().createCustomCursor(
					   imageList.get(i),
					   new Point(5, 5), 
					   "cursor00"+i);
		}
        
    }

    public void run() {
        int count = 0;
        while(isAnimate()) {
            try {
                Thread.currentThread().sleep(200);
            } catch (Exception ex) {
            }

            getFrame().setCursor(cursors[count % cursors.length]);
            count++;
        }
        getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    
    public void stopAnimate(){
      if(animate){
        animate = false;
      } 
    }
    
    public void startAnimate(){
    	if(!animate){
	        animate = true;
	        new Thread(this).start();
    	} 
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Cursor Hack");

        JButton button = new JButton("Start Animation");
        
        //final AnimatedCursor animatedCursor = new AnimatedCursor(frame);
        final AnimatedCursor animatedCursor = AnimatedCursor.getInstance();
        animatedCursor.setFrame(frame);
        
        button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				animatedCursor.startAnimate();
			}
		});
        
        JButton button2 = new JButton("Stop");
        button2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				animatedCursor.stopAnimate();
			}
		});

        JPanel panel = new JPanel();
        panel.add(button);
        panel.add(button2);
        frame.getContentPane().add(panel);
        
        frame.pack();
        frame.setVisible(true);
    }

	/**
	 * @param animate the animate to set
	 */
	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	/**
	 * @return the animate
	 */
	public boolean isAnimate() {
		return animate;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

}

