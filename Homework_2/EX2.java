//    (20 points) We have two arrays: B and N. The B array contains elements, such as "b3"
//    and "b26" and the N array contains elements like "n3" and "n26". The pair "b4" and
//    "n4" is called a matching pair, while the pair "b10" and "n17" is not. All the elements in
//    N have a unique match from B. But there is a single extra element e0 in B that doesn't
//    have any matches in N. The input is k, where k = jNj, the elements of B and the elements
//    of N. Write an ecient program that receives this input and prints the value e0.
//    Give a big-Oh estimate for the running time of your program. Brie
//    y justify your
//    answer.

//    finds the single extra element in array B that doesn't have a match in N.
import java.util.*;

import static java.lang.Integer.parseInt;

public class EX2 {
//    takes the inputs and runs the program.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter size of N: ");
        int k = sc.nextInt();
        while(k < 1) {
            k = sc.nextInt();
        }
        String[] B = new String[k+1];
        String[] N = new String[k];
        System.out.println("enter elements of B: ");
        for(int i = 0; i <= k; i++){
            System.out.print(i + "th element: ");
            B[i] = sc.next();
        }
        System.out.println("enter elements of N: ");
        for(int i = 0; i < k; i++){
            System.out.print(i + "th element: ");
            N[i] = sc.next();
        }
//        String[] B = {"b2", "b3", "b5", "b1", "b4"};
//        String[] N = {"n5", "n2", "n3", "n4"};
        System.out.println(extraElement(B, N));
    }

//    finds the extra element.
    public static String extraElement(String[] B, String[] N){
        mergeSort(B);
        mergeSort(N);
        for(int i = 0; i < N.length;i++){
            if(parseInt(B[i].substring(1)) != parseInt(N[i].substring(1)))
                return B[i];
        }
        return B[B.length-1];
    }

//    merge-sort contents of array arr.
    public static void mergeSort(String[] arr){
        int size = arr.length;
        if(size < 2) return;
        int mid = size / 2;
        String[] left = Arrays.copyOfRange(arr, 0, mid);
        String[] right = Arrays.copyOfRange(arr, mid, size);
        mergeSort(left);
        mergeSort(right);
        merge(left, right, arr);
    }
//    merge contents of arrays left and right into properly sized array arr.
    public static void merge(String[] left, String[] right, String[] arr){
        int i=0, j=0;
        while(i + j < arr.length){
            if(j == right.length || (i < left.length && parseInt(left[i].substring(1)) < parseInt(right[j].substring(1))))
                arr[i + j] = left[i++];
            else
                arr[i + j] = right[j++];
        }
    }


//    public static String extraElement(String[] B, String[] N){
//        mergeSort(B);
//        mergeSort(N);
//        String[] union = new String[B.length + N.length];
//        int i=0, j=0, counter=0;
//        while(i + j < union.length){
//            if(counter > 1)
//                break;
//            if(j == N.length || (i < B.length && parseInt(B[i].substring(1)) < parseInt(N[j].substring(1)))) {
//                union[i + j] = B[i++];
//                counter++;
//            }
//            else {
//                union[i + j] = N[j++];
//                counter--;
//            }
//        }
//        return union[i+j-1];
//    }

//    public static String extraElement2(String[] B, String[] N){
//        String[] union = new String[B.length + N.length];
//        for(int i = 0; i < B.length; i++)
//            union[i] = B[i];
//        for(int j = 0; j < N.length; j++)
//            union[B.length + j] = N[j];
//        return mergeSort2(union, 0);
//    }
//    public static String mergeSort2(String[] arr, int row){
//        int size = arr.length;
//        if(size < 2) return null;
//        int mid = (int)Math.ceil(size / 2);
//        String[] left = Arrays.copyOfRange(arr, 0, mid);
//        String[] right = Arrays.copyOfRange(arr, mid, size);
//        mergeSort2(left, row+1);
//        mergeSort2(right, row+1);
//        return merge2(left, right, arr, row);
//    }
//    public static String merge2(String[] left, String[] right, String[] arr, int row){
//        int i=0, j=0, counter=0;
//        while(i + j < arr.length){
//            if(row == 0 && counter > 1)
//                break;
//            if(j == right.length || (i < left.length && parseInt(left[i].substring(1)) < parseInt(right[j].substring(1)))) {
//                arr[i + j] = left[i++];
//                counter++;
//            }
//            else {
//                arr[i + j] = right[j++];
//                counter--;
//            }
//        }
//        return arr[i+j-1];
//    }
}
