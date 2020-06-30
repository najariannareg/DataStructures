//3. (15 points) Write a generic method that, given the root node (this is the Node class for
//linked binary trees) of a BST or AVL tree, returns the key of the median entry.

import java.util.ArrayList;

public class EX3 <K,V>{

    //return the key of the median entry
    public static <K,V> K median(LinkedBinaryTree.Node<Entry<K,V>> root){
        ArrayList<K> arr = new ArrayList<>();
        medianSubtree(root, arr);
        return arr.get(arr.size()/2 - 1 + (arr.size() & 1));
    }
    //creates an ArrayList containing all the entries sorted in increasing order
    private static <K,V> void medianSubtree(LinkedBinaryTree.Node<Entry<K,V>> root, ArrayList<K> arr){
        if(root != null) {
            if (root.getLeft() != null)
                medianSubtree(root.getLeft(), arr);
            if(root.getElement() != null)
                arr.add(root.getElement().getKey());
            if (root.getRight() != null)
                medianSubtree(root.getRight(), arr);
        }
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
        map.put("e", 1);
        map.put("f", 1);
        System.out.println(median((LinkedBinaryTree.Node<Entry<String, Integer>>)map.root()));
    }


}
