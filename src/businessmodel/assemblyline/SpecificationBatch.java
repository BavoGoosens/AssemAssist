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
		ArrayList<Order> list = new ArrayList<Order>(this.getScheduler().getOrders());
		for(Order order: list)
			this.scheduleOrder(order);
		
		this.getScheduler().getOrders().clear();
		for(Order order: orderList){
			this.getScheduler().setEstimatedCompletionDateOfOrder(this.getScheduler().getPreviousOrder(order),order);
			this.getScheduler().getOrders().add(order);
		}

	}

	private void reschedule(Order currentOrder) {

		this.getScheduler().generateShifts();
		ArrayList<Order> similarVehicleOptionsOrder = new ArrayList<Order>();
		this.getScheduler().ScheduleDay();
		orderList.add(currentOrder);

		if (orderList.size() != 0){


			for(Order order: orderList){
				int count = 0;
				for (VehicleOption option: this.options){
					for(VehicleOption option2: order.getOptions()){
						if (option.toString().equals(option2.toString())) 
							count++;
					}
				}
				if (count == this.options.size()){
					similarVehicleOptionsOrder.add(order);
				}
			}

			for(Order ord: similarVehicleOptionsOrder)
				orderList.remove(ord);
			Collections.reverse(similarVehicleOptionsOrder);
			for(Order ord: similarVehicleOptionsOrder)
				orderList.addFirst(ord);

		}else{
			orderList.addLast(currentOrder);
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

	private void setOptions(ArrayList<VehicleOption> options) throws IllegalArgumentException {
		if(options == null)
			throw new IllegalArgumentException("Not valid options");
		this.options = options;
	}

}

