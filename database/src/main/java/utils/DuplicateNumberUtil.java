package utils;

import java.util.ArrayList;
import java.util.List;

public class DuplicateNumberUtil {
    private static int limitOfNumbers = 10;
    public static List<Integer> generateDuplicateNumbersUpTo(int number, int upToNumber) {
        int desiredNumber = Integer.parseInt(String.valueOf(number) + String.valueOf(number));
        List<Integer> result = new ArrayList<>();
        result.add(desiredNumber);
        for (int i = 0; i < limitOfNumbers; i ++) {
            desiredNumber = Integer.parseInt(String.valueOf(number) + String.valueOf(i) + String.valueOf(number));
            if (desiredNumber < upToNumber){
                result.add(desiredNumber);
            }
        }
        return result;
    }
}
