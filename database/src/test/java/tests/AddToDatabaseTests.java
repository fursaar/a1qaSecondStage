package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testlisteners.AddToDatabaseListener;
import utils.RandomUtils;

@Listeners(AddToDatabaseListener.class)

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
}
