//4. (a) (25 points) Suppose you have two nonempty stacks S and T and a doubly linked
//list D. Write an ecient method/function that uses D to modify S to store all the
//original elements of both S and T, and to make T empty. In the resulting S, the
//original elements of S should go above all of the original elements of T. Note that
//both sets of elements should still be in their original order.
import java.util.Stack;

public class EX4<E>{
    //runs and checks the program
    public static void main(String[] args) {
        Stack<Integer> S = new Stack<>();
        S.push(0);
        S.push(1);
        S.push(2);
        S.push(3);
        Stack<Integer> T = new Stack<>();
        T.push(10);
        T.push(11);
        T.push(12);
        TtoS(S, T);
        printStack(S);
    }

    //moves the elements from T to S in the way described above
    public static void TtoS(Stack S, Stack T){
        DLL D = new DLL();
        while(!T.empty()) D.addFirst(T.pop());
        while(!S.empty()) D.addFirst(S.pop());
        while(!D.isEmpty()) S.push(D.removeFirst());
    }

    //destructively prints the elements of the stack (for testing)
    public static void printStack(Stack S){
        while(!S.empty()) System.out.println(S.pop());
    }
}

//b) if S has m elements and T has n elements then, it takes m steps to put
// the elements of S in D, plus n steps from T to D, plus m+n steps to take
// the elements from D back to S. Thus it performs 2(m+n) steps, where the
// constant 2 can be dropped, therefore the complexity is O(m+n)
