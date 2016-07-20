import java.awt.Color;

import javax.swing.JFrame;


public class TransparentFrame extends JFrame {

	public TransparentFrame(String title){
		super(title);
		setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        getContentPane().setLayout(new java.awt.BorderLayout());
	}
	
	public TransparentFrame(){
		super();
		setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        getContentPane().setLayout(new java.awt.BorderLayout());
	}
	
	public void displayContents(){
		pack();
        setVisible(true);
	}
}
