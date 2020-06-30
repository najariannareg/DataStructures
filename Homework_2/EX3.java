//    (20 points) Write a program that inputs an array arr of n elements and outputs the
//    number of pairs of elements that are in-order. We will call a pair of elements arr[i] and
//    arr[j] in-order if i < j and arr[i] < arr[j]. The running time of your program should be
//    O(n log n). Inecient solutions will receive partial points.

//    inputs an array arr of n elements and outputs the number of pairs of elements that are
//    in-order in O(nlogn) time.
import java.util.Arrays;

public class EX3 {
//    takes the array and runs the program.
    public static void main(String[] args) {
        int[] arr = {1, 6, 2, 9, 14, 11, 22};
        System.out.println(mergeSort(arr) + " pairs are in order");
    }
//    merge-sort contents of array arr.
    public static int mergeSort(int[] arr){
        int size = arr.length;
        if(size < 2) return 0;
        int mid = size / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, size);
        return mergeSort(left) + mergeSort(right) + merge(left, right, arr);
    }
//    merge contents of arrays left and right into properly sized array arr.
//    it counts the number of pairs.
    public static int merge(int[] left, int[] right, int[] arr) {
        int i = 0, j = 0, count = 0;
        while (i + j < arr.length) {
            if (j == right.length || (i < left.length && left[i] < right[j])) {
                arr[i + j] = left[i++];
                count = count + right.length - j;
            }
            else
                arr[i + j] = right[j++];
        }
        return count;
    }
}
