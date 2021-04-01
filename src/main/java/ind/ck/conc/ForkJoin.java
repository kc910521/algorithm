package ind.ck.conc;

import java.util.concurrent.RecursiveTask;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午4:31 20-12-7
 **/
public class ForkJoin {

    static class SumTask extends RecursiveTask<Long> {

        static final int threshold = 5;

        private long[] array;

        private int startIdx;

        private int endIdx;

        public SumTask(long[] array, int startIdx, int endIdx) {
            this.array = array;
            this.startIdx = startIdx;
            this.endIdx = endIdx;
        }

        @Override
        protected Long compute() {
            if (endIdx - startIdx <= threshold) {
                long sum = 0;
                for (int i = startIdx; i <= endIdx; i ++) {
                    sum += array[i];
                }
                return sum;
            } else {
                int spIdx = (startIdx + endIdx) /2;
                RecursiveTask r1 = new SumTask(array, startIdx, spIdx);
                RecursiveTask r2 = new SumTask(array, spIdx + 1, endIdx);
                // intrest
                invokeAll(r1, r2);
                long v1 = (long) r1.join();
                long v2 = (long) r2.join();
                return v1 + v2;
            }
        }
    }

    public static void main(String[] args) {
        long[] array = {1L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 1L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L};
        SumTask task = new SumTask(array, 0, array.length - 1);
        System.out.println(task.compute());

    }

}
