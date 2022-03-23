package utils;

import aquality.selenium.browser.AqualityServices;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class EmulateKeysUtils {
    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public static void pasteClipboardData() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            AqualityServices.getLogger().error("Robot exception");
        }
        robot.delay((Integer) JsonUtil.configData.getValue("/robotDelay"));
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay((Integer) JsonUtil.configData.getValue("/robotDelay"));
    }
}
