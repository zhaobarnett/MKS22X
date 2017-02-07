public class Recursion{
    public static String name(){
	return "Zhao,Barnett";
    }

    public static double sqrt(double n){
	if(n < 0){
	    throw new IllegalArgumentException();
	}
        if(n == 0){
	    return 0;
	} else{
	    return helper(n,1);
	}
    }

    private static double helper(double n, double guess){
	double betterGuess = (n / guess + guess) / 2;
	if((betterGuess * betterGuess - n) / betterGuess < 0.000000000000001){
	    return betterGuess;
	} else{
	    return helper(n, betterGuess);
	}
    }

    public static void main(String[] args){
	//System.out.println(sqrt(-1));
	System.out.println(sqrt(100));
	System.out.println(sqrt(4));
	System.out.println(sqrt(0.0025));
	System.out.println(sqrt(0.000025));
	System.out.println(sqrt(0));
    }
    
}
