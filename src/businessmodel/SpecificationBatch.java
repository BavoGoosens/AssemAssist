package businessmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import businessmodel.category.VehicleOption;
import businessmodel.order.Order;
/**
 * A class representing a specification batch scheduling algorithm.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public class SpecificationBatch extends SchedulingAlgorithm {

	private LinkedList<Order> orderList = new LinkedList<Order>();
	private VehicleOption option;

	/**
	 * Creates a specification batch algorithm with a assemblyline and an option to schedule to options from.
	 * @param 	scheduler
	 * 			The assemblyline of the algorithm.
	 * @param 	option
	 */
	public SpecificationBatch(Scheduler scheduler, VehicleOption option){
		super(scheduler);
		this.setOption(option);
		ArrayList<Order> list = new ArrayList<Order>(this.getScheduler().getOrders());
		for(Order order: list)
			this.scheduleOrder(order);
		
	}

	private void reschedule(Order currentOrder) {
		
		this.getScheduler().generateShifts();
		ArrayList<Order> similarVehicleOptionsOrder = new ArrayList<Order>();
		this.getScheduler().ScheduleDay();
		orderList.add(currentOrder);
	
		if (orderList.size() != 0){
			
			for(Order order: orderList)
				for(VehicleOption option: order.getOptions())
					if (option.toString().equals(this.option.toString()))
						similarVehicleOptionsOrder.add(order);
			
			for(Order ord: similarVehicleOptionsOrder)
				orderList.remove(ord);
			Collections.reverse(similarVehicleOptionsOrder);
			for(Order ord: similarVehicleOptionsOrder)
				orderList.addFirst(ord);

		}else{
			orderList.addLast(currentOrder);
		}
		
		this.getScheduler().getOrders().clear();
		for(Order order: orderList){
			this.getScheduler().getOrdermanager().setEstimatedCompletionDateOfOrder(order);
			this.getScheduler().getOrders().add(order);
		}
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

	private void setOption(VehicleOption option) throws IllegalArgumentException {
		if(option == null)
			throw new IllegalArgumentException("Not an option");
		this.option = option;
	}

}

