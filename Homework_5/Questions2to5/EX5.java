//5. (15 points) Write a method that takes an array arr of n entries and a comparator for
//the key-type and produces an array of k largest entries in arr, based on their keys. The
//execution time of your algorithm should be O(n + k log n) and it should use a max-heap.

import java.util.Comparator;

public class EX5<K,V>{
    public static <K,V> Entry<K,V>[] kLargest(Entry<K,V>[] arr, Comparator<K> comp, int k){
        heapify(arr, comp);
        print(arr);
        Entry<K,V>[] kArr = (Entry<K,V>[]) new Entry[k];
        for(int i = 0; i < k; i++){
            kArr[i] = arr[0];
            swap(arr, 0, arr.length-1-i);
            downHeap(arr, comp, 0, arr.length-1-i);
        }
        return kArr;
    }
    private static <K,V> void heapify(Entry<K,V>[] arr, Comparator<K> comp){
        int start = parent(arr.length-1);
        for(int index = start; index >= 0; index--)
            downHeap(arr, comp, index, arr.length);
    }
    private static <K,V> void downHeap(Entry<K,V>[] arr, Comparator<K> comp, int parent, int size){
        while(hasLeft(arr, parent, size)){
            int leftIndex = left(parent);
            int bigChildIndex = leftIndex;
            if(hasRight(arr, parent, size)){
                int rightIndex = right(parent);
                if(comp.compare(arr[leftIndex].getKey(), arr[rightIndex].getKey()) < 0)
                    bigChildIndex = rightIndex;
            }
            if(comp.compare(arr[bigChildIndex].getKey(), arr[parent].getKey()) <= 0)
                break;
            swap(arr, parent, bigChildIndex);
            parent = bigChildIndex;
        }
    }
    private static <K,V> void swap(Entry<K,V>[] arr, int i, int j){
        Entry<K,V> temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static int parent(int index){return (index-1)/2;}
    private static int left(int index){return index*2+1;}
    private static int right(int index){return index*2+2;}
    private static <K,V> boolean hasLeft(Entry<K,V>[] arr, int index, int size){
        return left(index) < size;
    }
    private static <K,V> boolean hasRight(Entry<K,V>[] arr, int index, int size){
        return right(index) < size;
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
    public static <K,V> void print(Entry<K,V>[] arr){
        for(int i=0; i < arr.length; i++) {
            if (i == 0) System.out.print("{");
            System.out.print(arr[i].getValue() + (i == arr.length - 1 ? "}" : ", "));
        }
        System.out.println();
    }
    public static void main(String[] args) {
        PQEntry<Integer,Integer>[] arr = (PQEntry<Integer,Integer>[])new PQEntry[8];
        Comparator<Integer> comp = new DefaultComparator<>();
        arr[0] = new PQEntry<>(0,0);
        arr[1] = new PQEntry<>(8,5);
        arr[2] = new PQEntry<>(2,1);
        arr[3] = new PQEntry<>(5,3);
        arr[4] = new PQEntry<>(11,6);
        arr[5] = new PQEntry<>(7,4);
        arr[6] = new PQEntry<>(4,2);
        arr[7] = new PQEntry<>(13,7);
        Entry<Integer,Integer>[] kArr = kLargest(arr, comp, 8);
        print(kArr);
    }
}