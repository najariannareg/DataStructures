import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E>, Iterable<E>{
    public static final int CAPACITY = 16;
    private E[] data;
    private int size = 0;
    public ArrayList(){this(CAPACITY);}
    public ArrayList(int capacity){
        data = (E[]) new Object[capacity];
    }

    public void checkIndex(int i, int n) throws IndexOutOfBoundsException{
        if(i < 0 || i >= n) throw new IndexOutOfBoundsException("invalid i");
    }

    public void resize(int capacity){
        E[] temp = (E[]) new Object[capacity];
        for(int k = 0; k < size; k++)
            temp[k] = data[k];
        data = temp;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E value = data[i];
        data[i] = e;
        return value;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size+1);
        if(size == data.length) resize(data.length*2);
        for(int k = size; k > i; i++)
            data[k] = data[k-1];
        data[i] = e;
        size++;
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        if(size < data.length/4) resize(data.length/2);
        E value = data[i];
        for(int k = i; k < size-1; k++)
            data[k] = data[k+1];
        data[--size] = null;
        return value;
    }


    private class ArrayIterator implements Iterator<E>{
        private int j = 0;
        private boolean removable = false;

        @Override
        public boolean hasNext() { return j < size; }

        @Override
        public E next() throws NoSuchElementException {
            if(j == size) throw new NoSuchElementException("no next element");
            removable = true;
            return data[j++];
        }

        @Override
        public void remove() throws IllegalStateException {
            if(!removable) throw new IllegalStateException("nothing to remove");
            ArrayList.this.remove(--j);
            removable = false;
        }
    }

    public Iterator<E> iterator(){return new ArrayIterator();}

}
