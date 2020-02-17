# OldNetflix

GENERIC NODE  
The abstract Obj class act as a container which can hold all subclass instances. In this code the Obj class is a superclass for both DVD and Customer which means an Obj variable can hold both DVD and Customer instances. The Node class is created with Obj as the data to be stored, therefore even if I can’t create an Obj instance because it is abstract, I can create either DVD or Customer and store in the Node.
                                                                                                                                        

LINKEDLIST STRUCTURE (CIRCULAR LINKED LIST)
At the top of the Linked ADT is the LinkedList class which is a super class to Queue and OrderList. This hierarchy helps to pass methods from LinkedList to its subclass. The functionality that is required and shared by the subclass are
1.	Searchable
2.	Add/ remove 
3.	Size
Basically, these linked Structures all act as a link to connect Nodes together, and Nodes stores Obj, therefore this structure just helps to store Obj as collection or set. The subclasses of LinkedList each have features unique to them, this solidifies the fact that different list can have various features be it Queue or Ordered like properties.


EVENT PROCESSING 
The Netflix class acts as a database class which helps to store Netflix’s DVD list(both name and quantity) and Customer list(name, Max loan amount, customers wish list and loan list). The Netflix class also has interface methods which help to carry out activities which are processed in the Event class. The Event class just act as a connector between the A1(main class) and the Netflix class. Its functions are to parse a string to the required format for each event (ADD, REM, RET, E.T.C.), and know what method to call for each event.

A1(Main class) 
The A1 class (class with the main method inside) main method is called first, it calls Event’s processAllEvent method after reading a specific line with a command in front, finally it calls Netflix’s printAllCust to basically list all the action that has affected the Netflix database both Customers and DVD.









                                                                                                                                  
