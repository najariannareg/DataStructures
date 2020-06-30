public class SNode<E> {
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
