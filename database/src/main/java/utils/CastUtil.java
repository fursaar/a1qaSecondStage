package utils;

import java.util.List;

public class CastUtil {
    public static String castListValuesToString(List list) {
        String firstValue = String.valueOf(list.get(0));
        String otherValues = "";
        for (int i =1; i < list.size(); i++) {
            otherValues = otherValues + ", " + String.valueOf(list.get(i));
        }
        return firstValue + otherValues;
    }
}
