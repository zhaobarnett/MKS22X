public class MazeSolver{
    private boolean animate;
    
    public MazeSolver(String filename){
	this(filename, false);
    }
    
    public MazeSolver(String filename, boolean animate){
	this.animate = animate;
    }

    public void solve(){
	solve(1);
    }
    
  `public void solve(int style){

  `} 
    // - style is 0-4, where 0-DFS, 1-BFS,2-BestFirst, 3-A*
    //  - This method will instantiate the Frontier based on which style was chosen. 
    // It will then add the starting location of the maze to the Frontier.
    //Finally it will process each subsequent element of the frontier until the end is found. 

    public String toString(){

    }
    //- the toString of the maze instanceVariable.

}
