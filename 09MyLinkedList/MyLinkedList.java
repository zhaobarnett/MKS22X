public class MyLinkedList{
    private int size;
    private LNode start;
    private LNode end;

    public MyLinkedList(){
    size = 0;
    start = null;
    end = null;
    }

    //this can be an inner class because you would only instantiate it in MyLinkedList
    private class LNode{
    private int value;
    private LNode next;
    private LNode prev;
    
    private LNode(int value){
        this.value = value;
    }

    }

    public boolean add(int value){
    if(start == null){
        LNode l = new LNode(value);
        start = l;
        size++;
    } else{
        LNode l = new LNode(value);
        end = l;
        size++;
    }
    }

    public int size(){
    return size;
    }

    public String toString(){
    String retVal = "[";
    for(int i = 0; i < size; i++){


    }
    }

}
