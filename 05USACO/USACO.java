import java.util.*;
import java.io.*;

public class USACO {
    private int[][] lake;
    private int e;
    private Scanner in;
    private int n;
    
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

    public String toString() {
	String ans = "";
	for (int r = 0; r < lake.length; r++) {
	    for (int c = 0; c < lake[r].length; c++) {
		ans += lake[r][c];
		ans += ' ';
	    }
	    ans+= "\n";
	}
	return ans;
    }

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

    private int bronzeSize(){
	int dep = 0;
	for(int r = 0; r < lake.length; r++){
	    for(int c = 0; c < lake[0].length; c++){
		dep += lake[r][c];
	    }
	}
	return dep*72*72;
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
	//System.out.println(x.silver("testfile2"));
    }

}
