package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.order.Order;

public class FreeShift extends Shift{

	private Shift nextShift;

	public FreeShift(int hours,int numberofworkposts, Shift nextShift) {
		super(hours, numberofworkposts);
		this.nextShift = nextShift;
	}
	
	
	@Override
	protected ArrayList<TimeSlot> canAddOrder(Order order){
		ArrayList<TimeSlot> timeslots;	
		LinkedList<TimeSlot> temp = this.getTimeSlots();
		temp.add(this.getNextShift().getTimeSlots().get(0));
		temp.add(this.getNextShift().getTimeSlots().get(1));
		for(TimeSlot slot : temp){
			timeslots = checkSlot(slot);
			if (timeslots != null)
				return timeslots;
		}
		return null;
	}
	
	/**
	 * A method to check if a number of slots are available.
	 * @param 	slot
	 * 			the first slot to check.
	 * @return	a list of slots if there is room. null if there is no place available.
	 */
	private ArrayList<TimeSlot> checkSlot(TimeSlot slot){
		ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		for(int i = 0; i < this.getNumberofworkposts(); i++){
			if (slot.getWorkSlots().get(i).isOccupied())
				return null;
			timeslots.add(slot);
			slot = this.getNextTimeSlot(slot);
		}
		return timeslots;
	}
	
	/**
	 * A method to get the next shift of the day.
	 * @return	the next shift of the day
	 */
	protected Shift getNextShift(){
		return this.nextShift;
	}
}
