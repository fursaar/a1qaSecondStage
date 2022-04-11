package utils;

import pojos.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {
    public static List<Integer> sortList(List<Integer> list) {
        Collections.sort(list);
        return list;
    }
}
