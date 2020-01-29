package ind.ck.leetcode;

import java.util.Arrays;
import java.util.Stack;

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
//        System.out.println(isAnagram("我at", "t我a"));
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(myAtoi("20000000000000000000"));
    }

    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/37/
     *
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        char[] chars = str.toCharArray();
        if (chars == null || chars.length == 0) {
            return 0;
        }
//        int[] resultArrs = new int[chars.length];
        Stack<Integer> resStack = new Stack<Integer>();
        boolean negative = false;
        Long result = 0L;
        int nowIdx = 0;
        int resIdx = 0;
        int power = 0;
        // 原始数组筛选
        while (nowIdx < chars.length) {
            char nowChar = chars[nowIdx];
            if (nowChar == ' ') {
                nowIdx++;
                continue;
            } else if ((nowChar >= '0' && nowChar <= '9')) {
                break;
            } else if (nowChar == '+') {
                nowIdx++;
                break;
            } else if (nowChar == '-') {
                negative = true;
                nowIdx++;
                break;
            } else {
                return 0;
            }
        }
        // 获取有效绝对值数组
        while (nowIdx < chars.length) {
            char nowChar = chars[nowIdx];
            if (nowChar >= '0' && nowChar <= '9') {
                resStack.push(Integer.valueOf(nowChar + ""));
                nowIdx++;
            } else {
                break;
            }
        }
        // 处理正负累加和溢出
        if (negative) {
            while (!resStack.empty()) {
                Integer pop = resStack.pop();
                long additive = -(pop * Double.valueOf(Math.pow(10, power)).longValue());
                if (additive < -2147483648 || -2147483648 - additive > result || (power > 9 && additive > 0)) {
                    return -2147483648;
                } else {
                    result = result + additive;
                }
                power++;
            }
        } else {
            while (!resStack.empty()) {
                Integer pop = resStack.pop();
                long additive = pop * Double.valueOf(Math.pow(10, power)).longValue();
                if (additive > 2147483647 || 2147483647 - additive < result || (power > 9 && additive < 0)) {
                    return 2147483647;
                } else {
                    result = result + additive;
                }
                power++;
            }

        }
        return result.intValue();
    }

    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/36/
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] chars = s.trim().toLowerCase().toCharArray();
        int lowIdx = 0;
        int highIdx = chars.length - 1;
        while (lowIdx <= highIdx) {
            if (!((chars[lowIdx] >= 'a' && chars[lowIdx] <= 'z') || (chars[lowIdx] >= '0' && chars[lowIdx] <= '9'))) {
                lowIdx++;
                continue;
            }
            if (!((chars[highIdx] >= 'a' && chars[highIdx] <= 'z') || (chars[highIdx] >= '0' && chars[highIdx] <= '9'))) {
                highIdx--;
                continue;
            }
            if (chars[lowIdx] != chars[highIdx]) {
                return false;
            } else {
                lowIdx++;
                highIdx--;
            }
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/35/
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if (len1 != len2){
            return false;
        }
        int[] map = new int[26];
        for (int i = 0; i < len1; i++) {
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if(map[i] != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/35/
     *
     * @param s
     * @param t
     * @return
     */
//    public static boolean isAnagram(String s, String t) {
//        if (s == null || t == null) {
//            return false;
//        }
//        char[] chars1 = s.toCharArray();
//        char[] chars2 = t.toCharArray();
//        if (chars1.length != chars2.length) {
//            return false;
//        }
//        Arrays.sort(chars1);
//        Arrays.sort(chars2);
//        for (int i = 0; i < chars1.length; i++) {
//            if (chars1[i] != chars2[i]) {
//                return false;
//            }
//        }
//        return true;
//    }




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
