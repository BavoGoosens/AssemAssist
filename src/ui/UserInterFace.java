package ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import component.Component;
import control.Controller;
import businessmodel.Action;
import businessmodel.AssemblyTask;
import businessmodel.CarModel;
import businessmodel.Order;
import businessmodel.User;
import businessmodel.WorkPost;

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
		if (this.control.canPlaceOrder(currentuser))
			this.order(currentuser);
		if (this.control.canPerformAssemblyTask(currentuser))
			this.performAssemblyTask(currentuser);
		if (this.control.canAdvanceAssemblyLine(currentuser))
			this.advance(currentuser);	
	}

	private void advance(User currentuser) {
		while (true) {
			this.displayString(" > Hello " + currentuser.getFirstname() + "! Do you "
					+ "want to move the assembly line forward? (yes/no)\n");
			this.displayString(">> ");
			String response = this.getInput();
			if (response.equalsIgnoreCase("yes")) {
				this.displayString("Please enter the time that was spent during the current phase.\n");
				response = this.getInput();
				try {
					int time = Integer.parseInt(response);
					this.control.advanceAssemblyLine(time);
				} catch (NumberFormatException e) {
					this.badInput();
				}
			} else if (response.equalsIgnoreCase("no")) {
				break;
			}
		}
		this.login();
	}
	
	/**
	 * This method interacts with the mechanic about the tasks that have to be performed.
	 * 
	 * @param currentuser
	 * 		  The current user.
	 */
	private void performAssemblyTask(User currentuser) {
		
		ArrayList<WorkPost> workposts = this.control.getWorkingStations();
		WorkPost currentworkpost = null;
		AssemblyTask task = null;
		
		while (true){
			
			this.displayString(" > Hello " + currentuser.getFirstname() + " please enter workpost number \n" );
			this.displayString(" > To quit: enter \"Quit\"\n");
			int count = 1;
			
			for(WorkPost w : workposts)
				this.displayString(" " + Integer.toString(count++) + ") " + w.toString() + "\n");
			
			this.displayString(" >> ");
			String response = this.getInput();
			if (response.equalsIgnoreCase("Quit")) {
				break;
			}
			if (response.matches("\\d*")){
				int res = Integer.parseInt(response) - 1;
				if (res < workposts.size()){
					currentworkpost = workposts.get(res);
				}else{
					this.badInput();
					continue;
				}
			}else{
				this.badInput();
				continue;
			}
			innerloop:
			while(true){
				count = 1;
				if (currentworkpost.getPendingTasks().size() == 0)
					break innerloop;
				
				for(AssemblyTask Atask : currentworkpost.getPendingTasks())
					this.displayString(" " + Integer.toString(count++) + ") " + Atask.toString() + "\n");
					
				count = 1;
				this.displayString(" >> ");
				response = this.getInput();
				if (response.matches("\\d*")){
					int res = Integer.parseInt(response) - 1;
					task = currentworkpost.getPendingTasks().get(res);
					if (res < currentworkpost.getPendingTasks().size()){
						this.displayString(" " +"List of actions to perform \n");
						for(Action action : task.getActions())
							this.displayString(" --> "  + action.getDescription() + "\n");
						this.displayString(" " +"if finished, type 'done' \n");
					}else{
						this.badInput();
						continue;
					}
				}else{
					this.badInput();
					continue;
				}
					
				this.displayString(" >> ");
				response = this.getInput();
				if (!response.equals("done")){
					this.badInput();
					continue;
				}
				
				task.completeAssemblytask();
				currentworkpost.removePendingTask(task);
				
				this.displayString(" Do you want to continue working? yes/no \n");
				this.displayString(" >> ");
				response = this.getInput();
				if (response.equals("yes"))
					continue;
				else if (response.equals("no"))
					break;
				else
					this.badInput();
				
			}
			
			this.displayString("All done! \n");
		}
		this.login();
	}

	public void order(User currentuser){
		this.displayOrderOverview(currentuser);
		while(true){
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
				if (carparts == null | carparts.isEmpty())
					continue;					
				Order order = new Order(currentuser,carparts);
				Date datum = this.control.getCompletionTimeEstimate();
				boolean answered = false;
				while (answered == false){
					this.displayString(order.toString() + "\n" +
							" > It will be finished around this time: " + datum.toString() + "\n" + 
							" > Is this the order you wish to place? (yes/no) \n >>" );
					String answer = this.getInput();
					if (answer.equalsIgnoreCase("yes")){
						this.control.placeOrder(order);
						this.displayString("\n Your order has been placed. We will let you know when it's finished. \n");
						answered = true;
					}else if (answer.equalsIgnoreCase("no")){
						this.displayString(" Allright please enter your new order or quit the system \n");
						answered = true;
					} else{
						this.badInput();
					}
				}
				continue;
			}
			if (response.equalsIgnoreCase("help"))
				continue;
			if (response.equalsIgnoreCase("overview"))
				this.displayOrderOverview(currentuser);
			else{
				this.badInput();
			}
		}
		this.login();
	}

	private ArrayList<Component> chooseCar(CarModel cm) {
		ArrayList<Component[]> possibleparts =  cm.getPossibilities();
		ArrayList<Component> parts = new ArrayList<Component>();
		for(Component[] choices : possibleparts){
			String type = "";
			String choice = "";
			for(int i = 0 ; i< choices.length; i ++){
				type = choices[i].getClass().getName();
				choice = choice + "\n" + " " + Integer.toString(i + 1) + ") " + choices[i].toString();
			}
			Component part = null;;
			type = type.split("\\.")[1];
			while (true){
				String output = "\n > Please enter the number of the " + type + " part you wish to order" + choice + "\n >>";
				this.displayString(output);
				String response = this.getInput();
				if (response.matches("\\d")){
					int res = Integer.parseInt(response) - 1;
					if (res < choices.length){
						part = choices[res];
						break;
					} else {
						this.badInput();
					}
				}
				if (response.equalsIgnoreCase("cancel"))
					break;
			}
			// the user wants to cancel
			if (part == null)
				break;
			// part has been chosen add to list
			parts.add(part);
		}
		return parts;
	}

	private CarModel chooseCarModel(ArrayList<CarModel> cml) {
		this.displayString("\n > Enter the number of the car model you wish to order \n"
				+ " > Enter cancel to abort: \n");
		int count = 1;
		for(CarModel cm : cml){
			this.displayString(" " + Integer.toString(count) + ") " + cm.getCarmodel() + "\n >>");
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
		this.badInput();
		return chooseCarModel(cml);	
	}

	/**
	 * This method prints out when a bad input is given.
	 */
	public void badInput(){
		this.displayString("\n You entered something wrong. Try again! \n");
	}

	/**
	 * This method prints out when a bad login is given.
	 */
	public void badLogin(){
		this.displayString("We could not find you in the System \n \n");
	}

	
	public void displayOrderOverview(User currentuser){
		ArrayList<Order> completed = this.control.getCompletedOrders(currentuser);
		ArrayList<Order> pending = this.control.getPendingOrders(currentuser);
		if (completed != null & completed.size() > 0){
			this.displayString("These are your completed orders: \n");
			for (Order ord : completed){
				this.displayString(ord.toString()+ "\n");
			}
		}else{
			this.displayString("\n There are no completed orders for you");
		}
		if (pending != null  & pending.size() > 0){
			this.displayString("\n These are your pending orders: \n");
			for (Order ord : pending){
				this.displayString(ord.toString() + "\n");
			}
		}else{
			this.displayString("\n There are no pending orders for you");
		}

	}

	/**
	 * This method prints out the possible commands when a user wants to place an order.
	 */
	public void displayOrderHelp(){
		String help = "\n > to place a new order enter: order <CR> "
				+ "\n > to quit enter: quit <CR> "
				+ "\n > to view your order overview enter: overview <CR>"
				+ "\n > to see the list of available commands enter: help <CR>" +
				"\n >>";
		this.displayString(help);
	}

	/**
	 * This method prints out the string supplied by the caller.
	 * 
	 * @param str
	 * 		  String that needs to be printed to the console.
	 */
	public void displayString(String str){
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
