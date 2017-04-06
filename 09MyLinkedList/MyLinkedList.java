import java.util.*;

public class MyLinkedList{
    private int size;
    private LNode start;
    private LNode end;

    public MyLinkedList(){
	size = 0;
	//not really necessary, default value of uninstantiated object is null
	start = null;
	end = null;
    }

    //this can be an inner class because you would only instantiate it in MyLinkedList
    private class LNode{
	private int value;
	private LNode next;
	private LNode prev;
	
	private LNode(int value){
	    this.value = value;
	}

	public String toString(){
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
	if(x.prev == null && x.next == null){
	    x.prev.next = x.next;
	    x.next.prev = x.prev;
	}
	else if(x.prev == null){
	    x.next.prev = null;
	    start = x.next;
	}
	else{
	    x.prev.next = null;
	    end = x.prev;
	}
    }

    public int remove(int index){
	LNode toBeRemoved = null;;
	LNode current = start;
	int retVal = 0;
	for(int i = 0; i < index + 1; i++){
	    if(i == index){
		toBeRemoved = current;
		retVal = current.value;
	    }
	    current = current.next;
	}
	remove(toBeRemoved);
	return retVal;
    }
	
    public String toStringx(){
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
    public String toString(){
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
	//remove
	System.out.println(c.remove(0));
	System.out.println(c);
	System.out.println(c.remove(5));
	System.out.println(c);
	System.out.println(c.remove(9));
	System.out.println(c);
	
    }
    
}
