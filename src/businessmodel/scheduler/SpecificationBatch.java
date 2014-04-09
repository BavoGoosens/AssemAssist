package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import businessmodel.order.Order;

public class SpecificationBatch extends SchedulingAlgorithm {

	private LinkedList<Order> orderList = new LinkedList<Order>();

	public SpecificationBatch(Scheduler scheduler){
		super(scheduler);
	}

	@Override
	public void schedule(LinkedList<Order> orders){
		for(Order order: orders)
			this.scheduleOrder(order);
		this.reschedule();
				for(Order order: orderList)
					System.out.println(order);

	}

	@Override
	public void scheduleOrder(Order currentOrder) {

		ArrayList<Order> similarCarOptionsOrder = new ArrayList<Order>();
		for(Order order: orderList){
			if (currentOrder.equalsCarOptions(order)){
				similarCarOptionsOrder.add(order);
			}
		}

		if (similarCarOptionsOrder.size() != 0){

			similarCarOptionsOrder.add(currentOrder);

			for(Order ord: similarCarOptionsOrder)
				orderList.remove(ord);

			Collections.reverse(similarCarOptionsOrder);

			for(Order ord: similarCarOptionsOrder)
				orderList.addFirst(ord);

		}else{
			orderList.addLast(currentOrder);
		}



	}

	private void reschedule() {

		ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();

		for(Order order: orderList){
			for (Shift sh: this.getScheduler().getShifts()){
				timeslots = sh.canAddOrder(order);
				if(timeslots!= null){
					sh.addOrderToSlots(order,timeslots);
					order.updateEstimatedCompletionTimeOfOrder(this.getScheduler().getPrevious(order));
					break;
				}
			}
		}

	}

}

