package input;

import java.awt.*;

/**
 * Singleton state class to centralize input state.
 * 
 * @see Singleton
 * 
 * @author Benjamin Steenhoek
 * @version 1
 */
public class InputState {
    private boolean mouseDown;
    private Point mousePosition;

    private static InputState instance;

    private InputState()
    {
        mouseDown = false;
        mousePosition = new Point(0, 0);
    }

    private static void initSingleton()
    {
        if (instance == null)
        {
            instance = new InputState();
        }
    }

    static void setMousePosition(Point mousePosition)
    {
        initSingleton();
        instance.mousePosition = mousePosition;
    }

    static void setMouseDown(boolean mouseDown)
    {
        initSingleton();
        instance.mouseDown = mouseDown;
    }

    public static Point getMousePosition()
    {
        initSingleton();
        return instance.mousePosition;
    }

    public static boolean getMouseDown()
    {
        initSingleton();
        return instance.mouseDown;
    }
}
