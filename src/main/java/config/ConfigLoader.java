package config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

/**
 * Configuration loader for simple JSON configuration files.
 * Loads from "$CWD/config/" by default.
 * 
 * @see Singleton
 * 
 * @author Benjamin Steenhoek
 * @version 1
 */
public class ConfigLoader {
    private Gson gson;
    private GsonBuilder gsonBuilder;
    private static ConfigLoader instance;

    private ConfigLoader()
    {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    private static void initSingleton()
    {
        if (instance == null)
        {
            instance = new ConfigLoader();
        }
    }

    private static FileReader loadJSON(String filename)
    {
        try {
            return new FileReader(filename);
        }
        catch (FileNotFoundException ex) {
            System.err.println("File not found.");
            return null;
        }
    }

    static <T> T loadConfig(Type typeToLoad)
    {
        initSingleton();

        FileReader fr = loadJSON("config/" + typeToLoad.getTypeName() + ".json");
        if (fr != null)
        {
            return instance.gson.fromJson(fr, typeToLoad);
        }
        return null;
    }
}
