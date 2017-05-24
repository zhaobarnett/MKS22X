import java.util.*;

public class FrontierStack implements Frontier{
    private LinkedList<Location> list;

    public FrontierStack(){
	list = new LinkedList<Location>();
    }

    public void add(Location l){
	list.add(l);
    }

    public Location next(){
	return list.removeLast();
    }

    public int size(){
	return list.size();
    }

}
