package database.tables;

import aquality.selenium.browser.AqualityServices;
import database.DataBaseManager;
import database.businessobjects.Test;
import utils.CastUtil;
import utils.DuplicateNumberUtil;
import utils.RandomUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestTable {

    public static void deleteTestsByAuthorId(int authorId) {
        try {
            Statement deleteStatement = DataBaseManager.getConnection().createStatement();
            deleteStatement.executeUpdate(String.format("delete from test where author_id = %d", authorId));
        } catch (SQLException e) {
            AqualityServices.getLogger().error("SQL exception");
        }
    }

    public static List<Test> getTestsByIds(List<Integer> ids) {
        List<Test> testsList = new ArrayList<>();
        try {
            Statement selectStatement = DataBaseManager.getConnection().createStatement();
            ResultSet resultSet = selectStatement.executeQuery(String.format("select name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id from test where id in(%s) limit 10", CastUtil.castListValuesToString(ids)));
            while (resultSet.next()) {
                Test test = new Test();
                test.setBrowser(resultSet.getString("browser"));
                test.setAuthor_id(1);
                test.setEnv(resultSet.getString("env"));
                test.setMethod_name(resultSet.getString("method_name"));
                test.setName(resultSet.getString("name"));
                test.setProject_id(7);
                test.setSession_id(resultSet.getInt("session_id"));
                test.setStatus_id(resultSet.getInt("status_id"));
                testsList.add(test);
            }
        } catch (SQLException e) {
            AqualityServices.getLogger().error("SQL exception");
        }
        return testsList;
    }

    public static void insertTests(List<Test> tests) {
        for (int i = 0; i < tests.size(); i ++) {
            tests.get(i).insertTest();
        }
    }

    public static void updateTests(List<Test> tests, String newName, int newStatus_id, String newMethod_name, int newProject_id, int newSession_id, String newEnv, String newBrowser, int newAuthor_id) {
        for (int i = 0; i < tests.size(); i ++) {
            tests.get(i).updateTest(newName, newStatus_id, newMethod_name, newProject_id, newSession_id, newEnv, newBrowser, newAuthor_id);
        }
    }

    public static void deleteTests(List<Test> tests) {
        for (int i = 0; i < tests.size(); i ++) {
            tests.get(i).deleteTest();
        }
    }

    public static boolean isTestsInDB(List<Test> tests) {
        boolean result = false;
        for (int i = 0; i < tests.size(); i ++) {
            while (tests.get(i).isTestInDB()) {
                result = tests.get(i).isTestInDB();
            }
        }
        return result;
    }

    public static int getMaxId()  {
        int result = 0;
        try {
            Statement executeStatement = DataBaseManager.getConnection().createStatement();
            ResultSet maxId = executeStatement.executeQuery("select MAX(id) FROM union_reporting.test;");
            maxId.next();
            result = maxId.getInt("MAX(id)");
        } catch (SQLException e) {
            AqualityServices.getLogger().error("SQL exception");
        }
        return  result;
    }

}
