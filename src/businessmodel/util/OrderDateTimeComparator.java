package businessmodel.util;

import java.util.Comparator;

import org.joda.time.DateTime;

import businessmodel.order.Order;

/**
 * A class representing a comparator for orders.
 * 
 * @author SWOP team 10 2013-2014
 * 
 */
public class OrderDateTimeComparator implements Comparator<Order> {

	@Override
	public int compare(Order o, Order oo) {
		if (o.isCompleted() && oo.isCompleted()){
			DateTime t1 = o.getCompletionDate();
			DateTime t2 = oo.getCompletionDate();
			return t1.compareTo(t2);
		} else if (o.isCompleted() && !oo.isCompleted()) {
			return 1;
		} else if (!o.isCompleted() && oo.isCompleted()){
			return -1;
		} else {
			DateTime t1 = o.getTimestamp();
			DateTime t2 = oo.getTimestamp();
			return t1.compareTo(t2);
		}
	}
	
}
