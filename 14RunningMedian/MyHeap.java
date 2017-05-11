import java.util.*;

public class MyHeap{
    private ArrayList<Integer> list;
    private boolean max;

    public MyHeap(boolean b){
	list = new ArrayList<Integer>();
	max = b;
    }

    public MyHeap(){
	list = new ArrayList<Integer>();
	max = true;
    }

    public void add(int i){
	if(list.size() == 0){
	    list.add(0, 0);
	    list.add(1, i);
	} else{
	    list.add(i);
	    pushUp();
	}
    }

    public int remove(){
	int retVal = list.get(1);
	if(list.size() >= 2){
	    if(list.size() == 2){
		list.remove(1);
	    } else{
		int newTop = list.remove(list.size() - 1);
		list.set(1,newTop);
		pushDown(1);
	    }
	}
	return retVal;
    }

    public int peek(){
	return list.get(1);
    }

    private void swap(int index1, int index2){
	int i = list.get(index1);
	list.set(index1, list.get(index2));
	list.set(index2, i);
    }

    private void pushUp(){
	int location = list.size() - 1;
	if(max){
	    while(location > 1 && list.get(location / 2).compareTo(list.get(location)) < 0){
		    swap(location / 2, location);
		    location /= 2;
		}
	} else{
	    while(location > 1 && list.get(location / 2).compareTo(list.get(location)) > 0){
		swap(location / 2, location);
		location /= 2;
	    }
	}
    }

    // private void pushDown(){
    //	int location = 1;
    //	while(location * 2 + 1 < list.size()){
    //	    if(list.get(location).compareTo(list.get(location * 2)) < 0 && list.get(location).compareTo(list.get(location * 2 + 1)) < 0){
    //		swap(location, location * 2);
    //		location *= 2;
    //	    }
    //	    else if(list.get(location).compareTo(list.get(location * 2)) < 0 && list.get(location).compareTo(list.get(location * 2 + 1)) > 0){
    //		swap(location, location * 2);
    //		location *= 2;
    //	    }
    //	    else if(list.get(location).compareTo(list.get(location * 2)) > 0 && list.get(location).compareTo(list.get(location * 2 + 1)) < 0){
    //		swap(location, location * 2 + 1);
    //		location = location * 2 + 1;
    //	    } else{
    //		break;
    //	    }
    //	}
    //}

    private void pushDown(int location){
	int constant;
	if(max){
	    constant = 1;
	} else{
	    constant = -1;
	}
	//does it have children?
	if(location * 2 < list.size()){
	    //does it have more than one child?
	    if(location * 2 + 1 < list.size()){
		if(list.get(location).compareTo(list.get(location * 2)) * constant < 0 || list.get(location).compareTo(list.get(location * 2 + 1)) * constant < 0){
		    if(list.get(location * 2).compareTo(list.get(location * 2 + 1)) * constant < 0){
			swap(location, location * 2 + 1);
			pushDown(location * 2 + 1);
		    } else{
			swap(location, location * 2);
			pushDown(location * 2);
		    }
		}
	    }
	    //only one child
	    else{
		if(list.get(location).compareTo(list.get(location * 2)) * constant < 0){
		    swap(location, location * 2);
		    pushDown(location * 2);
		}
	    }
	}
    }

    public String toString(){
	String ans = "";
	for(int i = 1; i < list.size(); i++){
	    ans += list.get(i);
	}
	return ans;
    }
	    
    public static void main(String[] args){
	String s = "abcdefghijklmnopqrstuvwxyz";
	MyHeap max = new MyHeap();
	for(int i = 0; i < 26; i++){
	    max.add(s.substring(i, i + 1));
	}
	System.out.println(max);
	System.out.println(max.peek());
	System.out.println(max.remove());
	System.out.println(max);

	MyHeap min = new MyHeap(false);
	for(int i = 25; i >= 0; i--){
	    min.add(s.substring(i, i + 1));
	}
	System.out.println(min);
	System.out.println(min.remove());
	System.out.println(min);	
    }
    
}
