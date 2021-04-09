package controller.config;

import logger.Logger;
import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static controller.config.WebConfigManager.BROWSER_LANGUAGE;

public class DictionaryFileManager {

    protected static Logger logger = Logger.getInstance(DictionaryFileManager.class);

    private static ThreadLocal<Map<String, Map<String, String>>> localeData = new ThreadLocal<>();
    private static final String resourcesFileName = "resource_locale";
    private static final String KEY = "key";


    public static String readDataFromJson(String key) {
        String locale = Reporter.getCurrentTestResult().getTestContext()
                .getCurrentXmlTest().getParameter("locale");
        if (Objects.isNull(locale) || locale.isEmpty()) {
            return readData(key, BROWSER_LANGUAGE);
        } else {
            return readData(key, locale);
        }
    }

    public static String readData(String key, String locale) {
        logger.trace(String.format("Set locale - %s", locale));
        readResourceFile();
        String returnValue = getLocaleData().get(key).get(locale);
        if (returnValue == null) {
            String error_message = "Element " + key + " NOT FOUND in " + resourcesFileName;
            throw new IllegalArgumentException(error_message);
        }
        return returnValue;
    }

    /**
     * Read locale full JSON file
     */
    @SneakyThrows
    private static synchronized void readResourceFile() {
        if (!getLocaleData().isEmpty()) {
            return;
        }
        logger.info("Read data from: " + resourcesFileName);
        JSONParser parser = new JSONParser();
        String localDir = new java.io.File(".").getCanonicalPath();

        String fileName = String.format("%s/src/test/resources/localization/%s.json", localDir, resourcesFileName);

        Object obj = parser.parse(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));

        JSONArray jsonArray = (JSONArray) obj;
        for (Object o : jsonArray) {
            Map<String, String> property = (Map) o;
            String propertyKey = property.remove(KEY);
            getLocaleData().put(propertyKey, property);
        }
    }

    private static Map<String, Map<String, String>> getLocaleData() {
        Map<String, Map<String, String>> map = localeData.get();
        if (Objects.isNull(map)) {
            logger.info("Setup Dictionary File Manager!");
            map = new HashMap<>();
            localeData.set(map);
        }
        return map;
    }

    public static void clear() {
        localeData.set(null);
    }

}
