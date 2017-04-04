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

    public boolean add(int value){
	if(start == null){
	    LNode l = new LNode(value);
	    start = l;
	    size++;
	}
	return true;
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
		retVal += current.value + "]";
	    }
	    //reference to next node
	    current = current.next;
	}
	return retVal;
    }
    
    public static void main(String[] args){
	//testing basic add and toString
	MyLinkedList a = new MyLinkedList();
	System.out.println(a.add(0));
	System.out.println(a.size());
	System.out.println(a);
    }
    
}
