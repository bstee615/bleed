package Drip;

import input.InputManager;
import util.DoubleRange;
import util.DripSet;
import util.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

class DripInfoManager {
    private InputManager inputManager;
    private Timer addDripTimer;
    private Timer stepTimer;
    private DripSet drips;
    private DripFactory dripFactory;
    private Rectangle bounds;

    DripInfoManager(InputManager inputManager)
    {
        this.inputManager = inputManager;
        drips = new DripSet(100);
        dripFactory = new DripFactory();
    }

    private void addNewDrips()
    {
        HashSet<DripInfo> newDrips = dripFactory.generateNewDrips();
        drips.union(newDrips);
    }

    private void stepAllDrips()
    {
        drips.forEach(DripInfo::stepOnce);
        drips.removeIf(this::shouldRemove);
    }

    private boolean shouldRemove(DripInfo drip)
    {
        return drip.shouldRemove() || drip.isOld() || drip.isOutsideBounds(bounds);
    }

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

    public void setNumberOfDrips(DoubleRange numberOfDrips) {
        dripFactory.setNumberOfDrips(numberOfDrips);
    }

    public void setSize(DoubleRange size) {
        dripFactory.setSize(size);
    }

    public void setLateralVelocity(DoubleRange lateralVelocity) {
        dripFactory.setLateralVelocity(lateralVelocity);
    }

    public void setVerticalVelocity(DoubleRange verticalVelocity) {
        dripFactory.setVerticalVelocity(verticalVelocity);
    }

    public void setLifeTimeMS(int lifeTimeMS) {
        dripFactory.setLifeTimeMS(lifeTimeMS);
    }

    public void setMaxElements(int maxElements) { drips.setMaxElements(maxElements); }
}
