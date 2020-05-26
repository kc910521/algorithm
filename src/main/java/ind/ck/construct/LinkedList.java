package ind.ck.construct;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午2:05 20-4-10
 **/
public class LinkedList {


    private Node head;

    public void add(int value) {
        if (head == null) {
            head = new Node(value, null);
        } else {
            Node node = new Node(value, head);
            head = node;
        }
    }


    public class Node {

        Node next;

        int value;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

    }


}
