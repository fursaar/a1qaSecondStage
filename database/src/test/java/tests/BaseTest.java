package tests;

import database.DataBaseManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void init() {
        DataBaseManager.getConnection();
    }

    @AfterMethod
    public void terminate() {
    }
}
