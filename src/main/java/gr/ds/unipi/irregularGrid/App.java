package gr.ds.unipi.irregularGrid;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	int length = 10_000_000;
    	int pointsPerCell = 10_000;
    	Point[] points = new Point[length];
    	double minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
    	double x, y;
    	for (int i = 0; i < length; i++) {
    		x = Math.random() * 10;
    		y = Math.random() * 20;
    		if (x < minX) minX = x;
    		if (x > maxX) maxX = x;
    		if (y < minY) minY = y;
    		if (y > maxY) maxY = y;
    		points[i] = new Point(x, y, i, i, i);
    	}
    	long startTime = System.nanoTime();
        new IrregularGrid(minX, minY, maxX, maxY, pointsPerCell, points);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(String.format("Min X: %f, Min Y: %f, Max X: %f, Max Y: %f", minX, minY, maxX, maxY));
        System.out.println("Total execution time (ms): "  + elapsedTime/1000000);

    }
}
