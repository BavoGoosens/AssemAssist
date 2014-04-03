package businessmodel;

public abstract class TimeSlot {

	private Order [] orders;
	
	public TimeSlot(int sizeworkposts){
		this.orders = new Order [sizeworkposts];
	}

	public void addOrderToTimeslot(Order order, int i){
		this.getOrders()[i] = order;
	}
	
	protected Order [] getOrders(){
		return this.orders;
	}
	
	protected boolean IsAvailable(int i){
		return (this.getOrders()[i] == null);
	}
	
	
}
