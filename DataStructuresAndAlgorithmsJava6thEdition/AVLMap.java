import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/** An implementation of a sorted map using an AVL tree. */
public class AVLMap<K,V> extends AbstractSortedMap<K,V> {
  // To represent the underlying tree structure, we use a specialized
  // subclass of the LinkedBinaryTree class that we name AVLTree
  private AVLTree<K,V> tree = new AVLTree<>();

  /** Constructs an empty map using the natural ordering of keys. */
  public AVLMap() {
    super();						// the AbstractSortedMap constructor
    tree.addRoot(null);				// create a sentinel leaf as root
  }
  /** Constructs an empty map using the given comparator to order keys. */
  public AVLMap(Comparator<K> comp) {
    super(comp);					// the AbstractSortedMap constructor
    tree.addRoot(null);				// create a sentinel leaf as root
  }

  // size method omitted here due to similarity with BSTMap

  // expandExternal method omitted here due to similarity with BSTMap
  
  // treeSearch method omitted here due to similarity with BSTMap

  /** Returns position with the minimal key in the subtree rooted at p. */
  private Position<Entry<K,V>> treeMin(Position<Entry<K,V>> p) {
    Position<Entry<K,V>> walk = p;
    while (tree.isInternal(walk))
      walk = tree.left(walk);
    return tree.parent(walk);		// we want the parent of the leaf
  }
  /** Returns position with the maximum key in the subtree rooted at p. */
  private Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p) {
    Position<Entry<K,V>> walk = p;
    while (tree.isInternal(walk))
      walk = tree.right(walk);
    return tree.parent(walk);		// we want the parent of the leaf
  }

  // get method omitted here due to similarity with BSTMap

  // put method omitted here due to similarity with BSTMap

  // remove method omitted here due to similarity with BSTMap


  // firstEntry method omitted here due to similarity with BSTMap

  // lastEntry method omitted here due to similarity with BSTMap

  // floorEntry method omitted here due to similarity with BSTMap

  // ceilingEntry method omitted here due to similarity with BSTMap

  // lowerEntry method omitted here due to similarity with BSTMap

  // higherEntry method omitted here due to similarity with BSTMap


  // entrySet method omitted here due to similarity with BSTMap

  // subMap method (and corresponding subMapRecurse utility method)
  // omitted here due to similarity with BSTMap

  /** Recomputes the height of the given position based on its children's heights.*/
  private void recomputeHeight(Position<Entry<K,V>> p) {
    tree.setHeight(p, 1 + Math.max(tree.getHeight(tree.left(p)), tree.getHeight(tree.right(p))));
  }
  /** Returns whether a position has balance factor between -1 and 1 inclusive. */
  private boolean isBalanced(Position<Entry<K,V>> p) {
    return Math.abs(tree.getHeight(tree.left(p)) - tree.getHeight(tree.right(p))) <= 1;
  }
  /** Returns a child of p with height no smaller than that of the other child. */
  private Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) {
    if (tree.getHeight(tree.left(p)) > tree.getHeight(tree.right(p)))
      return tree.left(p);		// clear winner
    if (tree.getHeight(tree.left(p)) < tree.getHeight(tree.right(p)))
      return tree.right(p);		// clear winner
    // equal height children; break tie while matching parent's orientation
    if (tree.isRoot(p))
      return tree.left(p);		// choice is irrelevant
    if (p == tree.left(tree.parent(p)))
      return tree.left(p);		// return aligned child
    else
      return tree.right(p);
  }
  /**
   * Utility used to rebalance after an insert or removal operation. This
   * traverses the path upward from p, performing a trinode restructuring
   * when imbalance is found, continuing until balance is restored.
   */
  private void rebalance(Position<Entry<K,V>> p) {
    int oldHeight, newHeight;
    do {
      oldHeight = tree.getHeight(p);	// not yet recalculated if internal
      if (!isBalanced(p)) {				// imbalance detected
        // perform trinode restructuring, setting p to resulting root,
        // and recompute new local heights after the restructuring
        p = tree.restructure(tallerChild(tallerChild(p)));
        recomputeHeight(tree.left(p));
        recomputeHeight(tree.right(p));
      }
      recomputeHeight(p);
      newHeight = tree.getHeight(p);
      p = tree.parent(p);
    } while (oldHeight != newHeight && p != null);
  }
}
