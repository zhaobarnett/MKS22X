import java.util.*;
import java.io.*;

public class USACO {
    private int[][] lake;
    private int e;
    private Scanner in;
    private int n;
    //silver global variables
    private int[][] pasture;
    private Scanner silverFile;
    private int time;
    private int rowDest;
    private int colDest;
    private int[][] tempPasture;

    public USACO(){}
    
    public int bronze(String fileName){
	try {
	    in = new Scanner(new File(fileName));
	    makeSize(fileName);
	    loadWords(fileName);
	    bronzeStomp();
	    bronzeLake();
	}
	catch(FileNotFoundException e){
	    System.out.println("Invalid filename or path");
	    System.exit(1);
	}
	return bronzeSize();
    }

    //toString for bronze
    //public String toString() {
    //	String ans = "";
    //	for (int r = 0; r < lake.length; r++) {
    //	    for (int c = 0; c < lake[r].length; c++) {
    //		ans += lake[r][c];
    //		ans += ' ';
    //	    }
    //	    ans+= "\n";
    //	}
    //	return ans;
    //}

    //create the 2-d array for bronze
    private void makeSize(String fileName){
	String firstline = in.nextLine();
	//System.out.println(firstline);
	Scanner f = new Scanner(firstline);
	int row = f.nextInt();
	int col = f.nextInt();
	e = f.nextInt();
	n = f.nextInt();
	//System.out.println(row);
	//System.out.println(col);
	//System.out.println(e);
	//System.out.println(n);
	lake = new int[row][col];
    }

    //loads the 2-d array for bronze
    private void loadWords(String fileName){
	for (int r = 0; r < lake.length; r++) {
	    String line = in.nextLine();
	    Scanner s = new Scanner(line);
	    for (int c = 0; c < lake[r].length; c++){
		//System.out.println(line);
		lake[r][c] = s.nextInt();
	    }
	}
    }

    //cow stomping function
    private void bronzeStomp(){
	try{
	    while(in.hasNextLine()){
		String instruction = in.nextLine();
		Scanner z = new Scanner(instruction);
		int row = z.nextInt();
		int col = z.nextInt();
		int inc = z.nextInt();
		//System.out.println(row+" "+col+" "+inc);
		int largest = 0;
		for(int r = row; r < row + 3; r++){
		    for(int c = col; c < col + 3; c++){
			if(lake[r-1][c-1] > largest){
			    largest = lake[r-1][c-1];
			}
		    }
		}
		for(int r = row; r < row + 3; r++){
		    for(int c = col; c < col + 3; c++){
			if (lake[r-1][c-1]>largest-inc){
			    lake[r-1][c-1] = largest-inc;
			}
			//System.out.println(toString());
		    }
		}
	    }
	    //System.out.println(toString());
	}
	catch(IndexOutOfBoundsException e){
	    System.out.println("Invalid Index.");
	}
    }

    //changes the values in lake to reflect elevation
    private void bronzeLake(){
	for(int r = 0; r < lake.length; r++){
	    for(int c = 0; c < lake[0].length; c++){
		if(lake[r][c] < e){
		    int temp = lake[r][c];
		    lake[r][c] = e - temp;
		}
		else{
		    lake[r][c] = 0;
		}
	    }
	}
	//System.out.println(toString());
    }

    //calculates the volume of water in lake
    private int bronzeSize(){
	int dep = 0;
	for(int r = 0; r < lake.length; r++){
	    for(int c = 0; c < lake[0].length; c++){
		dep += lake[r][c];
	    }
	}
	return dep*72*72;
    }

    public int silver(String filename){
	try{
	    silverFile = new Scanner(new File(filename));
	    createPasture();
	    addCow();
	    for(int x = 0; x < time; x++){
		moveCow();
	    }
	    //System.out.println(toString());
	} catch(FileNotFoundException e){
	    System.out.println("Invalid filename or path");
	    System.exit(1);
	}
	return pasture[rowDest][colDest];
    }

    //initialize and load 2-d array for silver
    private void createPasture(){
	String firstLine = silverFile.nextLine();
	//System.out.println(firstLine);
	Scanner s = new Scanner(firstLine);
	int row = s.nextInt();
	int col = s.nextInt();
	time = s.nextInt();
	pasture = new int[row][col];
	tempPasture = new int[row][col];
	//loading the array
	for(int r = 0; r < pasture.length; r++){
	    String pastureLine = silverFile.nextLine();
	    //System.out.println(pastureLine);
	    for(int c = 0; c < pasture[r].length; c++){
		String a = pastureLine.substring(c, c + 1);
		if(a.equals(".")){		    
		    pasture[r][c] = 0;
		}
		if(a.equals("*")){
		    pasture[r][c] = -1;
		}
	    }
	}
    }

    //place the cow in the pasture
    private void addCow(){
	String lastLine = silverFile.nextLine();
	//System.out.println(lastLine);
	Scanner z = new Scanner(lastLine);
	int row = z.nextInt();
	int col = z.nextInt();
	rowDest = z.nextInt() - 1;
	//System.out.println(rowDest);
	colDest = z.nextInt() - 1;
	//System.out.println(colDest);
	pasture[row - 1][col - 1] = 1;
    }

    private void moveCow(){
	for(int r = 0; r < tempPasture.length; r++){
	    for(int c = 0; c < tempPasture[r].length; c++){
		//add up the values from all four directions
		//does not add values if space is -1
		if(pasture[r][c] == -1){
		    tempPasture[r][c] = -1;
		} else{
		    //checks if the value to be added is out of bounds or -1
		int up;
		if(r - 1 < 0 || pasture[r - 1][c] == -1){
		    up = 0;
		} else{
		    up = pasture[r - 1][c];
		}	
		int down;
		if(r + 1 >= pasture.length || pasture[r + 1][c] == -1){
		    down = 0;
		} else{
		    down = pasture[r + 1][c];
		}
		int left;
		if(c - 1 < 0 || pasture[r][c - 1] == -1){
		    left = 0;
		} else{
		    left = pasture[r][c - 1];
		}
		int right;
		if(c + 1 >= pasture[r].length || pasture[r][c + 1] == -1){
		    right = 0;
		} else{
		    right = pasture[r][c + 1];
		}
		tempPasture[r][c] = up + down + left + right;
		}
	    }
	}
	//change the values in the pasture
	for(int r = 0; r < pasture.length; r++){
	    for(int c = 0; c < pasture[r].length; c++){
		pasture[r][c] = tempPasture[r][c];
	    }
	}
	//System.out.println(toString());
    }

    public String toString(){
	String retVal = "";
	for(int r = 0; r < pasture.length; r++){
	    for(int c = 0; c < pasture[r].length; c++){
		retVal += pasture[r][c] + " ";
	    }
	    retVal += "\n";
	}
	return retVal;
    }
    
    public static void main (String[] args) {
        USACO x = new USACO(); //does not have to do anything.
	//tests for bronze
	//System.out.println(x.bronze("bdata.txt"));
	System.out.println(x.bronze("btest1.txt"));
	System.out.println("Answer: 342144");
	
	System.out.println(x.bronze("btest2.txt"));
	System.out.println("Answer: 102762432");
	    
	System.out.println(x.bronze("btest3.txt"));
	System.out.println("Answer: 1058992704");

	//tests for silver
	System.out.println(x.silver("stest1.txt"));
	System.out.println("Answer: 1");

	System.out.println(x.silver("stest2.txt"));
	System.out.println("Answer: 74");
	
	System.out.println(x.silver("stest3.txt"));
	System.out.println("Answer: 6435");

	System.out.println(x.silver("stest4.txt"));
	System.out.println("Answer: 339246");
    }

}
