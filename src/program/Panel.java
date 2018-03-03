package program;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	
    int cellsWide = 0;
    int cellsHigh = 0;
    
    int cellWidth = 0;
    int cellHeight = 0;
	
	public Panel() {
		
		// Enable left / right click actions (add/remove cell)
		addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				// Only consider mouse presses once the grid has been set up
				if (cellsWide != 0 && cellsHigh != 0 && cellWidth != 0 && cellHeight != 0) {
					
				    int x = e.getX();
				    int y = e.getY();
		
				    int width = cellWidth * cellsWide;
				    int height = cellHeight * cellsHigh;
				    
				    // If somewhere on the grid was clicked
				    if (x < width && y < height) {
				    	
				    	// Find the fraction vertically / horizontally (e.g. for horizontal, 0 is leftmost, 0.5 is middle, 1 is rightmost)
					    double fractionH = x / (float) width;
					    double fractionV = y / (float) height;
					    
					    // Determine what row/column was clicked
					    int row = (int) Math.floor(fractionV*cellsHigh);
					    int column = (int) Math.floor(fractionH*cellsWide);
					    
					    if(e.isMetaDown()) {
					    	// If right click, remove cell at (row, column)
						    Grid.set(row, column, 0);
					    } else {
					    	// If left click (or other), add cell at (row, column)
						    Grid.set(row, column, 1);
					    }
					    
					    // Update display to show changes
					    repaint();
				    
				    }
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		// Enable exit and proceed actions
		addKeyListener(new KeyListener() {
			@Override
			
			// If a key has been pressed
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
			    switch( keyCode ) {
				case 10:
				case 32:
					Main.run(); // [Enter] or [Space] to proceed one timestep
    				break;
				case 27:
    				System.exit(0); // [Esc] exits the program
    				break;
				default:
					break;
				}
		    }
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
         });
		
	}
	

	// Display Grid.cellGrid on screen
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        clearScreen(g);

        cellsWide = Grid.cellGrid.get(0).size();
        cellsHigh = Grid.cellGrid.size();
        
        cellWidth = Window.screenWidth / cellsWide;
        cellHeight = Window.screenHeight / cellsHigh;
                
        // For each cell
		for (int row = 0; row < Grid.cellGrid.size(); row++) {
			for (int column = 0; column < Grid.cellGrid.get(0).size(); column++) {
				
				// Paint every second cell light blue to act as a visual aid
				// for showing the size of the grid and the relative positions of the cells
				if((row*cellsWide+column)%2 == 0) {
					g.setColor(new Color(232/255f, 242/255f, 254/255f, 1));
					g.fillRect(column*cellWidth, row*cellHeight, cellWidth, cellHeight);
				}
				
				//Paint the cell black in the appropriate location if it's alive
				if(Grid.get(row, column) == 1) {
					paintCell(g,column*cellWidth,row*cellHeight,cellWidth,cellHeight);
				}
				
			}
		}
		
	}
	
	// Paint a cell black in a given location with a given height/width
	public void paintCell(Graphics g, int x, int y, int dx, int dy) {
		g.setColor(new Color(0f, 0f, 0f, 1));
		g.fillRect(x, y, dx, dy);
	}
	
	// Reset screen to white
	public void clearScreen(Graphics g) {
		g.setColor(new Color(1f, 1f, 1f, 1));
		g.fillRect(0, 0, Window.screenWidth, Window.screenHeight);
	}
	

}
