public class CDLL<E> {
    public static class DNode<E>{
        private E element;
        private DNode<E> prev;
        private DNode<E> next;
        public DNode(E e, DNode<E> p, DNode<E> n){
            element = e;
            prev = p;
            next = n;
        }
        public E getElement(){return element;}
        public DNode<E> getPrev(){return prev;}
        public DNode<E> getNext(){return next;}
        public void setPrev(DNode<E> p){prev = p;}
        public void setNext(DNode<E> n){next = n;}
    }
    private int size = 0;
    private DNode<E> trail = null;
    public CDLL(){}
    public int size(){return size;};
    public boolean isEmpty(){return size == 0;}
    public void rotateForward(){
        if(!isEmpty()) trail = trail.getNext();
    }
    public void rotateBackward(){
        if(!isEmpty()) trail = trail.getPrev();
    }
    public E first(){
        if(isEmpty()) return null;
        return trail.getNext().getElement();
    }
    public E last(){
        if(isEmpty()) return null;
        return trail.getElement();
    }

    public void addFirst(E e){
        if(isEmpty()){
            trail = new DNode<>(e, trail, trail);
        }else{
            DNode<E> newest = new DNode<>(e, trail, trail.getNext());
            newest.getPrev().setNext(newest);
            newest.getNext().setPrev(newest);
        }
        size++;
    }
    public void addLast(E e){
        addFirst(e);
        trail = trail.getNext();
    }

    public void addBefore(E e, DNode<E> node){
        DNode<E> newest = new DNode<>(e, node.getPrev(), node);
        newest.getPrev().setNext(newest);
        newest.getNext().setPrev(newest);
        size++;
    }
    public void addAfter(E e, DNode<E> node){
        DNode<E> newest = new DNode<>(e, node, node.getNext());
        newest.getPrev().setNext(newest);
        newest.getNext().setPrev(newest);
        size++;
    }

    public E removeFirst(){
        if(isEmpty()) return null;
        return remove(trail.getNext());
    }
    public E removeLast(){
        if(isEmpty()) return null;
        rotateBackward();
        return remove(trail.getNext());
    }
    public E remove(DNode<E> node){
        if(size == 1) trail = null;
        E removed = node.getElement();
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        node = null;
        size--;
        return removed;
    }

}
