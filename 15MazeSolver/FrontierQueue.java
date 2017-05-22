public class FrontierQueue implements Frontier{
    private LinkedList list;

    public FrotierQueue{
	list = new LinkedList();
    }

    public void add(Location l){
	list.add(l);
    }

    public Location next(){
	return list.getFirst();
    }

}
