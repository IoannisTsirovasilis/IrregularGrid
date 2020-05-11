package gr.ds.unipi.irregularGrid;

import java.util.Arrays;

import gr.ds.unipi.irregularGrid.util.Points;

public class IrregularGrid {
	private final Cell[][] cells;
	
	public IrregularGrid(double xMin, double yMin, double xMax, double yMax, int averagePointsPerCell, Point[] points) {
		double dx = xMax - xMin;
		double dy = yMax - yMin;
		double a = dx / dy;
		int xSplits = (int) Math.round(Math.sqrt(points.length / (a * averagePointsPerCell)));
		int ySplits = (int) Math.round(a * xSplits);
		
		int xStripNumberOfPoints = points.length / xSplits; 
		int yStripNumberOfPoints = points.length / ySplits; 
		cells = new Cell[ySplits][xSplits];
		
		long startTime = System.nanoTime();        
		Points.sort(points, Point.XComparator, 0);
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("Sort along x execution time (ms): "  + elapsedTime/1000000);
		
		startTime = System.nanoTime();        
		Points.sort(points, Point.YComparator, 1);
		elapsedTime = System.nanoTime() - startTime;
		System.out.println("Sort along y execution time (ms): "  + elapsedTime/1000000);
		int row;
		int col;
		int k = 0;
		for (int i = 0; i < points.length; i++) {
			row = points[i].getXYRank()[1] / yStripNumberOfPoints; 
			col = points[i].getXYRank()[0] / xStripNumberOfPoints; 
			if (row >= ySplits) row = ySplits - 1;
			if (col >= xSplits) col = xSplits - 1;
			
			if (cells[row][col] == null) {
				cells[row][col] = new Cell(k++);
			}
			cells[row][col].getPoints().add(points[i]);
		}
		
		int ep = 0;
		int nCells = 0;
		int unused = 0;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j] != null) {
					ep += cells[i][j].getPoints().size();
					System.out.println(cells[i][j].toString());
					nCells++;
				} else {
					unused++;
				}
			}
		}
		
		/*System.out.println(String.format("Total points assigned to cells: %d", ep));
		System.out.println(String.format("Total cells of grid: %d", xSplits * ySplits));
		System.out.println(String.format("Total cells used: %d", nCells));
		System.out.println(String.format("Total cells unused: %d", unused));
		System.out.println(String.format("Grid shape: %d x %d", ySplits, xSplits));*/
	}
}
