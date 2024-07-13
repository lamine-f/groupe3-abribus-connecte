package lord.dic1.iot.autobus.configs;


import lord.dic1.iot.autobus.tools.FileManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MqttConfigurationManager extends ConfigurationManager {
    private static MqttConfigurationManager instance;
    private final Properties configProperties;
    private final String basePath = "/smartautobus/src/main/java/resources/mqttconfig.properties";

    private MqttConfigurationManager() {
        super();
        configProperties = new Properties();
        String currentPath = FileManager.getInstance().getAbsolutePath()+basePath;
        try {
            FileInputStream fis = new FileInputStream(currentPath);
            configProperties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MqttConfigurationManager getInstance() {
        if (instance == null) instance = new MqttConfigurationManager();
        return instance;
    }

    public String getConfigValue(String key) {
        return configProperties.getProperty(key);
    }
}