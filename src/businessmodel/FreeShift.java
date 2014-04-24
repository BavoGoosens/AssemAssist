package businessmodel;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.order.Order;

public class FreeShift extends Shift{

	private Shift nextShift;

	public FreeShift(int hours,int numberofworkposts, Shift nextShift) {
		super(hours, numberofworkposts);
		this.setNextShift(nextShift);
	}
	
	@Override
	protected ArrayList<TimeSlot> canAddOrder(Order order){
		ArrayList<TimeSlot> timeslots= null;	
		LinkedList<TimeSlot> temp = this.getTimeSlots();
		temp.add(this.getNextShift().getTimeSlots().get(0));
		temp.add(this.getNextShift().getTimeSlots().get(1));
		for(int i = 0 ; i< temp.size()-2; i++){
			timeslots = checkTimeSlots(temp.get(i));
			if (timeslots != null)
				break;
		}
		temp.removeLast();
		temp.removeLast();
		return timeslots;
	}

	/**
	 * A method to get the next shift of the day.
	 * @return	the next shift of the day
	 */
	protected Shift getNextShift(){
		return this.nextShift;
	}

	/**
	 * A method to check if a number of Timeslot's are available to handle an order.
	 * @param 	timeslot
	 * 			the first slot to check.
	 * @return	a list of slots if there is room. null if there is no place available.
	 */
	private ArrayList<TimeSlot> checkTimeSlots(TimeSlot timeslot){
		ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		for(int numberofworkslot = 0; numberofworkslot < this.getNumberofworkposts(); numberofworkslot++){
			if (timeslot.workSlotOccupied(numberofworkslot))
				return null;
			timeslots.add(timeslot);
			timeslot = this.getNextTimeSlot(timeslot);
		}
		return timeslots;
	}
	
	private void setNextShift(Shift nextShift) {
		this.nextShift = nextShift;
	}
}
