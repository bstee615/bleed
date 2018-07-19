package util;

import java.awt.*;

public class Vector2f {
    private double x;
    private double y;

    public Vector2f(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Point point)
    {
        this(point.getX(), point.getY());
    }

    public Vector2f(Vector2f toCopy)
    {
        this(toCopy.x, toCopy.y);
    }
    
    public Vector2f plus2f(Vector2f that)
    {
        return new Vector2f(this.x + that.x, this.y + that.y);
    }

    public Vector2f minus2f(Vector2f that)
    {
        return new Vector2f(this.x - that.x, this.y - that.y);
    }

    public Point asPoint()
    {
        return new Point((int)x, (int)y);
    }

    @Override
    public String toString()
    {
        return String.format("(%.3f, %.3f)", x, y);
    }

    public static Vector2f zero()
    {
        return new Vector2f(0d, 0d);
    }
}
