package testlisteners;

import database.businessobjects.Test;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.JsonUtil;

public class AddToDatabaseListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        addResultToDataBase(iTestResult);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        addResultToDataBase(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        addResultToDataBase(iTestResult);
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

    private void addResultToDataBase(ITestResult iTestResult) {
        Test test = new Test(iTestResult.getName(), iTestResult.getStatus(), iTestResult.getMethod().getMethodName(), (Integer) JsonUtil.testData.getValue("/project_id"), (Integer) JsonUtil.testData.getValue("/session_id"), JsonUtil.testData.getValue("/env").toString(), "none", (Integer) JsonUtil.testData.getValue("/author_id"));
        test.insertTest();
        Assert.assertTrue(test.isTestInDB());
    }
}
