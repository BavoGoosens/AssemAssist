package ui;

import java.util.ArrayList;
import java.util.Scanner;

import component.Component;

import control.Controller;
import businessmodel.CarModel;
import businessmodel.Order;
import businessmodel.User;

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

	private Controller control;

	public UserInterFace(Controller control){
		this.control = control;
	}
	/**
	 * This method asks the current User for their login information.
	 * 
	 * @return  String[]
	 * 			An array of length 2 with the login information.
	 * 
	 */
	public void login(){
		User currentuser;
		while (true){
			System.out.println("Please enter your login information");
			System.out.print("Username: ");
			String username = this.scan.next();
			System.out.print("Password: ");
			String password = this.scan.next();
			if (this.control.authenticate(username,password)){
				currentuser = this.control.getUser(username);
				break;
			} else {
				this.badLogin();
			}
		}
		this.order(currentuser);
		this.performAssemblyTask(currentuser);
		this.advance(currentuser);	
	}

	private void advance(User currentuser) {
		// TODO Auto-generated method stub

	}
	private void performAssemblyTask(User currentuser) {
		// TODO Auto-generated method stub

	}
	
	public void order(User currentuser){
		while(true){
			if (this.control.canPlaceOrder(currentuser)){
				this.displayOrderOverview(currentuser);
				this.displayOrderHelp();
				String response = this.getInput();
				if (response.equalsIgnoreCase("quit"))
					break;
				if (response.equalsIgnoreCase("order")){
					ArrayList<CarModel> cml = this.control.getAvailableCarModels(currentuser);
					CarModel cm = this.chooseCarModel(cml);
					if (cm == null)
						continue;
					ArrayList<Component> carparts = this.chooseCar(cm);
					Order order = new Order(currentuser,carparts, null);
					this.control.placeOrder(order);
				}
				if (response.equalsIgnoreCase("help")){
					this.displayOrderHelp();
				}
				if (response.equalsIgnoreCase("overview"))
					continue;
			}
		}
		this.login();
	}

	private ArrayList<Component> chooseCar(CarModel cm) {
		ArrayList<ArrayList<Component>> possibleparts =  cm.getPosibilities();
		ArrayList<Component> parts = new ArrayList<Component>();
		for(ArrayList<Component> choices : possibleparts){
			this.displayString("Enter the number of the" + choices.get(0).getClass() + "part you would like to order");
			int count = 1;
			for(Component comp : choices){
				this.displayString(Integer.toString(count) + ") " + comp.toString());
				count ++;
			}
			String response = this.getInput();
			
		}
	}
	
	private CarModel chooseCarModel(ArrayList<CarModel> cml) {
		this.displayString("Enter the number of the car model you wish to order or enter cancel to abort: \n");
		int count = 1;
		for(CarModel cm : cml){
			this.displayString(Integer.toString(count) + ") " + cm.toString());
			count ++;
		}
		String response = this.getInput();
		// user wishes to cancel
		if (response.equalsIgnoreCase("cancel"))
			return null;
		// user entered a number
		if (response.matches("\\d*")){
			int res = Integer.parseInt(response);
			//user entered a valid number
			if (res <= cml.size())
				return cml.get(res - 1);	
		}
		return chooseCarModel(cml);	
	}
	
	public void badLogin(){
		System.out.println("We could not find you in the System \n \n");
	}

	public void displayOrderOverview(User currentuser){
		ArrayList<Order> completed = this.control.getCompletedOrders(currentuser);
		ArrayList<Order> pending = this.control.getPendingOrders(currentuser);
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
