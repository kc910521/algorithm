package ind.ck.construct;

import java.util.Arrays;

/**
 * @Author caikun
 * @Description
 *
 * @Date 下午4:37 20-5-25
 **/
public class Graphs {

    // 小心负权回路
    // 99意味正无穷
    static int[][] data = {
        {0, 2, 6, 4},
        {99, 0, 3, 99},
        {7, 99, 0, 1},
        {5, 99, 12, 0}
    };

    public static void main(String[] args) {
        System.out.println(data[1][2]);
        for (int k = 0; k < data.length; k ++) {
            for (int i = 0;i < data.length; i ++) {
                for (int j = 0; j < data[i].length; j ++) {
                    if (data[i][j] > data[i][k] + data[k][j]) {
                        data[i][j] = data[i][k] + data[k][j];
                    }
                }
            }
        }



        for (int [] d1 : data) {
            System.out.println(Arrays.toString(d1));
        }
    }

}
