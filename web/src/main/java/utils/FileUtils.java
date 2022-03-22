package utils;

import java.io.File;

public class FileUtils {
    public static String getFilePath(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }
}
