//4. (12 points) Write a method that, given an array of entries and a comparator for the
//key-type, checks if the array represents a heap.

import java.util.Comparator;

public class EX4 {

    public static <K,V> boolean checkHeapIt(Entry<K,V>[] arr, Comparator<K> comp){
        for(int p = parent(arr.length-1); p >= 0; p--) {
            if (hasRight(arr, p)) {
                if (comp.compare(arr[left(p)].getKey(), arr[p].getKey()) < 0 || comp.compare(arr[right(p)].getKey(), arr[p].getKey()) < 0)
                    return false;
            }
            if(comp.compare(arr[left(p)].getKey(), arr[p].getKey()) < 0)
                return false;
        }
        return true;
    }

    private static int parent(int j){return (j-1)/2;}
    private static int left(int j){return j*2+1;}
    private static int right(int j){return j*2+2;}
    private static <K,V> boolean hasLeft(Entry<K,V>[] arr, int j){
        return left(j) < arr.length;
    }
    private static <K,V> boolean hasRight(Entry<K,V>[] arr, int j){
        return right(j) < arr.length;
    }



    //for testing
    protected static class PQEntry<K,V> implements Entry<K,V> {
        private K key;
        private V value;
        public PQEntry(K k, V v){
            key = k;
            value = v;
        }
        public K getKey(){return key;}
        public V getValue(){return value;}
        public void setKey(K k){key = k;}
        public void setValue(V v){value = v;}
    }
    public static <V> void print(Entry<Integer,V>[] arr){
        for(int j=0; j < arr.length; j++)
            System.out.println(arr[j].getValue());
    }
    public static void main(String[] args) {
        PQEntry<Integer,Integer>[] arr = (PQEntry<Integer,Integer>[])new PQEntry[4];
        Comparator<Integer> comp = new DefaultComparator<>();
        arr[0] = new PQEntry<>(0,0);
        arr[1] = new PQEntry<>(8,3);
        arr[2] = new PQEntry<>(2,1);
        arr[3] = new PQEntry<>(5,2);
//        arr[4] = new PQEntry<>(9,4);
        System.out.println(checkHeapIt(arr, comp));
    }





//    does not function correctly

//    public static <K,V> boolean checkHeap(Entry<K,V>[] arr){
//        return checkHeapSubtree(arr, 0, true);
//    }
//    public static <K,V> boolean checkHeapSubtree(Entry<K,V>[] arr, int j, boolean isHeap){
//        if(hasLeft(arr, j)){
//            System.out.println("a");
//            if (compareEntry(arr[left(j)], arr[j]) > 0)
//                checkHeapSubtree(arr, left(j), isHeap);
//            else isHeap = false;
//        }
//        if(hasRight(arr, j) && isHeap){
//            System.out.println("b");
//            if (compareEntry(arr[right(j)], arr[j]) > 0)
//                checkHeapSubtree(arr, right(j), isHeap);
//            else isHeap = false;
//        }
//        return isHeap;
//    }


//    does not function correctly

//    public static <K,V> boolean checkHeapChildren(Entry<K,V>[] arr, Comparator<K> comp){
//        return checkHeapChildrenSubtree(arr, 0, comp);
//    }
//
//    public static <K,V> boolean checkHeapChildrenSubtree(Entry<K,V>[] arr, int j, Comparator<K> comp){
//        ArrayList<Integer> children = childrenIndices(arr, j);
//        for(int i=0; i < children.size(); i++) {
//            if (comp.compare(arr[children.get(i)].getKey(), arr[j].getKey()) < 0)
//                return false;
//            checkHeapChildrenSubtree(arr, children.get(i), comp);
//        }
//        return true;
//    }
//
//    private static <K,V> ArrayList<Integer> childrenIndices(Entry<K,V>[] arr, int j){
//        ArrayList<Integer> childrenIndices = new ArrayList<>();
//        if(hasLeft(arr, j))
//            childrenIndices.add(left(j));
//        if(hasRight(arr, j))
//            childrenIndices.add(right(j));
//        return childrenIndices;
//    }



//    unneeded

//    private static <K,V> Entry<K,V>[] children(Entry<K,V>[] arr, int j){
//        ArrayList<Entry<K,V>> children = new ArrayList<>();
//        if(hasLeft(arr, j))
//            children.add(arr[left(j)]);
//        if(hasRight(arr, j))
//            children.add(arr[right(j)]);
//        Entry<K,V>[] childrenArray = (Entry<K,V>[]) new Entry[children.size()];
//        children.toArray(childrenArray);
//        return childrenArray;
//    }

//    private static <K,V> int numChildren(Entry<K,V>[] arr, int j){
//        if(hasRight(arr, j)) return 2;
//        if(hasLeft(arr, j)) return 1;
//        return 0;
//    }

//    private static <K> int compareKey(K a, K b){
//        return ((Comparable<K>)a).compareTo(b);
//    }
//    private static <K,V> int compareEntry(Entry<K,V> a, Entry<K,V> b){
//        return compareKey(a.getKey(), b.getKey());
//    }
}
