import java.util.NoSuchElementException;

public interface Iterator<E> {
    boolean hasNext();
    E next() throws NoSuchElementException;
    void remove() throws IllegalStateException;
}
