//1. (30 points) Implement bubble sort for a doubly linked list of integers. Your bubble
//sort method/function should receive only the header node of the list. Write a program
//that (i) constructs a doubly linked list of integers, (ii) calls your method to bubble sort
//the list, and (iii) prints the sorted sequence. Your method should not access or use
//any of the data/methods of the DoublyLinkedList class. Thus, the nested Node
//class must be declared public for use outside of the DoublyLinkedList class.

//bubble sort of a doubly linked list
public class EX1 {
    //runs and tests the program
    public static void main(String[] args) {
        DLL<Integer> D = new DLL<>();
        D.addLast(5);
        D.addLast(3);
        D.addLast(11);
        D.addLast(7);
        D.addLast(2);
        bubbleSortDLL(D.getHead());
        print(D.getHead());
    }

    //sorts the DLL using bubble sort
    //something is wrong with the code I can't fix it, feedback please
    public static void bubbleSortDLL(DLL.Node<Integer> head){
        DLL.Node<Integer> temp1;
        DLL.Node<Integer> temp2;
        DLL.Node<Integer> i = head;
        DLL.Node<Integer> j = head.getNext();
        boolean flag = true;
        while(flag){
            flag = false;
            while(j != null){
                if(i.getElement() > j.getElement()){
                    temp1 = new DLL.Node<Integer>(i.getElement(), i.getPrev(), i.getNext());
                    temp2 = new DLL.Node<Integer>(j.getElement(), j.getPrev(), j.getNext());
                    i.setNext(temp2.getNext());
                    i.setPrev(j);
                    j.setNext(i);
                    j.setPrev(temp1.getPrev());
                    flag = true;
                }
                i = i.getNext();
                j = j.getNext();
            }
        }
    }

    //prints the elements of the DLL
    public static void print(DLL.Node<Integer> head){
        DLL.Node<Integer> current = head;
        while(current.getNext() != null){
            System.out.println(current.getElement());
            current = current.getNext();
        }
    }

}
