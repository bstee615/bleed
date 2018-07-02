package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Implements MouseMotionAdapter to update input state.
 * 
 * @see InputState
 * 
 * @author Benjamin Steenhoek
 * @version 1
 */
public class MouseMoveListener extends MouseMotionAdapter {
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        InputState.setMousePosition(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        InputState.setMousePosition(e.getPoint());
    }
}
