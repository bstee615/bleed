package config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sun.security.krb5.Config;
import util.DoubleRange;
import util.Vector2f;

/**
 * Singleton template class for loading info from JSON config files.
 * 
 * @see Singleton
 * 
 * @author Benjamin Steenhoek
 * @version 1
 */
public class DripConfig {
    // DripFactory
    private int lifetimeMS;
    private DoubleRange numberOfDrips;
    private DoubleRange size;
    private DoubleRange lateralVelocity;
    private DoubleRange verticalVelocity;

    // DripPanel
    private Vector2f gravity;
    private int stepIntervalMS;
    private int addDripIntervalMS;

    // General
    private int FPS;

    public static int getLifetimeMS (){initSingleton();return instance.lifetimeMS;}
    public static DoubleRange getNumberOfDrips (){initSingleton();return instance.numberOfDrips;}
    public static DoubleRange getSize (){initSingleton();return instance.size;}
    public static DoubleRange getLateralVelocity (){initSingleton();return instance.lateralVelocity;}
    public static DoubleRange getVerticalVelocity (){initSingleton();return instance.verticalVelocity;}
    public static Vector2f getGravity (){initSingleton();return instance.gravity;}
    public static int getStepIntervalMS (){initSingleton();return instance.stepIntervalMS;}
    public static int getAddDripIntervalMS (){initSingleton();return instance.addDripIntervalMS;}
    public static int getFPS(){initSingleton();return instance.FPS;}

    private static DripConfig instance;

    private static void initSingleton()
    {
        if (instance == null)
        {
            instance = ConfigLoader.loadConfig(DripConfig.class);
        }
    }

    public static void main(String[] args)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        DripConfig dripConfig = new DripConfig();
        dripConfig.numberOfDrips = new DoubleRange(0, 3);
        dripConfig.size = new DoubleRange(5, 10);
        dripConfig.lateralVelocity = new DoubleRange(-2d, 4d);
        dripConfig.verticalVelocity = new DoubleRange(0, 4d);
        dripConfig.lifetimeMS = 0;

        dripConfig.gravity = new Vector2f(0, 0.3d);
        dripConfig.stepIntervalMS = 10;
        dripConfig.addDripIntervalMS = 50;

        dripConfig.FPS = 32;

        System.out.println(gson.toJson(dripConfig));
    }
}
