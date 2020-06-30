/*4. (20 points) Write a recursive program that given an array of integers, determines and outputs the number of subarrays the sum of the elements of which is 0. Note that a subarray is a contiguous portion of the original array. The input to the program is a natural number n > 0, representing the length of the array, and the n elements of the array. Inecient solutions will receive partial credit.*/

import java.util.*;

class HM1EX4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("array length: ");
    int n = sc.nextInt();
    int[] arr = new int[n];
    for(int i = 0; i < n; i++){
      System.out.print(i + "th element: ");
      arr[i] = sc.nextInt();
    }
    //System.out.println("number of subarrays is: " + subNumIt(arr));
    System.out.println("number of subarrays is: " + subNumRecRow(arr, 0, 0, 0));
  }

  //iterative
  private static int subNumIt(int[] arr){
    int num = 0;
    int sum = 0;
    for(int i = 0; i < arr.length; i++){
      sum = 0;
      for(int j = i; j < arr.length; j++){
        sum += arr[j];
        if(j > 1 && sum == 0)
          num++;
      }
    }
    return num;
  }

  //recursive
  private static int subNumRecRow(int[] arr, int num, int sum, int row){
    if(row == arr.length - 1)
      return num;
    else{
      num += subNumRecCol(arr, 0, 0, 0);
    }
    return subNumRecRow(arr, num, sum, row+1);
  }

  private static int subNumRecCol(int[] arr, int num, int sum, int col){
    if(col == arr.length-1)
      return num;
    else{
      sum += arr[col];
      if(col > 1 && sum == 0)
        num++;
    }
    return subNumRecCol(arr, num, sum, col+1);
  }

}