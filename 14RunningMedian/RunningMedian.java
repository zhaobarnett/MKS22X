public class RunningMedian{

    private MyHeap smallHalf, bigHalf;
    
    public RunningMedian(){
	//smallHalf should be a max heap
	smallHalf = new MyHeap();
	//bigHalf should be a min heap
	bigHalf = new MyHeap(false);
    }

    public void add(int value){
	//adding the value:	
	//first add to small half
        if(smallHalf.size() == 0){
	    smallHalf.add(value);
	} else if(smallHalf.size() == 1 && bigHalf.size() == 0){
	    if(value < smallHalf.peek()){
		int temp = smallHalf.remove();
		smallHalf.add(value);
		bigHalf.add(temp);
	    } else{
		bigHalf.add(value);
	    }
	}
	//finding which heap to add to when each has size of at least one
	else if(value < getMedian()){
	    smallHalf.add(value);
	} else{
	    bigHalf.add(value);
	}

	//rebalancing the heaps so they are equal in size:
	//smallHalf is more than 1 smaller
	if(smallHalf.size() + 2 <= bigHalf.size()){
	    smallHalf.add(bigHalf.remove());
	} else if(bigHalf.size() + 2 <= smallHalf.size()){
	    bigHalf.add(smallHalf.remove());
	}
    }
    
    public double getMedian(){
        if(smallHalf.size() == bigHalf.size()){
	    return (smallHalf.peek() + bigHalf.peek()) / 2.0; //converts the dividend to a double
	}
	else if(smallHalf.size() > bigHalf.size()){
	    return smallHalf.peek();
	}
	else{
	    return bigHalf.peek();
	}
    }

    public static void main(String[] args){
	RunningMedian r = new RunningMedian();     
	for(int i = -100000; i < 100001; i++){
	    r.add(i);
	}
	System.out.println(r.getMedian());
    }
    
}
