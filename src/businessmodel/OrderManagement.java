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

	public OrderManagement(ArrayList<CarModel> carmodels){
		this.orders = new ArrayList<Order>();
		setCarmodels(carmodels);
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public ArrayList<CarModel> getCarmodels() {
		return carmodels;
	}

	private void setCarmodels(ArrayList<CarModel> carmodels){
		this.carmodels = carmodels;
	}
	
	public void addOrder(Order order){
		this.getOrders().add(order);
	}

	public void deleteOrder(Order order){
		this.getOrders().remove(order);
	}
}
