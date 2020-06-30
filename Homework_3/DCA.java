//Deque Circular Array
public class DCA<E> implements Deque<E> {
    private final int CAPACITY = 1000;
    private E[] data;
    private int f = 0;
    private int size = 0;

    public DCA(){
        data = (E[]) new Object[CAPACITY];
    }
    public DCA(int capacity){
        data = (E[]) new Object[capacity];
    }

    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}
    public E first(){
        if(isEmpty()) return null;
        return data[f];
    }
    public E last(){
        if(isEmpty()) return null;
        return data[(f + size - 1) % data.length];
    }
    public void addFirst(E e) throws IllegalStateException{
        if(size == data.length) throw new IllegalStateException("Queue is full");
        if(isEmpty()) data[0] = e;
        else{
            f = (f - 1 + data.length) % data.length;
            data[f] = e;
        }
        size++;
    }
    public void addLast(E e) throws IllegalStateException{
        if(size == data.length) throw new IllegalStateException("Queue is full");
        if(isEmpty()) data[0] = e;
        else data[(f + size) % data.length] = e;
        size++;
    }
    public E removeFirst(){
        if(isEmpty()) return null;
        E removed = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        size--;
        return removed;
    }
    public E removeLast(){
        if(isEmpty()) return null;
        int index = (f + size - 1) % data.length;
        E removed = data[index];
        data[index] = null;
        size--;
        return removed;
    }


    //prints all the elements of the array (for testing)
    public void print(){
        for(int i = f; i < size; i++)
            System.out.print(data[i % data.length] + " ");
        System.out.println();
    }
}
