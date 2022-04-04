package database;

import aquality.selenium.browser.AqualityServices;
import utils.JsonUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
    private static final String userName = "root";
    private static final String password = "1234";
    private static final String connectionUrl = JsonUtil.configData.getValue("/url").toString() + JsonUtil.configData.getValue("/port").toString() + JsonUtil.configData.getValue("/nameOfDB").toString();
    private static Connection connection;

    private static void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            AqualityServices.getLogger().error("SQL exception");
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            connectToDatabase();
        }
        return connection;
    }
}
