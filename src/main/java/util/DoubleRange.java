package util;


public class DoubleRange {
    private double minimum;
    private double range;

    public DoubleRange(double minimum, double range)
    {
        this.minimum = minimum;
        this.range = range;
    }

    // Range is [minimum, minimum+range)
    public double randomWithin()
    {
        return ((Math.random() * range) + minimum);
    }
}
