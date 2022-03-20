package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    public static ISettingsFile jsonFile;
    static {
        try {
            jsonFile = new JsonSettingsFile(new File("./src/main/resources/configData.json/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
