package Drip;

import config.ConfigLoader;
import config.DripConfig;
import util.*;

import java.awt.*;
import java.util.HashSet;

/**
 * Factory class to ease random generation of DripInfo instances.
 * Loads from DripConfig
 * 
 * @see DripConfig
 * @see DripInfo
 * 
 * @author Benjamin Steenhoek
 * @version 1
 */
class DripFactory {
    private DoubleRange numberOfDrips;
    private DoubleRange size;
    private DoubleRange lateralVelocity;
    private DoubleRange verticalVelocity;
    private Point dripPoint;
    private Vector2f gravity;
    private int lifeTimeMS;

    DripFactory() {
        numberOfDrips = DripConfig.getNumberOfDrips();
        size = DripConfig.getSize();
        lateralVelocity = DripConfig.getLateralVelocity();
        verticalVelocity = DripConfig.getVerticalVelocity();
        lifeTimeMS = DripConfig.getLifetimeMS();
    }

    /**
     * Generates a set of new drips based on the instance variables of this factory.
     */
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
        }

        return newDrips;
    }

//region Getters & Setters
//---------------------------------------------------------------------------------------
    void setDripPoint(Point point)
    {
        dripPoint = point;
    }

    void setGravity(Vector2f gravity)
    {
        this.gravity = gravity;
    }
//---------------------------------------------------------------------------------------
//endregion

}
