package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Implements Swing mouse listener to update input state.
 * 
 * @see InputState
 * 
 * @author Benjamin Steenhoek
 * @version 1
 */
public class MouseClickListener implements MouseListener {
    @Override
    public void mousePressed(MouseEvent e) {
        InputState.setMouseDown(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        InputState.setMouseDown(false);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        InputState.setMouseDown(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        InputState.setMousePosition(e.getPoint());
    }
}
