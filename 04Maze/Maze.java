import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze{
    
    private char[][]maze;
    private boolean animate;
    
    public Maze(String filename){
	try{
	    File text = new File(filename);
	    Scanner inf = new Scanner(text);
	    //put contents of file into an ArrayLists temporarily
	    ArrayList<String> lines = new ArrayList<String>();
	    while(inf.hasNextLine()){
		lines.add(inf.nextLine());
	    }
	    maze = new char[lines.size()][(lines.get(0)).length()];
	    int numOfE = 0;
	    int numOfS = 0;
	    for(int r = 0; r < lines.size(); r++){
		for(int c = 0; c < (lines.get(r)).length(); c++){
		    char a = (lines.get(r)).charAt(c);
		    maze[r][c] = a;
		    if(a == 'E'){
			numOfE++;
		    }
		    if(a == 'S'){
			numOfS++;
		    }
		}
	    }
	    //check to see if there are a correct numer of E's and S's
	    if(numOfE != 1 || numOfS != 1){
		System.out.println("The number of E's or S's is incorect.");
		System.exit(0);
	    }
	} catch(FileNotFoundException e){
	    System.out.println("This file does not exist.");
	    System.exit(0);
	}
	animate = false;
    }

    private void wait(int millis){
	try{
	    Thread.sleep(millis);
	} catch(InterruptedException e){
	}
    }

    public void setAnimate(boolean b){
	animate = b;
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }

    public boolean solve(){
	int startr=-1,startc=-1;
	//Initialize starting row and starting col with the location of the S.
	for(int r = 0; r < maze.length; r++){
	    for(int c = 0; c < maze[r].length; c++){
		if(maze[r][c] == 'S'){
		    startr = r;
		    startc = c;
		}
	    }
	}
	maze[startr][startc] = '@';//erase the S, and start solving!
	return solve(startr,startc);
    }


    //possible moves
    int[][] allMoves =
    {
	{ -1, 0 },
	{ 1, 0 },
	{ 0, 1 },
	{ 0, -1 }
    };
    
    private boolean solve(int row, int col){
	if(animate){
	    System.out.println("\033[2J\033[1;1H"+this);
            wait(200);
	}
	//base case
	//if(maze[row][col] == 'E'){
	//  return true;
	//}
	//try all possible directions
	for(int whichMove = 0; whichMove < 4; whichMove++){
	    //determine the new space
	    int newR = row + allMoves[whichMove][0];
	    int newC = col + allMoves[whichMove][1];
	    //base case goes in here!
	    if(maze[newR][newC] == 'E'){
		return true;
	    }
	    //if not E, checks the new space to see if it is available
	    if(maze[newR][newC] == ' '){
		maze[newR][newC] = '@';
		if(solve(newR, newC)){
		    return true;
		}
		//if there's no possible solution from the new space
		maze[newR][newC] = '.';
	    }
	}	
	return false;	
    }
    
    public String toString(){
	String retVal = "";
	for(int r = 0; r < maze.length; r++){
	    for(int c = 0; c < maze[r].length; c++){
		retVal += maze[r][c];
	    }
	    retVal += "\n";
	}
	return retVal;
    }
    
    public static void main(String[]args){
        Maze f;
        f = new Maze("data2.dat");
        
	f.setAnimate(true);
        f.solve();
	
        System.out.println(f);
    }

}
