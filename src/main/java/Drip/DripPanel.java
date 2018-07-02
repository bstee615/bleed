package Drip;

import config.DripConfig;
import util.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

import input.*;

/**
 * @author Benjamin Steenhoek
 * @version 1
 */
public class DripPanel extends JPanel {
    private DripInfoManager dripInfoManager;
    private Color dripColor;

    public DripPanel() {
        addMouseListeners();
        dripColor = new Color(0, 255, 0, 75);
    }

    private void doDrawing(Graphics g)
    {
        for (DripInfo dripInfo : dripInfoManager.getDrips())
        {
            drawDrip((Graphics2D)g, dripInfo);
        }
    }

    private void updateDripManager()
    {
        dripInfoManager.setBounds(getBounds());
        dripInfoManager.setDripPoint(InputState.getMousePosition());
        if (InputState.getMouseDown())
        {
            dripInfoManager.StartDripping();
        }
        else
        {
            dripInfoManager.StopDripping();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        updateDripManager();
    }

//region Drawing helpers
//---------------------------------------------------------------------------------------
    private void fillCircleAroundPoint(Graphics2D g2d, Point point, int radius) {
        int mouseX = (int)point.getX();
        int mouseY = (int)point.getY();
        g2d.fill(new Ellipse2D.Double(mouseX - radius, mouseY - radius, radius * 2, radius * 2));
    }

    private void drawDrip(Graphics2D g2d, DripInfo dripInfo)
    {
        g2d.setColor(dripColor);
        fillCircleAroundPoint(g2d, dripInfo.position.asPoint(), (int)dripInfo.radius);
    }
//---------------------------------------------------------------------------------------
//endregion

//region Swing initialization
//---------------------------------------------------------------------------------------

    // TODO FIX: This method must be called after the Panel is initialzed in Swing.
    public void initDripInfoManager()
    {
        dripInfoManager = new DripInfoManager();
        dripInfoManager.setGravity(DripConfig.getGravity());
        dripInfoManager.initStepTimer(DripConfig.getStepIntervalMS());
        dripInfoManager.initAddDripTimer(DripConfig.getAddDripIntervalMS());
        dripInfoManager.StartStepping();
    }

    private void addMouseListeners()
    {
        addMouseListener(new MouseClickListener());
        addMouseMotionListener(new MouseMoveListener());
    }
//---------------------------------------------------------------------------------------
//endregion
}
