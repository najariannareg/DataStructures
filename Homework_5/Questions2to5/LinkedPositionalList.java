//import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionalList<E>, Iterable<E> {
    private static class Node<E> implements Position<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }
        public E getElement() throws IllegalStateException{
            if(next == null) throw new IllegalStateException("Position no longer valid");
            return element;
        }
        public Node<E> getPrev(){return prev;}
        public Node<E> getNext(){return next;}
        public void setElement(E e){element = e;}
        public void setPrev(Node<E> p){prev = p;}
        public void setNext(Node<E> n){next = n;}
    }

    private int size = 0;
    private Node<E> header;
    private Node<E> trailer;
    public LinkedPositionalList(){
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    public Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if(!(p instanceof Node)) throw new IllegalArgumentException("invalide p");
        Node<E> node = (Node<E>) p;
        if(node.getNext() == null) throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }
    public Position<E> position(Node<E> node){
        if(node == header || node == trailer) return null;
        return node;
    }

    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}

    public Position<E> first(){return header.getNext();}
    public Position<E> last(){return trailer.getPrev();}
    public Position<E> before(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return position(node.getPrev());
    }
    public Position<E> after(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    public Position<E> addBetween(E e, Node<E> pred, Node<E> succ){
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }
    public Position<E> addFirst(E e){
        return addBetween(e, header, header.getNext());
    }
    public Position<E> addLast(E e){
        return addBetween(e, trailer.getPrev(), trailer);
    }
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        E value = node.getElement();
        node.setElement(e);
        return value;
    }
    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        E value = node.getElement();
        Node<E> pred = node.getPrev();
        Node<E> succ = node.getNext();
        pred.setNext(succ);
        succ.setPrev(pred);
        node.setElement(null);
        node.setPrev(null);
        node.setNext(null);
        size--;
        return value;
    }



    private class PositionIterator implements Iterator<Position<E>>{
        private Position<E> cursor = first();
        private Position<E> recent = null;

        public boolean hasNext(){return cursor != null;}
        public Position<E> next() throws NoSuchElementException{
            if(cursor == null) throw new NoSuchElementException("no next element");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }
        public void remove() throws IllegalStateException{
            if(recent == null) throw new IllegalStateException("nothing to remove");
            LinkedPositionalList.this.remove(recent);
            recent = null;
        }
    }

    private class PositionIterable implements Iterable<Position<E>>{
        public Iterator<Position<E>> iterator(){return new PositionIterator();}
    }

    public Iterable<Position<E>> positions(){
        return new PositionIterable();
    }

    private class ElementIterator implements Iterator<E>{
        Iterator<Position<E>> posIt = new PositionIterator();
        public boolean hasNext(){return posIt.hasNext();}
        public E next(){return posIt.next().getElement();}
        public void remove(){posIt.remove();}
    }

    public Iterator<E> iterator(){
        return new ElementIterator();
    }

}
