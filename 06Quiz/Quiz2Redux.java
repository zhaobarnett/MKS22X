import java.util.*;

public class Quiz2Redux{  
    /*Returns an ArrayList<String> that contains all subsets of the
     *characters of String s. Assume s has no duplicate characters.
     *the characters should appear in the same order that they occur 
     *in the original string.
     */
    public static ArrayList<String> combinations(String s){
	ArrayList<String>words = new ArrayList<String>();
	help( words , s , 0 , "");
	Collections.sort(words);
	return words;
    }
    
    private static void help( ArrayList<String> words, String s , int index , String subset ){
	//this means that help has looped through all of s already
	if(index == s.length()){
	    words.add(subset);
	} else{
	    //you include the character at this index
	    help( words , s , index + 1 , subset + s.substring(index, index + 1));
	    //you do not include the character at this index
	    help( words , s , index + 1 , subset);
	}
    }

    public static void main(String[] args){
	System.out.println(combinations("abcd"));
	System.out.println(combinations("kji"));
	System.out.println(combinations(""));
    }

}
