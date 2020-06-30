public class Stack2Array1<E> implements S2A1<E> {
    private static final int CAPACITY = 1000;
    private E[] data;
    private int i;
    private int j;
    public Stack2Array1(){this(CAPACITY);};
    public Stack2Array1(int capacity){
        data = (E[]) new Object[capacity];
        i = -1;
        j = capacity;
    }
    public int size1(){return (i+1);}
    public boolean isEmpty1(){return i == -1;}
    public E top1(){
        if(isEmpty1()) return null;
        return data[i];
    }
    public void push1(E e) throws IllegalStateException{
        if(size1() == j) throw new IllegalStateException("stack is full");
        data[++i] = e;
    }
    public E pop1(){
        if(isEmpty1()) return null;
        E poped = data[i];
        data[i--] = null;
        return poped;
    }

    public void print1() {
        if (!isEmpty1()) {
            for (int k = 0; k <= i; k++) {
                System.out.print(data[k] + " ");
            }
            System.out.println();
        }
    }

    public int size2(){return (j-1);}
    public boolean isEmpty2(){return j == data.length;}
    public E top2(){
        if(isEmpty2()) return null;
        return data[j];
    }
    public void push2(E e) throws IllegalStateException{
        if(size2() == i) throw new IllegalStateException("stack is full");
        data[--j] = e;
    }
    public E pop2(){
        if(isEmpty2()) return null;
        E poped = data[j];
        data[j++] = null;
        return poped;
    }

    public void print2() {
        if (!isEmpty2()) {
            for (int k = data.length-1; k >= j; k--) {
                System.out.print(data[k] + " ");
            }
            System.out.println();
        }
    }
}
