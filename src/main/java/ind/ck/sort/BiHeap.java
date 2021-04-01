package ind.ck.sort;

import java.util.Arrays;

/**
 * @Author caikun
 * @Description 二叉排序树是用来做查找的,而堆是用来做排序的。
 * @Date 下午6:45 21-2-24
 **/
public class BiHeap {

    private Node[] nodes = new Node[10];

    private int lastIndex = -1;


    public void add(Node node) {
        assert node != null;
        // 将节点插入末尾
        nodes[++ lastIndex] = node;
        // 和节点的父亲比对交换工作
        pullUp();
    }

    public void stout() {
        System.out.println(Arrays.asList(nodes));
    }

    public void popInOrder() {
        int lIdx = lastIndex;
        for (int i = 0;i < lIdx; lIdx --) {
            System.out.println(popMin().value);
        }
    }

    private void pullUp() {
        if (lastIndex == 0) {
            return;
        }
        int currIndex = lastIndex;
        while (currIndex != 0) {
            // 得到节点的父亲index
            int faIdx = (currIndex - 1) >> 1;
            // 值比父亲小，则和父亲交换
            if (nodes[currIndex].value < nodes[faIdx].value) {
                swap(faIdx, currIndex);
                // 当前位置更新
                currIndex = faIdx;
            } else {
                break;
            }
        }

        // 不需要和左右比对（堆的性质）
    }

    private void swap(int idx1, int idx2) {
        Node tmp = nodes[idx1];
        nodes[idx1] = nodes[idx2];
        nodes[idx2] = tmp;
    }

    private void pushDown() {
        int currIndex = 0;
        // 左孩子索引<即可，因为下一个一定等于
        while (((currIndex << 1) + 1) < lastIndex) {
            int nValue = nodes[currIndex].value;
            // 得到两个孩子索引
            int lIdx = (currIndex << 1) + 1;
            int rIdx = (currIndex << 1) + 2;
            // 两个孩子索引都大于容量则break
            // 和比较小的孩子交换
            int lValue = nodes[lIdx].value;
            int rValue = nodes[rIdx].value;
            if (nValue > lValue || nValue > rValue) {
                // 为什么和小的交换？因为交换上来的要保证是小的。
                if (lValue < rValue) {
                    // 向左下沉
                    swap(currIndex, lIdx);
                    currIndex = lIdx;
                } else {
                    // 向右下沉
                    swap(currIndex, rIdx);
                    currIndex = rIdx;
                }

            } else {
                // 孩子都比自己大就break
                break;
            }
            // 得到当前索引
        }


    }

    public Node popMin() {
        if (lastIndex < 0) {
            return null;
        }
        Node ret = nodes[0];
        if (lastIndex == 0) {
            nodes[0] = null;
            return ret;
        }
        // 后变前
        nodes[0] = nodes[lastIndex];
        nodes[lastIndex --] = null;
        // 头和左右比对
        pushDown();
        return ret;
    }

    static class Node {
        private int value;

        public int getValue() {
            return value;
        }

        public Node(int value) {
            this.value = value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value + "";
        }
    }

    public static void main(String[] args) {
        BiHeap biHeap = new BiHeap();
        biHeap.add(new Node(3));
        biHeap.add(new Node(1));
        biHeap.add(new Node(8));
        biHeap.add(new Node(7));
        biHeap.add(new Node(22));
        biHeap.add(new Node(2));
        biHeap.add(new Node(6));
        biHeap.add(new Node(9));
        biHeap.add(new Node(0));
        biHeap.stout();
        biHeap.popInOrder();

    }




}
