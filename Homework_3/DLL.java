//Doubly Linked List
public class DLL<E> {
    public static class Node<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }
        public E getElement(){return element;}
        public Node<E> getPrev(){return prev;}
        public Node<E> getNext(){return next;}
        public void setPrev(Node<E> p){prev = p;}
        public void setNext(Node<E> n){next = n;}
    }
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;
    public DLL(){
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, header, null);
        header.setNext(trailer);
    }
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}
    public Node<E> getHead(){
        if(isEmpty()) return null;
        return header.getNext();
    }
    public E first(){
        if(isEmpty()) return null;
        return header.getNext().getElement();
    }
    public E last(){
        if(isEmpty()) return null;
        return trailer.getPrev().getElement();
    }
    public void addFirst(E e){
        addBetween(e, header, header.getNext());
    }
    public void addLast(E e){
        addBetween(e, trailer.getPrev(), trailer);
    }
    public E removeFirst(){
        if(isEmpty()) return null;
        return remove(header.getNext());
    }
    public E removeLast(){
        if(isEmpty()) return null;
        return remove(trailer.getPrev());
    }
    public void addBetween(E e, Node<E> prev, Node<E> next){
        Node<E> newest = new Node<E>(e, prev, next);
        prev.setNext(newest);
        next.setPrev(newest);
        size++;
    }
    public E remove(Node<E> node){
        Node<E> prev = node.getPrev();
        Node<E> next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return node.getElement();
    }
}