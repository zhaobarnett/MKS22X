public class QueenBoard{
    private int[][] board;
    private int solutionCount;

    public QueenBoard(int size){
	board = new int[size][size];
	for(int x = 0; x < size; x++){
	    for(int y = 0; y < size; x++){
		board[x][y] = 0;
	    }
	}
    }

    private void addQueen(int row, int column){
	board[row][column] = -4;
	for(int y = 0; int < board.length; y++){
	    board[row][y] += 1;
	}
	for(int x = 0; int < board[0].length; x++){
	    board[x][column] += 1;
	}
	//change diagonal values
    }

    private void removeQueen(int row, int column){
	board[row][column] = 3;
	for(int y = 0; int < board.length; y++){
	    board[row][y] -= 1;
	}
	for(int x = 0; int < board[0].length; x++){
	    board[x][column] -= 1;
	}
	//change diagonal values
    }

    public boolean solve(){
	return solveH(0);
    }

    private boolean solveH(int col){
    }
