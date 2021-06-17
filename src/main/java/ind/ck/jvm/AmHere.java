package ind.ck.jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 上午10:20 21-2-2
 **/
public class AmHere {

    public void sand() {
        try {
            System.out.println("mmmm");
        } catch (Exception e) {

        }
    }

    public void sand2() {
        System.out.println("mmmm2222");
    }

    /**
     * 一种神奇的feb，在小范围内累加
     * @param n
     * @return
     */
    public static int nearFeb(int n) {
        if (n < 3) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = dp[2] = 1;
        for (int i = 3; i < dp.length; i ++) {

            dp[i] = getFixedLast(dp[i - 1] + "", 7) + getFixedLast(dp[i - 2] + "", 7);
            System.out.println(i + "," + dp[i]);
        }
        return dp[n];
    }

    /**
     * 获取后pos+1位的int值
     * @param value
     * @param pos
     * @return
     */
    public static int getFixedLast(String value, int pos) {
        assert pos > 2;
        if (value.length() > pos) {
            String substring = value.substring(value.length() - pos - 1);
            if (substring.charAt(0) == '0') {
                // 1st 0
                int v = 1;
                for (int i = pos; i > 0; i --) {
                    v *= 10;
                }
                return v + Integer.valueOf(substring.substring(1));
            } else {
                return Integer.valueOf(substring);
            }
        } else {
            // <= donothing
            return Integer.valueOf(value);
        }
    }


    public static int isPow2(int value, int times) {
        if (value == 1) {
            return times;
        }
        if (value % 2 != 0) {
            return 3;
        }
        return isPow2(value / 2, times + 1);
    }


    public static int dia(String tel) {
        List<Integer> ns = new ArrayList<>(20);
        // 第一位在5
        ns.add(5);
        for (int i = 0; i < tel.length(); i ++) {
            ns.add(Integer.valueOf(tel.substring(i, i + 1)));
        }
        int sum = 0;
        int [][] cache = new int[][] {
                /** 0到 0,1,2,3,...9，0 的距离缓存，懒得bfs了，这样快**/
                {0, 4, 3, 4, 3, 2, 3, 2, 1, 2, },
                {4, 0, 1, 2, 1, 2, 3, 2, 3, 4, },
                {3, 1, 0, 1, 2, 1, 2, 3, 2, 3, },
                {4, 2, 1, 0, 3, 2, 1, 4, 3, 2, },
                {3, 1, 2, 3, 0, 1, 2, 1, 2, 3, },

                {2, 2, 1, 2, 1, 0, 1, 2, 1, 2, },
                {3, 3, 2, 1, 2, 1, 0, 3, 2, 1, },
                {2, 2, 3, 4, 1, 2, 3, 0, 1, 2, },
                {1, 3, 2, 3, 2, 1, 2, 1, 0, 1, },
                {2, 4, 3, 2, 3, 2, 1, 2, 1, 0, },

        };
        // 5-1 2;1-3 2;3-2 1
        //
        for (int i = ns.size() - 2; i >= 0; i --) {
            sum += cache[ns.get(i + 1)][ns.get(i)];
            System.out.println(ns.get(i + 1) + "," + ns.get(i) + "," + sum);
        }
        return sum;
    }


    public static void main(String[] args) throws IOException {
        System.out.println(dia("55555"));


//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String n = br.readLine().trim();
//        System.out.println(dia(Long.valueOf(n)));
//        System.out.println(getFixedLast("1", 5));
//        System.out.println(getFixedLast("21", 5));
//        System.out.println(getFixedLast("123456789", 5));
//        System.out.println(getFixedLast("120056789", 5));
//        System.out.println(getFixedLast("102000009", 5));
//        System.out.println(getFixedLast("12121212121212123", 5));

//        String res = nearFeb(967) + "";
//////        res = "1010101010102";
//        int sIdx = res.length() - 6;
//        System.out.println("===================");
//        System.out.println(res.substring(sIdx > 0 ? sIdx : 0));

//        System.out.println(isPow2(2));
//        System.out.println(isPow2(8));
//        System.out.println(isPow2(18));
//        System.out.println(isPow2(20));
//        System.out.println(isPow2(22));
//        System.out.println(isPow2(1));
//        System.out.println(isPow2(3));
//        System.out.println(isPow2(4));
//        System.out.println(isPow2(5));
//        System.out.println(isPow2(6));


//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine().trim());
//        System.out.println(n);
//        System.out.println(get32BitBinString(4));
//        System.out.println(get32BitBinString(-4));
//        System.out.println(get32BitBinString(126));
//        System.out.println(get32BitBinString(-126));

    }

    private static String get32BitBinString(int number) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < 32; i++){
            sBuilder.append(number & 1);
            number = number >>> 1;
        }
        return sBuilder.reverse().toString();
    }
}
