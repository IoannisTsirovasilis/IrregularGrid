package gr.ds.unipi.irregularGrid;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import gr.ds.unipi.irregularGrid.util.Points;

public class PointsTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PointsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PointsTest.class );
    }
    
    public void testSortX() {
    	Point p1 = new Point(1, 2, 1, 0, 1);
    	Point p2 = new Point(-11, 2, 2, 1, 2);
    	Point p3 = new Point(5, 2, 3, 2, 3);
    	Point p4 = new Point(-2, 2, 4, 3, 4);
    	
    	Point sp1 = new Point(-11, 2, 2, 0, 2);
    	Point sp2 = new Point(-2, 2, 4, 1, 4);
    	Point sp3 = new Point(1, 2, 1, 2, 1);
    	Point sp4 = new Point(5, 2, 3, 3, 3);
    	
    	Point[] a = {p1, p2, p3, p4};
    	
    	Point[] sorted = {sp1, sp2, sp3, sp4};
    	
    	Points.sort(a, Point.XComparator, 0);
    	
    	for (int i = 0; i < a.length; i++) {
    		assertEquals(true, a[i].deepEquals(sorted[i]));
    	}
    	
    }
    
    public void testSortY() {
    	Point p1 = new Point(1, 10, 1, 1, 0);
    	Point p2 = new Point(-11, 5, 2, 2, 1);
    	Point p3 = new Point(5, 2, 3, 3, 2);
    	Point p4 = new Point(-2, 22, 4, 4, 3);
    	
    	Point sp1 = new Point(5, 2, 3, 3, 0);
    	Point sp2 = new Point(-11, 5, 2, 2, 1);
    	Point sp3 = new Point(1, 10, 1, 1, 2);
    	Point sp4 = new Point(-2, 22, 4, 4, 3);
    	
    	Point[] a = {p1, p2, p3, p4};
    	
    	Point[] sorted = {sp1, sp2, sp3, sp4};
    	Points.sort(a, Point.YComparator, 1);
    	
    	for (int i = 0; i < a.length; i++) {
    		assertEquals(true, a[i].deepEquals(sorted[i]));
    	}    	
    }
    
    public void testSortY100000Point() {
    	int length = 100000;
    	int axis = 1;
    	Point[] a = new Point[length];
    	for (int i = 0; i < length; i++) {
    		a[i] = new Point(0, Math.random() * length * 10000, i, i, i);
    	}
    	Points.sort(a, Point.YComparator, axis);
    	for (int i = 1; i < a.length; i++) {
    		
    		assertEquals(true, a[i - 1].getY() <= a[i].getY() && a[i - 1].getXYRank()[axis] == a[i].getXYRank()[axis] - 1);
    	}    
    }
    
    public void testSortX100000Point() {
    	int length = 100000;
    	int axis = 0;
    	Point[] a = new Point[length];
    	for (int i = 0; i < length; i++) {
    		a[i] = new Point(0, Math.random() * length * 10000, i, i, i);
    	}
    	Points.sort(a, Point.YComparator, axis);
    	for (int i = 1; i < a.length; i++) {
    		assertEquals(true, a[i - 1].getY() <= a[i].getY() && a[i - 1].getXYRank()[axis] == a[i].getXYRank()[axis] - 1);
    	}    
    }
}

