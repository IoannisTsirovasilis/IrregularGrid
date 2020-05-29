package gr.ds.unipi.irregularGrid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
	final static public Random RANDOM = new Random(System.currentTimeMillis());
	
    public static void main( String[] args ) throws NumberFormatException, IOException
    {
    	int length = 10_000_000;
    	int pointsPerCell = 10_000;
    	Point[] points = new Point[length];
    	double minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
    	double x, y;
    	Random r = new Random();
    	String fileName = "C:/Users/user/Desktop/u10M.csv";    	
    	// File csvOutputFile = new File(fileName);
    	int i = 0;
    	BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
    	String row;
    	boolean isFirstLine = true;
    	while ((row = csvReader.readLine()) != null) {
    		if (isFirstLine) {
    			isFirstLine = false;
    			continue;
    		}
    	    String[] data = row.split(",");
    	    x = Double.parseDouble(data[0]);
    	    y = Double.parseDouble(data[1]);
    	    
    	    if (x < minX) minX = x;
    		if (x > maxX) maxX = x;
    		if (y < minY) minY = y;
    		if (y > maxY) maxY = y;
    		points[i] = new Point(x, y, i, i, i);
    		i++;
    	}
    	csvReader.close();
    	
    	long startTime = System.nanoTime();
        IrregularGrid grid = new IrregularGrid(minX, minY, maxX, maxY, pointsPerCell, points);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(String.format("Min X: %f, Min Y: %f, Max X: %f, Max Y: %f", minX, minY, maxX, maxY));
        System.out.println("Total execution time (ms): "  + elapsedTime/1000000);
        grid.calculateEmptyCells();
        System.out.println("Empty cells:" + grid.getNumberOfEmptyCells());
        

    }
    
    static double normalY(double x, double mean, double std) {
    	return (1 / (std * Math.sqrt(2 * Math.PI))) * Math.exp(-0.5 * Math.pow((x - mean) / std, 2));
    }

    // https://stackoverflow.com/questions/5853187/skewing-java-random-number-generation-toward-a-certain-number
    static public double nextSkewedBoundedDouble(double min, double max, double skew, double bias) {
        double range = max - min;
        double mid = min + range / 2.0;
        double unitGaussian = RANDOM.nextGaussian();
        double biasFactor = Math.exp(bias);
        double retval = mid+(range*(biasFactor/(biasFactor+Math.exp(-unitGaussian/skew))-0.5));
        return retval;
    }
}
