package lord.dic1.iot.autobus.configs;


import lord.dic1.iot.autobus.tools.FileManager;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private final Properties configProperties;
    private final String basePath = "/smartautobus/src/main/java/resources/config.properties";

    protected ConfigurationManager() {
        String currentPath = FileManager.getInstance().getAbsolutePath()+basePath;
        configProperties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(currentPath);
            configProperties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) instance = new ConfigurationManager();
        return instance;
    }

    public String getConfigValue(String key) {
        return configProperties.getProperty(key);
    }
}