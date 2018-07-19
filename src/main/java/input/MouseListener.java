package input;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseInputAdapter {
    private InputManager inputManager;

    public MouseListener(InputManager inputManager)
    {
        this.inputManager = inputManager;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        inputManager.setMouseDown();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        inputManager.setMouseUp();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        inputManager.setMousePosition(e.getPoint());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        inputManager.setMouseUp();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        inputManager.setMousePosition(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        inputManager.setMousePosition(e.getPoint());
    }
}
