package businessmodel;

public class RestrictedTimeSlot extends TimeSlot{

	public RestrictedTimeSlot(int sizeworkposts){
		super(sizeworkposts);
	}
	
	@Override
	protected boolean IsAvailable(int i){
		return (i < 0 && getOrders()[i] == null);
	} 
	
	@Override
	public void addOrderToTimeslot(Order order, int i) throws IllegalArgumentException{
		if(order == null) 
			throw new IllegalArgumentException("An order cannot be null");
		if(!this.IsAvailable(i))
			throw new IllegalArgumentException("There is already an order scheduled.");
		if ( i != 0)
			throw new IllegalArgumentException("Cannot access that work post!");
		this.getOrders()[i] = order;
	}
}
