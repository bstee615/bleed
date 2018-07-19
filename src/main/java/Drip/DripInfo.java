package Drip;

import util.Vector2f;

import java.awt.*;

public class DripInfo {
    Vector2f position;
    private Vector2f velocity;
    private Vector2f acceleration;
    private Vector2f friction;
    double radius;

    private long creationTime;
    private int lifeTimeMS;

    private boolean shouldRemove;

    DripInfo(Point point, double radius, Vector2f gravity, int lifeTimeMS)
    {
        position = new Vector2f(point);
        velocity = Vector2f.zero();
        acceleration = gravity;
        friction = Vector2f.zero();
        this.radius = radius;
        creationTime = System.nanoTime();
        this.lifeTimeMS = lifeTimeMS;
    }

    void stepOnce()
    {
        position = position.plus2f(velocity);
        velocity = velocity.plus2f(acceleration);
        acceleration = acceleration.minus2f(friction);
    }

    public void markToRemove() {
        shouldRemove = true;
    }

    private long NanosecondsToMilliseconds(long ns)
    {
        return ns / 1000000;
    }

    Vector2f getVelocity()
    {
        return new Vector2f(velocity);
    }

    void setVelocity(Vector2f newVelocity)
    {
        velocity = newVelocity;
    }

    boolean isOld()
    {
        return lifeTimeMS > 0 && NanosecondsToMilliseconds(System.nanoTime() - creationTime) > lifeTimeMS;
    }

    boolean isOutsideBounds(Rectangle bounds)
    {
        return bounds != null && !bounds.contains(position.asPoint());
    }

    boolean shouldRemove() {
        return shouldRemove;
    }
}
