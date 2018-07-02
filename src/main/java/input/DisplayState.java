package input;

import config.DripConfig;

/**
 * Singleton state class to centralize display state.
 * 
 * @see Singleton
 * 
 * @author Benjamin Steenhoek
 * @version 1
 */
public class DisplayState {
    private int FPS;
    private static DisplayState instance;

    private DisplayState()
    {
        FPS = DripConfig.getFPS();
    }

    private static void initSingleton()
    {
        if (instance == null)
        {
            instance = new DisplayState();
        }
    }

    public static int getMSInterval()
    {
        initSingleton();
        return 1000 / instance.FPS;
    }
}
