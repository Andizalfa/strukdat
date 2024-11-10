package rbt;

public class Node {
    int key;
    Node left, right, parent;
    boolean color;

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public Node(int key) {
        this.key = key;
        this.color = RED; // Node baru selalu merah
        this.left = this.right = this.parent = null;
    }
}
