package ind.ck.leetcode;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//        System.out.println(KMP("hello", "lo"));
//        System.out.println(countAndSay(5));
//        String[] a = new String[]{"flower", "flow", "flight"};
//        System.out.println(longestCommonPrefix(a));

        int[] a = new int[]{2,1,5,4,3,8,7};
//        Arrays.sort(a);
//        System.out.println("st:"+Arrays.toString(a));
//        System.out.println(binarySearch(a, 0, 0, 0));
//        System.out.println(binarySearch(a, 0, 0, 2));
//        System.out.println(3 + ":" + binarySearch(a, 0, 0, 3));
//        System.out.println(6 + ":" +binarySearch(a, 0, 0, 6));
//        System.out.println(8 + ":" +binarySearch(a, 0, 0, 8));
//        System.out.println(12 + ":" +binarySearch(a, 0, 0, 12));


//        Arrays.sort(a, 0, 2);
//        System.out.println(Arrays.toString(a));
//        SortWithin(a, 2);
//        System.out.println(Arrays.toString(a));

        // twosun
        System.out.println(Arrays.toString(twoSum(a, 11)));

    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> resMap = new HashMap<>();
        int[] res = new int[2];
        Stream.iterate(0, integer -> integer + 1).limit(nums.length).forEach(idx -> {
            Integer idx2 = resMap.get(nums[idx]);
            if (idx2 == null) {
                resMap.put(target - nums[idx], idx);
            } else {
                res[0] = idx;
                res[1] = idx2;
            }
        });
//        if (res[0] == res[1] == 0)
        return res;
    }


    /**
     * 基本有序数组的排序
     * @param arr case:2, 1, 5, 4, 3, 8, 7
     * @param k case:2
     */
    public static void SortWithin(int[] arr, int k) {
        // nil judge
        if (arr == null || arr.length == 0) {
            return;
        }
        // for i in arr
        Arrays.sort(arr, 0, k + 1);
        System.out.println("turn:0;" + Arrays.toString(arr));
        for (int i = k + 1; i < arr.length; i ++) {
            int pos = binarySearch(arr, i - k, i, arr[i]);
            if (pos != i) {
                int tmp = arr[i];
                // 后移动
                System.arraycopy(arr, pos, arr, pos + 1, i - pos);
                arr[pos] = tmp;
            }
            System.out.println("idx:" + i + ";" + Arrays.toString(arr));
        }
    }
    // get last postion
    public static int binarySearch(int[] a, int minIdx, int maxIdx, int tarVal) {
        int lowIdx = minIdx;
        int highIdx = maxIdx - 1;
        int mid = -1;
        while (lowIdx <= highIdx) {

            mid = lowIdx + ((highIdx - lowIdx) >> 1);
            if (a[mid] < tarVal) {
                int oldLow = lowIdx;
                lowIdx = mid + 1;

            } else if (a[mid] > tarVal) {
                highIdx = mid - 1;
            } else {
                // equals
//                return mid;
                break;
            }
        }
        return lowIdx;
    }






//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> mother = new ArrayList<>();
//        Queue<TreeNode> q = new LinkedList<>();
//        if (root == null) {
//            return mother;
//        }
//        q.offer(root);
//        while (!q.isEmpty()) {
//            // 记录级别size
//            int qsize = q.size();
//            // 遍历本级别
//            List<Integer> ilist = new ArrayList<>();
//            while (qsize --  > 0) {
//                TreeNode tpNode = q.poll();
//                ilist.add(tpNode.val);
//                // 后一个级别入q
//                if (tpNode.left != null) {
//                    q.add(tpNode.left);
//                }
//                if (tpNode.right != null) {
//                    q.add(tpNode.right);
//                }
//            }
//            // 一个级别完成，2list
//            mother.add(ilist);
//        }
//
//        return mother;
//    }

    public static String longestCommonPrefix(final String[] strs) {
        StringBuilder stringBuilder = new StringBuilder();
        if (strs.length < 1) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        char[] firstChars = strs[0].toCharArray();
        for (int i = 1; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (firstChars.length < j + 1 || firstChars[j] == '-') {
                    continue;
                } else {
                    if (firstChars[j] == chars[j]) {

                    } else {
                        firstChars[j] = '-';
                    }
                }
            }
        }
        for (char c : firstChars) {
            if (c == '-') {
                break;
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }



    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/39/
     *
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        int idxTurn = 0;
        String nowVal = "1";
        Map<String, Integer> hmap = null;
        while (idxTurn < n - 1) {
            String[] split = nowVal.split("");
            int lIdx = 0;
            int hIdx = 0;
            List<Integer> resList = new LinkedList<Integer>();
            while (hIdx < split.length) {
                if (split[lIdx].equals(split[hIdx])) {
                    hIdx++;
                    if (hIdx == split.length) {
                        resList.add(hIdx - lIdx);
                        resList.add(Integer.valueOf(split[hIdx - 1]));
                    }
                } else {
                    resList.add(hIdx - lIdx);
                    resList.add(Integer.valueOf(split[hIdx - 1]));
                    if (hIdx == split.length) {
                        resList.add(hIdx - lIdx + 1);
                        resList.add(Integer.valueOf(split[hIdx]));
                    }
                    lIdx = hIdx;
                }
            }
            //hmap toval
            idxTurn++;
            // 赋结果
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer e : resList) {
                stringBuilder.append(e);
            }
            nowVal = stringBuilder.toString();
        }
        return nowVal;
    }

    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/38/
     *
     * @param pattern
     * @return
     */
    public static int[] lps(String pattern) {
        int[] lps = new int[pattern.length()];
        int idx = 0;
        // 定义两个索引，idx为前索引，i为后索引
        for (int i = 1; i < pattern.length(); ) {
            if (pattern.charAt(i) == pattern.charAt(idx)) {
                lps[i] = idx + 1;
                idx++;
                i++;
            } else {
                if (idx == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    idx = lps[idx - 1];
                }
            }
        }
        return lps;
    }

    public static int KMP(String source, String pattern) {
        // 得到lps数组，也就是所谓的'next数组'
        int[] lps = lps(pattern);
        char[] ptChars = pattern.toCharArray();
        // source index
        int sIdx = 0;
        // pattern index
        int pIdx = 0;
        // 循环到有索引到底为止
        while (sIdx < source.length() && pIdx < ptChars.length) {
            // 字符相等
            if (source.charAt(sIdx) == ptChars[pIdx]) {
                // source和pattern索引前进
                sIdx++;
                pIdx++;
            } else {
                // 失败的匹配，字符不等
                if (pIdx == 0) {
                    // pattern索引是或已经是在首位（无法再回退），则主串指针向右移动
                    sIdx++;
                } else {
                    // pattern索引不在首位，前一个对应的lps数组元素 即为pattern的索引
                    pIdx = lps[pIdx - 1];

                }
            }
        }
        if (pIdx == ptChars.length) {
            // 成功,注意不是ptChars.length - 1
            // 返回首个匹配位置
            return sIdx - ptChars.length;
        }
        return -1;
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


    private int[] arrs = {7,6,4,3,1};

    public void rotate(int[] nums, int k) {
        int realK = k % nums.length;
        for (int idx = 0; idx < realK; idx ++) {
            int tim = nums[0];
            for (int idx2 = 1; idx2 < nums.length; idx2 ++) {
                int tim2 = nums[idx2];
                nums[idx2] = tim;
                tim = tim2;
            }
            nums[0] = tim;
        }
    }

}
