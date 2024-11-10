package rbt;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        List<Integer> keys = Arrays.asList(10, 20, 30, 15, 25, 5, 40, 35, 50, 60);

        System.out.println("==== Binary Search Tree (BST) ====");
        System.out.println("Keys yang akan dimasukkan:");
        System.out.println(keys);

        for (int key : keys) {
            tree.bstInsert(key);
        }
        System.out.println("\nVisualisasi Binary Search Tree (BST) sebelum penyeimbangan:");
        tree.printBST();

        System.out.println("\n==== Red-Black Tree (RBT) ====");
        tree = new RedBlackTree(); 
        for (int key : keys) {
            tree.insert(key);
        }
        System.out.println("\nVisualisasi Red-Black Tree (RBT) setelah penyeimbangan:");
        tree.printTree();

        System.out.println("\nPre-Order Traversal:");
        tree.preorderTraversal(tree.getRoot());
        System.out.println("\nIn-Order Traversal:");
        tree.inorderTraversal(tree.getRoot());
        System.out.println("\nPost-Order Traversal:");
        tree.postorderTraversal(tree.getRoot());
    }
}
