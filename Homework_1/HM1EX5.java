/*5. (20 points) Write a recursive program that inputs a natural number n > 0, generates and prints a square shape of asterisks (the symbol *) with n squares of decremental sizes printed within each other. The smallest square should be a single asterisk.*/

import java.util.*;

class HM1EX5 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("enter n > 0: ");
    int n = sc.nextInt();
    while(n <= 0){
      System.out.print("enter n > 0: ");
      n = sc.nextInt();
    }
    rectangles(n);
  }


  private static int[][] table(int n){
    int size = 4 * n - 3;
    int[][] table = new int[size][size];
    return table;
  }

  private static int[][] buildRect(int[][] table, int start, int end){
    if(start > table.length/2){
      return table;
    }
    else{
      for(int column = start; column <= end; column++)
        table[start][column] = 1;
      for(int row = start + 1; row <= end; row++)
        table[row][end] = 1;
      for(int column = end - 1; column >= start; column--)
        table[end][column] = 1;
      for(int row = end - 1; row > start; row--)
        table[row][start] = 1;
    }
    return buildRect(table, start + 2, end -2);
  }

  private static void print(int[][] arr){
    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j < arr[i].length; j++){
        if(arr[i][j] == 0)
          System.out.print(" ");
        else
          System.out.print("*");
      }
      System.out.println();
    }
  }

  private static void rectangles(int n){
    int[][] table = table(n);
    table = buildRect(table, 0, table.length -1);
    print(table);
  }

}