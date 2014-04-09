package businessmodel.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import businessmodel.CarOption;
import businessmodel.order.Order;

public class SpecificationBatch extends SchedulingAlgorithm {
	
	LinkedList<Order> list = new LinkedList<Order>();
	private HashMap<Order, Order> queue = new HashMap<Order, Order>();
	
	public SpecificationBatch(Scheduler scheduler){
		super(scheduler);
	}

	@Override
	public void scheduleOrder(Order currentOrder) {

		ArrayList<Order> similarCarOptionsOrder = new ArrayList<Order>();
		for(Order order: this.getOrders()){
			if (currentOrder.equalsCarOptions(order)){
				similarCarOptionsOrder.add(order);
			}
		}
		
		if (similarCarOptionsOrder.size() != 0){
			
			similarCarOptionsOrder.add(currentOrder);
			
			for(Order ord: similarCarOptionsOrder)
				list.remove(ord);
			
			Collections.reverse(similarCarOptionsOrder);
			
			for(Order ord: similarCarOptionsOrder)
				list.addFirst(ord);

		}else{
			list.addLast(currentOrder);
		}
		
		for(Order ord: this.getOrders())
			System.out.println(ord);
		System.out.println("--------------------");
	}

	@Override
	public void updateSchedule(){

	}

	public LinkedList<Order> getOrders(){
		return list;
	}
	
	public HashMap<Order, Order> getOrdersScheduled(){
		return queue;
	}
	
	private class Pair
	{
	    private final Order currentOrder;
	    private final Order order;

	    public Pair(Order currentOrder, Order order){
	    	this.currentOrder = currentOrder;
	        this.order = order;
	    }

	    public Order getKey()   { return currentOrder; }
	    public Order getValue() { return order; }
	}
}

