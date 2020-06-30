import org.w3c.dom.Node;

public class SLL<E> {
    public static class SNode<E>{
        private E element;
        private SNode<E> next;
        public SNode(E e, SNode<E> n){
            element = e;
            next = n;
        }
        public E getElement(){return element;}
        public SNode<E> getNext(){return next;}
        public void setNext(SNode<E> n){next = n;}
    }
    private int size = 0;
    private SNode<E> head = null;
    private SNode<E> tail = null;
    public SLL(){}

    public SNode<E> getHead(){return head;}
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}
    public E first(){
        if(isEmpty()) return null;
        return head.getElement();
    }
    public E last(){
        if(isEmpty()) return null;
        return tail.getElement();
    }
    public void addFirst(E e){
        head = new SNode<>(e, head);
        if(isEmpty()) tail = head;
        size++;
    }
    public void addLast(E e){
        SNode<E> newest = new SNode<>(e, null);
        if(isEmpty()) head = newest;
        else tail.setNext(newest);
        tail = newest;
        size++;
    }
    public E removeFirst(){
        if(isEmpty()) return null;
        E removed = head.getElement();
        head = head.getNext();
        size--;
        if(isEmpty()) tail = null;
        return removed;
    }

    public void print(){
        if(!isEmpty()) {
            SNode<E> curr = head;
            while (curr != null) {
                System.out.print(curr.getElement() + " ");
                curr = curr.getNext();
            }
            System.out.println();
        }
    }


    //EX6
    public E nth(int n){
        if(n >= size) return null;
        SNode<E> current = head;
        for(int i=0; i < n; i++)
            current = current.getNext();
        return current.getElement();
    }

    //EX7
    public void insert(E e, int n) throws IllegalStateException{
        if(n == 0) addFirst(e);
        else if(isEmpty()) throw new IllegalStateException("list is empty");
        else if(n < 0 || n > size) throw new IllegalStateException("n out of bounds");
        else{
            SNode<E> prev = head;
            for(int i = 1; i < n; i++){
                prev = prev.getNext();
            }
            SNode<E> newest = new SNode<>(e, prev.getNext());
            prev.setNext(newest);
            size++;
        }
    }

    //EX8
    public void reverseSLLIt(){
        SNode<E> prev = null;
        SNode<E> next = head.getNext();
        while(next != null){
            head.setNext(prev);
            prev = head;
            head = next;
            next = next.getNext();
        }
        head.setNext(prev);
    }
}
