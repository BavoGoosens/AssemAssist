package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.order.Order;

public class FreeShift extends Shift{

	private Shift nextShift;

	public FreeShift(int hours, Shift nextShift) {
		super(hours);
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
	
	private ArrayList<TimeSlot> checkSlot(TimeSlot slot){
		ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		for(int i = 0; i < this.getNumberofworkposts(); i++){
			if (slot.getWorkSlots().get(i).isOccupied())
				return null;
			slot = this.getNext(slot);
			timeslots.add(slot);
		}
		return timeslots;
	}
	
	protected Shift getNextShift(){
		return this.nextShift;
	}
}
