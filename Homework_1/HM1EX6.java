// 6. (20 points) There is a square table 4  4 lled with lowercase English letters. You can form words by starting with a letter at a random position and appending it with new letters by going up, down, left or right. The word can pass through a position multiple times but it is not allowed to leave the boundaries of the table. Write a recursive program that inputs the 4  4 table and a word and prints \YES" or \NO" to indicate if the given word can be formed on the given table.

import java.util.*;

class HM1EX6 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    char[][] arr = new char[4][4];
    
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        System.out.print("character (" + i + ", " + j + "): ");
        arr[i][j] = sc.next().charAt(0);
      }
    }
    System.out.print("word: ");
    String word = sc.next();
    while(!word.equals("x")){
      result(arr, word);
      word = sc.next();
    }
    
    
  }

  private static boolean check(char[][] arr, String word, int row, int col){
    if(row <0 || row >= arr.length || col < 0 || col >= arr[0].length)
      return false;
    if(word.length() == 0)
      return true;
    if(arr[row][col] == word.charAt(0)){
      return check(arr, word.substring(1), row-1, col) || check(arr, word.substring(1), row+1, col) || check(arr, word.substring(1), row, col-1) || check(arr, word.substring(1), row, col+1);
    }
    return false;
  }

  private static boolean loop(char[][] arr, String word){
    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j < arr[i].length; j++){
        if(check(arr, word, i,j))
          return true;
      }
    }
    return false;
  }

  private static void print(boolean check){
    if(check)
      System.out.println("YES");
    else
      System.out.println("NO");
  }

  private static void result(char[][] arr, String word){
    boolean check = loop(arr, word);
    print(check);
  }

} 