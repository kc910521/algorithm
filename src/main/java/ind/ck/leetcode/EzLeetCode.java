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
//        System.out.println(myAtoi("20000000000000000000"));
        System.out.println(strStr("hello", "k"));
    }

    /**
     * 别问问就kmp
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/38/
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        int[] N = getN(needle);
        int res = 0;
        int sourceLength = haystack.length();
        int patternLength = needle.length();
        for (int i = 0; i <= (sourceLength - patternLength); ) {
            res++;
            String str = haystack.substring(i, i + patternLength);//要比较的字符串
            int count = getNext(needle, str, N);
//            p("移动"+count+"步");
            if (count == 0) {
                return i;
            }
            i = i + count;
        }
        return -1;

    }

    /**
     * 得到下一次要移动的次数
     *
     * @param pattern
     * @param str
     * @param N
     * @return 0, 字符串匹配；
     */
    private static int getNext(String pattern, String str, int[] N) {
        int n = pattern.length();
        char v1[] = str.toCharArray();
        char v2[] = pattern.toCharArray();
        int x = 0;
        while (n-- != 0) {
            if (v1[x] != v2[x]) {
                if (x == 0) {
                    return 1;//如果第一个不相同，移动1步
                }
                return x - N[x - 1];//x:第一次出现不同的索引的位置，即j
            }
            x++;
        }
        return 0;
    }

    private static int[] getN(String pattern) {
        char[] pat = pattern.toCharArray();
        int j = pattern.length() - 1;
        int[] N = new int[j + 1];
        for (int i = j; i >= 2; i--) {
            N[i - 1] = getK(i, pat);
        }
        return N;
    }

    private static int getK(int j, char[] pat) {
        int x = j - 2;
        int y = 1;
        while (x >= 0 && compare(pat, 0, x, y, j - 1)) {
            x--;
            y++;
        }
        return x + 1;
    }

    private static boolean compare(char[] pat, int b1, int e1, int b2, int e2) {
        int n = e1 - b1 + 1;
        while (n-- != 0) {
            if (pat[b1] != pat[b2]) {
                return true;
            }
            b1++;
            b2++;
        }
        return false;
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
