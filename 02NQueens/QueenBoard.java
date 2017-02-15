public class QueenBoard{
    private int[][] board;
    private int solutionCount;

    public QueenBoard(int size){
	board = new int[size][size];
	for(int row = 0; row < size; row++){
	    for(int column = 0; column < size; column++){
		board[row][column] = 0;
	    }
	}
    }

    private boolean addQueen(int r, int c){
	if (board[r][c] != 0){
	    return false;
	} else{
	    board[r][c] = -1;
	    //changing the horizontal values
	    for(int column = c + 1; column < board[r].length; column++){
		board[r][column] += 1;
	    }
	    ///changing the diagonal values
	    for(int x = 1; x < board[r].length; x++){
		if(r + x  < board.length && c + x < board.length){
		    board[r + x][c + x] += 1;
		}
	    }
	    for(int x = 1; x < board.length; x++){
		if(r - x > 0 && c + x < board.length){
		    board[r - x][c + x] += 1;
		}
	    }
	    return true;
	}
    }

    private void removeQueen(int r, int c){
	board[r][c] = 0;
	//changing the horizontal values
	for(int column = c + 1; column < board[r].length; column++){
	    board[r][column] -= 1;
	}
	///changing the diagonal values
	for(int x = 1; x < board[r].length; x++){
	    if(r + x  < board.length && c + x < board.length){
		board[r + x][c + x] -= 1;
	    }
	}
	for(int x = 1; x < board.length; x++){
	    if(r - x > 0 && c + x < board.length){
		board[r - x][c + x] -= 1;
	    }
	}
    }

    public boolean solve(){
    	return solveH(0);
    }

    private boolean solveH(int col){
	//base case
	if(col == board.length){
	    return true;
	}
	for(int row = 0; row < board.length; row++){
	    if(addQueen(row, col)){
		addQueen(row, col);
		//recurvisvely trying next row
		if(solveH(col + 1)){
		    return solveH(col + 1);
		}
		//otherwise, you backtrack remove the queen
		else{
		    removeQueen(row, col);
		}
	    }
	} return false;
    }

    public String toString(){
	String retVal = "";
	for (int r = 0; r < board.length; r++){
	    for (int c = 0; c < board[r].length; c++){
		retVal = retVal + board[r][c] + ' ';
	    }
	    retVal += "\n\n";
	}
	return retVal;
    }

    public static void main(String[] args){
	QueenBoard b = new QueenBoard(4);
	b.addQueen(0,1);
	b.removeQueen(0,1);
	b.solve();
	System.out.println(b);
    }

}
