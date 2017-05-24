import java.util.*;

public class FrontierQueue implements Frontier{
    private LinkedList<Location> list;

    public FrontierQueue(){
	list = new LinkedList<Location>();
    }

    public void add(Location l){
	list.add(l);
    }

    public Location next(){
	return list.removeFirst();
    }

    public int size(){
	return list.size();
    }

}
