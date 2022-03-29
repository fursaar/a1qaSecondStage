package utils;

public class RandomUtils {
    public static boolean getTrueOrFalse() {
        int randomNum = (int) (Math.random() * 2);
        if (randomNum == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int generateRandomNumberInRange(int minNumInRange, int maxNumInRange) {
        return (int) (Math.random() * ((maxNumInRange - minNumInRange) + 1) + minNumInRange);
    }
}
