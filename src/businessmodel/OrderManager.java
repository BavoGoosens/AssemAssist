package businessmodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

/**
 * A class that represents an order manager.
 * This class handles all the orders for a car manufacturing company.
 * 
 * @author   SWOP team 10
 *
 */
public class OrderManager {

	/**
	 * A list that holds all the completed orders of a car manufacturing company.
	 */
	LinkedList<Order> completedorders;

	/**
	 * A list that holds all the pending orders for a car manufacturing company.
	 */
	LinkedList<Order> pendingorders;

	/**
	 * A list that holds all the car models of a car manufacturing company.
	 */
	ArrayList<CarModel> carmodels = new ArrayList<CarModel>();;

	/**
	 * A production scheduler this Order Manager uses.
	 */
	ProductionScheduler production;

	/**
	 * A inventory for this Order manager
	 */
	Inventory inventory;

	private boolean firstorder = true;

	/**
	 * A constructor for the class OrderManager.
	 * 
	 * @param    carmodels
	 *           the car models that an car manufacturing company offers.
	 */
	@SuppressWarnings("deprecation")
	public OrderManager(){
		this.pendingorders = new LinkedList<Order>();
		this.completedorders = new LinkedList<Order>();
		Date start = new Date();
		start.setHours(6);
		start.setMinutes(0);
		start.setSeconds(0);
		this.setProductionScheduler(new ProductionScheduler(this, start));
		this.inventory = new Inventory();
		this.setCarmodels(inventory);
	}

	/**
	 * A method that adds a new Order.
	 * 
	 * @param order
	 * 		  An Order that needs to be added.
	 */
	public void placeOrder(Order order){

		this.addOrder(order);
		boolean added = this.getProductionScheduler().update();
		if (added == false){
			this.updateEstimatedTime(0);
		}
		if(this.firstorder == true){
			this.getProductionScheduler().advance(60);
			this.firstorder = false;
		}
	}

	/**
	 * A method to get the orders of this order manager.
	 * 
	 * @return All the order
	}s of this order manager.
	 */
	public LinkedList<Order> getOrders() {
		LinkedList<Order> temp = this.getPendingOrders();
		for(Order order: this.getCompletedOrders()){
			temp.add(order);
		}
		return temp;
	}

	/**
	 * A method to get the car models of this order manager.
	 * 
	 * @return   this.carmodels
	 */
	public ArrayList<CarModel> getCarmodels() {
		return carmodels;
	}

	/**
	 * A method to set the ca
	}r models of this order manager to the given car models.
	 * 
	 * @param    carmodels
	 *           the new car models of this order manager.
	 */
	private void setCarmodels(Inventory inventory){
		CarModelSpecification spec1 = new CarModelSpecification(this.getInventory().bodytypes,
				this.getInventory().colortypes,this.getInventory().enginetypes,
				this.getInventory().gearboxtypes,this.getInventory().seattypes,
				this.getInventory().aircotypes,this.getInventory().wheeltypes);
		CarModel carmodel1 = new CarModel("Audi A8",spec1);
		CarModel carmodel2 = new CarModel("Audi TT",spec1);
		CarModel carmodel3 = new CarModel("Audi R8",spec1);
		this.getCarmodels().add(carmodel1);
		this.getCarmodels().add(carmodel2);
		this.getCarmodels().add(carmodel3);
	}

	/**
	 * A method to add an order to this order manager. It also updates iets estimated 
	 * 
	 * @param   order
	 *          the order that needs to be added.
	 */
	public void addOrder(Order order){
		this.getPendingOrders().add(order);
	}

	/**
	 * A method to get the completed orders of this order manager.
	 * 
	 * @return  the completed orders of this order manager.
	 */
	public LinkedList<Order> getCompletedOrders(){
		return this.completedorders;
	}

	/**
	 * A method to get the pending orders of this order manager.
	 * 
	 * @return the pending orders of this order manager.
	 */
	public LinkedList<Order> getPendingOrders(){
		return this.pendingorders;
	}

	/**
	 * A method to get the completed orders of a given user of this order manager.
	 * 
	 * @return  the completed orders of a given user of this order manager.
	 */
	public ArrayList<Order> getCompletedOrders(User user){
		ArrayList<Order> completedorders = new ArrayList<Order>();
		for (Order order: this.getCompletedOrders()){
			if (order.getUser() == user)
				completedorders.add(order);
		}
		return completedorders;
	}

	/**
	 * A method to get the pending orders of a given user of this order manager.
	 * 
	 * @return the pending orders of a given user this order manager.
	 */
	public ArrayList<Order> getPendingOrders(User user){
		ArrayList<Order> pendingorders = new ArrayList<Order>();
		pendingorders.addAll(this.getProductionScheduler().getScheduledOrders());
		for (Order order: this.getPendingOrders()){
			if (order.getUser() == user)
				pendingorders.add(order);
		}
		return pendingorders;
	}


	public ProductionScheduler getProductionScheduler() {
		return production;
	}

	public void setProductionScheduler(ProductionScheduler production) {
		this.production = production;
	}

	@Override
	public String toString() {
		return "orders= " + this.getOrders().toString() + ", carmodels= " + carmodels.toString();
	}

	/**
	 * A method to update the completion time of the pending orders.
	 * @param index
	 */
	@SuppressWarnings("deprecation")
	public void updateEstimatedTime(int time){
		if (time == 0){
			int day = this.production.getToday().getDay() + 1;
			Date start = (Date) this.production.getToday().clone();
			start.setHours(start.getHours()+8);
			start.setDate(day);
			start.setHours(6);
			start.setMinutes(0);
			start.setSeconds(0);
			time = 60*3;
			for(Order order: this.getPendingOrders()){
				start.setMinutes(time);
				order.setDate(start);
			}
		}else{
			for(Order order: this.getPendingOrders()){
				order.getDate().setMinutes(order.getDate().getMinutes()+time);
			}
		}
	}

	/**
	 * A method that moves a finished order from the pending list to the finished list.
	 * 
	 * @param finished 
	 * 		  The Order that needs to be moved.
	 */
	public void finishedOrder(Order finished) {
		this.getCompletedOrders().add(finished);
	}

	/**
	 * This method returns the number of specified pending orders if possible.
	 * 
	 * @param nb
	 * 		  The number of orders 
	 * 
	 * @return LinkedList<Order>
	 * 		   A list with the requested orders.
	 */
	public LinkedList<Order> getNbOrders(int nb) {
		LinkedList<Order> res = new LinkedList<Order>();
		for (int i = 0 ; i < nb - 1; i++){
			Order tmp = this.getPendingOrders().pollFirst();
			if (tmp == null)
				break;
			res.add(tmp);
		}
		return res;
	}

	/**
	 * A method to get the inventory of this order manager.
	 * @return the inventory of this class.
	 */
	private Inventory getInventory() {
		return inventory;
	}
}