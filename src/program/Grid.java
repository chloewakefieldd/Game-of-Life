package program;

import java.util.ArrayList;

public class Grid {

	public static ArrayList<ArrayList<Integer>> cellGrid = new ArrayList<ArrayList<Integer>>();
	
	// Create a cellGrid with a given number of rows and columns
	public static void create(int numRows, int numColumns) {
		for(int i = 0; i < numRows; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for(int j = 0; j < numColumns; j++) {
				row.add(0);
			}
			cellGrid.add(row);
		}
	}

	// Return the state of the cell at a particular location
	public static int get(int row, int column) {
		return cellGrid.get(row).get(column);
	}

	// Set the state of the cell at a particular location
	public static void set(int row, int column, int value) {
		cellGrid.get(row).set(column, value);
	}
	
	// Find the number of neighbours a particular cell has
	public static int numNeighbouringCells(int row, int column) {
		int numNeighbours = 0;
		
		int startRow = row - 1;
		int startColumn = column - 1;
		int endRow = row + 1;
		int endColumn = column + 1;

		// Adjust the start/end rows/columns so that invalid positions are ignored
		startRow = startRow < 0 ? 0 : startRow;
		startColumn = startColumn < 0 ? 0 : startColumn;
		endRow = endRow > cellGrid.size() - 1 ? cellGrid.size() - 1 : endRow;
		endColumn = endColumn > cellGrid.get(0).size() - 1 ? cellGrid.get(0).size() - 1 : endColumn;
		
		// Tally up the cell's neighbours
		for(int currentRow = startRow; currentRow <= endRow; currentRow++) {
			for(int currentColumn = startColumn; currentColumn <= endColumn; currentColumn++) {
				if(!(currentRow == row && currentColumn == column)) {
					if(get(currentRow, currentColumn) == 1) {
						numNeighbours++;
					}
				}
			}
		}
		return numNeighbours;
	}
	
	// Make a copy, TempGrid.cellGrid, of Grid.cellGrid
	public static void copyTempCellGrid() {
		
		int numRows = TempGrid.cellGrid.size();
		int numColumns =  TempGrid.cellGrid.get(0).size();
		
		cellGrid = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < numRows; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for(int j = 0; j < numColumns; j++) {
				row.add(TempGrid.get(i, j));
			}
			cellGrid.add(row);
		}
	}
	
	// Print the cellGrid to console (formatted)
	public static void print() {
		for (int row = 0; row < cellGrid.size(); row++) {
			for (int column = 0; column < cellGrid.get(0).size(); column++) {
				System.out.print(cellGrid.get(row).get(column)+" ");
			}
			System.out.println();
		}
	}
	
	// Check if a cell is alive on the border of the grid. If so, expand the grid in all directions by one
	public static void expandIfNecessary() {
		boolean hasReachedEdge = false;
		for (int row = 0; row < cellGrid.size(); row++) {
			for (int column = 0; column < cellGrid.get(0).size(); column++) {
				if(row == 0 || column == 0 || row == cellGrid.size() - 1 || column == cellGrid.get(0).size() - 1) {
					if(get(row, column) == 1) {
						hasReachedEdge = true;
						break;
					}
				}
			}
		}
		if(hasReachedEdge) {
			expand();
		}
	}
	
	// Expand the grid in all directions by one
	public static void expand() {
		for (int row = 0; row < cellGrid.size(); row++) {
			cellGrid.get(row).add(0, 0);
			cellGrid.get(row).add(cellGrid.get(row).size(), 0);
		}
		ArrayList<Integer> emptyRowTop = new ArrayList<Integer>();
		ArrayList<Integer> emptyRowBottom = new ArrayList<Integer>();
		for (int col = 0; col < cellGrid.get(0).size(); col++) {
			emptyRowTop.add(0);
			emptyRowBottom.add(0);
		}
		cellGrid.add(0, emptyRowTop);
		cellGrid.add(cellGrid.size(), emptyRowBottom);
	}

}