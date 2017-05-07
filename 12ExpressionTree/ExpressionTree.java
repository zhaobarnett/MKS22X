import java.util.*;

public class ExpressionTree{
  
  /*return the value of the specified expression tree*/
  public double evaluate(){
    /*you are to write this method*/
      String postfix = toStringPostfix();
      return eval(postfix);
    //return 0.0;
  }

    private static double eval(String s){
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
  
  /*return the expression as an infix notation string with parenthesis*/
  /* The sample tree would be: "( 3 + (2 * 10))"     */
  public String toString(){
      if(isValue()){
	  return getValue() + "";
      } else{
	  //toString is implied
	  return "(" + getLeft() + " " + getOp() + " " + getRight() + ")";
      }
      //return "";
  }
  
  /*return the expression as a postfix notation string without parenthesis*/
  /* The sample tree would be: "3 2 10 * +"     */
  public String toStringPostfix(){
      if(isValue()){
	  return getValue() + "";
      } else{
	  //toStringPostfix must be called, otherwise toString is called
	  return "" + getLeft().toStringPostfix() + " " + getRight().toStringPostfix() + " " + getOp();
      }
      //return "";
  }
  
  /*return the expression as a prefix notation string without parenthesis*/
  /* The sample tree would be: "+ 3 * 2 10"     */
  
  public String toStringPrefix(){
    /*you are to write this method*/
      if(isValue()){
	  return getValue() + "";
      } else{
	  return "" + getOp() + " " + getLeft().toStringPrefix() + " " + getRight().toStringPrefix();
      }
    //return "";
  }
  
  
  
  
  
  
  private char op;
  private double value;
  private ExpressionTree left,right;
  
  /*TreeNodes are immutable, so no issues with linking them across multiple
  *  expressions. The can be constructed with a value, or operator and 2
  * sub-ExpressionTrees*/
  public ExpressionTree(double value){
    this.value = value;
    op = '~';
  }
  public ExpressionTree(char op,ExpressionTree l, ExpressionTree r){
    this.op = op;
    left = l;
    right = r;
  }
  
  
  
  public char getOp(){
    return op;
  }
  
  /* accessor method for Value, precondition is that isValue() is true.*/
  private double getValue(){
    return value;
  }
  /* accessor method for left, precondition is that isOp() is true.*/
  private ExpressionTree getLeft(){
    return left;
  }
  /* accessor method for right, precondition is that isOp() is true.*/
  private ExpressionTree getRight(){
    return right;
  }
  
  private boolean isOp(){
    return hasChildren();
  }
  private boolean isValue(){
    return !hasChildren();
  }
  
  private boolean hasChildren(){
    return left != null && right != null;
  }
  
  
  public static void main(String[] args){
    //ugly main sorry!
    ExpressionTree a = new ExpressionTree(4.0);
    ExpressionTree b = new ExpressionTree(2.0);

    ExpressionTree c = new ExpressionTree('+',a,b);
    System.out.println(c);
    System.out.println(c.toStringPostfix());
    System.out.println(c.toStringPrefix());
    System.out.println(c.evaluate());


    ExpressionTree d = new ExpressionTree('*',c,new ExpressionTree(3.5));
    System.out.println(d);
    System.out.println(d.toStringPostfix());
    System.out.println(d.toStringPrefix());
    System.out.println(d.evaluate());

    ExpressionTree ex = new ExpressionTree('-',d,new ExpressionTree(1.0));
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());

    ex = new ExpressionTree('+',new ExpressionTree(1.0),ex);
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());

    ex = new ExpressionTree('/',ex,new ExpressionTree(2.0));
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());
    
    
  }
  
}
