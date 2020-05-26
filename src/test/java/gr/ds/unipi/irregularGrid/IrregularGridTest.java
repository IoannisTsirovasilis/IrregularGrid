package gr.ds.unipi.irregularGrid;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class IrregularGridTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public IrregularGridTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( IrregularGridTest.class );
    }
    
    public void testXYOffsetBuildProperly() {
    	int length = 1_000_000;
    	Point[] a = new Point[length];
    	int pointsPerCell = 1_000;
    	double minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
    	for (int i = 0; i < a.length; i++) {
    		double x = Math.random() * 10;
    		double y = Math.random() * 20;
    		
    		if (x < minX) minX = x;
    		if (x > maxX) maxX = x;
    		if (y < minY) minY = y;
    		if (y > maxY) maxY = y;    		
    		
    		a[i] = new Point(x, y, i, i, i);
    	}
    	IrregularGrid ig = new IrregularGrid(minX, minY, maxX, maxY, pointsPerCell, a);    	
    	Cell[][] cells = ig.getCells();
    	double[] xOffset = ig.getXOffset();
    	double[] yOffset = ig.getYOffset();
    	for (int i = 0; i < cells.length; i++) {
        	for (int j = 0; j < cells[0].length; j++) {
        		if (cells[i][j] == null) continue;
        		System.out.println(String.format("X => [%f, %f], Y => [%f, %f]", xOffset[j],
						xOffset[j + 1], yOffset[i],yOffset[i + 1]));
        		for (Point p : cells[i][j].getPoints()) {    				
        			System.out.println(String.format("(x, y) => (%f, %f)", p.getX(), p.getY()));
        			boolean isInCell =  p.getX() >= xOffset[j] && p.getX() <= xOffset[j + 1]
            				&& p.getY() >= yOffset[i] && p.getY() <= yOffset[i + 1];
            		assertEquals(true, isInCell);
        		}        		
        	}
        }
    }
    
    

}
