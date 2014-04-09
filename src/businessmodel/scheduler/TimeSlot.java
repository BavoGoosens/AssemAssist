package businessmodel.scheduler;

import java.util.LinkedList;

import businessmodel.order.Order;

/**
 * A class that represents a TimeSlot. Each TimeSlot has a number of WorkPost's. 
 * @author SWOP 2014 team 10
 *
 */
public class TimeSlot {

	/**
	 * A list that holds the WorkSlot's of this TimeSlot.
	 */
	private LinkedList<WorkSlot> workslots;

	/**
	 * A constructor for the class TimeSlot. A number of WorkSlots will be generated.
	 * @param	sizeworkposts
	 * 			the number of workSlots that will be generated.
	 */
	public TimeSlot(int sizeworkposts){
		generateWorkSlots(sizeworkposts);
	}

	/**
	 * A method to plan an order into a WorkSlot. 
	 * @param	order
	 * 			the order that needs to be placed in a WorkSlot.
	 * @param 	workpost 
	 * 			the number of the WorkSlot.
	 */
	public void addOrderToWorkSlot(Order order, int workpost) {
		this.getWorkSlots().get(workpost).addOrder(order);
	}

	/**
	 * A method that generates the WorkSlot's of this TimeSlot.
	 * @param	sizeworkposts
	 * 			the number of WorkSlot's that will be generated.
	 */
	private void generateWorkSlots(int sizeworkposts){
		this.workslots = new LinkedList<WorkSlot>();
		for(int i = 0; i< sizeworkposts; i++){
			WorkSlot temp = new WorkSlot();
			this.getWorkSlots().add(temp);
		}
	}

	/**
	 * A method to get the WorkSlot's of this TimeSlot.
	 * @return	this.workslots
	 */
	protected LinkedList<WorkSlot> getWorkSlots(){
		return this.workslots;
	}
}
