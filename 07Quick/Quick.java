import java.util.*;

public class Quick{
    //public boolean debug;
    
    public static int part ( int [] data, int start, int end){
	boolean debug = true;
	if(debug){
	    System.out.println("This is the original array: " + printArray(data));
	}
	//generates random index to be pivot
	Random randgen = new Random();
	int randomIndex = randgen.nextInt(end - start + 1) + start;
	if(debug){
	    System.out.println("Pivot at index: " + randomIndex);
	}
	int pivot = data[randomIndex];
	if(debug){
	    System.out.println("The pivot is: " + pivot);
	}
	//swaps the pivot with the last element
	swap(data, randomIndex, end);
	if(debug){
	    System.out.println("The pivot should be at the end of the array: " + printArray(data));
	}
	//loops through the array and swaps accordingly
	int wall = 0;
	//all numbers smaller than the index are placed to the left of the wall
	//all numbers larger than the index are placed to the right of the wall
	for(int index = start; index < end; index++){
	    if(data[index] <= pivot){
		//if the number at this index is smaller than the pivot, it is moved to "wall" index
		swap(data, index, wall);
		//the wall becomes the next index because a smaller number has been added to the left of the wall
	        wall += 1;
	    }
	}
	swap(data, wall, end);
	if(debug){
	    System.out.println("The array should be partitioned: " + printArray(data));
	}
	return wall;
    }

    private static void swap(int[] data, int index1, int index2){
	int temp = data[index1];
	data[index1] = data[index2];
	data[index2] = temp;
    }

    public static String printArray(int[] data){
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

    //still needs work
    public static int quickselect(int []data, int k){
	int wall = part(data, 0, data.length - 1);
	if(wall != k){
	    if(wall < k){
		part(data, 0, wall);
	    }
	    if(wall > k){
		part(data, wall, data.length - 1);
	    }
	}
    }

    public static void main(String[] args){
	int[]ary = { 2, 10, 15, 23, 0,  5};
	System.out.println(part(ary, 0, 5));
	System.out.println(part(ary, 0, 4));
	int[]ary1 = {999,999,999,4,1,0,3,2,999,999,999};
	System.out.println(part(ary1, 0, 10));
	quickselect( ary , 0 );
	quickselect( ary , 1 ); 
	quickselect( ary , 2 );
	quickselect( ary , 3 ); 
	quickselect( ary , 4 );  
	quickselect( ary , 5 );  
    }
}
