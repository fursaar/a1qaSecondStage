package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    public static ISettingsFile getJsonFile(String nameOfJsonFile) {
        ISettingsFile outputFile = null;
        try {
            outputFile = new JsonSettingsFile(new File(String.format("./src/main/resources/%s.json/", nameOfJsonFile)));
        } catch (IOException e) {
            AqualityServices.getLogger().error(String.format("%s file not found", nameOfJsonFile));
        }
        return outputFile;
    }
}