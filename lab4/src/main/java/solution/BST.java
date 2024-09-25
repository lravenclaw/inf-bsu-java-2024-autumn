package solution;

import solution.*;
import java.util.*;
import java.lang.*;

public class BST<T extends Comparable> {
    class BSTNode<T> {
        public T data;
        BSTNode<T> left;
        BSTNode<T> right;

        public BSTNode(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private BSTNode<T> root;

    public BST() {
        root = null;
    }

    public BST(List<T> initialization_list) {
        for (T data : initialization_list) {
            insert(data);
        }
    }

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private BSTNode insertRec(BSTNode<T> root, T data) {
        if (root == null) {
            root = new BSTNode(data);
            return root;
        }

        if (data.compareTo(data) < 0) {
            root.left = insertRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    public boolean search(T data) {
        return searchRec(root, data);
    }

    private boolean searchRec(BSTNode<T> root, T data) {
        if (root == null) {
            return false;
        }

        if (data.equals(root.data)) {
            return true;
        }

        if (data.compareTo(root.data) < 0) {
            return searchRec(root.left, data);
        } else {
            return searchRec(root.right, data);
        }
    }

    public interface ValueHandler<T> {
        void call(T value);
    }

    public void preOrderTraversal(BST.ValueHandler<T> handler) {
        preOrderTraversal(root, handler);
    }

    private void preOrderTraversal(BSTNode<T> root, BST.ValueHandler<T> handler) {
        if (root == null) {
            return;
        }

        handler.call(root.data);

        preOrderTraversal(root.left, handler);
        preOrderTraversal(root.right, handler);
    }

    public void postOrderTraversal(BST.ValueHandler handler) {
        postOrderTraversal(root, handler);
    }

    private void postOrderTraversal(BSTNode<T> root, BST.ValueHandler handler) {
        if (root == null) {
            return;
        }
        
        postOrderTraversal(root.left, handler);
        postOrderTraversal(root.right, handler);

        handler.call(root.data);
    }

    public void inOrderTraversal(BST.ValueHandler handler) {
        inOrderTraversal(root, handler);
    }

    private void inOrderTraversal(BSTNode<T> root, BST.ValueHandler handler) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left, handler);

        handler.call(root.data);

        inOrderTraversal(root.right, handler);
    }
}
