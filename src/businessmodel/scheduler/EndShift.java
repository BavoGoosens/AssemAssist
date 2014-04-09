package businessmodel.scheduler;

import java.util.ArrayList;
import businessmodel.order.Order;

public class EndShift extends Shift {

	public EndShift(int hours,int numberofworkposts) {
		super(hours,numberofworkposts);
	}
	
	@Override
	protected ArrayList<TimeSlot> canAddOrder(Order order){
		ArrayList<TimeSlot> timeslots;
		for(int i = 0 ; i< this.getTimeSlots().size()-2;i++){
			TimeSlot slot = this.getTimeSlots().get(i);
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
			timeslots.add(slot);
			slot = this.getNextTimeSlot(slot);
		}
		return timeslots;
	}
}
