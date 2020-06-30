//2. (15 points) Write a method that, given the root node of a binary search tree and
//a comparator for strings, checks if the tree is balanced (for every internal node v, the
//heights of the children of v dier by at most 1). The entries have string keys and integer
//values. To test your method, write a comparator class for strings that orders the strings
//based on the ASCII ordering of their rst characters.

import java.util.Comparator;

public class EX2 {
    //just a string comparator
    private static class ComparatorString implements Comparator<String> {
        public int compare(String a, String b){
            return a.charAt(0) - b.charAt(0);
        }
    }

    //checks if the tree is balanced
    public static boolean isBalanced(LinkedBinaryTree.Node<Entry<String, Integer>> root, ComparatorString comp){
        if(root == null) return true;
        int left = height(root.getLeft());
        int right = height(root.getRight());
        if(Math.abs(left-right) <= 1 && isBalanced(root.getLeft(), comp) && isBalanced(root.getRight(), comp))
            return true;
        else
            return false;
    }

    //returns the height of a node
    public static int height(LinkedBinaryTree.Node<Entry<String, Integer>> root){
        int h = 1;
        if(root != null) {
            if (root.getLeft() != null)
                h = Math.max(h, 1 + height(root.getLeft()));
            if (root.getRight() != null)
                h = Math.max(h, 1 + height(root.getRight()));
        }
        return h;
    }


    //for testing
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("l", 1);
        map.put("h", 1);
        map.put("r", 1);
        map.put("p", 1);
        map.put("v", 1);
        map.put("w", 1);
        map.put("h", 1);
        map.put("i", 1);
        map.put("g", 1);
//        map.put("e", 1);
//        map.put("f", 1);
        System.out.println(height((LinkedBinaryTree.Node<Entry<String, Integer>>)map.root()));
        System.out.println(isBalanced((LinkedBinaryTree.Node<Entry<String, Integer>>)map.root(), new ComparatorString()));
    }
}
