package ind.ck.lambda;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午4:13 21-6-7
 **/
public class JSS {


//    static int min[][] = null;
//    static int max[][] = null;

    private static final char CODE_PLUS = '+';
    private static final char CODE_SUB = '-';


    /**
     * 把所有负号后面的都改成负号昂
     * @param opers 最少2个昂
     */
    public static void operFix(final char[] opers) {
        assert opers != null && opers.length > 1;
        int idx = 1;
        while (idx < opers.length) {
            if (opers[idx - 1] == CODE_SUB) {
                opers[idx] = CODE_SUB;
            }
            idx ++;
        }
    }

    public static  int min(int[] nums, char[] opers) {
        // todo: 校验length of nums&opers
        assert nums != null && nums.length > 1;
        int sum = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            if (CODE_SUB == opers[i - 1]) {
                // 减法
                sum -= nums[i];
            } else {
                // 加法
                sum += nums[i];
            }
        }
        return sum;
    }
//
//    public static int min(int[] nums, char[] opers) {
//        for (int i = 0; i < nums.length; i++) {
//            min[i][i] = nums[i];
//            max[i][i] = nums[i];
//        }
//        for (int step = 1; step < nums.length; step++) {
//            for (int i = 0; i + step < nums.length; i++) {
//                int f = i, t = i + step;
//                int s = nums[f];
//                for (int j = f + 1; j <= t; j++) {
//                    s += (opers[j] == '+' ? 1 : -1) * nums[j];
//                }
//                min[f][t] = max[f][t] = s;
//                for (int j = f + 1; j <= t; j++) {
//                    if (opers[j] == '-') {
//                        int nowMax = max[f][j - 1] - min[j][t];
//                        int nowMin = min[f][j - 1] - max[j][t];
//                        min[f][t] = Math.min(min[f][t], nowMin);
//                        max[f][t] = Math.max(max[f][t], nowMax);
//                    }
//                }
//            }
//        }
//        return min[0][nums.length - 1];
//
//    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine().trim();
        String[] num_strs = a.split("\\+|\\-");
        String[] oper_strs = a.split("\\d+");

        int[] nums = new int[num_strs.length];

        for (int i = 0; i < num_strs.length; i ++) {
            nums[i] = Integer.valueOf(num_strs[i]);
        }
        int res;
        if (nums.length == 1) {
            // 就一个就输出了
            res = nums[0];
        } else {
            char[] opers = new char[oper_strs.length - 1];
            for (int j = 1; j < oper_strs.length; j ++) {
                opers[j - 1] = oper_strs[j].charAt(0);
            }
            operFix(opers);
            res = min(nums, opers);
        }

        System.out.println(res);

    }
}
