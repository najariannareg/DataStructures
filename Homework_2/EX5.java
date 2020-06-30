//    (20 points) Write a method/function that, given the head node of a singly linked list
//    of integer elements, satises all three points below:
//    (a) modies the list by removing all the nodes with prime elements,
//    (b) prints the sizes of the original and modied list,
//    (c) returns the head of the modied list.
//    Your method may traverse the list only once. You are not allowed to create any nodes.
//    Test your method properly by using it in a program. Any code that does not compile
//    will not receive any points.

public class EX5 {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.addLast(2);
        test.addLast(4);
        test.addLast(7);
        test.addLast(9);
        test.addLast(14);
        test.addLast(11);
        test.removePrimes(test.getHead());

    }
}


class SinglyLinkedList<E> implements Cloneable {

    private static class Node<E> {


        private E element;            // reference to the element stored at this node
        private Node<E> next;         // reference to the subsequent node in the list

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() { return element; }

        public Node<E> getNext() { return next; }

        public void setNext(Node<E> n) { next = n; }
    }

    private Node<E> head = null;               // head node of the list (or null if empty)
    private Node<E> tail = null;               // last node of the list (or null if empty)
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() { }              // constructs an initially empty list

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public E first() {             // returns (but does not remove) the first element
        if (isEmpty()) return null;
        return head.getElement();
    }

    public E last() {              // returns (but does not remove) the last element
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void addFirst(E e) {                // adds element e to the front of the list
        head = new Node<>(e, head);              // create and link a new node
        if (size == 0)
            tail = head;                           // special case: new node becomes tail also
        size++;
    }

    public void addLast(E e) {                 // adds element e to the end of the list
        Node<E> newest = new Node<>(e, null);    // node will eventually be the tail
        if (isEmpty())
            head = newest;                         // special case: previously empty list
        else
            tail.setNext(newest);                  // new node after existing tail
        tail = newest;                           // new node becomes the tail
        size++;
    }

    public E removeFirst() {                   // removes and returns the first element
        if (isEmpty()) return null;              // nothing to remove
        E answer = head.getElement();
        head = head.getNext();                   // will become null if list had only one node
        size--;
        if (size == 0)
            tail = null;                           // special case as list is now empty
        return answer;
    }


//    checks if the integer is prime or not.
    public boolean isPrime(int n){
        for(int i = 2; i < n/2; i++)
            if(n % i == 0)
                return false;
        return true;
    }

//    removes the prime numbers, prints the original and modified sizes,
//    and returns the modified head.
    public Node<E> removePrimes(Node<E> head){
        if(head == null)
            return null;
        int sizeO = 0, sizeM = 0;
        Node<E> prevNode = new Node<E>(null, head);
        Node<E> currNode = head;
        while(currNode != null){
            if(isPrime((int)currNode.getElement())){
                prevNode.setNext(currNode.getNext());
                currNode = currNode.getNext();
            }
            else{
                prevNode = currNode;
                currNode = currNode.getNext();
                sizeM++;
            }
            sizeO++;
        }
        System.out.println(sizeO);
        System.out.println(sizeO);
        return prevNode;
    }

//    returns the head (used for testing only)
    public Node<E> getHead(){
        return head;
    }




    @SuppressWarnings({"unchecked"})
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        SinglyLinkedList other = (SinglyLinkedList) o;   // use nonparameterized type
        if (size != other.size) return false;
        Node walkA = head;                               // traverse the primary list
        Node walkB = other.head;                         // traverse the secondary list
        while (walkA != null) {
            if (!walkA.getElement().equals(walkB.getElement())) return false; //mismatch
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }
        return true;   // if we reach this, everything matched successfully
    }

    @SuppressWarnings({"unchecked"})
    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
        // always use inherited Object.clone() to create the initial copy
        SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe cast
        if (size > 0) {                    // we need independent chain of nodes
            other.head = new Node<>(head.getElement(), null);
            Node<E> walk = head.getNext();      // walk through remainder of original list
            Node<E> otherTail = other.head;     // remember most recently created node
            while (walk != null) {              // make a new node storing same element
                Node<E> newest = new Node<>(walk.getElement(), null);
                otherTail.setNext(newest);     // link previous node to this one
                otherTail = newest;
                walk = walk.getNext();
            }
        }
        return other;
    }

    public int hashCode() {
        int h = 0;
        for (Node walk=head; walk != null; walk = walk.getNext()) {
            h ^= walk.getElement().hashCode();      // bitwise exclusive-or with element's code
            h = (h << 5) | (h >>> 27);              // 5-bit cyclic shift of composite code
        }
        return h;
    }



    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = head;
        while (walk != null) {
            sb.append(walk.getElement());
            if (walk != tail)
                sb.append(", ");
            walk = walk.getNext();
        }
        sb.append(")");
        return sb.toString();
    }
}
