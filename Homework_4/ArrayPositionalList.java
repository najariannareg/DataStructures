//4. (20 points) Write a generic class ArrayPositionalList that represents a positional list
//using an array of xed capacity as the underlying data structure. Note that your class
//should implement the PositionalList<E> interface.

//import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayPositionalList<E> implements PositionalList<E>, Iterable<E> {
    public static class Node<E> implements Position<E> {
        private E element;
        private int index;
        public Node(E e, int i){
            element = e;
            index = i;
        }
        public E getElement(){return element;}
        public int getIndex(){return index;}
        public void setElement(E e){element = e;}
        public void setIndex(int i){index = i;}
    }

    private static final int CAPACITY = 32;
    private Position<E>[] data;
    private int size = 0;
    public ArrayPositionalList(){this(CAPACITY);}
    public ArrayPositionalList(int capacity){
        data = new Position[capacity];
    }

    //check
    private Node<E> validate(Position<E> p) throws IllegalStateException{
        if(!(p instanceof Node)) throw new IllegalStateException("invalid p");
        Node<E> node = (Node<E>) p;
        if(node.getIndex() == -1) throw new IllegalStateException("p no longer in list");
        return node;
    }

    //size, isEmpty
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}

    //getters
    public Position<E> first(){return data[0];}
    public Position<E> last(){return data[size-1];}
    public Position<E> before(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        if(node.getIndex() <= 0 || node.getIndex() > size-1) throw new IllegalArgumentException("invalid i");
        return data[node.getIndex()-1];
    }
    public Position<E> after(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        if(node.getIndex() < 0 || node.getIndex() >= size-1) throw new IllegalArgumentException("invalid i");
        return data[node.getIndex()+1];
    }


    //adders
    public Position<E> addFirst(E e) throws IllegalArgumentException{
        if(size == data.length) throw new IllegalArgumentException("data is full");
        Node<E> node;
        for(int k = size; k > 0; k--){
            data[k] = data[k-1];
            node = (Node<E>)data[k];
            node.setIndex(node.getIndex()+1);
        }
        size++;
        return data[0] = new Node<>(e, 0);
    }
    public Position<E> addLast(E e) throws IllegalArgumentException{
        if(size == data.length) throw new IllegalArgumentException("data is full");
        return data[size] = new Node<>(e, size++);
    }
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException, IllegalStateException{
        Node<E> nodeP = validate(p);
        if(nodeP.getIndex() <= 0 || nodeP.getIndex() >= size-1) throw new IllegalArgumentException("invalid i");
        if(size == data.length) throw new IllegalStateException("data is full");
        Node<E> node;
        for(int k = size; k > nodeP.getIndex(); k--) {
            data[k] = data[k-1];
            node = (Node<E>)data[k];
            node.setIndex(node.getIndex()+1);
        }
        size++;
        int index = nodeP.getIndex();
        return data[index] = new Node<>(e, index);
    }
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException, IllegalStateException{
        Node<E> nodeP = validate(p);
        if(nodeP.getIndex() <= 0 || nodeP.getIndex() >= size-1) throw new IllegalArgumentException("invalid i");
        if(size == data.length) throw new IllegalStateException("data is full");
        Node<E> node;
        for(int k = size; k > nodeP.getIndex()+1; k--) {
            data[k] = data[k-1];
            node = (Node<E>)data[k];
            node.setIndex(node.getIndex()+1);
        }
        size++;
        int index = nodeP.getIndex();
        return data[index+1] = new Node<>(e, index+1);
    }

    //updater
    public E set(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> nodeP = validate(p);
        if(nodeP.getIndex() <= 0 || nodeP.getIndex() >= size-1) throw new IllegalArgumentException("invalid i");
        E value = nodeP.getElement();
        nodeP.setElement(e);
        return value;
    }

    //remover
    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> nodeP = validate(p);
        if(nodeP.getIndex() <= 0 || nodeP.getIndex() >= size-1) throw new IllegalArgumentException("invalid i");
        E value = nodeP.getElement();
        for(int k = nodeP.getIndex(); k < size-1; k++)
            data[k] = data[k+1];
        data[--size] = null;
        nodeP.setIndex(-1);
        nodeP.setElement(null);
        return value;
    }




    //5. (15 points) Extend your answer in question 4 with an iterator (using the Iterator
    //and Iterable interfaces) that enumerates the contents of an ArrayPositionalList in
    //reversed order.
    private class ArrayReverseIterator implements Iterator<E>{
        private Position<E> cursor = last();
        private Position<E> recent = null;

        @Override
        public boolean hasNext() {return validate(cursor).getIndex() > 0;}

        @Override
        public E next() throws NoSuchElementException {
            if(!hasNext()) throw new NoSuchElementException("no next element");
            recent = cursor;
            cursor = before(cursor);
            return recent.getElement();
        }

        @Override
        public void remove() throws IllegalStateException {
            if(recent == null) throw new IllegalStateException("nothing to remove");
            ArrayPositionalList.this.remove(recent);
            recent = null;
        }
    }

    public Iterator<E> iterator(){
        return new ArrayReverseIterator();
    }





    //for testing
    public static void main(String[] args) {
        ArrayPositionalList<Integer> arr = new ArrayPositionalList<>();
        arr.addLast(0);
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(3);
        arr.addLast(4);
        arr.addLast(5);
        Iterator<Integer> it = arr.iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }
    //it is not printing the last element, and with this implementation of ArrayPositionalList it won't.
    //I guess I need to include sentinels, a header and a trailer so that the next method in the iterator can
    //get to the last element











//    private static final int CAPACITY = 1000;
//    private E[] data;
//    private int size = 0;
//    public ArrayPositionalList(){this(CAPACITY);}
//    public ArrayPositionalList(int capacity){
//        data = (E[]) new Object[capacity];
//    }
//    public int size(){return size;}
//    public boolean isEmpty(){return size == 0;}
//    public Position<E> first(){return data[0];}
//    public E last(){return data[size-1];}
//    public E before(int i) throws IndexOutOfBoundsException{
//        if(i <= 0 || i > size-1) throw new IndexOutOfBoundsException("invalid i: " + i);
//        return data[i-1];
//    }
//    public E after(int i) throws IndexOutOfBoundsException{
//        if(i < 0 || i >= size-1) throw new IndexOutOfBoundsException("invalid i: " + i);
//        return data[i+1];
//    }
//    public int addFirst(E e) throws IllegalStateException{
//        if(size == data.length) throw new IllegalStateException("data is full");
//        for(int k = size; k > 0; k--)
//            data[k] = data[k-1];
//        data[0] = e;
//        size++;
//        return 0;
//    }
//    public int addLast(E e) throws IllegalStateException{
//        if(size == data.length) throw new IllegalStateException("data is full");
//        data[size] = e;
//        return size++;
//    }
//    public int addBefore(int i, E e) throws IndexOutOfBoundsException, IllegalStateException{
//        if(i <= 0 || i >= size-1) throw new IndexOutOfBoundsException("invalid i: " + i);
//        if(size == data.length) throw new IllegalStateException("data is full");
//        for(int k = size; k > i; k--)
//            data[k] = data[k-1];
//        data[i] = e;
//        size++;
//        return i;
//    }
//    public int addAfter(int i, E e) throws IndexOutOfBoundsException, IllegalStateException{
//        if(i <= 0 || i >= size-1) throw new IndexOutOfBoundsException("invalid i: " + i);
//        if(size == data.length) throw new IllegalStateException("data is full");
//        for(int k = size; k > i+1; k--)
//            data[k] = data[k-1];
//        data[i+1] = e;
//        size++;
//        return i+1;
//    }
//    public E set(int i, E e) throws IndexOutOfBoundsException{
//        if(i <= 0 || i >= size-1) throw new IndexOutOfBoundsException("invalid i: " + i);
//        E value = data[i];
//        data[i] = e;
//        return value;
//    }
//    public E remove(int i) throws IndexOutOfBoundsException{
//        if(i <= 0 || i >= size-1) throw new IndexOutOfBoundsException("invalid i: " + i);
//        E value = data[i];
//        for(int k = i; k < size-1; k++)
//            data[k] = data[k+1];
//        data[size-1] = null;
//        size--;
//        return value;
//    }





}
