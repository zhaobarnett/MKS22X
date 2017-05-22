public class Location implements Comparable<Location>{
    private int row;
    private int col;
    private Location previous;
    private int distanceToStart;
    private int distanceToGoal;
    private boolean aStar;

    public Location(int r, int c, Location previous, int distToStart, int distToGoal, boolean aStar){
	row = r;
	col = c;
	this.previous = previous;
	distanceToStart = distToStart;
	distanceToGoal = distToGoal;
	this.aStar = aStar;
    } 

    public int compareTo(Location other){
	if(aStar){
	    return distanceToGoal + distanceToStart - (other.distToGoal() + other.distToStart());
	} else{
	    return distanceToGoal - other.distToGoal();
	}
    }
    
    //accessor methods
    public int getRow(){
	return row;
    }

    public int getCol(){
	return col;
    }

    public int distToStart(){
	return distanceToStart;
    }

    public int distToGoal(){
	return distanceToGoal;
    }
    
}
