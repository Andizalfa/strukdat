package rbt;

public class RedBlackTree {
    private Node root;
    private final Node TNULL;

    public RedBlackTree() {
        TNULL = new Node(0);
        TNULL.color = Node.BLACK;
        root = TNULL;
    }

    public void insert(int key) {
        Node node = new Node(key);
        node.parent = null;
        node.key = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = Node.RED;

        Node y = null;
        Node x = root;

        while (x != TNULL) {
            y = x;
            if (node.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.key < y.key) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = Node.BLACK;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == Node.RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == Node.RED) {
                    u.color = Node.BLACK;
                    k.parent.color = Node.BLACK;
                    k.parent.parent.color = Node.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = Node.BLACK;
                    k.parent.parent.color = Node.RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.color == Node.RED) {
                    u.color = Node.BLACK;
                    k.parent.color = Node.BLACK;
                    k.parent.parent.color = Node.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = Node.BLACK;
                    k.parent.parent.color = Node.RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = Node.BLACK;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void printTree() {
        printTreeHelper(this.root, "", true);
    }

    private void printTreeHelper(Node root, String indent, boolean last) {
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String color = root.color == Node.RED ? "RED" : "BLACK";
            System.out.println(root.key + "(" + color + ")");
            printTreeHelper(root.left, indent, false);
            printTreeHelper(root.right, indent, true);
        }
    }

    private class BSTNode {
        int key;
        BSTNode left, right;

        public BSTNode(int key) {
            this.key = key;
            this.left = this.right = null;
        }
    }

    private BSTNode bstRoot;

    public void bstInsert(int key) {
        BSTNode node = new BSTNode(key);
        if (bstRoot == null) {
            bstRoot = node;
        } else {
            BSTNode current = bstRoot;
            BSTNode parent = null;
            while (current != null) {
                parent = current;
                if (key < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            if (key < parent.key) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
    }

    public void printBST() {
        printBSTHelper(bstRoot, "", true);
    }

    private void printBSTHelper(BSTNode root, String indent, boolean last) {
        if (root != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(root.key);
            printBSTHelper(root.left, indent, false);
            printBSTHelper(root.right, indent, true);
        }
    }

    public void preorderTraversal(Node node) {
        if (node != TNULL) {
            System.out.print(node.key + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    public void inorderTraversal(Node node) {
        if (node != TNULL) {
            inorderTraversal(node.left);
            System.out.print(node.key + " ");
            inorderTraversal(node.right);
        }
    }

    public void postorderTraversal(Node node) {
        if (node != TNULL) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.key + " ");
        }
    }

    public Node getRoot() {
        return root;
    }
}
