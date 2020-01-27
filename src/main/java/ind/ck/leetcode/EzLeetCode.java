package ind.ck.leetcode;

import java.util.Arrays;

/**
 * leetcode 草稿
 * <p>
 * Created by KCSTATION on 2020/1/26.
 */
public class EzLeetCode {

    public static void main(String[] args) {
//        System.out.println(firstUniqChar("loveleetcode"));
//        System.out.println(0 - 'a');
//        char c = "我abcd发".charAt(0);
//        char c1 = "我abcd发".charAt(1);
        System.out.println(isAnagram("我at", "c我a"));
    }

    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/35/
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        if (chars1.length != chars2.length) {
            return false;
        }
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }
        return true;
    }




    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/34/
     *
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        int[] iDict = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int cidx = s.charAt(i) - 'a';
            if (cidx >= 0 && cidx <= 25) {
                iDict[cidx]++;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            int cidx = s.charAt(i) - 'a';
            if (cidx >= 0 && cidx <= 25) {
                if (iDict[cidx] == 1) {
                    return i;
                }
            }
        }
        return -1;
    }
}
