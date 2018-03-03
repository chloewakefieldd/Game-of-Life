package program;

import java.util.ArrayList;

public class TempGrid {

	public static ArrayList<ArrayList<Integer>> cellGrid;
	

	// Make a copy, Grid.cellGrid, of TempGrid.cellGrid
	public static void copyCellGrid() {
		
		int numRows = Grid.cellGrid.size();
		int numColumns =  Grid.cellGrid.get(0).size();
		
		cellGrid = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < numRows; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for(int j = 0; j < numColumns; j++) {
				row.add(Grid.get(i, j));
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

	// Print the cellGrid to console (formatted)
	public static void print() {
		for (int row = 0; row < cellGrid.size(); row++) {
			for (int column = 0; column < cellGrid.get(0).size(); column++) {
				System.out.print(cellGrid.get(row).get(column)+" ");
			}
			System.out.println();
		}
	}

}