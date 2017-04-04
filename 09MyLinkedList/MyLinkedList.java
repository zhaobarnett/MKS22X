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
	
    }

    //adds value to the end
    public boolean add(int value){
	if(start == null){
	    start = new LNode(value);
	    size++;
	    return true;
	} else{
	    //current is a temp reference
	    LNode current = start;
	    //iterating through the linked list
	    for(int i = 0; i < size; i++){
		//when the last node is reached, a new node is added
		if(i == size - 1){
		    current.next = new LNode(value);
		    size++;
		    return true;
		}
		//otherwise, continue iterating through the linked list
		current = current.next;
	    }
	}
	return false;
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
    
    public int size(){
	return size;
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
	
    }
    
}
