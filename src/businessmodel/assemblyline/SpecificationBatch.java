package businessmodel.assemblyline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
		for(Order order: list){
			this.scheduleOrder(order);
		}


	}

	/**
	 * Reschedule the order according to the SpecificationBatch algorithm.
	 * @param currentOrder
	 */
	private LinkedList<Order> reschedule() {


		ArrayList<Order> list = new ArrayList<Order>(this.getScheduler().ordersOnAssemblyLine());
		for(Order order: list){

			ArrayList<Order> similarVehicleOptionsOrder = new ArrayList<Order>();
			this.orderList.add(order);

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

		this.flushAssemblyLineScheduler();

		for(Order order: orderList){
			this.getScheduler().getOrders().add(order);
			this.getScheduler().checkIfAssemblyLineCanAdvance();
			this.getScheduler().setEstimatedCompletionDateOfOrder(this.getScheduler().getPreviousOrder(order), order);
		}

		return orderList;

	}

	private void flushAssemblyLineScheduler() {
		
		ArrayList<Order> onAssemblyLine = new ArrayList<>();
		Iterator<WorkPost> postIterator = this.getScheduler().getAssemblyLine().getWorkPostsIterator();
		while(postIterator.hasNext())
			onAssemblyLine.add(postIterator.next().getOrder());

		for(Order order: this.getScheduler().getOrders()) {
			if (!onAssemblyLine.contains(order))
				this.getScheduler().getAssemblyLine().getMainScheduler().orderCannotBePlaced(order);
		}
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


		LinkedList<Order> list = this.reschedule();

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

