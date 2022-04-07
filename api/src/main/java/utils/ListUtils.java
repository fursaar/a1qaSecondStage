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

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(10);
        list.add(3);
        list.add(2);
        System.out.println(list);
        System.out.println(sortList(list));
    }
}
