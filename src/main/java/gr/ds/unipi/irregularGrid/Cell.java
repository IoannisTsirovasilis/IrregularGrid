package gr.ds.unipi.irregularGrid;

import java.util.ArrayList;

public class Cell {
	private final int id;
	private final ArrayList<Point> points;
	/*private final double lowerX;
	private final double lowerY;
	private final double upperX;
	private final double upperY;	
	
	public Cell(double lowerX, double lowerY, double upperX, double upperY) {
		this.lowerX = lowerX;
		this.lowerY	= lowerY;
		this.upperX = upperX;
		this.upperY = upperY;
		points = new ArrayList<Point>();
	}*/
	
	public Cell(int id) {
		this.id = id;
		points = new ArrayList<Point>();
	}
	
	/*public double getLowerX() {
		return lowerX;
	}
	public double getLowerY() {
		return lowerY;
	}
	public double getUpperX() {
		return upperX;
	}
	public double getUpperY() {
		return upperY;
	}
	*/
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	
	@Override
	public String toString() {
		return String.format("Cell: %d, Number of points: %d", id, points.size());
	}
}
