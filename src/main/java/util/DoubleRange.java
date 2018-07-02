package util;

/**
 * Utility class to generate random numbers in a range.
 * Range is [minimum, minimum+range).
 * 
 * @author Benjamin Steenhoek
 * @version 1
 */
public class DoubleRange {
    private double minimum;
    private double range;

    public DoubleRange(double minimum, double range)
    {
        this.minimum = minimum;
        this.range = range;
    }

    public double randomWithin()
    {
        return ((Math.random() * range) + minimum);
    }
}
