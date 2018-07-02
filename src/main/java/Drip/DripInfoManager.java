package Drip;

import util.DripSet;
import util.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * Manages DripInfo creation, updating, and removal.
 * 
 * @see DripInfo
 * 
 * @author Benjamin Steenhoek
 * @version 1
 */
class DripInfoManager {
    private Timer addDripTimer;
    private Timer stepTimer;
    private DripSet drips;
    private DripFactory dripFactory;
    private Rectangle bounds;

    DripInfoManager()
    {
        drips = new DripSet();
        dripFactory = new DripFactory();
    }

    private void addNewDrips()
    {
        HashSet<DripInfo> newDrips = dripFactory.generateNewDrips();
        drips.union(newDrips);
    }

    private void stepAllDrips()
    {
        drips.forEach(drip::stepOnce);
        drips.removeIf(this::shouldRemove);
    }

    private boolean shouldRemove(DripInfo drip)
    {
        return drip.shouldRemove() || drip.isOld() || drip.isOutsideBounds(bounds);
    }

//region Timers
//---------------------------------------------------------------------------------------
    void initStepTimer(int intervalMS)
    {
        stepTimer = new Timer(intervalMS, e -> stepAllDrips());
        stepTimer.setInitialDelay(0);
    }

    void initAddDripTimer(int intervalMS)
    {
        addDripTimer = new Timer(intervalMS, e -> addNewDrips());
        addDripTimer.setInitialDelay(0);
    }

    void StartDripping()
    {
        if (!addDripTimer.isRunning()) {
            addDripTimer.start();
        }
    }

    void StartStepping()
    {
        if (!stepTimer.isRunning())
        {
            stepTimer.start();
        }
    }

    void StopDripping()
    {
        if (addDripTimer.isRunning()) {
            addDripTimer.stop();
        }
    }
//---------------------------------------------------------------------------------------
//endregion

//region Getters & Setters
//---------------------------------------------------------------------------------------
    void setGravity(Vector2f gravity)
    {
        dripFactory.setGravity(gravity);
    }

    void setBounds(Rectangle bounds)
    {
        this.bounds = bounds;
    }

    void setDripPoint(Point point)
    {
        dripFactory.setDripPoint(point);
    }

    HashSet<DripInfo> getDrips() {
        return drips;
    }
//---------------------------------------------------------------------------------------
//endregion

}
