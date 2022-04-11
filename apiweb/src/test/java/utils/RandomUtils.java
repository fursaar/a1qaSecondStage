package utils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomUtils {
    private static final String chars = "abcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(chars.length());
            char rndChar = chars.charAt(rndCharAt);
            stringBuilder.append(rndChar);
        }
        String randomString = String.valueOf(stringBuilder);
        List letters = Arrays.asList(randomString.split(""));
        Collections.shuffle(letters);
        return (String) letters.stream().collect(Collectors.joining());
    }
}
