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
    private static int[] chaoseArray2 = {1, 9, 3,2, 1, 6,4};


    public static int binarySearch(int[] arr, int tarVal) {
        int lIdx = 0;
        int rIdx = arr.length - 1;
        int midIdx = -1;
        while (lIdx <= rIdx) {
            midIdx = (lIdx + rIdx) / 2;
            int midVal = arr[midIdx];
            if (tarVal == midVal) {
                return midIdx;
            } else if (tarVal > midVal) {
                lIdx = midIdx + 1;
            } else {
                rIdx = midIdx - 1;
            }
        }
        return rIdx;
    }


    public static int sort(int[] arr, int l, int h) {
        int i, j, temp, t;
        if (l > h) {
           return -1;
        }
        i = l;
        j = h;
        temp = arr[l];
        while (i < j) {

            while (i < j && temp <= arr[j]) {
                j --;
            }
            while (i < j && temp >= arr[j]) {
                i ++;
            }
            if (i < j) {
                // 把比目标大的和比目标小的数进行交换
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        // 结束，把数值插入唯一正确的位置，并把之前位置的数据交换过来
        arr[l] = arr[i];
        arr[i] = temp;
        return j;
    }

    /**
     * 快拍形式
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort(int[] arr, int l, int r) {
        int j = sort(arr, l, r);
        if (j > -1) {
            quickSort(arr, l, j - 1);
            quickSort(arr, j + 1, r);
        }

    }


    public static void main(String[] args) {
        quickSort(chaoseArray2, 0 , chaoseArray2.length - 1);
        System.out.println(Arrays.toString(chaoseArray2));


//        System.out.println(binarySearch(sortedArray, 2));
//        System.out.println(binarySearch(sortedArray, 12));
//
//        System.out.println(binarySearch(sortedArray, 1));
//        System.out.println(binarySearch(sortedArray, 99));
//        System.out.println(binarySearch(sortedArray, 11));
//        System.out.println(indexInBinarySearch(sortedArray2, 1));
//        Character[] a = {'a', 'b', 'c'};
//        List<Character> c1 = Arrays.asList(a);
//        System.out.println("dddddddd");
//
//        /**
//         * abc
//         *  bc
//         *      c
//         *      -c
//         *  b
//         *     c-
//         *      b-
//         *
//         * **/
//
//
//
//
////        findA(c1, new ArrayList<>());
//        findB(c1);
//        System.out.println();
//        System.out.println("dddddddd");
    }































    // 左右边界

//    public static int indexInBinarySearch(int[] sortedArray, int targetValue) {
//        if (sortedArray == null || sortedArray.length == 0) {
//            return -1;
//        }
//        int lIdx = 0;
//        int rIdx = sortedArray.length - 1;
//
//        while (lIdx <= rIdx) {
//            int nowIdx = (lIdx + rIdx) / 2;
//            if (sortedArray[nowIdx] == targetValue) {
//                return nowIdx;
//            } else if (sortedArray[nowIdx] < targetValue) {
//                lIdx = nowIdx + 1;
//            } else if (sortedArray[nowIdx] > targetValue) {
//                //  high = mid;
//                rIdx = nowIdx - 1;
//            }
//        }
//        return -1;
//    }




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




}
