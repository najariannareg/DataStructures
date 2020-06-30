//Write a method to find the min element in the stack.
//The stack should be the same at the end.
//Auxiliary data structures may be used.

import java.util.Stack;
public class EX15 {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(3);
        s.push(7);
        s.push(4);
        s.push(1);
        s.push(9);
        s.push(2);
        System.out.println(findMin(s));
    }
    public static int findMin(Stack s) throws IllegalStateException{
        if(s.empty()) throw new IllegalStateException("stack is empty");
        int min = (int)s.pop();
        while(!s.empty()){
            if(min > (int)s.peek())
                min = (int)s.pop();
            else s.pop();
        }
        return min;
    }
}
