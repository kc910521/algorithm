package ind.ck.leetcode;

import java.util.Arrays;

/**
 * leetcode 草稿
 * <p>
 * Created by KCSTATION on 2020/1/26.
 */
public class EzLeetCode {

    public static void main(String[] args) {
        System.out.println(firstUniqChar(""));
    }


    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/34/
     *
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        int[] b = new int[26];
        for (int i = 0; i < s.length(); i++) {
            b[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (b[s.charAt(i) - 'a'] < 2) return i;
        }
        return -1;
    }
}
