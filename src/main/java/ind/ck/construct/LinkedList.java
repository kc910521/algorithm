package ind.ck.construct;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午2:05 20-4-10
 **/
public class LinkedList {


    private Node head = new Node();

    public void add(int value) {
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node(value, null);
    }

    public void show() {
        Node node = head;
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    public Node getHead() {
        return this.head;
    }

    public void revert(Node nodeNotHead) {
        if (nodeNotHead == null | nodeNotHead.next == null) {
            return;
        }
        Node nxt = nodeNotHead.next;
        Node nxtnxt = nxt.next;
        Node headNxt = head.next;
        // 头节点的下一个去链接当前节点的下一个
        head.next = nxt;
        // 现在当前节点的下一个和头节点的下一个是相同的
        // 去处理该节点，即当前节点节点的下一个的下一个
        // 应当是之前头节点的下一个
        nxt.next = headNxt;
        // 现在当前节点的下一个还是那个老的节点，
        // 当前的下一个变为当前的下下个
        nodeNotHead.next = nxtnxt;
        revert(nodeNotHead);
    }


    public class Node {

        Node next;

        int value;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        private Node() {
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
        ll.show();
        ll.revert(ll.getHead().next);
        ll.show();

    }


}
