package businessmodel.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.joda.time.Period;

import businessmodel.OrderManager;
import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;
import businessmodel.util.OrderTupleComperator;
import businessmodel.util.Tuple;

public class OrderStatistics implements Observer {
	
	/**
	 * The average delay of all the finished orders.
	 */
	private int average;

	/**
	 * The median delay of all the finished orders.
	 */
	private int median;

	private ArrayList<Tuple<Order, Integer>> finishedOrders;
	
	public OrderStatistics(Subject subject){
		this.finishedOrders = new ArrayList<Tuple<Order, Integer>>();
		subject.subscribeObserver(this);
	}
	
	public int getAverage() {
		return this.average;
	}
	
	public int getMedian() {
		return this.median;
	}
	
	private void updateAverage(){
		if (this.finishedOrders.size() > 0) {
			int count = 0;
			for (Tuple<Order, Integer> tup : this.finishedOrders){
				count += tup.getY();
			}		
			this.average = (int) Math.floor(count / this.finishedOrders.size());
		} else {
			this.average = 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void updateMedian() {
		if (this.finishedOrders.size() > 0) {
			ArrayList<Tuple<Order, Integer>> temp = 
					(ArrayList<Tuple<Order, Integer>>) this.finishedOrders.clone();
			Collections.sort(temp, new OrderTupleComperator());
			if ( temp.size() % 2 == 0 ){
				int fml = temp.get(temp.size()/2).getY();
				int fol = temp.get(temp.size()/2 + 1).getY();
				this.median = (fml + fol) / 2;
			} else {
				this.median = temp.get((int) Math.ceil(temp.size()/2)).getY();
			}
		} else {
			this.median = 0;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void update(Subject subject) {
		if (subject instanceof OrderManager) {
			this.finishedOrders = new ArrayList<Tuple<Order, Integer>>();
			OrderManager orderManager = (OrderManager) subject;
			LinkedList<Order> finishedOrders = orderManager.getCompletedOrders();
			for (Order order: finishedOrders) {
				Period period = new Period(order.getOrder_placed_on_workpost(),
						order.getCompletionDate());
				Period normalPeriod = new Period(order.getOrder_placed_on_workpost(), 
						order.getStandardtime_on_assemblyline());
				Period delay = period.minus(normalPeriod);
				this.finishedOrders.add(new Tuple(order, delay.getMinutes()));
			}
			this.updateAverage();
			this.updateMedian();
		}
	}

}
