package MetodosOrdenamiento;

import Herramientas.Generar;

// Java program to
// implement Tree Sort
class TreeSort {

    // Class containing left and
    // right child of current
    // node and key value
    static class Node
    {
        int key;
        Node left, right;

        public Node(int item)
        {
            key = item;
            left = right = null;
        }
    }

    // Root of BST
    Node root;

    // Constructor
    TreeSort()
    {
        root = null;
    }

    // This method mainly
    // calls insertRec()
    void insert(int key)
    {
        root = insertRec(root, key);
    }

    /* A recursive function to
    insert a new key in BST */
    Node insertRec(Node root, int key)
    {

		/* If the tree is empty,
		return a new node */
        if (root == null)
        {
            root = new Node(key);
            return root;
        }

		/* Otherwise, recur
		down the tree */
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        /* return the root */
        return root;
    }

    // A function to do
    // inorder traversal of BST
    void inorderRec(Node root)
    {
        if (root != null)
        {
            inorderRec(root.left);
            inorderRec(root.right);
        }
    }
    void treeins(int[] arr)
    {
        for (int j : arr) {
            insert(j);
        }

    }

    // Driver Code
    public static void main(String[] args)
    {
        TreeSort tree = new TreeSort();
        int[] arreglo= Generar.cargar_arreglo("arreglo_30000000");

        long startTime = System.nanoTime(); // Capturing start time
        tree.treeins(arreglo);
        tree.inorderRec(tree.root);
        long endTime = System.nanoTime(); // Capturing end time
        double duration = (endTime - startTime) / 1_000_000_000.0; // Calculating duration in seconds
        System.out.println("Time taken for sorting: " + duration + " seconds");
    }
}

// This code is contributed
// by Vibin M