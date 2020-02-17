

public class Queue extends LinkedList {
/*******************************************
 * inserts at the back , using a
 *  circular linked list
 **********************************************/
	public void enqueue(Obj data){

		if(isEmpty()){
			insertFirst(data);
		}
		else{
			last.setNext(new Node(data,last.getNext()));
			last = last.getNext();
			size++;
		}
	}

}
