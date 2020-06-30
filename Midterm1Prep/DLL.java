public class DLL<E> {
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
        public DNode<E> getNext(){return next;}
        public DNode<E> getPrev(){return prev;}
        public void setNext(DNode<E> n){next = n;}
        public void setPrev(DNode<E> p){prev = p;}
    }
    private int size = 0;
    private DNode<E> header;
    private DNode<E> trailer;
    public DLL(){
        header = new DNode<>(null, null, null);
        trailer = new DNode<>(null, header, null);
        header.setNext(trailer);
    }

    public DNode<E> getHead(){return header.getNext();}
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}
    public E first(){
        if(isEmpty()) return null;
        return header.getNext().getElement();
    }
    public E last(){
        if(isEmpty()) return null;
        return trailer.getPrev().getElement();
    }

    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }
    public void addLast(E e){
        addBetween(e, trailer.getPrev(), trailer);
    }
    private void addBetween(E e, DNode<E> pred, DNode<E> succ){
        DNode<E> newest = new DNode<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
    }

    public void addFirst1(E e) {
        addAfter(e, header);
    }
    public void addLast1(E e){
        addBefore(e, trailer);
    }
    public void addBefore(E e, DNode<E> curr){
        DNode<E> newest = new DNode<>(e, curr.getPrev(), curr);
        newest.getPrev().setNext(newest);
        newest.getNext().setPrev(newest);
        size++;
    }
    public void addAfter(E e, DNode<E> curr){
        DNode<E> newest = new DNode<>(e, curr, curr.getNext());
        newest.getPrev().setNext(newest);
        newest.getNext().setPrev(newest);
        size++;
    }

    public E removeFirst(){
        if(isEmpty()) return null;
        return remove(header.getNext());
    }
    public E removeLast(){
        if(isEmpty()) return null;
        return remove(trailer.getPrev());
    }
    public E remove(DNode<E> node){
        E removed = node.getElement();
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        node = null;
        size--;
        return removed;
    }

    public void print(){
        if(!isEmpty()) {
            DNode<E> curr = header.getNext();
            while (curr != trailer) {
                System.out.print(curr.getElement() + " ");
                curr = curr.getNext();
            }
            System.out.println();
        }
    }
}
