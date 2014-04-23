package ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.Option;

import businessmodel.CarModel;
import businessmodel.Model;
import businessmodel.category.CarOption;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.exceptions.UnsatisfiedRestrictionException;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.User;
import control.StandardOrderController;
import control.StandardOrderHandler;

public class GarageHolderView extends View{

	private ArrayList<Order> pending_orders;

	private ArrayList<Order> completed_orders;

	private ArrayList<CarModel> available_carmodels;

	private StandardOrderController controller;

	private int step = 0;

	private User user;

	private Scanner scan = new Scanner(System.in);

	public GarageHolderView(Model model, User user) {
		super(model);
		this.user = user;
		this.controller = new StandardOrderHandler(this.getModel(), this.user);
		this.update();
	}

	@Override
	public void display() {
		System.out.println("> These are your pending orders: ");
		int size = this.pending_orders.size();
		int count = 1; 
		for (Order or : this.pending_orders){
			System.out.println("> " + count + or.toString());
			count ++;
		}
		System.out.println("> These are your completed orders: ");
		for (Order or : this.completed_orders){
			System.out.println("> " + count + or.toString());
			count ++;
		}
		System.out.println("> If you wish to inspect an individual order enter the order's ranking number");
		System.out.println("> If you wish to place a new order enter ORDER: ");
		System.out.println(">> ");
		String response = this.scan.nextLine();
		this.check(response);
		if (response.equalsIgnoreCase("order")){
			this.startNewOrder();
			this.step++;
		}
		Pattern pattern = Pattern.compile("\\d*");
		if (pattern.matcher(response).find()){
			int number = Integer.parseInt(response);
			if (number > size) {
				int index = number - size - 1;
				StandardCarOrder or = (StandardCarOrder) this.completed_orders.get(index);
				this.check(or);
				this.step++;
			} else {
				StandardCarOrder or = (StandardCarOrder) this.completed_orders.get(number - 1);
				this.check(or);
				this.step++;
			}
		} else {
			this.error();
		}
	}

	private void check(StandardCarOrder or) {
		this.displayHelp();
		System.out.println("> Here are the order details: ");
		// kan gedetailleerder worden gemaakt
		System.out.println(or.toString());
	}

	private void startNewOrder() {
		// choose the car model
		Iterator<CarModel> iter = this.getModel().getCarModels(this.user);
		while (iter.hasNext())
			this.available_carmodels.add(iter.next());
		System.out.println("> Please enter the corresponding number of the car model you wish to order:");
		int count = 1;
		for (CarModel cm : this.available_carmodels){
			System.out.println("> " + count + ") "+ cm.toString());
		}
		System.out.println(">> ");
		String response = this.scan.nextLine();
		this.check(response);
		Pattern pattern = Pattern.compile("\\d*");
		if (pattern.matcher(response).find()){
			int number = Integer.parseInt(response);
			if (number > this.available_carmodels.size() || number < 1){
				System.out.println("! You entered something wrong please try again");
				this.startNewOrder();
			}

			number -= 1;
			CarModel chosen = this.available_carmodels.get(number);				
			this.step ++;
			displayOrderingForm(chosen);
		}

	}

	private void displayOrderingForm(CarModel model){
		ArrayList <CarOption> chosen = new ArrayList<>();
		ArrayList <CarOption> available = model.getPossibilities();
		StandardCarOrder validorder = null;
		while (validorder == null){
			System.out.println("> Your chosen car options: ");
			int rem = 1;
			for (CarOption cho : chosen){
				System.out.println("> " + rem + ") " + cho.toString());
				rem ++;
			}
			System.out.println("> Enter the number of the option you want to include in your car");
			System.out.println("> If you want to remove a chosen car option enter REMOVE followed by the number of the option");
			System.out.println("> If you have chosen everything enter ORDER");
			int number = 1;
			for (CarOption opt : available){
				System.out.println("> " + number + ") " + opt.toString());
				number ++;
			}
			String response = this.scan.nextLine();
			this.check(response);
			Pattern remover = Pattern.compile("(?i)remove\\s*(\\d*)");
			Pattern pattern = Pattern.compile("^\\d*$");
			if (pattern.matcher(response).find()){
				int choice = Integer.parseInt(response);
				if ( choice > available.size() || choice < 1){
					System.out.println("! You entered something wrong. Please try again");
				} else {
					CarOption want = available.remove(choice - 1);
					chosen.add(want);
				}
			} else if (remover.matcher(response).find()) {
				String num = remover.matcher(response).group();
				int nam = Integer.parseInt(num);
				if ( nam > available.size() || nam < 1){
					System.out.println("! You entered something wrong. Please try again");
				} else {
					CarOption removed = chosen.remove(nam - 1);
					available.add(removed);
				}
			} else if (response.equalsIgnoreCase("order")){
				// try placing the order 
				// errors get thrown if it does not comply to the restrictions
				try{
					validorder = new StandardCarOrder(this.user, chosen);
				} catch (UnsatisfiedRestrictionException e){
					String message = e.getMessage();
					System.out.println("! Your requested selection of car options "
							+ "does not comply with the model restrictions: ");
					System.out.println("! " + message);
					System.out.println("> Please update your selection accordingly");
				} catch (IllegalArgumentException e) {
					System.out.println("! You entered something wrong. Please try again");
				} catch (NoClearanceException e) {
					System.out.println("! Your user type is wrong. Please login again");
					new LoginView(getModel()).display();
				}
			} else {
				System.out.println("! You entered something wrong. Please try again");
			}
		}
		this.controller.placeOrder(validorder);
	}

	@Override
	public void displayHelp() {
		super.helpOverview();
	}

	@Override
	public void error() {
		System.out.println("! Something went wrong please try again");
		this.displayHelp();
		this.display();
	}

	private void check(String str){
		if (str.equalsIgnoreCase("quit"))
			this.quit();
		if (str.equalsIgnoreCase("cancel"))
			this.cancel();
		if (str.equalsIgnoreCase("help"))
			this.displayHelp();
	}


	public void update() {
		try {
			Iterator<Order> pending = this.getModel().getPendingOrders(this.user);
			Iterator<Order> completed = this.getModel().getCompletedOrders(this.user);
			this.pending_orders.clear();
			this.completed_orders.clear();
			while (pending.hasNext())
				this.pending_orders.add(pending.next());
			while (completed.hasNext())
				this.completed_orders.add(completed.next());	
		} catch (NoClearanceException e) {
			// NOP
		}
	}

	@Override
	public void cancel() {
		if (this.step == 0){
			this.displayHelp();
			this.display();
		}
		if (this.step == 1){
			this.displayHelp();
			this.display();
			this.step--;
		}
		if (this.step == 2){
			this.displayHelp();
			this.startNewOrder();
			this.step--;
		}
	}

	@Override
	public void quit() {
		new LoginView(this.getModel()).display();
	}

}
