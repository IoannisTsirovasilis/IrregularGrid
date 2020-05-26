package gr.ds.unipi.irregularGrid;

import gr.ds.unipi.irregularGrid.util.Points;

public class IrregularGrid {
	private final Cell[][] cells;
	
	// Arrays that maintain the boundaries of the cells along x,y-axes
	private final double[] xOffset;
	private final double[] yOffset;
	
	public IrregularGrid(double xMin, double yMin, double xMax, double yMax, int averagePointsPerCell, Point[] points) {
		double dx = xMax - xMin;
		double dy = yMax - yMin;
		double a = dx / dy;
		int xSplits = (int) Math.round(Math.sqrt(points.length / (a * averagePointsPerCell)));
		if (xSplits == 0) xSplits = 1;
		int ySplits = (int) Math.round(a * xSplits);
		
		int xStripNumberOfPoints = points.length / xSplits; 
		int yStripNumberOfPoints = points.length / ySplits; 
		
		xOffset = new double[xSplits + 1];
		for (int i = 0; i < xOffset.length - 1; i++) {
			xOffset[i] = Integer.MAX_VALUE;
		}		
		
		yOffset = new double[ySplits + 1];
		
		for (int i = 0; i < yOffset.length - 1; i++) {
			yOffset[i] = Integer.MAX_VALUE;
		}
		
		xOffset[xSplits] = xMax;
		yOffset[ySplits] = yMax;
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
		int cellId = 0;
		for (int i = 0; i < points.length; i++) {			
			row = points[i].getXYRank()[1] / yStripNumberOfPoints; 
			col = points[i].getXYRank()[0] / xStripNumberOfPoints;
			
			if (row >= ySplits) row = ySplits - 1;
			if (col >= xSplits) col = xSplits - 1;
			
			if (points[i].getY() < yOffset[row]) {
				yOffset[row] = points[i].getY();
			}
			
			if (points[i].getX() < xOffset[col]) {
				xOffset[col] = points[i].getX();
			}
			
			
			if (cells[row][col] == null) {
				cells[row][col] = new Cell(cellId++);
			}
			cells[row][col].getPoints().add(points[i]);
		}
		
		double meanPoints = 0;
		double varPoints = 0;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j] != null) {
					meanPoints += cells[i][j].getPoints().size();
					// System.out.println(cells[i][j].toString());
				}
			}
		}
		
		meanPoints /= ySplits * xSplits;
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j] != null) {
					varPoints += Math.pow(cells[i][j].getPoints().size() - meanPoints, 2);
					// System.out.println(cells[i][j].toString());
				} else {
					varPoints += Math.pow(meanPoints, 2);
				}
			}
		}
		
		varPoints /= ySplits * xSplits;
		
		System.out.println(String.format("Total cells of grid: %dx%d=%d", xSplits, ySplits,xSplits * ySplits));
		System.out.println(String.format("Mean points per cell: %f", meanPoints));
		System.out.println(String.format("Variance: %f", Math.sqrt(varPoints)));
		
		System.out.print("Y: [");
		for (int j = 0; j < yOffset.length; j++) {
			System.out.print(String.format("%f, ", yOffset[j]));
		}
		System.out.println("]");
		System.out.print("X: [");
		for (int j = 0; j < xOffset.length; j++) {
			System.out.print(String.format("%f, ", xOffset[j]));
		}
		System.out.println("]");
		/*System.out.println(String.format("Total points assigned to cells: %d", ep));
		System.out.println(String.format("Total cells of grid: %d", xSplits * ySplits));
		System.out.println(String.format("Total cells used: %d", nCells));
		System.out.println(String.format("Total cells unused: %d", unused));
		System.out.println(String.format("Grid shape: %d x %d", ySplits, xSplits));*/
	}
	
	public double[] getXOffset() {
		return xOffset;
	}
	
	public double[] getYOffset() {
		return yOffset;
	}
	
	public Cell[][] getCells() {
		return cells;
	}
}
