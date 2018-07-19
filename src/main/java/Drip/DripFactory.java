package Drip;

import util.*;

import java.awt.*;
import java.util.HashSet;

class DripFactory {
    private DoubleRange numberOfDrips;
    private DoubleRange size;
    private DoubleRange lateralVelocity;
    private DoubleRange verticalVelocity;
    private Point dripPoint;
    private Vector2f gravity;
    private int lifeTimeMS;

    DripFactory() {
        numberOfDrips = new DoubleRange(0, 10);
        size = new DoubleRange(5, 8);
        lateralVelocity = new DoubleRange(-4d, 8d);
        verticalVelocity = new DoubleRange(0, 7d);
        lifeTimeMS = 1000;
    }

    HashSet<DripInfo> generateNewDrips()
    {
        HashSet<DripInfo> newDrips = new HashSet<>();
        if (gravity == null)
        {
            return new HashSet<DripInfo>();
        }

        int numberOfDrips = (int) this.numberOfDrips.randomWithin();
        for (; numberOfDrips > 0; numberOfDrips --)
        {
            double sizeOfDrip = size.randomWithin();
            DripInfo drip = new DripInfo(dripPoint, sizeOfDrip, gravity, lifeTimeMS);

            double lateralVelocityOfDrip = lateralVelocity.randomWithin();
            double verticalVelocityOfDrip = verticalVelocity.randomWithin();
            drip.setVelocity(drip.getVelocity().plus2f(new Vector2f(lateralVelocityOfDrip, -verticalVelocityOfDrip)));
            newDrips.add(drip);
        }

        return newDrips;
    }

    public void setNumberOfDrips(DoubleRange numberOfDrips) {
        this.numberOfDrips = numberOfDrips;
    }

    public void setSize(DoubleRange size) {
        this.size = size;
    }

    public void setLateralVelocity(DoubleRange lateralVelocity) {
        this.lateralVelocity = lateralVelocity;
    }

    public void setVerticalVelocity(DoubleRange verticalVelocity) {
        this.verticalVelocity = verticalVelocity;
    }

    public void setDripPoint(Point dripPoint) {
        this.dripPoint = dripPoint;
    }

    public void setGravity(Vector2f gravity) {
        this.gravity = gravity;
    }

    public void setLifeTimeMS(int lifeTimeMS) {
        this.lifeTimeMS = lifeTimeMS;
    }
}
