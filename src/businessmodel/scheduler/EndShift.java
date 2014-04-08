package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.LinkedList;

import businessmodel.order.Order;

public class EndShift extends Shift {

	public EndShift(int hours,Shift nextShift) {
		super(hours,nextShift);
	}
	
	@Override
	protected ArrayList<TimeSlot> canAddOrder(Order order){
		ArrayList<TimeSlot> timeslots;
		LinkedList<TimeSlot> temp = this.getTimeSlots();
		temp.remove(temp.size());
		temp.remove(temp.size());
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
}
