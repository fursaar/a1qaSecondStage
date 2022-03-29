package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testlisteners.ProcessingOfDataListener;
import utils.RandomUtils;

@Listeners(ProcessingOfDataListener.class)

public class ProcessingOfDataTest extends BaseTest{
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
}
