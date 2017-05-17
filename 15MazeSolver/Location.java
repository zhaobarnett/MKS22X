public class Location{
    private int row;
    private int col;
    private Location previous;
    private int distanceToStart;
    private int distanceToGoal;
    private boolean aStar;

    public Location(int r, int c, Location previous, boolean aStar){
	row = r;
	col = c;
	this.previous = previous;
	//distanceToStart = 
	//distanceToGoal = 
	this.aStar = aStar;
    } 

    //accessor methods
    public int getRow(){
	return row;
    }

    public int getCol(){
	return col;
    }

}
