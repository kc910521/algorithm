package ind.ck.construct;

import java.util.*;
import java.util.LinkedList;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午5:50 20-11-16
 **/
public class CommTree {

    private Node root = new Node();



    public void insertCom(int value, Node aroot) {
        if (aroot.value == null) {
            aroot.setValue(value);
            return;
        }
        if (value < aroot.value) {
            if (aroot.left == null) {
                aroot.left  = new Node(value);
            } else {
                insertCom(value, aroot.left);
            }
        } else {
            if (aroot.right == null) {
                aroot.right  = new Node(value);
            } else {
                insertCom(value, aroot.right);
            }
        }
    }



    public void saveInQ(int value) {
        Node root = getRoot();
        while (true) {
            if (root.value == null) {
                root.value = value;
                return;
            }
            if (value < root.value) {
                if (root.left == null) {
                    root.left = new Node(value);
                    return;
                } else {
                    root = root.left;
                }
            } else {
                if (root.right == null) {
                    root.right = new Node(value);
                    return;
                } else {
                    root = root.right;
                }
            }

        }

    }


    public void printInQ(Node aroot) {
        Stack<Node> stack = new Stack<>();
        stack.push(aroot);
        while (stack.size() > 0) {
            Node pop = stack.pop();
            System.out.println(pop.value);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }

//            if (pop.getValue() == null) {
//                pop.setValue();
//            }


        }

    }





































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

    private void printAllInPath(Node root, String lastPath) {
        if (root == null) {
            return;
        }
        lastPath = lastPath + "->" + root.value;
        if (root.left == null && root.right == null) {
            System.out.println(lastPath);
            return;
        }
        if (root.left != null) {
            printAllInPath(root.left, lastPath);
        }
        if (root.right != null) {
            printAllInPath(root.right, lastPath);
        }
    }


    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("runnable...");
//            }
//        }) {
//            @Override
//            public void run() {
//                System.out.println("thread...");
//            }
//        }.start();
        randomCreate();


//        randomCreate();
//        System.out.println("ddddddd");
//
//        Map map = new HashMap();
//
//        for (int i = 0; i < 13; i ++) {
//            String a = UUID.randomUUID().toString();
//            map.put(a, a);
//        }
//        System.out.println(map);



    }

    private static void randomCreate() {
        CommTree commTree = new CommTree();
//        commTree.insertCom(5, commTree.getRoot());
//        commTree.insertCom(5, commTree.getRoot());
//        commTree.insertCom(10, commTree.getRoot());
//        commTree.insertCom(6, commTree.getRoot());
//        commTree.insertCom(4, commTree.getRoot());
//        commTree.insertCom(1, commTree.getRoot());
//        commTree.insertCom(2, commTree.getRoot());
        commTree.saveInQ(5);
        commTree.saveInQ(5);
        commTree.saveInQ(10);
        commTree.saveInQ(6);
        commTree.saveInQ(4);
        commTree.saveInQ(1);
        commTree.saveInQ(2);
        commTree.printAllInCommon(commTree.getRoot());
        commTree.printAllInPath(commTree.getRoot(), "");
//        System.out.println("----------------1------------");
//        commTree.printAllInStack(commTree.getRoot());
//        System.out.println("----------------2------------");
//        commTree.printAllInQ(commTree.getRoot());
//        System.out.println("----------------3------------");
//        commTree.printInQ(commTree.getRoot());

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
