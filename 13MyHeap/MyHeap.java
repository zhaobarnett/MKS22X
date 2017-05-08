import java.util.*;

public class MyHeap{
    private ArrayList<String> list;
    private boolean max;

    public MyHeap(){
	MyHeap(true);
    }

    public MyHeap(boolean b){
	list = new ArrayList<String>();
	max = b;
    }

    public void add(String s){
	if(list.size() == 0){
	    list.add(1,s);
	} else{
	    list.add(s);
	    pushUp();
	}
    }

    public String remove(){
	if(list.size() >= 1){
	    String retVal = list.get(1);
	    if(list.size() == 1){
		list.remove(1);
		return retVal;
	    } else{
		String newTop = list.remove(list.size() - 1);
		list.set(1,newTop);
		pushDown();
		return retVal;
	    }
	}
    }

    public String peek(){
	return list.get(1);
    }

    private void swap(int index1, int index2){
	String s = list.get(index1);
	list.set(index1, list.get(index2));
	list.set(index2, s);
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

    private void pushDown(){
	int location = 1;
	while(location * 2 + 1 < list.size()){
	    if(list.get(location).compareTo(list.get(location * 2)) < 0 && list.get(location).compareTo(list.get(location * 2 + 1)) < 0){
		swap(location, location * 2);
		location *= 2;
	    }
	    else if(list.get(location).compareTo(list.get(location * 2)) < 0 && list.get(location).compareTo(list.get(location * 2 + 1)) > 0){
		swap(location, location * 2);
		location *= 2;
	    }
	    else if(list.get(location).compareTo(list.get(location * 2)) > 0 && list.get(location).compareTo(list.get(location * 2 + 1)) < 0){
		swap(location, location * 2 + 1);
		location = location * 2 + 1;
	    } else{
		break;
	    }
	}
    }

    public static void main(String[] args){
	
    }
    
    
}
