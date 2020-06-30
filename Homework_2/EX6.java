import java.util.*;

public class EX6 {
//    takes arrays and runs the program to check both methods.
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 2, 32, 8, 11, 5};
        int[] arr2 = {1, 3, 2, 32, 8, 11, 5};
        insertionSortRecursive(arr1, 0);
        printArray(arr1);
        quickSort(arr2);
        printArray(arr2);
    }


//    (a) (5 points) Modify the insertion sort algorithm by replacing the main loop with
//    recursion. Test your method properly by using it in a program.
    public static int insertionSortRecursiveInner(int[] arr, int item, int index){
        if(index == 0 || arr[index - 1] <= item)
            return index;
        arr[index] = arr[index - 1];
        return insertionSortRecursiveInner(arr, item, index-1);
    }
    public static void insertionSortRecursive(int[] arr, int i){
        if(i == arr.length)
            return;
        int item = arr[i];
        arr[insertionSortRecursiveInner(arr, arr[i], i)] = item;
        insertionSortRecursive(arr, i+1);
    }

//    public static void insertionSortRecursive(int[] arr, int item, int index, int i){
//        if(i == arr.length)
//            return;
//        else {
//            item = arr[i];
//            if (index == 0 || arr[index - 1] <= item)
//                return;
//            arr[index] = arr[index - 1];
//            insertionSortRecursive(arr, item, i - 1, i);
//        }
//        arr[index] = item;
//        insertionSortRecursive(arr, item, index, i+1);
//    }


//    (b) (5 points) Modify the quick-sort algorithm to rely on a randomly chosen pivot.
//    Test your method properly by using it in a program.
    public static void quickSort(int[] arr){
        int size = arr.length;
        if(size < 2) return;

        Random rand = new Random();
        int pivot = rand.nextInt(size);
        int swap = arr[pivot];
        arr[pivot] = arr[size - 1];
        arr[size - 1] = swap;
        pivot = arr[size - 1];

        int m = 0, k = size;
        int[] temp = new int[size];

        for(int i = 0; i < size - 1; i++) {
            if (arr[i] < pivot)
                temp[m++] = arr[i];
            else if (arr[i] > pivot)
                temp[--k] = arr[i];
        }

        int[] L = Arrays.copyOfRange(temp, 0, m);
        int[] E = new int[k - m];
        Arrays.fill(E, pivot);
        int[] G = Arrays.copyOfRange(temp, k, size);

        quickSort(L);
        quickSort(G);

        System.arraycopy(L, 0, arr, 0, m);
        System.arraycopy(E, 0, arr, m, k - m);
        System.arraycopy(G, 0, arr, k, size - k);
    }

//    prints the elements of the array (used for checking).
    public static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }


//    (c) (5 points) Give an example input of length 10 on which merge-sort runs in O(n log n)
//    time, insertion sort runs in O(n) time, and quick-sort (where the pivot is the last
//    element) runs in O(n2) time to sort in non-decreasing order of elements. Illustrate
//    all three sorting algorithms for that example. Specify the running times of all three
//    algorithms on the reverse of your example.

     /*
     an input already sorted in non-decreasing order of elements will satisfy the above mentioned requirements.
     an example can be the following: {1, 3, 4, 4, 6, 9, 11, 17, 21, 30}.
     This is true because, merge-sort doesn't discriminate between a sorted, partially sorted or unsorted arrays
     and always runs in O(nlogn) time, without a best, average or worst case scenarios. Whereas insertion sort
     traverses a sorted array only once without going into the nested loop responsible for the shifts, thus it
     runs in O(n) time, which is the best case. Finally, quick-sort stops representing a tree when all the work
     lands on its left side, therefore it is the worst case scenario with O(n^2) complexity.
     On the other hand, when we take the reverse of this example it becomes a non-increasing array in the form
     of {30, 21, 17, 11, 9, 6, 4, 4, 3, 1}.
     In this case merge-sort runs in O(nlogn) time, insertion sort runs in O(n^2) which is the worst case, and
     quick-sort runs in O(n^2) time because now all the work lands of the right side of the tree.
     */



}
