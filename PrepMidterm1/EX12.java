//Check if a given string is a palindrome using stack.

import java.util.Stack;
import java.lang.Math;
public class EX12 {
    public static void main(String[] args) {
        String s = "levellevel";
        System.out.println(isPalindrome(s));
    }
    public static boolean isPalindrome(String str){
        Stack<Character> s = new Stack<>();
        int mid = str.length()/2;
        for(int k = 0; k < mid; k++)
            s.push(str.charAt(k));
        if(str.length()%2 == 1)
            mid++;
        for(int k = mid; k < str.length(); k++){
            if(str.charAt(k) != s.peek()) return false;
            s.pop();
        }
        return true;
    }
}
