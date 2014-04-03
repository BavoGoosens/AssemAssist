package businessmodel;

public abstract class TimeSlot {

	private Order [] orders;
	
	public TimeSlot(int sizeworkposts){
		this.orders = new Order [sizeworkposts];
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
