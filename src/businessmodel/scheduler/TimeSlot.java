package businessmodel.scheduler;

import businessmodel.order.Order;

public class TimeSlot {

	private Order[] orders;
	private final boolean[] permissions;
	
	
	public TimeSlot(int sizeworkposts){
		this.orders = new Order [sizeworkposts];
		this.permissions = new boolean[sizeworkposts];
		for (int i = 0; i < this.permissions.length; i++){
			this.permissions[i] = true;
		}
	}

	public TimeSlot(int sizeworkposts, boolean[] permissions){
		this.orders = new Order [sizeworkposts];
		this.permissions = permissions;
	}
	
	// Exceptions moeten nog worden aangepast.
	public void addOrderToTimeslot(Order order, int i) throws IllegalArgumentException{
		if(order == null) 
			throw new IllegalArgumentException("An order cannot be null");
		if(!this.IsAvailable(i))
			throw new IllegalArgumentException("There is already an order scheduled.");
		if ( i < 0 || i > this.getOrders().length-1)
			throw new IllegalArgumentException("Cannot access that work post!");
		this.getOrders()[i] = order;
	}
	
	protected Order [] getOrders(){
		return this.orders;
	}
	
	protected boolean IsAvailable(int i){
		return (this.getOrders()[i] == null);
	}
	
}
