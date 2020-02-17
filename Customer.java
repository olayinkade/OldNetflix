

public class Customer extends Obj{
	private String name;
	private int maxLoan;
	private Queue wishList;// list of DVD;
	private Orderlist loanLisT;
	public Customer(String name,int maxLoan){
		this.name = name;
		this.maxLoan = maxLoan;
		this.wishList = new Queue(); 
		this.loanLisT = new Orderlist();

	}
	// compareTo
	public int compareTo(Customer data){
		return (this.name).compareTo(data.name);
	}
	public String toString(){
		return "Name "+name +" Maxloan "+ maxLoan;
	}
	/****************************************
	 **********ACCESSOR AND MUTATOR**********
	 ****************************************/
	public void setMaxLoan(int maxLoan){
		this.maxLoan = maxLoan;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getMaxLoan(){
		return maxLoan;
	}
	public String getName(){
		return name;
	}
	public void setWish(Queue wishList){
		this.wishList = wishList;
	}
	public void setLoan(Orderlist loanList){
		this.loanLisT = loanList;
	}
	public Queue getWish(){
		return wishList;
	}
	public Orderlist getLoan(){
		return loanLisT ;
	}
}