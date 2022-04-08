package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil<T> {
    public static ISettingsFile getJsonFile(String nameOfJsonFile) {
        ISettingsFile outputFile = null;
        try {
            outputFile = new JsonSettingsFile(new File(String.format("./src/main/resources/%s.json/", nameOfJsonFile)));
        } catch (IOException e) {
            AqualityServices.getLogger().error(String.format("%s file not found", nameOfJsonFile));
        }
        return outputFile;
    }

    public T castJsonStringToPojo(String jsonString, Class<T> pojo) {
        T result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readValue(jsonString, pojo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}