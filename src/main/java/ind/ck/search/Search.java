package ind.ck.search;

/**
 * @author CaiKun 2020-01-19 16:19:17
 * @Description:
 * @Project algorithm
 */
public class Search {

    private static int[] sortedArray = {2, 3, 5, 6, 7, 9, 12, 23, 23, 24, 25, 26, 26, 27, 27, 28};
    private static int[] sortedArray2 = {1, 1, 1, 1, 1, 1, 2};

    // 左右边界

    public static int indexInBinarySearch(int[] sortedArray, int targetValue) {
        if (sortedArray == null || sortedArray.length == 0) {
            return -1;
        }
        int lIdx = 0;
        int rIdx = sortedArray.length - 1;

        while (lIdx <= rIdx) {
            int nowIdx = (lIdx + rIdx) / 2;
            if (sortedArray[nowIdx] == targetValue) {
                return nowIdx;
            } else if (sortedArray[nowIdx] < targetValue) {
                lIdx = nowIdx + 1;
            } else if (sortedArray[nowIdx] > targetValue) {
                //  high = mid;
                rIdx = nowIdx - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(indexInBinarySearch(sortedArray2, 1));
    }


}
