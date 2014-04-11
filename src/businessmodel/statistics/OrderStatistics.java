package businessmodel.statistics;

import java.util.ArrayList;
import java.util.Collections;

import org.joda.time.LocalDate;

import businessmodel.observer.Observer;
import businessmodel.observer.Subject;
import businessmodel.order.Order;
import businessmodel.util.CarTupleComperator;
import businessmodel.util.OrderTupleComperator;
import businessmodel.util.Tuple;

public class OrderStatistics implements Observer {

	private Subject s;
	
	/**
	 * The average delay of all the finished orders.
	 */
	private int avarage;

	/**
	 * The median delay of all the finished orders.
	 */
	private int median;

	private ArrayList<Tuple<Order, Integer>> finished_orders;
	
	public OrderStatistics(Subject s){
		this.s = s;
		s.subscribeObserver(this);
	}
	
	@Override
	public void update(Subject s, Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Subject s) {
		// TODO Auto-generated method stub
		
	}
	
	private void updateAverage(){
		int count = 0;
		for (Tuple<Order, Integer> tup : this.finished_orders){
			count += tup.getY();
		}		
		this.avarage = (int) Math.floor(count / this.finished_orders.size());
	}
	
	private void updateMedian(){
		ArrayList<Tuple<Order, Integer>> temp = (ArrayList<Tuple<Order, Integer>>) this.finished_orders.clone();
		Collections.sort(temp, new OrderTupleComperator());
		if ( temp.size() % 2 == 0 ){
			int fml = temp.get(temp.size()/2).getY();
			int fol = temp.get(temp.size()/2 + 1).getY();
			this.median = (fml + fol) / 2;
		} else {
			this.median = temp.get((int) Math.ceil(temp.size()/2)).getY();
		}
	}

}
