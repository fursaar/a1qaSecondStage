package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    public static ISettingsFile testData;
    static {
        try {
            testData = new JsonSettingsFile(new File("./src/main/resources/testData.json/"));
        } catch (IOException e) {
            AqualityServices.getLogger().error("testData file not found");
        }
    }

    public static ISettingsFile configData;
    static {
        try {
            configData = new JsonSettingsFile(new File("./src/main/resources/configData.json/"));
        } catch (IOException e) {
            AqualityServices.getLogger().error("configData file not found");
        }
    }
}