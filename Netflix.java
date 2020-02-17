

public class Netflix{

	private static Orderlist DVDList = new Orderlist();
	private static Orderlist CustomerList =new Orderlist();

	/*****************************************
	 * it retrieves a specific customer from a 
	 * the CustomerList
	 * @param name uses this to search for the 
	 * customer
	 * @return the Customer searched for 
	 * This method doesn't handle null pointer so user
	 * would need to check that customer exist
	 * first before using function;
	 */
	public static Customer findCustomer( String name){// doesn't handle null pointer
		return ((Customer)(CustomerList.retrive(new Customer(name,0)).getData()));
	}


	/*****************************************
	 * it retrieves a specific DVD from a 
	 * the DVDList
	 * @param name uses this to search for the 
	 * DVD
	 * @return the DVD searched for.
	 * This method doesn't handle null pointer so user
	 * would need to check that DVD exist
	 * first before using function;
	 */
	public static DVD findDVD( String name){// doesn't handle null pointer
		return ((DVD)(DVDList.retrive(new DVD(name,0)).getData()));
	}



	/**************************************
	 * Adds customer to CustomerList
	 * Netflix adds customer to operations
	 * Assumes that customer names are unique
	 * creates customer only when his maxLoan
	 * is <= 10
	 * ***************************************/
	public static void addCustomer(Customer data){// 1 ACN
		if(CustomerList.search(data) == false){
			if(data.getMaxLoan() <= 10){
				CustomerList.orderInsert(data);
				System.out.println(data +" was added");
			}
			else
				System.out.println("ERROR : More than 10 DVD where you can only have atmost 10");
		}
		else{
			System.out.println("ERROR : "+data.getName() + " Already a netflix subscriber");
		}

	}

	/******************************************
	 * addDVD to Netflix list(DVDList), if DVD is
	 *  already
	 * present then increment quantity.Netflix
	 * adding DVD their list
	 * 
	 *******************************************/
	public static void addDVDtoN(DVD data){// 2 ACQ

		if(DVDList.search(data) == false){
			DVDList.orderInsert(data);
			System.out.println(data +" was added");
		}
		else{
			findDVD(data.getName()).increment(data.getQuantity());
			System.out.println(findDVD(data.getName())+" was incremented by "+data.getQuantity());

		}
		if(!CustomerList.isEmpty())
			waitingList((DVD)DVDList.retrive(data).getData());

	}

	/*******************************************
	 * addDVD to customer wishList
	 * it checks if the DVD was in the 
	 * DVDList(misspelled), checks if
	 * the customer exist in Netflix,
	 * check if the customer has the 
	 * DVD in the wish and Loan List
	 * and enqueues if not. Also it loops
	 * through the customer loanList and 
	 * satisfies all the customers wish list
	 * *****************************************/
	public static void addDVDtoC(String title,String name){//3 ADD

		if(DVDList.retrive(new DVD(title,0)) != null){
			DVD data = findDVD(title);
			DVD useless =new DVD(title,0) ;
			if( CustomerList.retrive(new Customer(name,0)) != null &&
					findCustomer(name).getWish().retrive(useless) == null &&
					findCustomer(name).getLoan().retrive(useless) == null 
					){

				findCustomer(name).getWish().enqueue(data);
				System.out.println(title +" was added to "+ name +" wish list");
				if(findCustomer(name).getMaxLoan() > 0 )
					findDVDForC(findCustomer(name));
				else{
					System.out.println("ERROR :" +name+" loan list is full");
				}
			}
			else if(CustomerList.retrive(new Customer(name,0)) == null){
				System.out.println("ERROR : "+name +" is not a Customer");
			}
			else if(findCustomer(name).getWish().retrive(useless)!= null){
				System.out.println("ERROR : "+name +" already has "+title+" on their wishlist");
			}
			else if(findCustomer(name).getLoan().retrive(useless)!= null){
				System.out.println("ERROR : "+name +" already has "+title+"  on their Loanlist");
			}
		}
		else{
			System.out.println("ERROR : "+title+" not present on NetFlix list or might be mispelled");
		}
	}

	/****************************************************
	 * removeDVD from the customer wishList
	 ****************************************************/

	public static void removeDVDfromC(String title,String name){//4 REM
		DVD less =new DVD(title,0);
		if(DVDList.retrive(less) != null){
			DVD data = findDVD(title);
			Customer useless = new Customer(name,0);
			if( CustomerList.retrive(useless) != null &&
					findCustomer(name).getWish().retrive(less) != null ){
				findCustomer(name).getWish().delete(data);
				System.out.println(title+ " was deleted from "+ name +" wishlist" );
			}
			else if(CustomerList.retrive(useless) == null){
				System.out.println("ERROR: "+useless.getName() +" Customer doesnt exist");
			}
			else if(findCustomer(name).getWish().retrive(less) == null ){
				System.out.println("ERROR: "+title +" not in "+ name +" wish List");
			}
		}
		else{
			System.out.println("ERROR: "+title+" not present in Data base or might be mispelled");
		}
	}

	/********************************************
	 * returns DVD, increment DVD quantity in the
	 * DVDlist, and then reassign it randomly to 
	 * another customer
	 *******************************************/
	public static void returnDVD(String title,String name){//5 RET
		DVD less =new DVD(title,0);

		if( DVDList.retrive(new DVD(title,0)) != null){
			Customer useless = new Customer(name,0);
			if(CustomerList.retrive(useless) != null && findCustomer(name).getLoan().retrive(less) != null){
				Customer cust =findCustomer(name);
				DVDList.retrive(less).getData().increment(1);
				cust.getLoan().delete(less);
				cust.increment(1);
				// reassign
				System.out.println(name +" has returned "+ title +" and it was reloaned to ");
				waitingList(findDVD(title));


			}
			else if(CustomerList.retrive(useless) == null){
				System.out.println("ERROR: "+useless.getName() +" doesnt exist");
			}
			else if(findCustomer(name).getLoan().retrive(less) == null){
				System.out.println("ERROR: "+title +" not in "+ name +" Loan List");
			}

		}
		else{
			System.out.println("ERROR : "+ title+" not present in Netflix database or might be mispelled");
		}
	}

	/****************************************
	 * when a customer misplaces or damages
	 * a DVD ,it is deleted from the loanlist
	 ****************************************/
	public static void LoseDVD(String title,String name){//6 LOS 
		DVD less =new DVD(title,0);
		if(DVDList.retrive(less) != null){
			Customer useless = new Customer(name,0);
			DVD data = (DVD)(DVDList.retrive(less).getData());
			if( CustomerList.retrive(useless) != null &&
					findCustomer(name).getLoan().retrive(less)!= null ){
				findCustomer(name).getLoan().delete(data);
				System.out.println(title +" was lost by "+ name +" and is expected to pay extra to replace");
			}
			else if(CustomerList.retrive(useless) == null){
				System.out.println("ERROR: "+useless.getName() +" doesnt exist");
			}
			else if(findCustomer(name).getLoan().retrive(less) == null){
				System.out.println("ERROR : " +name +" doesn't have "+ title+" in loan list");	
			}

		}
		else{
			System.out.println("ERROR : "+title+" not present in netflix database or might be mispelled");
		}

	}
	/************************************************
	 * Cancel subscription deletes the customer From
	 * CustomerList but checks if the customer has any 
	 * item on their loan list
	 *************************************************/
	public static void cancelSub(Customer name){// 7 RCN 
		if( CustomerList.retrive(name) != null){
			if(	findCustomer(name.getName()).getLoan().isEmpty() == true ){
				CustomerList.delete(name);
				System.out.println(name.getName()+" is no more a subscriber");
			}
			else
				System.out.println("ERROR : " +name.getName() +" Loan list not empty ");

		}
		else{
			System.out.println(name + " not a customer");
		}
	}

	/************************************************************************************
	 ********************************* HELPER METHODS************************************
	 ***********************************************************************************/

	/*********************************************
	 * Finding customers waiting for DVD and then
	 * assigning it to one of them randomly
	 *********************************************/
	private static void waitingList(DVD data){
		Orderlist wait = new Orderlist();
		Customer curr =(Customer) CustomerList.top();

		if(curr != null){
			int random ;
			Customer last =curr;			
			do{
				curr = (Customer) CustomerList.next();
				if(curr.getWish().search(data) == true)
					wait.orderInsert(curr);

			}while(curr != last);


			while(data.getQuantity() > 0 && !wait.isEmpty()){
				random = randgen(0,wait.getsize());
				curr = (Customer)wait.getCustatIndex(random);
				loanDVD(curr, data);
				wait.delete(curr);
				System.out.println("Loaned "+data.getName()+" to "+ curr.getName());
			}
		}
	}

	/*******************************************
	 * 	Deletes from the wishlist and add to
	 *  loanlist in order.
	 *  Also decrements the quantity of the total
	 *  DVD the customer has,
	 *  As well as decrementing the the customer 
	 *  maxloan amount.
	 * @param name
	 * @param title
	 */
	private static void loanDVD(Customer name,DVD title){
		name.getLoan().orderInsert(title);
		name.getWish().delete(title);
		title.increment(-1);//decrement 1
		name.increment(-1);
	}

	/***********************************
	 * Loops through the customer wishlist
	 * and loans all items on their loanlist
	 * 
	 * @param customer
	 */
	private static void findDVDForC(Customer data){
		if(data != null){
			DVD curr =null;
			DVD last = curr = (DVD)data.getWish().top();
			do{
				curr = (DVD)(data.getWish().next());
				if(!(data.getWish().isEmpty()) && curr.getQuantity() > 0 ){
					loanDVD(data, curr);
					System.out.println("Loaned "+curr.getName()+" to "+ data.getName());
				}

			}while(curr != last && !data.getWish().isEmpty());
		}
	}
	/*********************************************************
	 * prints all items in the CustomerList and the DVD on their
	 * loan list
	 */
	public static void printCustlist(){
		System.out.println("Netflix Customer List\n");
		if(CustomerList !=null){
			Customer list = (Customer)CustomerList.top();
			Customer last = list;
			do{

				list= (Customer)CustomerList.next();
				System.out.println("\n"+ list .getName()+ "\n-----------");
				System.out.println("DVDs ON LOAN\n-----------------");
				if(!list.getLoan().isEmpty()){
					DVD curr =(DVD) list.getLoan().top();
					DVD dvd = curr;

					do{
						curr = (DVD) list.getLoan().next();
						System.out.println(curr.getName());

					}while(curr != dvd );
				}
				System.out.println("\nQUEUE\n----------");
				if(!list.getWish().isEmpty()){
					DVD out = (DVD)list.getWish().top();
					DVD fin = out;
					do{
						out = (DVD) list.getWish().next();
						System.out.println(out.getName()); 

					}while(out != fin );
				}

			}while(list != last);
		}
	}
	// random number generator
	public static int randgen(int start,int end){
		return (int) (start +(Math.random()* (end - start)));

	}

}



