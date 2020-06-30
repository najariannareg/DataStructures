
public class ArrayStack<E> implements Stack<E>{
    private static final int CAPACITY = 1000;
    private E[] data;
    private int i;
    public ArrayStack(){this(CAPACITY);};
    public ArrayStack(int capacity){
        data = (E[]) new Object[capacity];
        i = -1;
    }
    public int size(){return (i+1);}
    public boolean isEmpty(){return i == -1;}
    public E top(){
        if(isEmpty()) return null;
        return data[i];
    }
    public void push(E e) throws IllegalStateException{
        if(size() == data.length) throw new IllegalStateException("stack is full");
        data[++i] = e;
    }
    public E pop(){
        if(isEmpty()) return null;
        E poped = data[i];
        data[i--] = null;
        return poped;
    }
}
