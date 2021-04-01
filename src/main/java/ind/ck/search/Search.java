package ind.ck.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author CaiKun 2020-01-19 16:19:17
 * @Description:
 * @Project algorithm
 */
public class Search {

    private static int[] sortedArray = {2, 3, 5, 6, 7, 9, 12, 23, 23, 24, 25, 26, 26, 27, 27, 28};
    private static int[] sortedArray2 = {1, 1, 1, 1, 1, 1, 2};

    // 左右边界

    public static int indexInBinarySearch(int[] sortedArray, int targetValue) {
        if (sortedArray == null || sortedArray.length == 0) {
            return -1;
        }
        int lIdx = 0;
        int rIdx = sortedArray.length - 1;

        while (lIdx <= rIdx) {
            int nowIdx = (lIdx + rIdx) / 2;
            if (sortedArray[nowIdx] == targetValue) {
                return nowIdx;
            } else if (sortedArray[nowIdx] < targetValue) {
                lIdx = nowIdx + 1;
            } else if (sortedArray[nowIdx] > targetValue) {
                //  high = mid;
                rIdx = nowIdx - 1;
            }
        }
        return -1;
    }




    public static void findA(final List<Character> dest, List<Character> rest) {
        if (dest.size() == 0) {
            System.out.println(rest);
            return;
        }
        for (int i = 0; i < dest.size(); i ++) {
            List<Character> rest2 = new ArrayList<>();
            List<Character> dest2 = new ArrayList<>();
            rest2.addAll(rest);
            dest2.addAll(dest);
            Character remove = dest2.remove(i);
            rest2.add(remove);
            findA(dest2, rest2);
        }
    }

    public static void findB(final List<Character> dest) {
        for (Character c : dest) {
            List<Character> dest2 = new ArrayList<>();
            dest2.addAll(dest);
            dest2.remove(c);
            findB(dest2);
            System.out.println(c);
        }
        return;
    }



    public static void main(String[] args) {
//        System.out.println(indexInBinarySearch(sortedArray2, 1));
        Character[] a = {'a', 'b', 'c'};
        List<Character> c1 = Arrays.asList(a);
        System.out.println("dddddddd");

        /**
         * abc
         *  bc
         *      c
         *      -c
         *  b
         *     c-
         *      b-
         *
         * **/




//        findA(c1, new ArrayList<>());
        findB(c1);
        System.out.println();
        System.out.println("dddddddd");
    }


}
