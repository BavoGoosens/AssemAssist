package businessmodel.scheduler;

import java.util.ArrayList;

import businessmodel.order.Order;


public class FIFO extends SchedulingAlgorithm {

	public FIFO(Scheduler scheduler){
		super(scheduler);
	}
	
	@Override
	public void schedule(Order order) {
		ArrayList<TimeSlot> slots;
		for(Shift sh: this.getScheduler().getShifts()){
			for(TimeSlot slot1 : sh.getTimeSlots()){
				slots = checkSlot(sh,slot1);
				addOrderToSlots(order,slots);
			}
		}
	}

	private ArrayList<TimeSlot> checkSlot(Shift sh, TimeSlot slot1){
		ArrayList<TimeSlot> slots;
		slots = new ArrayList<TimeSlot>();
		boolean temp = true;
		for(int i = 1; i < sh.getTimeSlots().getFirst().getWorkSlots().size(); i++){
			if (slot1.getWorkSlots().get(0).isOccupied())
				temp = false;
			slots.add(slot1);
			slot1 = sh.getNext(slot1);
		}
		if(temp = true)
			return slots;
		else
			return null;
	}
	
	private void addOrderToSlots(Order order, ArrayList<TimeSlot> slots) {
		int i = 0;
		for(TimeSlot slot: slots){
			slot.getWorkSlots().get(i).addOrder(order);
			i++;
		}
	}
}

