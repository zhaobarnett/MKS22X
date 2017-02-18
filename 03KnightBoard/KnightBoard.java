public class KnightBoard{
    private int[][] board;

    public KnightBoard(int startingRows, startingCols){
	board = new int[startingRows][startingCols];
    }

    public String toString(){
	String retVal = "";
	for(int r = 0; r < board[0].length; r++){
	    for(int c = 0; c < board.length; c++){
		if(board[r][c] >= 10){    
		    retVal += board[r][c] + " ";
		} else{
		    retVal += " " + board[r][c] + " ";
		}
	    }
	} return retVal;
    }

    public void solve(){
    }

    private boolean solveH(int row, int col, int level){
    }

    public static void main(String[] args){
    }

}
