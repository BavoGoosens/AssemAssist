package businessmodel;

import java.util.ArrayList;

public class OrderManagement {

	/**
	 * A list that holds all the orders of a car manufacturing company.
	 */
	ArrayList<Order> orders;
	
	/**
	 * A list that holds all the car models of a car manufacturing company.
	 */
	ArrayList<CarModel> carmodels;
	
	public OrderManagement(){
		this.orders = new ArrayList<Order>();
		this.carmodels = new ArrayList<CarModel>();
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public ArrayList<CarModel> getCarmodels() {
		return carmodels;
	}

	public void addOrder(){
		// parse uit String?
		// Order neworder = new Order();
	}
	
	public void deleteOrder(Order order){
		this.getOrders().remove(order);
	}
}
