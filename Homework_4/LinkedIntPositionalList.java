//2. (10 points) Write a class LinkedIntPositionalList that represents a positional list of
//integers using a doubly linked list as the underlying data structure. Note that your class
//should implement the PositionalList<Integer> interface.

//import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedIntPositionalList<Integer> implements PositionalList<Integer>, Iterable<Integer> {
    private static class Node<Integer> implements Position<Integer> {
        private Integer element;
        private Node<Integer> prev;
        private Node<Integer> next;

        public Node(Integer e, Node<Integer> p, Node<Integer> n) {
            element = e;
            prev = p;
            next = n;
        }

        public Integer getElement() throws IllegalStateException {
            if (next == null) throw new IllegalStateException("Position no longer valid");
            return element;
        }

        public Node<Integer> getPrev() {
            return prev;
        }

        public Node<Integer> getNext() {
            return next;
        }

        public void setElement(Integer e) {
            element = e;
        }

        public void setPrev(Node<Integer> p) {
            prev = p;
        }

        public void setNext(Node<Integer> n) {
            next = n;
        }
    }

    private int size = 0;
    private Node<Integer> header;
    private Node<Integer> trailer;

    public LinkedIntPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    public Node<Integer> validate(Position<Integer> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("invalide p");
        Node<Integer> node = (Node<Integer>) p;
        if (node.getNext() == null) throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }

    public Position<Integer> position(Node<Integer> node) {
        if (node == header || node == trailer) return null;
        return node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Position<Integer> first() {
        return header.getNext();
    }

    public Position<Integer> last() {
        return trailer.getPrev();
    }

    public Position<Integer> before(Position<Integer> p) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        return position(node.getPrev());
    }

    public Position<Integer> after(Position<Integer> p) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        return position(node.getNext());
    }

    public Position<Integer> addBetween(Integer e, Node<Integer> pred, Node<Integer> succ) {
        Node<Integer> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    public Position<Integer> addFirst(Integer e) {
        return addBetween(e, header, header.getNext());
    }

    public Position<Integer> addLast(Integer e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    public Position<Integer> addBefore(Position<Integer> p, Integer e) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    public Position<Integer> addAfter(Position<Integer> p, Integer e) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public Integer set(Position<Integer> p, Integer e) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        Integer value = node.getElement();
        node.setElement(e);
        return value;
    }

    public Integer remove(Position<Integer> p) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        Integer value = node.getElement();
        Node<Integer> pred = node.getPrev();
        Node<Integer> succ = node.getNext();
        pred.setNext(succ);
        succ.setPrev(pred);
        node.setElement(null);
        node.setPrev(null);
        node.setNext(null);
        size--;
        return value;
    }


    //5. (15 points) Extend your answer in question 4 with an iterator (using the Iterator
    //and Iterable interfaces) that enumerates the contents of an ArrayPositionalList in
    //reversed order.
    //Iterator Ascending Order
    private class AscendingIterator implements Iterator<Integer> {
        LinkedIntPositionalList<Integer> copy = new LinkedIntPositionalList<>();
        private Node<Integer> cursor;
        private Node<Integer> recent = null;

        //constructor: populates the copy and sorts it using merge_sort
        public AscendingIterator(){
            Position<Integer> cursorCopy = first();
            while(cursorCopy != null) {
                copy.addLast(cursorCopy.getElement());
                cursorCopy = after(cursorCopy);
            }
            mergeSort(copy);
            cursor = copy.validate(first());
        }

        public boolean hasNext(){ return cursor.getNext() != null;}

        public Integer next() throws NoSuchElementException{
            if(!hasNext()) throw new NoSuchElementException("no next element");
            //moves forward
            recent = cursor;
            cursor = cursor.getNext();
            return recent.getElement();
        }

        public void remove() throws IllegalStateException{
            if(recent == null) throw new IllegalStateException("nothing to remove");
            LinkedIntPositionalList.this.remove(recent);
            recent = null;
        }
    }

    //mergeSort for LinkedIntPositionalList
    public void mergeSort(LinkedIntPositionalList<Integer> list){
        int sz = list.size();
        if(sz < 2) return;
        int mid = sz/2;
        LinkedIntPositionalList<Integer> left = new LinkedIntPositionalList<>();
        LinkedIntPositionalList<Integer> right = new LinkedIntPositionalList<>();
        Node<Integer> walk = (Node<Integer>) list.first();
        for(int i = 0; i < mid; i++){
            left.addLast(walk.getElement());
            walk = walk.getNext();
        }
        for(int j = mid; j < sz; j++){
            right.addLast(walk.getElement());
            walk = walk.getNext();
        }
        mergeSort(left);
        mergeSort(right);
        merge(left, right, list);
    }
    public void merge(LinkedIntPositionalList<Integer> left, LinkedIntPositionalList<Integer> right, LinkedIntPositionalList<Integer> list){
        Node<Integer> cursorLeft = (Node<Integer>) left.first();
        Node<Integer> cursorRight = (Node<Integer>) right.first();
        Position<Integer> cursorList = list.first();

        while(cursorLeft.getNext() != null || cursorRight.getNext() != null){
            if(cursorRight.getNext() == null || (cursorLeft.getNext() != null && (int)cursorLeft.getElement() < (int)cursorRight.getElement())) {
                list.set(cursorList, (cursorLeft.getElement()));
                cursorLeft = cursorLeft.getNext();
            }else{
                list.set(cursorList, (cursorRight.getElement()));
                cursorRight = cursorRight.getNext();
            }
            cursorList = after(cursorList);
        }
    }
    //end of mergeSort
    //end of iterator

    //the running time of the iterator class constructor is O(nlogn), because copy = n but merge_sort = nlogn.
    //the space complexity is O(2n) therefore O(n).


    public Iterator<Integer> iterator(){
        return new AscendingIterator();
    }




    //for testing
    public void print(){
        Node<Integer> walk = (Node<Integer>)first();
        while(walk.getNext() != null) {
            System.out.println(walk.getElement());
            walk = walk.getNext();
        }
    }


    public static void main(String[] args) {
        LinkedIntPositionalList list = new LinkedIntPositionalList();
        list.addLast(5);
        list.addLast(1);
        list.addLast(24);
        list.addLast(17);
        list.addLast(8);
        list.addLast(11);
        list.addLast(35);

//        list.print();
//        System.out.println();
//        list.mergeSort(list);
//        list.print();

        Iterator it = list.iterator();
        while(it.hasNext())
            System.out.println(it.next());

//        for (Object o : list) System.out.println(o);
    }
}