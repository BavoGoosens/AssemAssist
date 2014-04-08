package businessmodel.scheduler;

import java.util.ArrayList;

import businessmodel.order.Order;

public class FreeShift extends Shift{

	public FreeShift(int hours) {
		super(hours);
	}
	
	@Override
	protected ArrayList<TimeSlot> canAddOrder(Order order){
		ArrayList<TimeSlot> timeslots;
		for(TimeSlot slot1 : this.getTimeSlots()){
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
