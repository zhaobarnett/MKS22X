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
	if(style == 0){
	    Frontier front = new FrontierQueue();
	}
	else if(style == 1){
	    Frontier front = new FrontierStack();
	}
	else if(style == 2 || style == 3){
	    Frontier front = new FrontierPriorityQueue();
	}
	// It will then add the starting location of the maze to the Frontier.
	front.add(maze.getStart());
	//Finally it will process each subsequent element of the frontier until the end is found. 
	while(front.size() > 0){
	    Location current = rest.next();
	    maze.set(current.getRow(),current.getCol(),'.');
	    if(maze.get(current.getRow(),current.getCol()) == 'E'){
		maze.set(current.getRow(),current.getCol(),'@');
		break;
	    }
	    for(n:getValidNeighbors(current, style == 3)){
		rest.add(n);
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
		ans.add(new Location(neighborRow, neighborCol, current, aStar));
	    }
	}
	return ans;
    }
    
    public String toString(){
	return maze.toString(); //the toString of the maze instanceVariable
    }

}
