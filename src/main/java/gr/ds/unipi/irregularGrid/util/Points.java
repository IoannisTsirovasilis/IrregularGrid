package gr.ds.unipi.irregularGrid.util;

import java.util.Comparator;

import gr.ds.unipi.irregularGrid.Point;

public class Points {
	private Points() {}
	
	public static void sort(Point[] a, Comparator<? super Point> c, int axis) throws IllegalArgumentException {
		if (axis != 0 && axis != 1) {
			throw new IllegalArgumentException("Valued provided for axis parameter is not valid. Allowed values: 0 (x-axis), 1 (y-axis).");
		}
		TimSort.sort(a, c, axis);
	}
}
