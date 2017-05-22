public class FrontierPriorityQueue implements Frontier{
    private MyHeap h; //ordered by compareTo

    public FrotierQueue{
	h = new MyHeap();
    }

    public void add(Location l){
	h.add(l);
    }

    public Location next(){
	return h.remove();
    }
    
}
