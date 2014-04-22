package ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import businessmodel.CarModel;
import businessmodel.Model;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.User;
import control.StandardOrderController;
import control.StandardOrderHandler;

public class GarageHolderView extends View{

	private ArrayList<Order> pending_orders;

	private ArrayList<Order> completed_orders;

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
		// TODO:
	}

	private void startNewOrder() {
		// TODO Auto-generated method stub
		// choose the car model

	}
	
	private void displayOrderingForm(CarModel model){
		// TODO:
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
