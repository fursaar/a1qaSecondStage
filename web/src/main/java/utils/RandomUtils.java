package utils;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

public class RandomUtils {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String COMMON_CHAR = "A1";
    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static final SecureRandom random = new SecureRandom();
    private static Set<Integer> previousResults = new HashSet<>();

    public static String generateRandomPassword(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length - 2; i++) {
            int rndCharAt = random.nextInt(PASSWORD_ALLOW_BASE.length());
            char rndChar = PASSWORD_ALLOW_BASE.charAt(rndCharAt);
            stringBuilder.append(rndChar);
        }
        String randomPassword = stringBuilder + COMMON_CHAR;
        List letters = Arrays.asList(randomPassword.split(""));
        Collections.shuffle(letters);
        return (String) letters.stream().collect(Collectors.joining());
    }

    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length - 2; i++) {
            int rndCharAt = random.nextInt(CHAR_LOWER.length());
            char rndChar = CHAR_LOWER.charAt(rndCharAt);
            stringBuilder.append(rndChar);
        }
        String randomString = stringBuilder + COMMON_CHAR;
        List letters = Arrays.asList(randomString.split(""));
        Collections.shuffle(letters);
        return (String) letters.stream().collect(Collectors.joining());
    }

    public static int generateRandomNumberInRange(int minNumInRange, int maxNumInRange) {
        return (int) (Math.random() * ((maxNumInRange - minNumInRange) + 1) + minNumInRange);
    }

    public static int generateRandomNumberInRangeExcept(int minNumInRange, int maxNumInRange, int...exceptNumbers) {
        int result = generateRandomNumberInRange(minNumInRange, maxNumInRange);
        for (int i = 0; i < exceptNumbers.length; i++) {
            if (result == exceptNumbers[i]) {
                return generateRandomNumberInRangeExcept(minNumInRange, maxNumInRange, exceptNumbers);
            }
        }
        if (previousResults.contains(result)) {
            return generateRandomNumberInRangeExcept(minNumInRange, maxNumInRange, exceptNumbers);
        } else {
            previousResults.add(result);
            return result;
        }
    }
}
