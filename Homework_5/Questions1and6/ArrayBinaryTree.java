//1. (23 points) Write a class ArrayBinaryTree that extends the AbstractBinaryTree
//class using an array of xed capacity as the underlying data structure. Note that the
//AbstractBinaryTree class in turn extends the AbstractTree class and implements the
//BinaryTree interface. Your class should support all of the following functionality:
//(a) two constructors that create an empty tree: a no-arg constructor that sets the default
//capacity of the array, and another constructor that receives the array capacity as an
//argument;
//(b) two methods for determining the height and depth of a given position and a method
//for determining the height of the tree;
//(c) functionality for traversing the elements of the tree, i.e. an iterator() method;
//(d) functionality for traversing the positions of the tree in preorder, postorder, inorder
//and breadth-rst order traversals, i.e. preorder(), postorder(), inorder(),
//breadthfirst() methods, all of which return an iterable collection of the positions
//of the tree;
//(e) functionality for traversing the positions of the tree, i.e. a positions() method
//implementing preorder traversal;
//(f) methods addRoot(e), addLeft(p; e), addRight(p; e), remove(p) similar to the
//corresponding methods for the LinkedBinaryTree class.
//Think carefully where you should add each of these methods; you may need
//to modify any of the AbstractTree, AbstractBinaryTree and ArrayBinaryTree
//classes.

import java.util.ArrayList;
import java.util.List;

public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {
    protected static class Node<E> implements Position<E> {
        private E element;
        private int index;
        public Node(E e, int i){
            element = e;
            index = i;
        }
        public E getElement(){return element;}
        public int getIndex(){return index;}
        public void setElement(E e){element = e;}
        public void setIndex(int i){index = i;}
    }

    private Node<E> createNode(E e, int i){return new Node<>(e, i);}

    private static final int CAPACITY = 16;
    private Position<E>[] data;
    private int size = 0;

    public ArrayBinaryTree(){this(CAPACITY);}
    public ArrayBinaryTree(int capacity){
        data = (Position<E>[]) new Position[capacity];
    }



    //check position
    private Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if(!(p instanceof Node)) throw new IllegalArgumentException("invalid p");
        Node<E> node = (Node<E>) p;
        if(node.getIndex() == -1) throw new IllegalArgumentException("p no longer in list");
        return node;
    }
    //indices
    private int parentIndex(Position<E> j)throws IllegalArgumentException{
        Node<E> node = validate(j);
        return (node.getIndex()-1)/2;
    }
    private int leftIndex(Position<E> j) throws IllegalArgumentException{
        Node<E> node = validate(j);
        return 2*node.getIndex()+1;
    }
    private int rightIndex(Position<E> j) throws IllegalArgumentException{
        Node<E> node = validate(j);
        return 2*node.getIndex()+2;
    }
    //check relatives
    private boolean parentInBounds(Position<E> j) throws IllegalArgumentException{
        Node<E> node = validate(j);
        return parentIndex(j) >= 0;
    }
    private boolean leftInBounds(Position<E> j) throws IllegalArgumentException{
        Node<E> node = validate(j);
        return leftIndex(j) < data.length;
    }
    private boolean rightInBounds(Position<E> j) throws IllegalArgumentException{
        Node<E> node = validate(j);
        return rightIndex(j) < data.length;
    }
    private boolean hasParent(Position<E> j) throws IllegalArgumentException{
        Node<E> node = validate(j);
        if(parentInBounds(node))
            return parent(node) != null;
        return false;
    }
    private boolean hasLeft(Position<E> j) throws IllegalArgumentException{
        Node<E> node = validate(j);
        if(leftInBounds(node))
            return left(node) != null;
        return false;
    }
    private boolean hasRight(Position<E> j) throws IllegalArgumentException{
        Node<E> node = validate(j);
        if(rightInBounds(node))
            return right(node) != null;
        return false;
    }



    //size, isEmpty
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}

    //getters
    public Position<E> root(){return data[0];}
    public Position<E> parent(Position<E> j) throws IllegalArgumentException{
        return data[parentIndex(j)];
    }
    public Position<E> left(Position<E> j)throws IllegalArgumentException{
        Node<E> node = validate(j);
        return data[leftIndex(j)];
    }
    public Position<E> right(Position<E> j)throws IllegalArgumentException{
        Node<E> node = validate(j);
        return data[rightIndex(j)];
    }

    //adders
    public Position<E> addRoot(E e) throws IllegalStateException{
        if(!isEmpty()) throw new IllegalStateException("tree is not empty");
        size = 1;
        return data[0] = createNode(e, 0);
    }
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException, IllegalStateException{
        Node<E> parent = validate(p);
        if(!leftInBounds(parent)) throw new IllegalStateException("left position out of bounds");
        if(hasLeft(parent)) throw new IllegalStateException("p already has a left child");
        size++;
        return data[leftIndex(parent)] = createNode(e, leftIndex(parent));
    }
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException, IllegalStateException{
        Node<E> parent = validate(p);
        if(!rightInBounds(parent)) throw new IllegalStateException("right position out of bounds");
        if(hasRight(parent)) throw new IllegalStateException("p already has a right child");
        size++;
        return data[rightIndex(parent)] = createNode(e, rightIndex(parent));
    }

    //setter
    public E set(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        if(isRoot(p)) return null;
        Position<E> parent = parent(p);
        if (p == left(parent)) {
            if (rightInBounds(p))
                return right(parent);
            else return null;
        }else
            return left(parent);
    }
    @Override
    public int numChildren(Position<E> p) {
        int count=0;
        if(leftInBounds(p))
            if(hasLeft(p))
                count++;
        if(rightInBounds(p))
            if(hasRight(p))
                count++;
        return count;
    }
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> snapshot = new ArrayList<>(2);
        if(leftInBounds(p))
            if(hasLeft(p))
                snapshot.add(left(p));
        if(rightInBounds(p))
            if(hasRight(p))
                snapshot.add(right(p));
        return snapshot;
    }



    //remover
    //doesn't work correctly
    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        if(numChildren(p) == 2) throw new IllegalStateException("p has two chidren");
        Node<E> child = (left(node) != null ? validate(left(node)) : validate(right(node)) );

        E removed = node.getElement();
        removeSubtree(child);

        size--;
        node.setElement(null);
        node.setIndex(-1);
        return removed;
    }

    private void removeSubtree(Position<E> p){
        Iterable<Position<E>> children = children(p);
        Position<E> parent = parent(p);
        Position<E> grandParent = parent(parent);
        if(p == left(parent)){
            data[leftIndex(grandParent)] = p;
            validate(p).setIndex(leftIndex(grandParent));
        }else{
            data[rightIndex(grandParent)] = p;
            validate(p).setIndex(rightIndex(grandParent));
        }

        for (Position<E> c : children)
            removeSubtree(c);
    }






    //for testing
    public static void main(String[] args) {
        ArrayBinaryTree<Integer> tree = new ArrayBinaryTree<>(10);
        Position<Integer> p0 = tree.addRoot(0);
        Position<Integer> p1 = tree.addLeft(p0, 1);
        Position<Integer> p2 = tree.addRight(p0, 2);
        //Position<Integer> p3 = tree.addLeft(p1, 3);
        Position<Integer> p4 = tree.addRight(p1, 4);
        Position<Integer> p6 = tree.addRight(p2, 6);
        Position<Integer> p9 = tree.addLeft(p4, 9);
        //Position<Integer> p10 = tree.addRight(p4, 10);

//        System.out.println(tree.sibling(p1).getElement());
//        System.out.println(tree.sibling(p4));
//        System.out.println(tree.sibling(p9));
//        System.out.println(tree.numChildren(p9));

        System.out.println(tree.remove(p1));
        System.out.println(p1.getElement());
        System.out.println(p4.getElement());
        System.out.println(p9.getElement());
//        System.out.println(tree.left(p2).getElement());
//        System.out.println(tree.right(p4).getElement());
//        System.out.println(tree.right(p6).getElement());
    }
}
