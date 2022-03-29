package testlisteners;

import database.businessobjects.Test;
import database.tables.TestTable;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DuplicateNumberUtil;
import utils.JsonUtil;
import utils.RandomUtils;

import java.util.List;

import static database.tables.TestTable.*;

public class ProcessingOfDataListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        List<Test> duplicateIdsTests = getTestsByIds(DuplicateNumberUtil.generateDuplicateNumbersUpTo(RandomUtils.generateRandomNumberInRange(1, 9), TestTable.getMaxId()));
        insertTests(duplicateIdsTests);
        updateTests(duplicateIdsTests, iTestResult.getName(), iTestResult.getStatus(), iTestResult.getMethod().getMethodName(), (Integer) JsonUtil.testData.getValue("/project_id"), (Integer) JsonUtil.testData.getValue("/session_id"), JsonUtil.testData.getValue("/env").toString(), "none", (Integer) JsonUtil.testData.getValue("/author_id"));
        deleteTests(duplicateIdsTests);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        List<Test> duplicateIdsTests = getTestsByIds(DuplicateNumberUtil.generateDuplicateNumbersUpTo(RandomUtils.generateRandomNumberInRange(1, 9), TestTable.getMaxId()));
        insertTests(duplicateIdsTests);
        updateTests(duplicateIdsTests, iTestResult.getName(), iTestResult.getStatus(), iTestResult.getMethod().getMethodName(), (Integer) JsonUtil.testData.getValue("/project_id"), (Integer) JsonUtil.testData.getValue("/session_id"), JsonUtil.testData.getValue("/env").toString(), "none", (Integer) JsonUtil.testData.getValue("/author_id"));
        deleteTests(duplicateIdsTests);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        List<Test> duplicateIdsTests = getTestsByIds(DuplicateNumberUtil.generateDuplicateNumbersUpTo(RandomUtils.generateRandomNumberInRange(1, 9), TestTable.getMaxId()));
        insertTests(duplicateIdsTests);
        updateTests(duplicateIdsTests, iTestResult.getName(), iTestResult.getStatus(), iTestResult.getMethod().getMethodName(), (Integer) JsonUtil.testData.getValue("/project_id"), (Integer) JsonUtil.testData.getValue("/session_id"), JsonUtil.testData.getValue("/env").toString(), "none", (Integer) JsonUtil.testData.getValue("/author_id"));
        deleteTests(duplicateIdsTests);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
