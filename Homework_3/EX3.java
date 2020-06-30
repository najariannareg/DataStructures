//3. (22 points) Implement the Deque ADT using a circular array. Note that all
// operations should have O(1) running time. Your ArrayDeque class should implement the Deque
//interface given in the textbook. Write a program to test all the methods of your class.

//Deque with circular arrays
public class EX3 {
    public static void main(String[] args) {
        DCA<Integer> test = new DCA<>(10);
        System.out.println(test.isEmpty());
        test.addFirst(6);
        test.addFirst(5);
        test.addFirst(7);
        //Something is not working right, and I can't find the mistake.
        //Please give feedback
        test.print();
        test.removeFirst();
        test.addLast(9);
        test.print();
        System.out.println(test.size());
        System.out.println(test.first());
        System.out.println(test.isEmpty());
        test.removeLast();
        test.addLast(8);
        test.print();
        System.out.println(test.last());
    }

}
