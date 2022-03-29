package database.businessobjects;

import aquality.selenium.browser.AqualityServices;
import database.DataBaseManager;

import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    private String name;
    private int status_id;
    private String method_name;
    private int project_id;
    private int session_id;
    private String env;
    private String browser;
    private int author_id;

    public Test(String name, int status_id, String method_name, int project_id, int session_id, String env, String browser, int author_id) {
        this.name = name;
        this.status_id = status_id;
        this.method_name = method_name;
        this.project_id = project_id;
        this.session_id = session_id;
        this.env = env;
        this.browser = browser;
        this.author_id = author_id;
    }

    public Test() {

    }

    public void insertTest() {
        try {
            Statement insertStatement  = DataBaseManager.getConnection().createStatement();
            insertStatement.executeUpdate(String.format("insert into test (name, session_id, project_id, env, method_name, author_id, status_id) values ('%s', %d, %d, '%s', '%s', %d, %d);", name, session_id, project_id, env, method_name, author_id, status_id));
        } catch (SQLException e) {
            AqualityServices.getLogger().error("SQL exception");
        }
    }

    public void deleteTest() {
        try {
            Statement deleteStatement = DataBaseManager.getConnection().createStatement();
            deleteStatement.executeUpdate(String.format("delete from test where name = '%s' and status_id = %d and method_name = '%s' and project_id = %d", name, status_id, method_name, project_id));
        } catch (SQLException e) {
            AqualityServices.getLogger().error("SQL exception");
        }
    }

    public void updateTest(String newName, int newStatus_id, String newMethod_name, int newProject_id, int newSession_id, String newEnv, String newBrowser, int newAuthor_id) {
        try {
            Statement deleteStatement = DataBaseManager.getConnection().createStatement();
            deleteStatement.executeUpdate(String.format("update test set name='%s', status_id=%d, method_name='%s', project_id=%d, session_id=%d, env='%s', browser='%s', author_id=%d where name = '%s' and status_id = %d and method_name = '%s' and project_id = %d", newName, newStatus_id, newMethod_name, newProject_id, newSession_id, newEnv, newBrowser, newAuthor_id, name, status_id, method_name, project_id));
        } catch (SQLException e) {
            AqualityServices.getLogger().error("SQL exception");
        } finally {
            this.name = newName;
            this.status_id = newStatus_id;
            this.method_name = newMethod_name;
            this.project_id = newProject_id;
            this.session_id = newSession_id;
            this.env = newEnv;
            this.browser = newBrowser;
            this.author_id = newAuthor_id;
        }
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", status_id=" + status_id +
                ", method_name='" + method_name + '\'' +
                ", project_id=" + project_id +
                ", session_id=" + session_id +
                ", env='" + env + '\'' +
                ", browser='" + browser + '\'' +
                ", author_id=" + author_id +
                '}';
    }
}
