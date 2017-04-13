import java.util.*;

public class MyLinkedList implements Iterable<Integer>{
    private int size;
    private LNode start;
    private LNode end;
    private MyLinkedList x;

    public MyLinkedList(){
	size = 0;
	start = null;
	end = null;
	x = this;
    }

    public Iterator<Integer> iterator(){
	//new instance of MLLIterator
	return new MyLinkedListIterator(x);
    }

    public class MyLinkedListIterator implements Iterator<Integer>{
	private MyLinkedList LinkedList;
	private LNode element;

	public MyLinkedListIterator(MyLinkedList x){
	    LinkedList = x;
	    element = start;
	}

	public boolean hasNext(){
	    return element != null;
	}
	
	public Integer next(){
	    if(hasNext()){
		int retVal = element.value; //you must save the value before going to next element
		element = element.next;
		return retVal; //this will terminate the function
	    } else{
		throw new NoSuchElementException();
	    }
	}
	
	public void remove(){
	    throw new UnsupportedOperationException();
	}

    }

    //this can be an inner class because you would only instantiate it in MyLinkedList
    private class LNode{
	private int value;
	private LNode next = null;
	private LNode prev = null;
	
	private LNode(int value){
	    this.value = value;
	}

	public String toString(){
	    return value + " ";
	}

	//for debugging
	public String toStringx(){
	    if(prev == null && next == null){
		return "(null)" + value + "(null)";
	    }
	    else if(prev == null){
		return "(null)" + value + "(" + next.value + ")";
	    }
	    else if(next == null){
		return "(" + prev.value + ")" + value + "(null)";
	    }
	    else{
		return "(" + prev.value + ")" + value + "(" + next.value + ")";
	    }
	}
	
    }

    //adds value to the end
    public boolean add(int value){
	if(start == null){
	    start = new LNode(value);
	    end = start;
	    size++;
	    return true;
	} else{
	    //current is a temp reference
	    // LNode current = start;
	    //iterating through the linked list
	    //for(int i = 0; i < size; i++){
	    	//when the last node is reached, a new node is added
	    //	if(i == size - 1){
	    //	    current.next = new LNode(value);
	    //	    size++;
	    //	    return true;
	    //	}
		//otherwise, continue iterating through the linked list
	    //	current = current.next;
	    //}
	    end.next = new LNode(value); //attaching new node to end
	    end.next.prev = end; //sets the new node's prev
	    end = end.next; //sets the new end
	    size++;
	    return true;
	}
    }

    public int get(int index){
	int ans = 0;
	if(index < 0 || index >= size){
	    throw new IndexOutOfBoundsException();
	} else{
	    LNode current = start;
	    for(int i = 0; i < index + 1; i++){
		if(i == index){
		    ans = current.value;
		}
		current = current.next;
	    }
	}
	return ans;
    }

    public int set(int index, int newValue){
	int oldValue = 0;
	if(index < 0 || index >= size){
	    throw new IndexOutOfBoundsException();
	} else{
	    LNode current = start;
	    for(int i = 0; i < index + 1; i++){
		if(i == index){
		    //save before overwriting
		    oldValue = current.value;
		    current.value = newValue;
		}
		current = current.next;
	    }
	}
	return oldValue;
    }

    public int indexOf(int value){
	int ans = -1;
	LNode current = start;
	for(int i = 0; i < size; i++){
	    if(current.value == value){
		ans = i;
		return ans; //end the loop
	    }
	    current = current.next;
	}
	return ans;
    }
    
    public int size(){
	return size;
    }

    private void remove(LNode x){
	if(x.prev != null && x.next != null){
	    x.prev.next = x.next;
	    x.next.prev = x.prev;
	}
	else if(x.prev == null){
	    start = x.next;
	    x.next.prev = null;
	}
	else{
	    end = x.prev;
	    x.prev.next = null;
	}
	size--;
    }

    public int remove(int index){
	if(index < 0 || index >= size){
	    throw new IndexOutOfBoundsException();
	} else{
	LNode current = start;
	int retVal = 0;
	for(int i = 0; i < index + 1; i++){
	    if(i == index){
		retVal = current.value;
		remove(current);
	    }
	    current = current.next;
	}
	return retVal;
	}
    }

    private void insertAfter(LNode toBeAdded, LNode location){
	toBeAdded.next = location.next;
	toBeAdded.prev = location;
	location.next = toBeAdded;
	if(toBeAdded.next != null){
	    toBeAdded.next.prev = toBeAdded;
	} else{
	    end = toBeAdded;
	}
	size++;
    }

    private void insertBefore(LNode toBeAdded, LNode location){
	toBeAdded.prev = location.prev;
	toBeAdded.next = location;
        location.prev = toBeAdded;
	if(toBeAdded.prev != null){
	    toBeAdded.prev.next = toBeAdded;
	} else{
	    start = toBeAdded;
	}
	size++;
    }

    public void add(int index, int value){
	if(index < 0 || index > size){
	    throw new IndexOutOfBoundsException();
	} else{
	    if(index == size){
		LNode toBeAdded = new LNode(value);
		insertAfter(toBeAdded, end);
	    } else{
		LNode current = start;
		for(int i = 0; i < index + 1; i++){
		    if(i == index){
			LNode toBeAdded = new LNode(value);
			insertBefore(toBeAdded, current);
		    }
		    current = current.next;
		}
	    }
	}
    }
	
    public String toString(){
	//current is a temporary reference to node when iterating through linked list
	LNode current = start;
	String retVal = "[";
	for(int i = 0; i < size; i++){
	    if(i < size - 1){
		retVal += current.value + ", ";
	    }
	    else{
		retVal += current.value;
	    }
	    //reference to next node
	    current = current.next;
	}
	return retVal+= "]";
    }

    //for debugging
    public String toStringx(){
	//current is a temporary reference to node when iterating through linked list
	LNode current = start;
	String retVal = "[";
	for(int i = 0; i < size; i++){
	    if(i < size - 1){
		retVal += current.toString() + ", ";
	    }
	    else{
		retVal += current.toString();
	    }
	    //reference to next node
	    current = current.next;
	}
	return retVal+= "]";
    }

    public static void main(String[] args){
	//testing basic add and toString
	MyLinkedList a = new MyLinkedList();
	System.out.println(a.add(0));
	System.out.println(a.size());
	System.out.println(a);

	//testing on an empty list
	MyLinkedList b = new MyLinkedList();
	System.out.println(b.size());
	System.out.println(b);

	//adding to the end of the linked list
	MyLinkedList c = new MyLinkedList();
	for(int x = 0; x < 10; x++){
	    System.out.println(c.add(x));
	    System.out.println(c.size());
	}
	System.out.println(c.size());
	System.out.println(c);
	
	//get
	//System.out.println(c.get(15));
	System.out.println(c.get(9));
	
	//set
	//System.out.println(c.set(-1, 1));
	//System.out.println(c.set(15, 1));
	System.out.println(c.set(3, 33));
	System.out.println(c);
	
	//indexOf
	System.out.println(c.indexOf(100));
	System.out.println(c.indexOf(33));

	System.out.println(c);
	//remove
	System.out.println(c.remove(0));
	System.out.println(c);
	System.out.println(c.remove(5));
	System.out.println(c);
	//System.out.println(c.remove(9));
	//System.out.println(c);

	//add
	c.add(5, 6);
	System.out.println(c);
	c.add(0, 0);
	System.out.println(c);
	c.add(10, 10);
	System.out.println(c);
	//c.add(100, 0);
	//c.add(-1, 0);

	//enhanced for loop
	MyLinkedList d = new MyLinkedList();
	for(int x = 0; x < 10; x++){
	    System.out.println(d.add(x));
	    //System.out.println(d.size());
	}
	for(int i: d){
	    System.out.println(i);
	}
	
    }
    
}
