/*
 * A1.java
 * COMP 2150 Object Orientation
 * (C) Computer Science, University of Manitoba
 * Assignment 1
 *
 * Main class
 *
 */
import java.io.*;
 
public class A1{

  public static void main(String [] args){

    // check that a command-line argument was provided
    if(args.length == 0){
      System.out.println("Usage: java A1 <input file>");
     /// System.exit(0);
    }
    BufferedReader inFile = File.openInputFile("sam.txt");
    
    // Event processing loop
    String line = File.getLine(inFile);
    while(line != null){
      System.out.println(line);
      line = File.getLine(inFile);
    }// while
    
    File.closeFile(inFile);
    
    
    System.out.println("\n\nEnd of processing.");
    //System.exit(0);
  }// main
  
}// class A1

