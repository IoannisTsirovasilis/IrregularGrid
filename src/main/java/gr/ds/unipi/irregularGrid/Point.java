package gr.ds.unipi.irregularGrid;

import java.util.Comparator;

public class Point {
	private final double x;
	private final double y;
	private final int id;
	
	// Represents the order of the point along x (position 0) and y (position 1) axes
	// An int dim; can be added to make this functionality generic
	private final int[] xyRank;
	
	public Point() {
		id = 0;
		x = 0;
		y = 0;
		xyRank = new int[2];
	}
	
	public Point(double x, double y) {
		id = 0;
		this.x = x;
		this.y = y;
		xyRank = new int[2];
	}
	
	public Point(double x, double y, int id) {
		this.id = id;
		this.x = x;
		this.y = y;
		xyRank = new int[2];
	}
	
	public Point(double x, double y, int id, int xRank, int yRank) {
		this.id = id;
		this.x = x;
		this.y = y;
		xyRank = new int[2];
		xyRank[0] = xRank;
		xyRank[1] = yRank;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getId() {
		return id;
	}
	
	public int[] getXYRank() {
		return xyRank;
	}
	
	public void setXYRank(int n1, int n2) {
		// Rank cannot be less that zero
		if (n1 < 0 || n2 <0) {
			return;
		}
		
		xyRank[0] = n1;
		xyRank[1] = n2;
	}
	
	// Should add exception handling instead of just "return;"
	public void setRankOnAxis(int n, int axis) {
		if (n < 0 || (axis != 0 && axis != 1)) {
			return;
		}
		
		xyRank[axis] = n;
	}
	
	// Should add exception handling instead of just "return;"
	public void setXRank(int n) {
		if (n < 0) {
			return;
		}
		
		xyRank[0] = n;
	}
	
	// Should add exception handling instead of just "return;"
	public void setYRank(int n) {
		if (n < 0) {
			return;
		}
		
		xyRank[1] = n;
	}
	
	public boolean deepEquals(Point p) {
		return x == p.x && y == p.y && id == p.id && xyRank[0] == p.xyRank[0] && xyRank[1]  == p.xyRank[1]; 
	}
	
	public boolean equals(Point p) {
		return x == p.x && y == p.y;
	}
	
	@Override
	public String toString() {
		return  String.format("(%f, %f), Id=%d, RankX=%d, RankY=%d", x, y, id, xyRank[0], xyRank[1]);
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
