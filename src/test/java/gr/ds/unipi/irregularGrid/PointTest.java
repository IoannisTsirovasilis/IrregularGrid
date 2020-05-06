package gr.ds.unipi.irregularGrid;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PointTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PointTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PointTest.class );
    }
    
    public void testEqualsAreEqual() {
    	Point p1 = new Point(1, 2);
    	Point p2 = new Point(1, 2);
    	
    	assertEquals(true, p1.equals(p2));
    }
    
    public void testEqualsAreNotEqualByY() {
    	Point p1 = new Point();
    	Point p2 = new Point(0, 1);
    	
    	assertEquals(false, p1.equals(p2));
    }
    
    public void testEqualsAreNotEqualByX() {
    	Point p1 = new Point();
    	Point p2 = new Point(1, 0);
    	
    	assertEquals(false, p1.equals(p2));
    }
    
    public void testEqualsAreNotEqualByXY() {
    	Point p1 = new Point(1, 2);
    	Point p2 = new Point(2, 1);
    	
    	assertEquals(false, p1.equals(p2));
    }
    
    public void testXComparatorReturnsCorrectSign() {
    	Point p1 = new Point(1, 2);
    	Point p2 = new Point(2, 1);
    	
    	assertEquals(1, Point.XComparator.compare(p2, p1));
    	assertEquals(-1, Point.XComparator.compare(p1, p2));
    	assertEquals(0, Point.XComparator.compare(p1, p1));
    }
    
    public void testYComparatorReturnsCorrectSign() {
    	Point p1 = new Point(1, 2);
    	Point p2 = new Point(2, 1);
    	
    	assertEquals(-1, Point.YComparator.compare(p2, p1));
    	assertEquals(1, Point.YComparator.compare(p1, p2));
    	assertEquals(0, Point.YComparator.compare(p1, p1));
    }
}
