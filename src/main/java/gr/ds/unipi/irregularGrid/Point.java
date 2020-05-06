package gr.ds.unipi.irregularGrid;

import java.util.Comparator;

public class Point {
	private final double x;
	private final double y;
	
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	

	public boolean equals(Point p) {
		return x == p.x && y == p.y;
	}
	
	public static Comparator<Point> XComparator = new Comparator<Point>() {		
		@Override
		public int compare(Point p1, Point p2) {
			// Returns the sign of the difference
			return (int) Math.signum(p1.x - p2.x);
		}
	};
	
	public static Comparator<Point> YComparator = new Comparator<Point>() {		
		@Override
		public int compare(Point p1, Point p2) {
			// Returns the sign of the difference
			return (int) Math.signum(p1.y - p2.y);
		}
	};
}
