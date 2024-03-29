package businessmodel.util;

import businessmodel.order.Order;
import org.joda.time.DateTime;

import java.util.Comparator;

/**
 * A class representing a comparator for orders.
 *
 * @author SWOP team 10 2013-2014
 */
public class OrderDateTimeComparator implements Comparator<Order> {

    @Override
    public int compare(Order o, Order oo) {
        if (o.isCompleted() && oo.isCompleted()) {
            DateTime t1 = o.getCompletionDate();
            DateTime t2 = oo.getCompletionDate();
            return t1.compareTo(t2);
        } else if (o.isCompleted() && !oo.isCompleted()) {
            return 1;
        } else if (!o.isCompleted() && oo.isCompleted()) {
            return -1;
        } else {
            DateTime t1 = o.getEstimatedDeliveryDate();
            DateTime t2 = oo.getEstimatedDeliveryDate();
            return t1.compareTo(t2);
        }
    }

}
