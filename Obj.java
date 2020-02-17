
public abstract class Obj {
	/*******************************************
	 * THIS class acts a container class which
	 * can hold both DVDs and Customers. so my Node 
	 * can be created as an Obj type which can hold 
	 * both 
	 * 
	 */
	
	public int compareTo(Obj data){
		int toreturn;
		if(this instanceof DVD && data instanceof DVD)
			toreturn =((DVD)this).compareTo((DVD)data);
		else if (this instanceof  Customer && data instanceof Customer) {
			toreturn =((Customer)this).compareTo((Customer)data);
		}
		else{// when comparing an DVD and a customer
			toreturn = -911;
			System.out.println("comparing a DVD and a Customer");
		}
			
		return toreturn;
	}
	
	public void increment(int n){
		
		if(this instanceof DVD)
			((DVD)this).setQuantity(((DVD)this).getQuantity()+n);
		else{
			((Customer)this).setMaxLoan(((Customer)this).getMaxLoan()+n);
			
		}
	}
	
}
