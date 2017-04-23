import java.util.*;

public class MyDeque{
    private int startIndex;
    private int endIndex;
    private int size;
    private String[] deque;

    public MyDeque(){
	startIndex = 0;
	endIndex = 0;
	size = 0;
	deque = new String[10];
    }

    private void resizeDeque(){
	//new array with space for 10 more elements
	String[] temp = new String[deque.length + 10];
	for(int i = 0; i < size; i++){
	    //copying over values
	    int dequeIndex = (startIndex + i) % deque.length;
	    temp[i] = deque[dequeIndex];
	}
	//temp is the new deque
	deque = temp;
	//changing bounds
	startIndex = 0;
	endIndex = size - 1;
    }

    public void addFirst(String s){
	if(s == null){
	    throw new NullPointerException();
	} else{
	    if(size == deque.length){
		resizeDeque();
	    }
	    //adds from back if not empty
	    if(size != 0){
	        if(startIndex == 0){
		    startIndex = deque.length - 1;
		} else{
		    startIndex = startIndex - 1;
		}
	    }
	    //adds to index 0 if empty
	    //System.out.println(startIndex);
	    deque[startIndex] = s;
	    size++;
	}
    }

    public void addLast(String s){
	if(s == null){
	    throw new NullPointerException();
	} else{
	    if(size == deque.length){
		resizeDeque();
	    }
	    if(size != 0){
		endIndex = (endIndex + 1) % deque.length;
	    }
	    deque[endIndex] = s;
	    size++;
	}
    }

    public String removeFirst(){
	String retVal;
	if(size == 0){
	    throw new NoSuchElementException();
	} else{
	    retVal = deque[startIndex];
	    deque[startIndex] = null;
	    //updating global variables
	    startIndex += 1;
	    //need to make sure the index is valid
	    startIndex = startIndex % deque.length;
	    size--;
	}
	return retVal;
    }

    public String removeLast(){
	String retVal;
	if(size == 0){
	    throw new NoSuchElementException();
	} else{
	    retVal = deque[endIndex];
	    deque[endIndex] = null;
	    //updating global variables
	    //similar to addFirst, modulo does not return negative number
	    //endIndex must be reset to last index
	    if(endIndex == 0){
		endIndex = deque.length - 1;
	    } else{
	    endIndex -= 1;
	    }
	    size--;
	}
	return retVal;
    }

    public String getFirst(){
	if(size == 0){
	    throw new NoSuchElementException();
	} else{
	    return deque[startIndex];
	}
    }

    public String getLast(){
	if(size == 0){
	    throw new NoSuchElementException();
	} else{
	    return deque[endIndex];
	}
    }

    public String toString(){
	String retVal = "start: " + startIndex + "\nend: " + endIndex + "\nsize: " + size + "\n[";
	for(int i = 0; i < deque.length; i++){
	    if(i == deque.length - 1){
		retVal += deque[i] + "]";
	    }
	    else{
		retVal += deque[i] + ", ";
	    }
	}
	return retVal;
    }

    public static void main(String[] args){
	MyDeque a = new MyDeque();
	//testing exceptions
	String s = null;
	//a.addFirst(s);
	//a.addLast(s);
	//a.removeFirst();
	//a.removeLast();
	//a.getFirst();
	//a.getLast();
	
	a.addFirst("a");
	a.addFirst("b");
	System.out.println(a);
	System.out.println(a.removeLast());
	System.out.println(a);
	System.out.println(a.getFirst());

	MyDeque b = new MyDeque();
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	for(int x = 0; x < alphabet.length(); x++){
	    b.addFirst(alphabet.substring(x, x+1));
	    System.out.println(b);
	}
        System.out.println(b.getFirst());
	System.out.println(b.getLast());
	System.out.println(b.removeFirst());
	System.out.println(b);
	System.out.println(b.removeLast());
	System.out.println(b);
	System.out.println(b.getFirst());
	System.out.println(b.getLast());
	    
    }
}

