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


    public static void main(String[] args) {
//        System.out.println(cutting(10));
        System.out.println(isAliasWon(10, true));
    }


}
