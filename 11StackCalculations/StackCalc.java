import java.util.*;

public class StackCalc{
    public static double eval(String s){
	//splitting the string
	String[] tokens = s.split(" ");
	Stack<String> values = new Stack<String>();
	for(String token: tokens){
	    if(isOp(token)){
		values.push(apply(token, values.pop(), values.pop()));
	    } else{
		values.push(token);
	    }
	}
	return Double.parseDouble(values.pop());
    }

    private static boolean isOp(String s){
	return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%");
    }

    private static String apply(String operation, String a, String b){
	Double x = Double.parseDouble(a);
	Double y = Double.parseDouble(b);
	String ans;
	if(operation.equals("+")){
	    ans = Double.toString(x + y);
	}
	else if(operation.equals("-")){
	    ans = Double.toString(y - x);
	}
	else if(operation.equals("*")){
	    ans = Double.toString(x * y);
	}
	else if(operation.equals("/")){
	    ans = Double.toString(y / x);
	}
	else{
	    ans = Double.toString(y % x);
	}
	return ans;
    }
    
    public static void main(String[] args){
	System.out.println(eval("10 2.0 +")); // 12.0
	System.out.println(eval("11 3 - 4 + 2.5 *")); // 30.0
	System.out.println(eval("8 2 + 99 9 - * 2 + 9 -")); // 893.0
    }

}
