package Drip;

import util.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

import input.*;

public class DripPanel extends JPanel {
    private InputManager inputManager;
    private DripInfoManager dripInfoManager;
    private Color dripColor;

    public DripPanel(InputManager inputManager) {
        super();
        initDripPanel(inputManager);
        setBackground(new Color(0, 0, 0, 225));
    }

    private void initDripPanel(InputManager inputManager)
    {
        this.inputManager = inputManager;
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
        dripInfoManager.setDripPoint(inputManager.getMousePosition());
        if (inputManager.isMouseDown())
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

    public void initDripInfoManager()
    {
        dripInfoManager = new DripInfoManager(inputManager);
        dripInfoManager.setGravity(new Vector2f(0, 0.3d));
        dripInfoManager.initStepTimer(20);
        dripInfoManager.initAddDripTimer(20);
        dripInfoManager.StartStepping();
    }

    private void addMouseListeners()
    {
        addMouseListener(new MouseListener(inputManager));
        addMouseMotionListener(new MouseListener(inputManager));
    }

    public DripInfoManager getDripInfoManager() { return dripInfoManager; }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    void setColor(Color color) { this.dripColor = color; }
}
