package businessmodel.statistics;

import java.util.ArrayList;

import org.joda.time.LocalDate;

import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;
import businessmodel.util.Tuple;

public class OrderStatistics implements Observer {

	/**
	 * The average delay of all the finished orders.
	 */
	private int avarage;

	/**
	 * The median delay of all the finished orders.
	 */
	private int median;

	private ArrayList<Tuple<Order, Integer>> number_of_cars;
	
	@Override
	public void update(Subject s, Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Subject s) {
		// TODO Auto-generated method stub
		
	}

}
