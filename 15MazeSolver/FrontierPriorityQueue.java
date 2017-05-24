import java.util.*;

public class FrontierPriorityQueue implements Frontier{
    private PriorityQueue<Location> heap; //ordered by compareTo

    public FrontierPriorityQueue(){
	heap = new PriorityQueue<Location>();
    }

    public void add(Location l){
	heap.add(l);
    }

    public Location next(){
	return heap.poll();
    }

    public int size(){
	return heap.size();
    }
    
}
