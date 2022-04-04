package utils;

public class RandomUtils {
    public static boolean getTrueOrFalse() {
        int randomNum = (int) (Math.random() * 2);
        return randomNum == 0;
    }

    public static int generateRandomNumberInRange(int minNumInRange, int maxNumInRange) {
        return (int) (Math.random() * ((maxNumInRange - minNumInRange) + 1) + minNumInRange);
    }
}
