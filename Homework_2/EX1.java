//    (10 points) We have an integer array the elements of which are initially increasing up
//    to some index i, and they are decreasing starting from index i. Let's call this index the
//    peak. Write an ecient method/function that, given such an array, determines the peak
//    index i. Write a program that inputs the elements of an array (note that the array size is
//    not given) and uses your method to output the peak index.
//    Give big-Oh estimates for the running times of both the method and the whole program.
//    Briefly justify your answer.

//    finds the peak of an integer array with elements initially increasing and then decreasing.
import java.util.*;

public class EX1 {
//    takes the inputs and runs the program.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the size of the array: ");
        int size = sc.nextInt();
        while(size < 0)
            size = sc.nextInt();
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            System.out.print(i + "th element: ");
            arr[i] = sc.nextInt();
        }
        System.out.println(findPeak(arr));
    }

//    finds the peak.
    public static int findPeak(int[] arr){
        int low = 0;
        int high = arr.length - 1;
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1])
                return mid;
            else if(arr[mid] < arr[mid+1])
                high = mid-1;
            else
                low = mid+1;
        }
        return -1;
    }
}

/*
the program run in O(logn) time because binary search is used to find the peak.
the programs splits the array from the middle and only uses one side of it.
 */