package program;

public class Main {
	
	public static Window window = new Window();
	
	public static void main(String[] args) {
		Grid.create(31, 31); // Create a 21x21 grid
	}
	
	public static void run() {
		Grid.expandIfNecessary(); //If a cell is alive at the grid border, expand the grid
		TempGrid.copyCellGrid(); //Copy grid to tempgrid
		
		// For each cell
		for (int row = 0; row < Grid.cellGrid.size(); row++) {
			for (int column = 0; column < Grid.cellGrid.get(0).size(); column++) {
				
				// Find the number of neighbours it has, and take the appropriate action for that cell
				switch (Grid.numNeighbouringCells(row, column)) {
					case 0:
					case 1:
						if (Grid.get(row, column) == 1) {
							TempGrid.set(row, column, 0); // Underpopulation
						}
						break;
					case 2:
						if (Grid.get(row, column) == 1) {
							TempGrid.set(row, column, 1); // Survival
						}
						break;
					case 3:
						if (Grid.get(row, column) == 1) {
							TempGrid.set(row, column, 1); // Survival
						} else if (Grid.get(row, column) == 0) {
							TempGrid.set(row, column, 1); // Creation of Life
						}
						break;
					case 4:
					case 5:
					case 6:
					case 7:
					case 8:
						if (Grid.get(row, column) == 1) {
							TempGrid.set(row, column, 0); //Overcrowding
						}
						break;
					default:
						break;
				}
			}
		}
		Grid.copyTempCellGrid(); // Copy tempgrid to grid
		window.update(); // Update the display
	}

}