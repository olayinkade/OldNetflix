

public class Node {
private Obj data;
private Node next;
public Node(Obj data,Node next){
	this.data= data;
	this.next =next; 
}
public void setData(Obj data){this.data =data;}
public void setNext(Node next){this.next = next;}
public Obj getData(){return data;}
public Node getNext(){return next;}



}
