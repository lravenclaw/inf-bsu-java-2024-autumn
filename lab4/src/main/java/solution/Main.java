package solution;

import solution.*;
import java.util.*;
import java.lang.*;

import java.util.List;

class Main {
    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        BST tree = new BST(data);

        CustomHandle handler = new CustomHandle();
        try {
            System.out.println("Root-Left-Right:");
            tree.preOrderTraversal(handler);
            System.out.println("\nLeft-Right-Root:");
            tree.postOrderTraversal(handler);
            System.out.println("\nLeft-Root-Right:");
            tree.inOrderTraversal(handler);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        int searchData = 40;
        System.out.print("\nValue " + searchData + " ");
        if (tree.search(searchData)) {
            System.out.println("found");
        } else {
            System.out.println("not found");
        }
    }
}
