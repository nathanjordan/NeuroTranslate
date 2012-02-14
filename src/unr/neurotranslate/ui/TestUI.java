package unr.neurotranslate.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestUI implements Runnable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TestUI t = new TestUI();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(t);
		
		}
	
	@Override
	public void run() {
        // Create the window
        JFrame f = new JFrame ("Hello, World!");
        // Sets the behaviour for when the window is closed
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add a label and a button
        f.getContentPane().add(new JLabel("Hello, world!"));
        f.getContentPane().add(new JButton("Press me!"));
        f.getContentPane().add(new JButton("Press me!"));
        f.getContentPane().add(new JButton("Press me!"));
        f.getContentPane().add(new JButton("Press me!"));
        f.getContentPane().add(new JButton("Press me!"));
        // arrange the components inside the window
        f.pack();
        //By default, the window is not visible. Make it visible.
        f.setVisible(true);
    	}

}
