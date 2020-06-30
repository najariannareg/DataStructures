//3. (15 points) Write a heapsort method that implements in-place heapsort for a given
//array of entries with integer keys.

public class EX3{
    public static <V> void heapSort(Entry<Integer,V>[] arr){
        phase1(arr);
        phase2(arr);
    }
    private static <V> void phase1(Entry<Integer,V>[] arr){
        for(int i = 1; i < arr.length; i++){
            upHeap(arr, i);
        }
    }
    private static <V> void phase2(Entry<Integer,V>[] arr){
        for(int i = arr.length-1; i > 0; i--){
            swap(arr, 0, i);
            downHeap(arr, 0, i);
        }
    }
    private static <V> void upHeap(Entry<Integer,V>[] arr, int child){
        while(child > 0){
            int parent = parent(child);
            if(arr[child].getKey() > arr[parent].getKey())
                break;
            swap(arr, child, parent);
            child = parent;
        }
    }
    private static <V> void downHeap(Entry<Integer,V>[] arr, int parent, int size){
        while(hasLeft(arr, parent, size)){
            int leftIndex = left(parent);
            int smallChildIndex = leftIndex;
            if(hasRight(arr, parent, size)){
                int rightIndex = right(parent);
                if(arr[leftIndex].getKey() > arr[rightIndex].getKey())
                    smallChildIndex = rightIndex;
            }
            if(arr[smallChildIndex].getKey() >= arr[parent].getKey())
                break;
            swap(arr, parent, smallChildIndex);
            parent = smallChildIndex;
        }
    }
    private static int parent(int index){return (index-1)/2;}
    private static int left(int index){return index*2+1;}
    private static int right(int index){return index*2+2;}
    private static <V> boolean hasLeft(Entry<Integer,V>[] arr, int index, int size){
        return left(index) < size;
    }
    private static <V> boolean hasRight(Entry<Integer,V>[] arr, int index, int size){
        return right(index) < size;
    }
    private static <V> void swap(Entry<Integer,V>[] arr, int index1, int index2){
        Entry<Integer,V> temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
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
        PQEntry[] arr = new PQEntry[5];
        arr[0] = new PQEntry<>(1,0);
        arr[1] = new PQEntry<>(8,3);
        arr[2] = new PQEntry<>(2,1);
        arr[3] = new PQEntry<>(5,2);
        arr[4] = new PQEntry<>(10,4);
        heapSort(arr);
        print(arr);
    }
}
