package controller.config;


import logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public abstract class BaseConfigManager {

    private static final String SELENIDE_STARTKEY = "selenide.";
    private static final String PROPERTY_FILE_NAME_PATTERN = "/%s.properties";

    protected static Logger logger = Logger.getInstance(BaseConfigManager.class);

    /**
     * Loads all properties from the file depending on the set up system profile.
     * If profile hasn't been set up, the default profile will be used.
     */
    protected static Properties loadProperties(String propertyBundle) {
        Properties properties = getProperties(propertyBundle);
        initSelenideProperties(properties);
        return properties;
    }

    public static Properties getProperties(String propertyBundle) {
        String propFileName = String.format(PROPERTY_FILE_NAME_PATTERN, propertyBundle);

        Properties localProperties = new Properties();
        try (InputStream propertyStream = BaseConfigManager.class.getResourceAsStream(propFileName)) {
            localProperties.load(propertyStream);
        } catch (IOException e) {
            throw new RuntimeException(String.format("%s  property file is not loaded because of %s",
                    propFileName, Arrays.toString(e.getStackTrace())));
        }
        return localProperties;
    }

    /**
     * Could be setup properties which initializing in {@link com.codeborne.selenide.SelenideConfig}
     * @param properties environment properties
     */
    private static void initSelenideProperties(Properties properties) {
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith(SELENIDE_STARTKEY)) {
                String value = properties.getProperty(key);
                System.setProperty(key, value);
            }
        }
    }
}
