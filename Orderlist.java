	

public class Orderlist extends LinkedList {
	
/*****************************************
 * Order insert into a linked List, doesn't
 * allow duplicates
 * 
 * ***************************************/
 
	public void orderInsert(Obj data){
		if(isEmpty()){
			insertFirst(data);
		}
		else if(!search(data)){
			if((last.getData()).compareTo(data) < 0){
				last.setNext(new Node(data,last.getNext()));
				last = last.getNext();
				
			}
			else{

				Node curr = last;
				Node prev = null;
				do{
					prev = curr;
					curr = curr.getNext();
				}while((curr.getData()).compareTo(data) < 0);
				prev.setNext(new Node(data,curr));
				
			}
			size++;
		}

	}
}
