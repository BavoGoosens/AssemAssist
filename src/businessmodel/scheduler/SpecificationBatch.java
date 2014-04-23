package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import businessmodel.category.CarOption;
import businessmodel.order.Order;

public class SpecificationBatch extends SchedulingAlgorithm {

	private LinkedList<Order> orderList = new LinkedList<Order>();
	private CarOption option;

	public SpecificationBatch(Scheduler scheduler, CarOption option){
		super(scheduler);
		this.option = option;
	}

	@Override
	public LinkedList<Order> schedule(LinkedList<Order> orders){

		for(Order order: orders)
			this.scheduleOrder(order);
		this.reschedule();
		return orderList;
	}

	@Override
	public void scheduleOrder(Order currentOrder) {
		
		ArrayList<Order> similarCarOptionsOrder = new ArrayList<Order>();
		orderList.add(currentOrder);
	
		if (orderList.size() != 0){
			
			for(Order order: orderList)
				for(CarOption option: order.getCar().getOptionsClone())
					if (option.toString().equals(this.option.toString()))
						similarCarOptionsOrder.add(order);
			
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
					break;
				}
			}
		}
	}

}

