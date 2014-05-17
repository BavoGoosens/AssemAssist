package businessmodel.util;

import java.util.Comparator;

import businessmodel.order.Order;

public class EndDateOfOrderComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		if (o1.getCompletionDate().isBefore(o2.getCompletionDate()))
			// Als order 1 voor Order 2 klaar is hoort die voor order 2 in de lijst te staan => 1
            return 1;
		else if(o1.getCompletionDate().isAfter(o2.getCompletionDate()))
			return -1;
		else 
			return 0;
	}
}
