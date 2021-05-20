package ind.ck.construct;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午2:05 20-4-10
 **/
public class LinkedList {


    private ListNode head = new ListNode();

    public void add(int value) {
        ListNode listNode = head;
        while (listNode.next != null) {
            listNode = listNode.next;
        }
        listNode.next = new ListNode(value, null);
    }

    public void show() {
        ListNode listNode = head;
        while (listNode != null) {
            System.out.println(listNode.value);
            listNode = listNode.next;
        }
    }

    public ListNode getHead() {
        return this.head;
    }

    public void revert(ListNode listNodeNotHead) {
        if (listNodeNotHead == null | listNodeNotHead.next == null) {
            return;
        }
        ListNode nxt = listNodeNotHead.next;
        ListNode nxtnxt = nxt.next;
        ListNode headNxt = head.next;
        // 头节点的下一个去链接当前节点的下一个
        head.next = nxt;
        // 现在当前节点的下一个和头节点的下一个是相同的
        // 去处理该节点，即当前节点节点的下一个的下一个
        // 应当是之前头节点的下一个
        nxt.next = headNxt;
        // 现在当前节点的下一个还是那个老的节点，
        // 当前的下一个变为当前的下下个
        listNodeNotHead.next = nxtnxt;
        revert(listNodeNotHead);
    }

    public ListNode reverseList(ListNode head) {
        // 各种方式判空和单节点
        if (head == null || head.next == null) {
            return head;
        }
        // 依次得到第{2,3...n}个节点b
        ListNode b = head.next;
        // 依次得到第{1... n-1}个节点a
        ListNode a = head;
        // 重要：将头节点的下一个关系干掉
        head.next = null;
        // 当b不为空时
        while (b != null) {
            // 依次修改向前指向

            // b的下一个为a
            ListNode c = b.next;
            b.next = a;
            // ab颠倒
            a = b;
            b = c;
        }
        // 返回head-next
        return head;
    }


    public class ListNode {

        ListNode next;

        int value;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }

        private ListNode() {
            // 头节点
            this.value = -1;
            this.next = null;
        }

    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.add(6);
        ll.add(7);
        ll.add(8);
        ll.add(9);
        ll.add(10);
        ll.show();
        ll.reverseList(ll.getHead().next);
        ll.show();

    }


}
