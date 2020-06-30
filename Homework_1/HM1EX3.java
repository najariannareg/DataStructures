/*3. (19 points) There are three types of chocolate bars with prices X, Y , and Z. Write a recursive program that inputs the amount W in dollars, determines and outputs in how many ways you can spend exactly W dollars to buy a set of one or more chocolate bars.*/

import java.util.*;

class HM1EX3 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("amount in dollars: ");
    int amount = sc.nextInt();
    System.out.print("number of chocolates: ");
    int nChocolate = sc.nextInt();
    int[] arr = new int[nChocolate];
    for(int i = 0; i < nChocolate; i++){
      System.out.print(i + 1 + "st chocolate's price: ");
      arr[i] = sc.nextInt();
    }
    System.out.println("there are " + count(arr, nChocolate, amount) + " ways");
  }

  private static int count(int[] arr, int m, int n){
    if(n < 0)
      return 0;
    else if(n == 0)
      return 1;
    else if(m <= 0 && n > 0)
      return 0;
    else{
      return count(arr, m - 1, n) + count(arr, m, n - arr[m - 1]);
    }
  }
}