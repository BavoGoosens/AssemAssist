package businessmodel.assemblyline;

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
	private ArrayList<VehicleOption> options;

	/**
	 * Creates a specification batch algorithm with a assemblyline and an option to schedule to options from.
	 * @param 	scheduler
	 * 			The assemblyline of the algorithm.
	 * @param 	options
	 */
	public SpecificationBatch(AssemblyLineScheduler scheduler, ArrayList<VehicleOption> options){

		super(scheduler);
		if (options == null) throw new IllegalArgumentException();

		this.setOptions(options);

		ArrayList<Order> temp = this.getScheduler().ordersOnAssemblyLine();
		ArrayList<Order> list = new ArrayList<Order>(this.getScheduler().getOrders());
		
		for(Order ord: temp){
			if (list.contains(ord)){
				list.remove(ord);
			}
		}
		
		for(Order order: list)
			this.scheduleOrder(order);

		this.getScheduler().changeAlgorithm("FIFO", null);
	}

	/**
	 * Reschedule the order according to the SpecificationBatch algorithm.
	 * @param currentOrder
	 */
	private LinkedList<Order> reschedule(Order currentOrder) {

		ArrayList<Order> temp = this.getScheduler().ordersOnAssemblyLine();
		ArrayList<Order> list = new ArrayList<Order>(this.getScheduler().getOrders());
		
		for(Order ord: temp){
			if (list.contains(ord)){
				list.remove(ord);
			}
		}
		
		this.orderList.add(currentOrder);
		
		for(Order order: list){

			ArrayList<Order> similarVehicleOptionsOrder = new ArrayList<Order>();
		
			if (orderList.size() != 0){

				for(Order rescheduleOrder: orderList){
					int count = 0;
					for (VehicleOption option: this.options){
						for(VehicleOption option2: rescheduleOrder.getOptions()){
							if (option.toString().equals(option2.toString())) 
								count++;
						}
					}
					if (count == this.options.size()){
						similarVehicleOptionsOrder.add(rescheduleOrder);
					}
				}

				for(Order ord: similarVehicleOptionsOrder)
					orderList.remove(ord);
				Collections.reverse(similarVehicleOptionsOrder);
				for(Order ord: similarVehicleOptionsOrder)
					orderList.addFirst(ord);

			}else{
				orderList.addLast(order);
			}
		}
		
		this.getScheduler().getOrders().clear();
        this.getScheduler().getOrders().addAll(temp);
        
		this.getScheduler().clearTimeTable(temp);

		for(Order order: orderList){
			this.getScheduler().getOrders().add(order);
			this.getScheduler().checkIfAssemblyLineCanAdvance();
			this.getScheduler().setEstimatedCompletionDateOfOrder(this.getScheduler().getPreviousOrder(order), order);
		}
		
		return orderList;

	}

	/**
	 * Set the options for which the algorithm should order the orders for rescheduling.
	 * @param options
	 * @throws IllegalArgumentException
	 */
	private void setOptions(ArrayList<VehicleOption> options) throws IllegalArgumentException {
		if(options == null)
			throw new IllegalArgumentException("Not valid options");
		this.options = options;
	}

	@Override
	public boolean scheduleOrder(Order currentOrder) {

		LinkedList<Order> list = this.reschedule(currentOrder);

		ArrayList<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		for(Order order: list){
			for (Shift sh: this.getScheduler().getShifts()){
				timeslots = sh.canAddOrder(order);
				if(timeslots!= null){
					sh.addOrderToSlots(order,timeslots);
					return true;
				}
			}
		}
		return false;
	}


}

