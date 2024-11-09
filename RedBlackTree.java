package rbt; 
import java.util.*;

class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        int key;
        boolean color;
        Node left, right, parent;

        public Node(int key) {
            this.key = key;
            this.color = RED; // Node baru selalu merah
            this.left = this.right = this.parent = null;
        }
    }

    private Node root;
    private Node TNULL;

    // Constructor
    public RedBlackTree() {
        TNULL = new Node(0);
        TNULL.color = BLACK;
        root = TNULL;
    }

    // Pre-order, In-order, Post-order Traversals
    public void preOrderHelper(Node node) {
        if (node != TNULL) {
            System.out.print(node.key + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    public void inOrderHelper(Node node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.print(node.key + " ");
            inOrderHelper(node.right);
        }
    }

    public void postOrderHelper(Node node) {
        if (node != TNULL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.key + " ");
        }
    }

    public void preOrder() {
        preOrderHelper(root);
        System.out.println();
    }

    public void inOrder() {
        inOrderHelper(root);
        System.out.println();
    }

    public void postOrder() {
        postOrderHelper(root);
        System.out.println();
    }

    // Rotate left
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

    // Rotate right
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
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Fix violations after insertion
    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == RED) {
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.color == RED) {
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = BLACK;
    }

    // Insert a node
    public void insert(int key) {
        Node node = new Node(key);
        node.parent = null;
        node.key = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = RED;

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
            node.color = BLACK;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    // Print the tree structure (visualization)
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

            String color = root.color == RED ? "RED" : "BLACK";
            System.out.println(root.key + "(" + color + ")");
            printTreeHelper(root.left, indent, false);
            printTreeHelper(root.right, indent, true);
        }
    }

    public void printTree() {
        printTreeHelper(this.root, "", true);
    }

    // Binary Search Tree (BST) insert method
    private class BSTNode {
        int key;
        BSTNode left, right;

        public BSTNode(int key) {
            this.key = key;
            this.left = this.right = null;
        }
    }

    private BSTNode bstRoot;

    // Insert a node into BST
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

    // Print the BST structure
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

    public void printBST() {
        printBSTHelper(bstRoot, "", true);
    }

    // Main method
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // Urutan angka yang lebih memicu pelanggaran
        List<Integer> keys = Arrays.asList(10, 20, 30, 15, 25, 5, 40, 35, 50, 60);

        System.out.println("==== Binary Search Tree (BST) ====");
        System.out.println("Keys yang akan dimasukkan:");
        System.out.println(keys);

        // Membangun BST dan mencetak visualisasinya
        for (int key : keys) {
            tree.bstInsert(key);
        }
        System.out.println("\nVisualisasi Binary Search Tree (BST) sebelum penyeimbangan:");
        tree.printBST(); // Visualisasi pohon BST

        // Membangun Red-Black Tree dan mencetak visualisasinya setelah penyeimbangan
        System.out.println("\n==== Red-Black Tree (RBT) ====");
        tree = new RedBlackTree(); // Reset tree untuk RBT
        for (int key : keys) {
            tree.insert(key);
        }
        System.out.println("\nVisualisasi Red-Black Tree (RBT) setelah penyeimbangan:");
        tree.printTree();  // Visualisasi pohon RBT setelah penyeimbangan

        // Hasil traversal RBT
        System.out.println("\n==== Traversals ====");

        long startTime, endTime;

        // Pre-order traversal
        System.out.print("\nPre-order: ");
        startTime = System.nanoTime();
        tree.preOrder();
        endTime = System.nanoTime();
        System.out.println("Waktu: " + (endTime - startTime) + " nanodetik");

        // In-order traversal
        System.out.print("\nIn-order: ");
        startTime = System.nanoTime();
        tree.inOrder();
        endTime = System.nanoTime();
        System.out.println("Waktu: " + (endTime - startTime) + " nanodetik");

        // Post-order traversal
        System.out.print("\nPost-order: ");
        startTime = System.nanoTime();
        tree.postOrder();
        endTime = System.nanoTime();
        System.out.println("Waktu: " + (endTime - startTime) + " nanodetik");
    }
}
