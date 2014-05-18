package businessmodel.util;

import java.util.Comparator;

import businessmodel.order.Order;

public class EndDateOfOrderComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		long henk1 = o1.getCompletionDate().getMillis();
		long henk2 = o2.getCompletionDate().getMillis();
		if (henk1 < henk2)
			// Als order 1 voor Order 2 klaar is hoort die voor order 2 in de lijst te staan => 1
            return 1;
		else if(henk1 > henk2)
			return -1;
		else 
			return 0;
	}
}
