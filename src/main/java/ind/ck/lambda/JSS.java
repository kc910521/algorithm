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



    /**
     * 位置
     */
    static class Step {

        int x = 0;
        int y = 0;

    }

    /**
     * 公主
     */
    static class Princess {
        // 0 北，1 西，2南;3 东
        private int headTo = 0;

        JSS.Step step = new JSS.Step();

        public void goForward() {
            switch (headTo) {
                case 0:
                    step.y ++;
                    break;
                case 1:
                    step.x --;
                    break;
                case 2:
                    step.y --;
                    break;
                case 3:
                    step.x ++;
                    break;
                default:
                    throw new RuntimeException("params error");
            }
        }

        public void turnLeft() {
            if (headTo == 3) {
                headTo = 0;
            } else {
                headTo ++;
            }
        }

        public void turnRight() {
            if (headTo == 0) {
                headTo = 3;
            } else {
                headTo --;
            }
        }

        /**
         * 回到原点
         * @return
         */
        private boolean circle() {
            return headTo == 0 && step.x == 0 && step.y == 0;
        }
    }

    /**
     * 控制器
     */
    static class Command {

        private JSS.Princess princess = null;

        public Command(JSS.Princess princess) {
            this.princess = princess;
        }

        private void exec(String c) {
            switch (c) {
                case "S":
                    princess.goForward();
                    break;
                case "R":
                    princess.turnRight();
                    break;
                case "L":
                    princess.turnLeft();
                    break;
                default:
                    throw new RuntimeException("?c");
            }
        }

    }

//    public static void main(String []args) throws Exception {
//        JSS.Princess princess = new JSS.Princess();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine().trim());
//        StringBuilder stringBuilder = new StringBuilder();
//        while (--n >= 0) {
//            stringBuilder.append(br.readLine().trim());
//        }
//        JSS.Command command = new JSS.Command(princess);
//        String[] commands = stringBuilder.toString().split("");
//        int i = 0;
//        for (; i < 6; i ++) {
//            for (String cm : commands) {
//                command.exec(cm);
//            }
//            if (princess.circle()) {
//                System.out.println("yes");
//                break;
//            }
//        }
//        if (i == 6) {
//            System.out.println("no");
//        }
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
