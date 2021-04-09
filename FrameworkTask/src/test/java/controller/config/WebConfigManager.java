package controller.config;


import java.util.Properties;

public class WebConfigManager extends BaseConfigManager {

    private static Properties properties;

    static {
        String configName = "config";
        logger.info(String.format("Environment config file: '%s'", configName));
        properties = loadProperties(configName);
    }

    public static final String BROWSER_LANGUAGE = properties.getProperty("browser.default.language");
    public static final String DEFAULT_BROWSER = properties.getProperty("browser.default.name");

}
