package utils;

import pojos.PostPojo;

import java.util.List;

public class ListUtils {
    public static boolean checkAscendingSorting(List<PostPojo> list) {
        boolean result = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == i + 1) {
                result = true;
            } else {
                return false;
            }
        }
        return result;
    } //Мне не очень нравится эта реализация, но другую я придумать не смог
}
