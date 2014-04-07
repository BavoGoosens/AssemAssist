package businessmodel.scheduler;

import businessmodel.Order;

/**
 * A class that represents a WorkSlot. Each TimeSlot has a number of WorkSlot's. Each WorkSlot represents a hour on an WorkPost.
 * 
 * @author SWOP 2014 team 10
 *
 */
public class WorkSlot{
	
	/**
	 * A variable that holds the current order of this WorkSlot.
	 */
	private Order order;
	
	/**
	 * A boolean that represents if this WorkSlot is occupied.
	 */
	private boolean occupied;
	
	/**
	 * A constructor to create a new WorkSlot.
	 */
	public WorkSlot(){
	}
	
	/**
	 * A method to check if this WorkSlot is occupied.
	 * @return	this.occupied
	 */
	protected boolean isOccupied(){
		return this.getOccupied();
	}

	/**
	 * A method to add a new Order.
	 * @param	order
	 *       	the new order of this WorkSlot. 
	 */
	protected void addOrder(Order order){
		this.setOrder(order);
		this.setOccupied(true);
	}
	
	/**
	 * A method to get the order of this WorkSlot.
	 * @return	this.order
	 */
	protected Order getOrder() {
		return this.order;
	}

	/**
	 * A method to set the order of this WorkSlot to the given order.
	 * @param	order
	 * 			the new order of this WorkSlot.
	 * @throws	IllegalArgumentException
	 * 			order == null
	 */
	private void setOrder(Order order) throws IllegalArgumentException{
		if (order == null)
			throw new IllegalArgumentException("Not an order");
		this.order = order;
	}

	/**
	 * A method to adapt if this WorkSlot is occupied.
	 * @param	occupied
	 * 			True of false
	 * @throws  IllegalArgumentException
	 * 			if occupied is not a boolean
	 */
	private void setOccupied(boolean occupied) throws IllegalArgumentException{
		if (occupied != false || occupied != true)
			throw new IllegalArgumentException("Not a boolean");
		this.occupied = occupied;
	}	
	
	/**
	 * A method to see if this WorkSlot in occupied.
	 * @return	this.occupied
	 */
	private boolean getOccupied(){
		return this.occupied;
	}
	
	/**
	 * A method to terminate this WorkSlot object.
	 */
	protected void terminate(){
		this.order = null;
	}
}
