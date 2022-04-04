package utils;

import java.util.List;

public class CastUtil {
    public static String castListValuesToString(List list) {
        String firstValue = String.valueOf(list.get(0));
        String otherValues = "";
        for (int i =1; i < list.size(); i++) {
            otherValues = String.format("%s, %s", otherValues, list.get(i));
        }
        return firstValue + otherValues;
    }

    public static void main(String[] args) {
        System.out.println(castListValuesToString(DuplicateNumberUtil.generateDuplicateNumbersUpTo(1,200)));
    }
}
