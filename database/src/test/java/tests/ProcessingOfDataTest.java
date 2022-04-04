package tests;

import database.tables.TestTable;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DuplicateNumberUtil;
import utils.JsonUtil;
import utils.RandomUtils;

import java.util.List;

import static database.tables.TestTable.*;
import static database.tables.TestTable.deleteTests;

public class ProcessingOfDataTest extends BaseTest{
    List<database.businessobjects.Test> duplicateIdsTests;
    @BeforeTest
    public void beforeTest() {
        duplicateIdsTests = getTestsByIds(DuplicateNumberUtil.generateDuplicateNumbersUpTo(RandomUtils.generateRandomNumberInRange(1, 9), TestTable.getMaxId()));
        insertTests(duplicateIdsTests);
    }

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
    @Test
    public void test6() {
        Assert.assertTrue(RandomUtils.getTrueOrFalse());
    }
    @Test
    public void test7() {
        Assert.assertTrue(RandomUtils.getTrueOrFalse());
    }
    @Test
    public void test8() {
        Assert.assertTrue(RandomUtils.getTrueOrFalse());
    }
    @Test
    public void test9() {
        Assert.assertTrue(RandomUtils.getTrueOrFalse());
    }
    @Test
    public void test10() {
        Assert.assertTrue(RandomUtils.getTrueOrFalse());
    }

    @AfterMethod
    public void afterTest(ITestResult iTestResult) {
        updateTests(duplicateIdsTests, iTestResult.getName(), iTestResult.getStatus(), iTestResult.getMethod().getMethodName(), (Integer) JsonUtil.testData.getValue("/project_id"), (Integer) JsonUtil.testData.getValue("/session_id"), JsonUtil.testData.getValue("/env").toString(), "none", (Integer) JsonUtil.testData.getValue("/author_id"));
        deleteTests(duplicateIdsTests);
        Assert.assertFalse(isTestsInDB(duplicateIdsTests));
    }
}
