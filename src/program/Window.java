package program;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	public static int screenWidth;
	public static int screenHeight;
	
	Panel panel = null;
	
	
	public Window() {
		
		// Remove title bar / borders
		setUndecorated(true);
		
		// Find the current screen resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screenSize.width;
		screenHeight = screenSize.height;
		
		// Create a new panel that fills the screen
		panel = new Panel();
		panel.setPreferredSize(screenSize);
		// Enable keyboard controls on the panel
		panel.setFocusable(true);
		add(panel);

		setTitle("Game of Life");
		// Close the process on program close
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		pack();
		
		// Centre the window
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// Update the display
	public void update() {
		panel.repaint();
	}

}
