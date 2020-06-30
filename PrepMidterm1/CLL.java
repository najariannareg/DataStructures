public class CLL<E> {
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
    private SNode<E> tail = null;
    public CLL(){}

    public SNode<E> getTail(){return tail;}
    public SNode<E> getHead(){return tail.getNext();}
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}
    public void rotate(){
        if(tail != null)
            tail = tail.getNext();
    }
    public E first(){
        if(isEmpty()) return null;
        return tail.getNext().getElement();
    }
    public E last(){
        if(isEmpty()) return null;
        return tail.getElement();
    }


    public void addFirst(E e){
        if(isEmpty()){
            tail = new SNode<>(e, null);
            tail.setNext(tail);
        }else{
            SNode<E> newest = new SNode<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }
    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }


    public E removeFirst(){
        if(isEmpty()) return null;
        E removed = tail.getNext().getElement();
        if(size == 1) tail = null;
        else tail.setNext(tail.getNext().getNext());
        size--;
        return removed;
    }


    public void print(){
        if(!isEmpty()) {
            SNode<E> curr = tail.getNext();
            do {
                System.out.print(curr.getElement() + " ");
                curr = curr.getNext();
            } while (curr != tail.getNext());
            System.out.println();
        }
    }


}
