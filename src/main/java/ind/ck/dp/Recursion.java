package ind.ck.dp;

/**
 * @author CaiKun 2020-01-19 16:59:05
 * @Description: https://mp.weixin.qq.com/s/mJ_jZZoak7uhItNgnfmZvQ
 * @Project algorithm
 */
public class Recursion {

    /**
     * ##################################################################
     * 得到阶乘的结果
     *
     * @param n
     * @return
     */
    public static int f(int n) {
        if (1 == n) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return f(n - 1) * n;
    }

    /**
     * feb
     *
     * @param n
     * @return
     */
    public static int feb(int n) {
        if (n <= 2) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    /**
     * #######################################################################
     * 青蛙跳台阶问题
     * 每次可以跳1或2阶
     * 求有多少种跳法
     * <p>
     * （每次）
     *
     * @param stairs
     * @return
     */
    public static int jumpMethod(int stairs) {
        if (stairs == 1) {
            return 1;
        }
        if (stairs == 2) {
            return 2;
        }
        return f(stairs - 1) + f(stairs - 2);
    }


    /**
     * ###################################################################
     * 使用递归的方式翻转链表
     * <p>
     * 记住，所谓翻转，你转换需要考虑的实际是方向，而非元素本身
     *
     * @param head
     * @return
     */
    public static Node reverseList(Node head) {//4
        // 在这里思考设置一个中断条件：
        // 当节点的下一元素为空，就是终端时机
        // 因为需要把这个节点返回给掉用者，所以中断时返回当前节点
        if (head == null || head.next == null) {
            return head;
        }
        // 从上往下，目的就是拿到最深处节点，同时此处之后回归
        Node cNode = reverseList(head.next);
        // 在这里思考每一步都需要做什么：
        // 翻转即是把每两个节点之间的关系转换一下
        // 例如A->B->C 在C返回后第一次真正的循环内部可以拿到方法入参head（B）和cNode（C）
        // 需要在这一轮实现 A->B<-C ，然后返回C，而且永远返回C，因为C会是翻转后的真正头节点
        // 之后的翻转是固定操作，不涉及递归知识

        //以B->C为例，我们的转换目标为B<-C
        // 1.保存B的下一个节点指针，即C
        Node nxt = head.next;
        // 2.让C的下一个节点指向b  (@98109S)
        nxt.next = head;
        // 3.将B的下一个节点置空
        // 目的是让转置后最后一个节点的next是空的
        // 你会怕影响下次循环时的关系吗？往下看
        head.next = null;
        // 也就是现在我们得到了A->B<-C，特别的，在这次递归结束时，B->next是null
        // 但是下次递归我们需要得到的是A<-B<-C，当时的入参head是A,而cNode依然为C
        // 所以B->next会在 @98109S（ind/ck/dp/Recursion.java:88） 处被设置为A，请反复理解此处
        return cNode;
    }


    public static void main(String[] args) {
//        System.out.println(jumpMethod(10));
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.next = n2;
        n2.next = n3;
        Node node = reverseList(n1);

        System.out.println(node.date);
        System.out.println(node.next.date);
        System.out.println(node.next.next.date);

    }

    public static class Node {
        int date;
        Node next;

        Node(int date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return date + "";
        }
    }
}
