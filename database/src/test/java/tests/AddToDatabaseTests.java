package tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.JsonUtil;
import utils.RandomUtils;

public class AddToDatabaseTests extends BaseTest{

    @Test
    public void test1() {
        Assert.assertTrue(RandomUtils.getTrueOrFalse());
    }

    @Test
    public void test2() {
        Assert.assertTrue(RandomUtils.getTrueOrFalse());
    }

    @Test
    public void test3() {
        Assert.assertTrue(RandomUtils.getTrueOrFalse());
    }

    @Test
    public void test4() {
        Assert.assertTrue(RandomUtils.getTrueOrFalse());
    }

    @Test
    public void test5() {
        Assert.assertTrue(RandomUtils.getTrueOrFalse());
    }

    @AfterMethod
    private void afterTest(ITestResult iTestResult) {
        database.businessobjects.Test test = new database.businessobjects.Test(iTestResult.getName(), iTestResult.getStatus(), iTestResult.getMethod().getMethodName(), (Integer) JsonUtil.testData.getValue("/project_id"), (Integer) JsonUtil.testData.getValue("/session_id"), JsonUtil.testData.getValue("/env").toString(), "none", (Integer) JsonUtil.testData.getValue("/author_id"));
        test.insertTest();
        Assert.assertTrue(test.isTestInDB());
    }
}
