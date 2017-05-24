import java.util.*;

public class MazeSolver{
    private boolean animate;
    private Maze maze;
    
    public MazeSolver(String filename){
	this(filename, false);
    }
    
    public MazeSolver(String filename, boolean animate){
	maze = new Maze(filename);
	this.animate = animate;
    }

    public void solve(){
	solve(1);
    }
    
    public void solve(int style){
	//style is 0-4, where 0-DFS, 1-BFS,2-BestFirst, 3-A*
	//This method will instantiate the Frontier based on which style was chosen.
	Frontier front = null; //needs to be initalized
	if(style == 0){
	    front = new FrontierQueue();
	}
	else if(style == 1){
	    front = new FrontierStack();
	}
	else if(style == 2 || style == 3){
	    front = new FrontierPriorityQueue();
	}
	// It will then add the starting location of the maze to the Frontier.
	front.add(maze.getStart());
	//Finally it will process each subsequent element of the frontier until the end is found. 
	while(front.size() > 0){
	    Location current = front.next();
	    //terminates the while loop and changes the correct path to '@' upon arrival at end
	    if(distToEnd(current.getRow(),current.getCol()) == 0){
		maze.set(current.getRow(),current.getCol(),'E');
		current = current.previous;
	        while(current.hasPrev()){
		    maze.set(current.getRow(),current.getCol(),'@');
		    current = current.previous;
		}
		maze.set(current.getRow(),current.getCol(),'S');
		break;
	    }
	    //otherwise continues searching
	    maze.set(current.getRow(),current.getCol(),'.');
	    for(Location n:getValidNeighbors(current, style == 3)){
		front.add(n); //valid neighbors added to the frontier
		maze.set(n.getRow(), n.getCol(), '?'); //frontiers are '?'
	    }
	    if (animate) {
		System.out.println(maze.toString(75));
	    }
	}
    }

    private ArrayList<Location> getValidNeighbors(Location current, boolean aStar){
        ArrayList<Location> ans = new ArrayList<Location>();
	int currentRow = current.getRow();
	int currentCol = current.getCol();
	int[][] neighbors = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	for(int i = 0; i < 4; i++){
	    int neighborRow = currentRow + neighbors[i][0];
	    int neighborCol = currentCol + neighbors[i][1];
	    if(maze.get(neighborRow,neighborCol) == ' '){
		ans.add(new Location(neighborRow, neighborCol, current, distToStart(neighborRow, neighborCol), distToEnd(neighborRow, neighborCol), aStar));
	    }
	}
	return ans;
    }

    private int distToStart(int neighborRow, int neighborCol){
	Location start = maze.getStart();
	return Math.abs(start.getRow() - neighborRow) + Math.abs(start.getCol() - neighborCol);
    }

    private int distToEnd(int neighborRow, int neighborCol){
	Location end = maze.getEnd();
	return Math.abs(end.getRow() - neighborRow) + Math.abs(end.getCol() - neighborCol);
    }
    
    public String toString(){
	return maze.toString(); //the toString of the maze instanceVariable
    }

    public static void main(String[] args){
	for(int i = 0; i < 4; i++){
	    MazeSolver m = new MazeSolver("data3.txt");
	    m.solve(i);
	    System.out.println(m);
	}
    }
	
}
