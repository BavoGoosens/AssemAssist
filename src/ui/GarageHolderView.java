package ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import businessmodel.CarManufacturingCompany;
import businessmodel.OrderManager;
import businessmodel.exceptions.NoClearanceException;
import businessmodel.observer.OrderManagerObserver;
import businessmodel.observer.Subject;
import businessmodel.order.Order;
import businessmodel.order.StandardCarOrder;
import businessmodel.user.GarageHolder;
import businessmodel.user.User;
import control.StandardOrderController;

public class GarageHolderView extends View implements OrderManagerObserver {

	private ArrayList<Order> pending_orders;

	private ArrayList<Order> completed_orders;

	private OrderManager subject;

	private StandardOrderController controller;

	private User user;
	
	private Scanner scan = new Scanner(System.in);

	public GarageHolderView(StandardOrderController control, CarManufacturingCompany cmc, User user) {
		super(cmc);
		this.controller = control;
		super.getCarManufacturingCompany().registerObserver(this);
		this.user = user;
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
		if (response.equalsIgnoreCase("order"))
			this.controller.startNewOrder();
		Pattern pattern = Pattern.compile("\\d*");
		if (pattern.matcher(response).find()){
			int number = Integer.parseInt(response);
			if (number > size) {
				int index = number - size - 1;
				StandardCarOrder or = (StandardCarOrder) this.completed_orders.get(index);
				this.controller.check(or);
			} else {
				StandardCarOrder or = (StandardCarOrder) this.completed_orders.get(number - 1);
				this.controller.check(or);
			}
		} else {
			this.error();
		}
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
			this.controller.quit();
		if (str.equalsIgnoreCase("cancel"))
			this.controller.cancel();
		if (str.equalsIgnoreCase("help"))
			this.controller.help();
	}
	

	@Override
	public void update(Subject s, Object o) {
		this.subject = (OrderManager) s;
		try {
			this.pending_orders = this.subject.getPendingOrders(this.user);
			this.completed_orders = this.subject.getCompletedOrders(this.user);
		} catch (IllegalArgumentException | NoClearanceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Subject s) {
		this.update(s, null);
	}

}
