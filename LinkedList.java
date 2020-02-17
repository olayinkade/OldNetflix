

public class LinkedList {
	/***************************************
	 * Functionality of linkedlist
	 * and method extent this class
	 * circular linked list
	 *–Add / remove functionality
	 *–Searchable 
	 *–Sometimes ordered (DVD loan list)
	 *–Sometimes queue-like (DVD wish list)
	 */

	protected Node last;
	private Node traversal;
	protected int size =0;
	public LinkedList(){// circular linkedlist
		last= null;
		traversal =null;
		size =0;

	}
	/***********************************
	 * checks if the linkedList is empty
	 */
	public boolean isEmpty(){
		return last==null;
	}


	/*************************************
	 * insert when list is empty, i.e
	 * when isEmpty() returns true
	 * 
	 ************************************/
	protected void insertFirst(Obj news){
		if(isEmpty()){
			last = new Node(news,null);
			last.setNext(last);
			size++;
		}

	}

	/******************************************
	 * searches for an item
	 * @param data
	 * @return returns true if item is present
	 */
	public boolean search(Obj data){
		boolean found = false;
		Node curr =null;
		if(!isEmpty()){
			curr = last;
			do{

				curr = curr.getNext();

				if( (curr.getData()).compareTo(data) == 0){
					found = true;
				}
			}while(curr != last && !found);
		}
		return found;
	}


	/********************************************
	 * DELETES a node
	 * @param data item you want deleted
	 */

	public void delete(Obj data){
		if(isEmpty())
			System.out.println("deleting from empty list");
		else if(!search(data))
			System.out.println("data not found");
		else{

			if(last == last.getNext())// only one item
				last = null;

			else{// more than one item
				Node curr = last;
				Node prev = null;

				do{
					prev = curr;
					curr = curr.getNext();
				}while(curr.getData().compareTo(data) != 0 && curr !=last);
				prev.setNext(curr.getNext());
				if(curr == last)
					last = prev;
				size--;
			}
		}

	}


	/*******************************************
	 * searches through the linkedList structure
	 * and returns  Node  if the Obj was found
	 * *****************************************/
	public Node retrive(Obj data){
		Node toreturn = null;
		if(!isEmpty()){
			Node curr = last;
			do{
				curr = curr.getNext();
			}while(curr != last && curr.getData().compareTo(data) != 0 );
			if(curr.getData().compareTo(data) == 0)
				toreturn = curr;
		}
		return toreturn;
	}

	/********************************************
	 * @return returns size of current linkedlist
	 *******************************************/
	public int getsize(){
		return size;
	}

	/************************************************
	 * this method loops through the linkedList n times
	 * and returns nth Node data
	 * @param number of times i have to loop
	 * @return
	 */
	public Obj getCustatIndex(int n){
		Obj toreturn=null;


		if(!isEmpty()){
			Node curr = last;
			int count = n;
			do{
				curr = curr.getNext();
				count--;
			}while(curr !=last && count !=0);
			toreturn = curr.getData();
		}
		return toreturn;
	}

	/**************************************************
	 ***************TRAVERSAL FUNCTIONALITY*************
	 *Both help to loop through the list
	 ***************************************************/
	public Obj top(){
		Obj toreturn =null;
		if(!isEmpty()){
			toreturn = last.getData();
			traversal = last;
		}
		else{
			System.out.println("List is empty WAR3");
		}
		return toreturn;
	}

	public Obj next(){
		Obj toreturn = null;

		if(!isEmpty()){
			traversal = traversal.getNext();
			toreturn = traversal.getData();
		}
		return  toreturn;
	}



}

