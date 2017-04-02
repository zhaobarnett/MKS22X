import java.util.*;

public class Merge{

    public static void merge(int[]a, int[]b, int[]destination){
	int wallA = 0;
	int wallB = 0;
	for(int x = 0; x < destination.length; x++){
	    if(wallA >= a.length){
		destination[x] = b[wallB];
		wallB++;
	    }
	    else if(wallB >= b.length){
		destination[x] = a[wallA];
		wallA++;
	    }
	    else if(a[wallA] < b[wallB]){
		destination[x] = a[wallA];
		wallA++;
	    } else{
		destination[x] = b[wallB];
		wallB++;
	    }
	}
    }

    public static void mergesort(int[]ary){
	//stops when the array has been split into single blocks
	//or if the original array is empty
	if(ary.length <= 1){
	    return;
	} else{
	    //copy of right side
	    int sizeR = ary.length / 2;
	    //System.out.println(sizeR);
	    int[]right = new int[sizeR];
	    for(int x = 0; x < sizeR; x++){
		right[x] = ary[x];
	    }
	    //copy of left side
	    int sizeL = ary.length - sizeR;
	    //System.out.println(sizeL);
	    int[]left = new int[sizeL];
	    for(int x = 0; x < sizeL; x++){
		left[x] = ary[sizeR + x];
	    }
	    mergesort(right);
	    mergesort(left);
	    //tail recursion
	    merge(right, left, ary);
	}
    }

    public static String printArray(int[]data){
	String retVal = "{";
	for(int index = 0; index < data.length; index++){
	    if(index == data.length - 1){
		retVal += data[index] + "}";
	    } else{
		retVal += data[index] + ", ";
	    }
	}
	return retVal;
    }
    
    public static void main(String[] args){
	//testing merge on basic arrays
	int[]a = {1, 3, 5, 7, 9, 11};
	int[]b = {2, 4, 6};
	int[]c = new int[9];
	merge(a, b, c);
	System.out.println(printArray(c));

	//merging with an empty array
	int[]a1 = {1, 3, 5, 7, 9, 11};
	int[]b1 = {};
	int[]c1 = new int[6];
	merge(a1, b1, c1);
	System.out.println(printArray(c1));

	//testing mergesort on a basic array
	Random randgen = new Random();
	int[]ary = new int[20];
	for(int x = 0; x < ary.length; x++){
	    ary[x] = randgen.nextInt(11) - 5;
	}
	System.out.println("Unsorted Array: " + printArray(ary));
	mergesort(ary);
	System.out.println(printArray(ary));

	//testing mergesort on an empty array
	int[]ary0 = {};
	mergesort(ary0);
	System.out.println(printArray(ary0));
	
    }
}
	
