package UserInterface;

import java.util.Scanner;

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
