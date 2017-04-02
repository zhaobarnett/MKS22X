import java.util.*;

public class Quick{
    
    public static int part ( int [] data, int start, int end){
	boolean debug = false;
	//generates random index to be pivot
	Random randgen = new Random();
	int randomIndex = randgen.nextInt(end - start + 1) + start;
	int pivot = data[randomIndex];
	//swaps the pivot with the last element
	swap(data, randomIndex, end);
	//loops through the array and swaps accordingly
	int wall = start;
	//all numbers smaller than the index are placed to the left of the wall
	//all numbers larger than the index are placed to the right of the wall
	for(int index = start; index < end; index++){
	    if(data[index] < pivot){
		//if the number at this index is smaller than the pivot, it is moved to "wall" index
		swap(data, index, wall);
		//the wall becomes the next index because a smaller number has been added to the left of the wall
	        wall += 1;
	    }
	}
	swap(data, wall, end);
	if(debug){
	    System.out.println("Partitioned array: " + printArray(data));
	}
	return wall;
    }

    //works as intended
    public static int dutchflag( int[] data, int start, int end ){
	boolean debug = false;
	Random randgen = new Random();
	int randomIndex = randgen.nextInt(end - start + 1) + start;
	int pivot = data[randomIndex];
	if(debug){
	    System.out.println("randomIndex: " + randomIndex);
	}
	//brings the pivot to the front
	swap(data, randomIndex, start);
	int i = start;
	int lessThan = start;
	int greaterThan = end;
	if(debug){
	    System.out.println("Pivot in the front: " + printArray(data));
	}
	while(i <= greaterThan){
	    //moves the wall foward to bunch equal values together
	    if(data[i] == pivot){
		i++;
	    }
	    //moves greater than value to the back
	    else if(data[i] > pivot){
		swap(data, i, greaterThan);
		greaterThan--;
	    }
	    //moves less than values to the front
	    else{
		swap(data, i, lessThan);
		lessThan++;
		i++;
	    }
	    if(debug){
		System.out.println("Intermediate: " + printArray(data));
	    }
	}
	//less than is location of beginning of equal block
	return lessThan;
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

    public static int quickselect(int[]data, int k){
	if(data.length == 0){
	    return 0;
	}
	boolean debug = false;
	int start = 0;
	int end = data.length - 1;
	int p = part(data, start, end);
	if(debug){
	    System.out.println(printArray(data));
	}
	while(p != k){
	    if(p < k){
		//do partition on everything to the right of p
		start = p + 1;
		p = part(data, start, end);
		if(debug){
		    System.out.println(printArray(data));
		}
	    }
	    if(p > k){
		//do partition on everything to the left of p
		end = p - 1;
		p = part(data, start, end);
		if(debug){
		    System.out.println(printArray(data));
		}
	    }
	}
	return data[k];
    }

    public static void quicksort(int[]ary){
	boolean debug = false;
	if(debug){
	    System.out.println("Unsorted Array: " + printArray(ary));
	}
	quicksortH(ary, 0, ary.length - 1);
	if(debug){
	    System.out.println("Sorted Array: " + printArray(ary));
	}
    }

    private static void quicksortH(int[] data, int start, int end){
	//stops when there is only one element between each wall (beause start will be equal to end)
	if(start < end){
	    boolean debug = false;
	    Random randgen = new Random();
	    int randomIndex = randgen.nextInt(end - start + 1) + start;
	    int pivot = data[randomIndex];
	    if(debug){
		System.out.println("randomIndex: " + randomIndex);
	    }
	    //brings the pivot to the front
	    swap(data, randomIndex, start);
	    int i = start;
	    int lessThan = start;
	    int greaterThan = end;
	    if(debug){
		System.out.println("Pivot in the front: " + printArray(data));
	    }
	    while(i <= greaterThan){
		//moves the wall foward to bunch equal values together
		if(data[i] == pivot){
		    i++;
		}
		//moves greater than value to the back
		else if(data[i] > pivot){
		    swap(data, i, greaterThan);
		    greaterThan--;
		}
		//moves less than values to the front
		else{
		    swap(data, i, lessThan);
		    lessThan++;
		    i++;
		}
		if(debug){
		    System.out.println("Intermediate: " + printArray(data));
		}
	    }
	    //lessThan is location of beginning of equal block
	    quicksortH(data, start, lessThan - 1);
	    //greaterThan is location of end of equal block
	    quicksortH(data, greaterThan + 1, end);
	}
    }

    public static void main(String[] args){
	//testing part and dutchflag:
	
	//all of the array
	int[]ary = { 2, 10, 15, 23, 0,  5};
	System.out.println("Original Array: " + printArray(ary));
	//System.out.println(part(ary, 0, ary.length - 1));
	System.out.println(dutchflag(ary, 0, ary.length - 1));
	System.out.println(printArray(ary));

	//part of the array
	int[]ary1 = {999,999,999,4,1,0,3,2,999,999,999};
	System.out.println("Original Array: " + printArray(ary1));
	//System.out.println(part(ary1, 3, 7));
	//System.out.println(dutchflag(ary1, 3, 7));
	System.out.println(dutchflag(ary1, 0, ary1.length - 1));
	System.out.println(printArray(ary1));

	//testing a randomly generated array
	int[]ary2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	Random randgen = new Random();
	for(int x = 0; x < 10; x++){
	    int element = randgen.nextInt(11) - 5;
	    ary2[x] = element;
	}
	System.out.println("Original Array: " + printArray(ary2));
	//System.out.println(part(ary2, 0, ary2.length - 1));
	System.out.println(dutchflag(ary2, 0, ary2.length - 1));
	System.out.println(printArray(ary2));
	
	System.out.println(quickselect( ary , 0 ));
	System.out.println(quickselect( ary , 1 )); 
	System.out.println(quickselect( ary , 2 ));
	System.out.println(quickselect( ary , 3 )); 
	System.out.println(quickselect( ary , 4 ));  
	System.out.println(quickselect( ary , 5 ));

	//testing a randomly generated array
	int[]ary3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	//Random randgen = new Random();
	for(int x = 0; x < 10; x++){
	    int element = randgen.nextInt(11) - 5;
	    ary3[x] = element;
	}
	System.out.println("Original Array: " + printArray(ary3));
	quicksort(ary3);
	System.out.println(printArray(ary3));

	//size 0 arrays
	int[] ary0 = {};
        quicksort(ary0);
	System.out.println(printArray(ary0));
	System.out.println(quickselect(ary0, 0));

	//more tests in driver
    }
}
