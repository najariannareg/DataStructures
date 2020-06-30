import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {
    protected static class PQEntry<K,V> implements Entry<K,V> {
        private K key;
        private V value;
        public PQEntry(K k, V v){
            key = k;
            value = v;
        }
        public K getKey(){return key;}
        public V getValue(){return value;}
        public void setKey(K k){key = k;}
        public void setValue(V v){value = v;}
    }
    private Comparator<K> comp;
    protected AbstractPriorityQueue(Comparator<K> c){comp = c;}
    protected AbstractPriorityQueue(){this(new DefaultComparator<K>());}
    protected int compare(Entry<K,V> a, Entry<K,V> b){
        return comp.compare(a.getKey(), b.getKey());
    }
    protected boolean checkKey(K key) throws IllegalArgumentException{
        try{
            return (comp.compare(key, key) == 0);
        }catch(ClassCastException e){
            throw new IllegalArgumentException("Incompatible Key");
        }
    }
    public boolean isEmpty(){return size() == 0;}
}
