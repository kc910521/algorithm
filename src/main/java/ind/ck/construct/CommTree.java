package ind.ck.construct;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午5:50 20-11-16
 **/
public class CommTree {

    private Node root = new Node();

    private void insert(int value, Node root) {
        if (root.value == null) {
            root.setValue(value);
            return;
        }
        if (value < root.value) {
            if (root.left == null) {
                root.left = new Node(value);
                return;
            } else {
                insert(value, root.left);
            }
        } else {
            if (root.right == null) {
                root.right = new Node(value);
                return;
            } else {
                insert(value, root.right);
            }
        }

    }

    private void printAllInCommon(Node root) {
        if (root == null) {
            return;
        }
        printAllInCommon(root.left);
        System.out.println(root.value);
        printAllInCommon(root.right);
    }

    private void printAllInQ(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (q.size() != 0) {
            Node poll = q.poll();
            System.out.println(poll.value);
            if (poll.left != null) {
                q.offer(poll.left);
            }
            if (poll.right != null) {
                q.offer(poll.right);
            }
        }

    }


    private void printAllInStack(Node root) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        for (;;) {
            if (stack.size() == 0) {
                break;
            }
            Node pop = stack.pop();
            System.out.println(pop.value);
            if (pop.left == null && pop.right == null) {
                // leaf node
//                System.out.println(pop.value);
            } else {
                Node left = pop.left;
                Node right = pop.right;

                if (right != null) {
                    stack.push(right);
                }
                if (left != null) {
                    stack.push(left);
                }
            }
        }

    }

    public static void main(String[] args) {
        randomCreate();
    }

    private static void randomCreate() {
        CommTree commTree = new CommTree();
        commTree.insert(5, commTree.getRoot());
        commTree.insert(5, commTree.getRoot());
        commTree.insert(10, commTree.getRoot());
        commTree.insert(6, commTree.getRoot());
        commTree.insert(4, commTree.getRoot());
        commTree.insert(1, commTree.getRoot());
        commTree.insert(2, commTree.getRoot());
        commTree.printAllInCommon(commTree.getRoot());
        System.out.println("----------------1------------");
        commTree.printAllInStack(commTree.getRoot());
        System.out.println("----------------2------------");
        commTree.printAllInQ(commTree.getRoot());

    }

    public Node getRoot() {
        return root;
    }

    private class Node {

        private Node left;

        private Node right;

        private Integer value;

        public Node(int value) {
            this.value = value;
        }

        public Node() {
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}
