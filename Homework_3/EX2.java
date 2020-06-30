//2. (20 points) Extend the CircularlyLinkedList class with a method/function that
//implements the removeLast operation for a circularly linked list. What is the running
//time of your method/function? Can you improve the running time? If so, how?

//remove the last element of a circularly linked list
public class EX2 {
    //runs and tests the program
    public static void main(String[] args) {
        //for the circularly linked list
        CLL<Integer> C = new CLL<>();
        C.addLast(0);
        C.addLast(1);
        C.addLast(2);
        C.addLast(3);
        C.addLast(4);
        C.print();
        System.out.println("removed last element: " + C.removeLast());
        C.print();

        //for the improved doubly circular linked list
        //there is a bug that I couldn't find nor fix
        //please leave feedback
        DCLL<Integer> DC = new DCLL<>();
        DC.addLast(0);
        DC.addLast(1);
        DC.addLast(2);
        DC.addLast(3);
        DC.addLast(4);
    }
}

//the runtime of the method is O(n), because it goes through n elements.
//I tried to improve it by creating a doubly circular linked list which has
//reference to the previous element as well.
//therefore, it doesn't need to traverse the entire list to reach the end, instead
//it just makes a single step backwards. this would reduce the complexity to O(1).
