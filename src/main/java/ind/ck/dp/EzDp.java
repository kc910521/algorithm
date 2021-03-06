package ind.ck.dp;

import java.util.LinkedList;
import java.util.List;

/**
 * @author CaiKun 2019-12-27 15:15:46
 * @Description: 动态规划
 * @Project algorithm
 */
public class EzDp {


    /**
     * 一栋楼有floorQuantity层，有eggQuantity个鸡蛋，
     * 最坏情况下，至少扔几次鸡蛋才能确定 @楼层F ？
     * <p>
     * 楼层F： 在这层，鸡蛋扔下去恰好没碎，高于F的楼层都会碎，低于F的楼层都不会碎
     * <p>
     * 1<=F<=floorQuantity
     *
     * @param eggQuantity
     * @param floorQuantity
     * @return
     */
    public int superEggDrop(int eggQuantity, int floorQuantity) {
        if (eggQuantity == 1) {
            return floorQuantity;
        }


        return -1;
    }


    /**
     * 给你一根长度为n的绳子，
     * 请把绳子剪成m段 (m和n都是整数，n>1并且m>1)
     * 每段绳子的长度记为k[0],k[1],…,k[m].
     * 请问k[0]k[1]…*k[m]可能的最大乘积是多少？
     */

    /**
     * 1.递归
     *
     * @return
     */
    public int cuttingForMaxProduct(int part) {
        return 0;
    }

    public static int cutting(int n) {
        //长度小于等等于1没办法剪
        if (n <= 1)
            return 0;
        //对于f(2)，长度为2的绳子，只有一种剪法，剪成两段长度为1的绳子，剪后的乘积为1
        if (n == 2)
            return 1;
        //对于f(3)，长度为3的绳子，只有一种剪法，剪成两段长度为1和2的绳子，但剪后的乘积为2
        if (n == 3)
            return 2;
        int max = 0;
        //数组用于存储绳子乘积最大值
        int value[] = new int[n + 1];
        value[0] = 0;
        value[1] = 1;
        //剪后的乘积为1，比自身更小；如果不是求自身的值，要求乘积最大值的话就没必要剪
        value[2] = 2;
        //剪后的乘积为2，比自身更小；如果不是求自身的值，要求乘积最大值的话也没必要剪
        value[3] = 3;
        //从f(4)开始迭代
        for (int i = 4; i <= n; i++) {
            max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int val = value[j] * value[i - j];
                max = val > max ? val : max;
            }
            value[i] = max;
        }
        max = value[n];
        return max;
    }


    /**
     * rule：
     * 每次选择一个数cNumber，该数是入参number除其本身外的约数
     * 下一次另一人选择的起始number=number-cNumber
     * Alias先手
     * 所有数字都>0
     * <p>
     * 胜利条件：
     * Alias的对手无法选择>=1的数
     *
     * @param number
     * @param isAliasTurn
     * @return
     */
    public static boolean isAliasWon(int number, boolean isAliasTurn) {
        if (number < 1) {
            return false;
//            throw new RuntimeException("illegal parameter");
        }
        if (number == 1) {
            return !isAliasTurn;
        }
        if (number == 2) {
            return isAliasTurn;
        }
        // 找到所有能选的正确约数
        List<Integer> approximations = new LinkedList<Integer>();
        for (int cNumber = 1; cNumber < number; cNumber++) {
            if (number % cNumber == 0) {
                approximations.add(cNumber);
                System.out.println("v:" + cNumber);
            }
        }


        return isAliasWon(number - approximations.get(0), !isAliasTurn);
    }


    /**
     * ind.ck.dp.Recursion#jumpMethod(int)
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法
     *
     * @return
     */
    public static int dpJumpMethod(int stairs) {
        int[] dt = new int[stairs + 1];
        dt[0] = 0;
        dt[1] = 1;
        dt[2] = 2;
        for (int i = 3; i < stairs + 1; i++) {
            dt[i] = dt[i - 1] + dt[i - 2];
        }
        return dt[stairs];
    }

    public static int jumpMethod(int stairs) {
        if (stairs <= 0) {
            return 0;
        }
        if (stairs == 1) {
            return 1;
        }
        if (stairs == 2) {
            return 2;
        }
        return jumpMethod(stairs - 1) + jumpMethod(stairs - 2);

    }


    /**
     * 您的目标是凑出某个金额w，需要用到尽量少的钞票。
     * 奇葩国家的钞票面额分别是1、5、11，
     * 非dp
     * @param total
     * @return
     */
    public static int minCashNum(int w) {
        if (w < 0) {
            return 999;
        }
        if (w == 0) {
            return 0;
        }
        if (w == 1) {
            return 1;
        }
        if (w == 5) {
            return 1;
        }
        if (w == 11) {
            return 1;
        }
        int a1 = minCashNum(w - 1);
        int a2= minCashNum(w - 5);
        int a3 = minCashNum(w - 11);
        if ((a1 * a2 * a3) == 0) {
            return 0;
        }
        int minVal = Math.min(a1, Math.min(a2, a3));
        return minVal + 1;
    }

    /**
     * c[i]=arg min(c[i-value[j] + 1 ])
     * 凑100为例，1,5,11三个面额
     * 我要分别考虑99,95，89凑到100需要的张数（1），也即100分别减三个面额的值
     * 取一个最小的张数值+1
     *
     *
     * @param w
     * @return
     */
    public static int minCashNumDp(int w) {
        int c[] = new int[w + 1];
        int value[] = {1, 5, 11};
        c[0] = 0;
        for (int i = 1; i <= w; i++) {

            int nowVal = 999;
            for (int j = 0; j < value.length; j ++) {
                if (i == value[j]) {
                    nowVal = 1;
                    break;
                } else {
                    int nt = i - value[j];
                    if (nt <= 0) {
//                        nowVal = 999;
                    } else if (nt == 0) {
                        nowVal = 1;
                    } else {
                        nowVal = Math.min(nowVal, c[nt]) + 1;
                    }
                }
            }
            c[i] = nowVal;
        }
        return c[w];
    }


    /**
     * 最大子序和
     * https://leetcode-cn.com/problems/maximum-subarray/solution/
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        // nil judge
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 初始化 最大子序合为 res为第一个元素，
        int res = nums[0];
        // 初始化，阶段最大tmpRes为0;
        int tmpRes = 0;
        // 我们想下这个题目的细节，实际上找的是：
        // 第一个正数、从前向后累加、后面出现的负数不会把前面正值给清零或者变负的    到符合这个条件最后一个正数的位置
        for (int i = 0; i < nums.length; i ++) {
            if (tmpRes > 0) {
                // 当前tmpRes 还大于0,说明还能救
                tmpRes += nums[i];
            } else {
                // 已经小于=0了，tmpRes 看起来可以放弃了，需要从头计数了，重新赋值吧
                tmpRes = nums[i];
            }
            res = Math.max(res, tmpRes);
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(cutting(10));
//        System.out.println(isAliasWon(10, true));

//        System.out.println(dpJumpMethod(10));

        // 1;1;1  2;1 1;2
//        System.out.println(jumpMethod(3));

//        System.out.println(minCashNumDp(100));
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}) );
    }


}
