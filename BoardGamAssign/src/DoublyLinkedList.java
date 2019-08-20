
public class DoublyLinkedList<E> {
	  //---------------- nested Node class ----------------
	  /**
	   * Node of a doubly linked list, which stores a reference to its
	   * element and to both the previous and next node in the list.
	   */
	  public static class Node<E> {

	    /** The element stored at this node */
	    private E element;               // reference to the element stored at this node

	    /** A reference to the preceding node in the list */
	    private Node<E> prev;            // reference to the previous node in the list

	    /** A reference to the subsequent node in the list */
	    private Node<E> next;     
	    // reference to the subsequent node in the list

	    /**
	     * Creates a node with the given element and next node.
	     *
	     * @param e  the element to be stored
	     * @param p  reference to a node that should precede the new node
	     * @param n  reference to a node that should follow the new node
	     */
	    public Node(E e, Node<E> p, Node<E> n) {
	      element = e;
	      prev = p;
	      next = n;
	 
	     
	    }

	    // public accessor methods
	    /**
	     * Returns the element stored at the node.
	     * @return the element stored at the node
	     */
	    public E getElement() { return element; }

	    /**
	     * Returns the node that precedes this one (or null if no such node).
	     * @return the preceding node
	     */
	    public Node<E> getPrev() { return prev; }

	    /**
	     * Returns the node that follows this one (or null if no such node).
	     * @return the following node
	     */
	    public Node<E> getNext() { return next; }
	    
	    

	    // Update methods
	    /**
	     * Sets the node's previous reference to point to Node n.
	     * @param p    the node that should precede this one
	     */
	    public void setPrev(Node<E> p) { prev = p; }

	    /**
	     * Sets the node's next reference to point to Node n.
	     * @param n    the node that should follow this one
	     */
	    public void setNext(Node<E> n) { next = n; }
	    
	  } //----------- end of nested Node class -----------

	  // instance variables of the DoublyLinkedList
	  /** Sentinel node at the beginning of the list */
	  private Node<E> header;                    // header sentinel

	  /** Sentinel node at the end of the list */
	  private Node<E> trailer;                   // trailer sentinel

	  /** Number of elements in the list (not including sentinels) */
	  private int size = 0;                      // number of elements in the list

	  /** Constructs a new empty list. */
	  public DoublyLinkedList() {
	    header = new Node<>(null, null, null);      // create header
	    trailer = new Node<>(null, header, null);   // trailer is preceded by header
	    header.setNext(trailer);                    // header is followed by trailer
	  }

	  // public accessor methods
	  /**
	   * Returns the number of elements in the linked list.
	   * @return number of elements in the linked list
	   */
	  public int size() { return size; }

	  /**
	   * Tests whether the linked list is empty.
	   * @return true if the linked list is empty, false otherwise
	   */
	  public boolean isEmpty() { return size == 0; }

	  /**
	   * Returns (but does not remove) the first element of the list.
	   * @return element at the front of the list (or null if empty)
	   */
	  public E first() {
	    if (isEmpty()) return null;
	    return header.getNext().getElement();   // first element is beyond header
	  }

	  /**
	   * Returns (but does not remove) the last element of the list.
	   * @return element at the end of the list (or null if empty)
	   */
	  public E last() {
	    if (isEmpty()) return null;
	    return trailer.getPrev().getElement();    // last element is before trailer
	  }

	  // public update methods
	  /**
	   * Adds an element to the front of the list.
	   * @param e   the new element to add
	   */
	  public void addFirst(E e) {
	    addBetween(e, header, header.getNext());    // place just after the header
	  }

	  /**
	   * Adds an element to the end of the list.
	   * @param e   the new element to add
	   */
	  public void addLast(E e) {
	    addBetween(e, trailer.getPrev(), trailer);  // place just before the trailer
	  }

	  /**
	   * Removes and returns the first element of the list.
	   * @return the removed element (or null if empty)
	   */
	  public E removeFirst() {
	    if (isEmpty()) return null;                  // nothing to remove
	    return remove(header.getNext());             // first element is beyond header
	  }

	  /**
	   * Removes and returns the last element of the list.
	   * @return the removed element (or null if empty)
	   */
	  public E removeLast() {
	    if (isEmpty()) return null;                  // nothing to remove
	    return remove(trailer.getPrev());            // last element is before trailer
	  }

	  // private update methods
	  /**
	   * Adds an element to the linked list in between the given nodes.
	   * The given predecessor and successor should be neighboring each
	   * other prior to the call.
	   *
	   * @param predecessor   node just before the location where the new element is inserted
	   * @param successor     node just after the location where the new element is inserted
	   */
	  private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
	    // create and link a new node
	    Node<E> newest = new Node<>(e, predecessor, successor);
	    predecessor.setNext(newest);
	    successor.setPrev(newest);
	    size++;
	  }

	  /**
	   * Removes the given node from the list and returns its element.
	   * @param node    the node to be removed (must not be a sentinel)
	   */
	  private E remove(Node<E> node) {
	    Node<E> predecessor = node.getPrev();
	    Node<E> successor = node.getNext();
	    predecessor.setNext(successor);
	    successor.setPrev(predecessor);
	    size--;
	    return node.getElement();
	  }

	  /**
	   * Produces a string representation of the contents of the list.
	   * This exists for debugging purposes only.
	   */
	  public String toString() {
	    StringBuilder sb = new StringBuilder("{| ");
	    Node<E> walk = header.getNext();
	    int count = 0;
	    sb.append("START ]--[ ");
	    walk = walk.getNext();
	    //sb.append(" ]--[ ");
	    while (walk != trailer && walk!= null) {


	    	if (walk==trailer.getPrev()) {
	    		sb.append("END");
	    		sb.append(", P: "+((Space)walk.getElement()).getPlayerOnBoard());
	    		sb.append(" |}");
	    		return sb.toString(); 
	    	
	    	}
	    	
	      sb.append(walk.getElement());
	      walk = walk.getNext();
	      count++;
	      
	      
	      if(count % 10 == 0)
	    	 sb.append(" ]\n-->[ ");
	      else if (walk!=trailer)
	         sb.append(" ]--[ ");

		    
	    }
	  
	    //sb.append(" |}");
	    return sb.toString();
	    
	    
	  }
	  public E findElement(int index) {
		  	int count = 0; 
		  	Node<E> curr = new Node<E>(null, null, null);
		    Node<E> walk = header.getNext();
		    //if (index == 0) {
		   
		    	if(index == 0) {
		    		curr = header.getNext(); 
		    	}
		    	else {
			    while (walk != trailer) {
			      walk = walk.getNext();
			      if (count == index) {
			    	  curr = walk; 
			      }
			      count++;
			      
			      }
		    //  System.out.println("Found element: " + ( curr.getElement()));
		    	}
		    
		      return (E) curr.getElement(); 
		    
		  
		  }
	  
	  public E jump(int dice, E currspot) {
		  
		  if (currspot == null) { throw new NullPointerException("currspot cannot be null"); }
		  
		  Node<E> walk = header;
		  Node<E>currentNode = null;
		  Node<E> walk2 = null;

		//  System.out.println(dice);
		//  int new_pos = 10; 
		  int count = 0;
		  int count2 = 0; 
		  E element = currspot; 
		  
		  while(walk != trailer ) {
			  walk = walk.getNext();
			  if(currspot.equals(walk.getElement())) {
				  currentNode = walk; 
				 // count2 = count+1;
				  walk2 = currentNode;

			  }
			  
			  count++;
			
			  //System.out.println("Curr node: " +currentNode.getElement());
			  //System.out.println("COUNT 1: "+count);
			  //System.out.println("COUNT 2: "+count2);
		  }
		  
		 // System.out.println(walk2.getElement());
		  if (walk2 == null) { throw new IllegalArgumentException("currspot doesn't exist in linked list"); }
		 // System.out.println(walk2.getNext().getElement());

		  while(walk2.getNext()!= trailer && count2 < dice) {
			 // System.out.println(walk2.getElement());
			 // System.out.println(walk2.getElement());
			  walk2 = walk2.getNext();

			  element = walk2.getElement();		

			  count2++;
		  }
		  //System.out.println(element);
		return element;
	  }
		  
	  	public int getCurrIndex(E currspot) {
	  	  	int count = 1; 
	  	  	int currIndex = 0;
		  //	Node<E> curr =new Node<E>(null, null, null);
		    Node<E> walk = header.getNext();
	
		    while (walk != trailer ) {
		      walk = walk.getNext();
		      if (currspot.equals(walk.getElement())) {
		    	  currIndex = count; 
		      }
		      count++;
		      
		      }
		    if(currspot.equals(trailer.getElement())) {
		    	currIndex = 26; 
		    }
		    return currIndex;
		    
	  		
	  	}
	  	public E jumpBack(E currspot) {
	  		
	  		if (currspot == null) {
	  			throw new NullPointerException("Current Spot cannot be null");
	  		}
	  		
			  Node<E> walk = header.getNext();
			  Node<E>currentNode = new Node<E>(null, null, null);
			  Node<E> walk2 = new Node<E>(null, null, null);

			//  System.out.println(dice);
			//  int new_pos = 10; 
			  int count = 0;
			  int count2 = 0; 
			  E element = currspot; 
			  
			  while(walk != trailer) {
				  walk = walk.getNext();
				  if(currspot.equals(walk.getElement())) {
					  currentNode = walk; 
					 // count2 = count+1;
					  walk2 = currentNode;

				  }
			  }
				  count++;
				
				
					  while(walk2.getPrev() != header && walk2.getPrev() != null && count2<3) {
						  walk2 = walk2.getPrev();
						 //System.out.println(walk2);
						  element = walk2.getElement();
//						  if(walk2.getElement()==null) {
//								 element = currspot;		  
//						  }
						  
						  count2++;
						  
					  }
				  
				  //System.out.println(element);
				return element;
			  
				  
				
				  //System.out.println("Curr node: " +currentNode.getElement());
				  //System.out.println("COUNT 1: "+count);
				  //System.out.println("COUNT 2: "+count2);
			  }
	} //----------- end of DoublyLinkedList class -----------


