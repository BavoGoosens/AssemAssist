package ui;

import java.util.ArrayList;
import java.util.Scanner;

import businessmodel.Order;

/**
 * This class provides a console user interface for the AssemAssist System.
 * 
 * @author Team 10
 *
 */
public class UserInterFace {
	
/**
	 * A variable which holds the scanner to read the input to the system.
	 */
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * This method asks the current User for their login information.
	 * 
	 * @return  String[]
	 * 			An array of length 2 with the login information.
	 * 
	 */
	public String[] loginPrompt(){
		String[] result = new String[2];
		System.out.println("Please enter your login information");
		System.out.print("Username: ");
		result[0] = this.scan.next();
		System.out.print("Password: ");
		result[1] = this.scan.next();
		return result;
	}
	
	public void badLogin(){
		System.out.println("We could not find you in the System \n \n");
	}
	
	public void displayOrderOverview(ArrayList<Order> completed, ArrayList<Order> pending){
		this.displayString("These are your completed orders: \n");
		for (Order ord : completed){
			this.displayString(ord.toString()+ "\n");
		}
		this.displayString("\n These are your completed orders: \n");
		for (Order ord : pending){
			this.displayString(ord.toString() + "\n");
		}

	}
	/**
	 * This method prints out the possible commands when a user wants to place an order.
	 */
	public void displayOrderHelp(){
		String help = "to place a new order enter: order <CR> "
				+ "\n to quit enter: quit <CR> "
				+ "\n to view your order overview enter: overview <CR> \n >"
				+ "to see the list of available commands enter: help <CR>";
		this.displayString(help);
	}
	
	/**
	 * This method prints out the string supplied by the caller.
	 * 
	 * @param str
	 * 		  String that needs to be printed to the console.
	 */
	public void displayString( String str){
		System.out.print(str);
	}
	
	/**
	 * This method returns the input the User supplied via the console.
	 * 
	 * @return String
	 * 		   The last string the user entered via the terminal.
	 */
	public String getInput(){
		return this.scan.next();
	}
	
	

}
