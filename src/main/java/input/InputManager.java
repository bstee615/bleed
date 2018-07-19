package input;

import java.awt.*;

public class InputManager {
    private boolean mouseDown;
    private Point mousePosition;

    public boolean isMouseDown() {
        return mouseDown;
    }

    void setMouseDown() {
        this.mouseDown = true;
    }

    void setMouseUp() {
        this.mouseDown = false;
    }

    public Point getMousePosition() {
        return mousePosition;
    }

    void setMousePosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }
}
