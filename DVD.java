

public class DVD extends Obj{
private String name;
private int quantity;// inventory quantity

public DVD(String name,int quantity ){
	this.name = name;
	this.quantity = quantity;
	
}

public int compareTo(DVD data){
	return (this.name).compareTo(data.name);
}
public String toString(){
	return "Name "+name +" Quantity "+ quantity;
}
/****************************************
 **********ACCESSOR AND MUTATOR**********
 ****************************************/
public void setName(String name){
	this.name = name;
}
public void setQuantity(int quantity){
	this.quantity = quantity;	
}
public String getName(){
	return name;
}
public int getQuantity(){
	return quantity;
}
}
