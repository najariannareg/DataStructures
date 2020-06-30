//Doubly Circular Linked List
//there is problem with code, please feedback
public class DCLL<E> {
    private static class Node<E>{
        private E element;
        private Node<E> next;
        private Node<E> prev;
        public Node(E e, Node<E> predecessor, Node<E> successor){
            element = e;
            prev = predecessor;
            next = successor;
        }
        public E getElement(){return element;}
        public Node<E> getNext(){return next;}
        public void setNext(Node<E> n){next = n;}
        public Node<E> getPrev(){return prev;}
        public void setPrev(Node<E> p){next = p;}
    }
    private Node<E> tail = null;
    private int size = 0;

    public DCLL(){}
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}

    public E first(){
        if(isEmpty()) return null;
        return tail.getNext().getElement();
    }
    public E last(){
        if(isEmpty()) return null;
        return tail.getElement();
    }

    //rotating
    public void rotateForward(){
        if(tail != null)
            tail = tail.getNext();
    }
    public void rotateBackward(){
        if(tail != null)
            tail = tail.getPrev();
    }

    //adding
    public void addFirst(E e){
        if(isEmpty()) {
            tail = new Node<E>(e, null, null);
            tail.setNext(tail);
            tail.setPrev(tail);
            size++;
        }else add(e, tail, tail.getNext());
    }
    public void addLast(E e){
        addFirst(e);
        tail = tail.getNext();
    }
    public void add(E e, Node<E> predecessor, Node<E> successor){
        if(isEmpty()){
            tail = new Node<E>(e, null, null);
            tail.setNext(tail);
            tail.setPrev(tail);
        } else{
            Node<E> newest = new Node<E>(e, predecessor, successor);
            predecessor.setNext(newest);
            successor.setPrev(newest);
        }
        size++;
    }

    //removing
    public E removeFirst(){
        if(isEmpty()) return null;
        return remove(tail.getNext());
    }
    //removes the last element of the doubly circular linked list
    public E removeLastDCLL(){
        rotateBackward();
        return removeFirst();
    }
    public E remove(Node<E> node){
        if(size == 1) tail = null;
        else {
            Node<E> prev = node.getPrev();
            Node<E> next = node.getNext();
            prev.setNext(next);
            next.setPrev(prev);
        }
        size--;
        return node.getElement();
    }




    //prints all elements for testing
    public void print(){
        if(!isEmpty()) {
            Node<E> current = tail.getNext();
            int k = 0;
            do {
                System.out.println(k + "th element: " + current.getElement());
                current = current.getNext();
                k++;
            } while (current != tail.getNext());
        }
        System.out.println();
    }


}
