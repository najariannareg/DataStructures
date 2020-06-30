//2. (20 points) Write a generic class that implements the stack ADT using only a priority
//queue and one additional integer instance variable.

public class EX2<V> implements Stack<V> {
    private int comp = 0;
    private PriorityQueue<Integer, V> stack = new SortedPriorityQueue<>();
    public int size(){return stack.size();}
    public boolean isEmpty(){return stack.isEmpty();}
    public void push(V value) {
        stack.insert(comp--, value);
    }
    public V pop(){
        comp++;
        return stack.removeMin().getValue();
    }
    public V top(){
        return stack.min().getValue();
    }

    public static void main(String[] args){
        EX2<Integer> test = new EX2<>();
        test.push(0);
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.top());
        System.out.println(test.isEmpty());
        System.out.println("size: " + test.size());
    }
}


