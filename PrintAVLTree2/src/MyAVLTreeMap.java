import java.util.Comparator;

import net.datastructures.Entry;
import net.datastructures.Position;
import net.datastructures.TreeMap;


public class MyAVLTreeMap<K,V> extends TreeMap<K,V> {
		Position<Entry<K,V>>myPos = tree.root();
		String [][] arr;
		int daheight; 


	  /** Constructs an empty map using the natural ordering of keys. */
	  public MyAVLTreeMap() { super(); }

	  /**
	   * Constructs an empty map using the given comparator to order keys.
	   * @param comp comparator defining the order of keys in the map
	   */
	  public MyAVLTreeMap(Comparator<K> comp) { super(comp); }

	  /** Returns the height of the given tree position. */
	  protected int height(Position<Entry<K,V>> p) {
	    return tree.getAux(p);
	  }

	  /** Recomputes the height of the given position based on its children's heights. */
	  protected void recomputeHeight(Position<Entry<K,V>> p) {
	    tree.setAux(p, 1 + Math.max(height(left(p)), height(right(p))));
	  }

	  /** Returns whether a position has balance factor between -1 and 1 inclusive. */
	  protected boolean isBalanced(Position<Entry<K,V>> p) {
	    return Math.abs(height(left(p)) - height(right(p))) <= 1;
	  }

	  /** Returns a child of p with height no smaller than that of the other child. */
	  protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) {
	    if (height(left(p)) > height(right(p))) return left(p);     // clear winner
	    if (height(left(p)) < height(right(p))) return right(p);    // clear winner
	    // equal height children; break tie while matching parent's orientation
	    if (isRoot(p)) return left(p);                 // choice is irrelevant
	    if (p == left(parent(p))) return left(p);      // return aligned child
	    else return right(p);
	  }

	  /**
	   * Utility used to rebalance after an insert or removal operation. This traverses the
	   * path upward from p, performing a trinode restructuring when imbalance is found,
	   * continuing until balance is restored.
	   */
	  protected void rebalance(Position<Entry<K,V>> p) {
	    int oldHeight, newHeight;
	    do {
	      oldHeight = height(p);                       // not yet recalculated if internal
	      if (!isBalanced(p)) {                        // imbalance detected
	        // perform trinode restructuring, setting p to resulting root,
	        // and recompute new local heights after the restructuring
	        p = restructure(tallerChild(tallerChild(p)));
	        recomputeHeight(left(p));
	        recomputeHeight(right(p));
	      }
	      recomputeHeight(p);
	      newHeight = height(p);
	      p = parent(p);
	      myPos = tree.root();
	
	     // System.out.println(newHeight);

	    } while (oldHeight != newHeight && p != null);
	    arr = new String[(int) Math.pow(newHeight*2, 2)*2][(int)Math.pow(newHeight*2,  2)*2];
	    daheight = arr.length; 
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
	  @Override
	  protected void rebalanceInsert(Position<Entry<K,V>> p) {
	    rebalance(p);
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
	  @Override
	  protected void rebalanceDelete(Position<Entry<K,V>> p) {
	    if (!isRoot(p))
	      rebalance(parent(p));
	  }

	  /** Ensure that current tree structure is valid AVL (for debug use only). */
	  private boolean sanityCheck() {
	    for (Position<Entry<K,V>> p : tree.positions()) {
	      if (isInternal(p)) {
	        if (p.getElement() == null)
	          System.out.println("VIOLATION: Internal node has null entry");
	        else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
	          System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
	          dump();
	          return false;
	        }
	      }
	    }
	    return true;
	  }
	  

	public int printTreeRec(Position<Entry<K,V>> currPos, int a, int b) {

		if(currPos.getElement() == null) {
//			printTree(arr);
			return 0;
		}
		
		else{		
			arr[a][b]= (String) currPos.getElement().getKey();
			int half = arr.length / 2;
			printTreeRec(tree.left(currPos), a+2, b-half/(int)Math.pow(2, (a/2)+1));
			if((tree.left(currPos)).getElement() != null) {
				arr[a+1][b-half/(int)Math.pow(2, (a/2)+2)] = ("/");
			}
			printTreeRec(tree.right(currPos), a+2, b+half/(int)Math.pow(2, (a/2)+1));
			if((tree.right(currPos)).getElement() !=null) {
				arr[a+1][b+half/(int)Math.pow(2, (a/2)+2)]=("\\");
			}
			return 1;
			
		}	
	}
	public String getRoot() {return (String) tree.root().getElement().getKey();}
	public void printTree() {
		int emptyCnt = 0; 
		
			for(int i =0; i<arr.length;i++) {
				emptyCnt++;
				for(int j = 0; j<arr.length; j++) {
					if(arr[i][j]!=null) {
						System.out.print(arr[i][j]);
						emptyCnt = 0;
					}
					else {
						System.out.print(" ");
					}
				
				}
				if (emptyCnt > 1) { System.out.println("\n\n"); break; }
				System.out.println();
			}
		
		}
	}


