package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.order.Order;

public class EndShift extends Shift {

	public EndShift(int hours) {
		super(hours);
	}
	
	@Override
	// dummy method
	protected void removeLastTimeSlot(){
		this.getTimeSlots().peekLast().terminate();
		this.getTimeSlots().removeLast();
	}
	
	@Override
	// overal kijken behalve de laatste twee bij een end shift.
	protected ArrayList<TimeSlot> canAddOrder(Order order){
		ArrayList<TimeSlot> timeslots;
		LinkedList<TimeSlot> temp = this.getTimeSlots();
		temp.remove(temp.size());
		temp.remove(temp.size());
		for(TimeSlot slot1 : temp){
			timeslots = checkSlot(slot1);
			if (timeslots != null)
				return timeslots;
		}
		return null;
	}

	private ArrayList<TimeSlot> checkSlot(TimeSlot slot1){
		ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		for(int i = 0; i < this.getNumberofworkposts(); i++){
			if (slot1.getWorkSlots().get(i).isOccupied())
				return null;
			slot1 = this.getNext(slot1);
			timeslots.add(slot1);
		}
		return timeslots;
	}
}
