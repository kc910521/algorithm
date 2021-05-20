package ind.ck.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 上午10:34 21-4-1
 **/
public class ArrayTest {

    public static int reverse(int x) {
        // 设置正负标志后得绝对值ab
//        boolean neg = x < 0;
        int ab = x;
        int res = 0;
        // 当ab ！= 0 时
        while (ab != 0) {
            // 得到最后一位 c = ab % 10
            int c = ab % 10;
            ab /= 10;
            // 不为0 则 * 10 + c | 为0这一顿操作也没意义
            int resTmp = res;
            res = res * 10 + c;
            // 判断溢出
            if ((res - c)/10 != resTmp) {
                return 0;
            }
        }
        return res;

    }

    public static String longestCommonPrefix(String[] strs) {
        // pankong
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 设定fst为数组第一个元素，和最长位置i
        int i = 0;
        String fst = strs[0];
        for (i = 0; i < fst.length() ; i ++) {
            // 获取本列
            String ai = fst.substring(i, i + 1);
            if (!right(strs, ai, i)) {
                break;
            }
        }
        // 输出fts
        return fst.substring(0, i);
    }

    private static boolean right(String[] strs, String ai, int i) {
        for (int j = 1;j < strs.length; j ++) {
            // 循环strs
            // 获取每个的第i个符号，
            // 超长或者不等 就直接返回
            if (strs[j].length() <= i ) {
                return false;
            }
            if (!strs[j].substring(i, i + 1).equals(ai)) {
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/
     * 无重复字符的最长子序列
     * @param s "abcabcbb"
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        // nul judge
        if (s == null || s.length() == 0) {
            return 0;
        }
        // left right hashSet init
        int left = 0;
        int right = 0;
        int sum = 0;
        Set<Character> hset = new HashSet<Character>();
        // fori
        for (;right < s.length();) {
            Character tmp = s.charAt(right);
            // haseSet contains
            if (hset.contains(tmp)) {
                // hset clear, left ++,right = left
                left ++;
                right = left;
                hset.clear();
            } else {
                // not contain
                right ++;
                // ====right ++
                hset.add(tmp);
                // hset.add
                sum = Math.max(sum, right - left);
                // sum = max(right - left, sum)
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(longestCommonPrefix(new String[]{"cog","dacecar","dar"}));

//        String ab = "abc";
//        System.out.println();
//        System.out.println(ab.substring(0, 0));
//        int i = 0;
//        for (i =0; i< 1; i++) {
//            break;
//        }
//        System.out.println("dd" + i);
//
//        System.out.println(reverse(123));
    }
}
