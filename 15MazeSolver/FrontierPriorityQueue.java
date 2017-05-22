public class FrontierPriorityQueue implements Frontier{
    private MyHeap heap; //ordered by compareTo

    public FrotierQueue{
	heap = new MyHeap();
    }

    public void add(Location l){
	heap.add(l);
    }

    public Location next(){
	return heap.remove();
    }
    
}
