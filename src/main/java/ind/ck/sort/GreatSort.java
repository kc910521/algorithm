package ind.ck.sort;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author caikun
 * @Description
 *
 *
 * @Date 下午4:47 21-7-6
 **/
public class GreatSort {

    private static int[] tp = new int[] {3,  5, 2, 1, 15, 22, 4, 99};


    private static int[] aa1 = new int[] {9, 12, 2, 7};
    // todo:警告！不能直接mergr
    // 2, 12* , _9*, 7
    //
    private static int[] aa2 = new int[] {2,4,5,6,7,9};
    // 归并排序
    public static void mergeSort(int[] ts, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(ts, l, mid);
            mergeSort(ts, mid + 1, r);
            merge(l, mid, r, ts);
        }
    }
    // 0  1 2 3 ;mid= 1 , l = 0, r = 3
    // 0 1 2 3 4 ; mid = 2
    private static void merge(int l, int mid, int r, int[] ints) {
        //
        int[] am = Arrays.copyOfRange(ints, 0, r + 1);
        int i = l;
        int ri = mid + 1;
        while (l <= mid && ri <= r ) {
            if (am[l] > am[ri]) {
                // 左大
                // 交换;左++
                ints[i] = am[ri];
                ri ++;
            } else {
                // 左小
                // 左++
                ints[i] = am[l];
                l ++;
            }
            i ++;

        }
        // complements
        while (l <= mid) {
            ints[i ++] = am[l ++];
        }
        while (ri <= r) {
            ints[i ++] = am[ri ++];
        }
    }

    public static void main(String[] args) {
        int c = 1000000007;
        System.out.println("Integer.MAX_VALUE :" + Integer.MAX_VALUE);
        int a = Integer.MAX_VALUE * 2;
        int b = (Integer.MAX_VALUE % c) * (2 % c);
        long la = Integer.MAX_VALUE * 2L;
        long lb = (Integer.MAX_VALUE % c) * (2 % c);
        System.out.println("a :" + a);
        System.out.println("b :" + b);
        System.out.println("la :" + la);
        System.out.println("lb :" + lb);

        System.out.println((-3 % c));
    }


//    public static void main(String[] args) {
////        merge(0, 1, aa1.length - 1, aa1);
////        System.out.println(Arrays.toString(aa1));
//        mergeSort(tp, 0, tp.length - 1);
//        System.out.println(Arrays.toString(tp));
//    }



}
