package businessmodel;

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
		this.setOption(option);
		ArrayList<Order> list = new ArrayList<Order>(this.getScheduler().getOrders());
		for(Order order: list)
			this.scheduleOrder(order);
		
	}

	private void reschedule(Order currentOrder) {
		
		this.getScheduler().generateShifts();
		ArrayList<Order> similarCarOptionsOrder = new ArrayList<Order>();
		this.getScheduler().ScheduleDay();
		orderList.add(currentOrder);
	
		if (orderList.size() != 0){
			
			for(Order order: orderList)
				for(CarOption option: order.getOptions())
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
		
		this.getScheduler().getOrders().clear();
		for(Order order: orderList)
			this.getScheduler().getOrders().add(order);
		
	}


	@Override
	public void scheduleOrder(Order currentOrder) {
		
		this.reschedule(currentOrder);
		
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

	private void setOption(CarOption option) throws IllegalArgumentException {
		if(option == null)
			throw new IllegalArgumentException("Not an option");
		this.option = option;
	}

}

