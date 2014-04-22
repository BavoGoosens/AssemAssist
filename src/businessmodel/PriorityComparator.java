package businessmodel;

import java.util.Comparator;

import businessmodel.order.Order;

public class PriorityComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		if(o1.getPriority() < o2.getPriority())
			return 1;
		if(o1.getPriority() > o2.getPriority())
			return -1;
		if(o1.getTimestamp().getMillis() < o2.getTimestamp().getMillis())
			return 1;
		if(o1.getTimestamp().getMillis() > o2.getTimestamp().getMillis())
			return -1;
		if(o1.getTimestamp().getMillis() == o2.getTimestamp().getMillis())
			return 1;
		else
			return 0;
	}
}
