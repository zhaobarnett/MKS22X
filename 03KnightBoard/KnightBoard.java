public class KnightBoard{
    private int[][] board;

    //2-d array with all possible moves for a knight
    int[][] allMoves  =
    {
	{ 0, 0 }, //this case allows the knight to be placed in the position designated by the solve function
	{ -2, 1 },
	{ -1, 2 },
	{ 1, 2 },
	{ 2, 1 },
	{ 2, -1 },
	{ 1, -2 },
	{ -1, -2 },
	{ -2, -1 }
    };
    
    public KnightBoard(int startingRows, int startingCols){
	board = new int[startingRows][startingCols];
    }

    public String toString(){
	String retVal = "";
	for(int r = 0; r < board.length; r++){
	    for(int c = 0; c < board[r].length; c++){
		if(board[r][c] >= 10){    
		    retVal += board[r][c] + " ";
		} else{
		    retVal += " " + board[r][c] + " ";
		}
	    }
	    retVal += "\n";
	} return retVal;
    }

    public void solve(){
	//trying to place the first knight at every square on the board
	for(int row = 0; row < board.length; row++){
	    for(int col = 0; col < board[row].length; col++){
		solveH(row, col, 1);
	    }
	}
    }

    private boolean addKnight(int newRow, int newCol, int level){
        if(
	   //check if the next "level" is within the bounds of the board
	    newRow >= 0 &&
	    newRow < board.length &&
	    newCol  >= 0 &&
	    newCol < board[0].length &&
	    //check if the next "level" is has already been occupied
	    board[newRow][newCol] == 0
	   ){
	    board[newRow][newCol] = level;
	    return true;
	} return false;
    }
	
    private boolean solveH(int row, int col, int level){
	//when level is greater than the total number of spaces, all spaces have been occupied
	if(level > board.length * board[0].length){
	    return true;
	}
	//for loop tries all possible moves from a space before returning false
	for(int whichMove = 0; whichMove < 9; whichMove++){
	    int newRow = row + allMoves[whichMove][0];
	    int newCol = col + allMoves[whichMove][1];
	    //add Knight to the new space if possible
	    if(addKnight(newRow, newCol, level)){
		//recursively checking if the knight will result in a "dead end"
		if(solveH(newRow, newCol, level + 1)){
		    return true;
		} else{
		    board[newRow][newCol] = 0;
		}
	    }
	} return false;
    }

    public static void main(String[] args){
	KnightBoard a = new KnightBoard(8,7);
	a.solve();
	System.out.println(a);
	
	//testing all board sizes up to 8 by 8
	for(int r = 1; r < 9; r++){
	    for(int c = 1; c < 9; c++){
		KnightBoard b = new KnightBoard(r,c);
		b.solve();
		System.out.println(r + " by " + c + " square");
		System.out.println(b);
	    }
	}
	//very slow for 6 by 8, 7 by 8, and 8 by 6
	
    }

}
