package businessmodel.util;

import businessmodel.order.Order;

import java.util.Comparator;

/**
 * A class representing a comparator for order tuples.
 *
 * @author SWOP team 10 2013-2014
 */
public class OrderTupleComperator implements Comparator<Tuple<Order, Integer>> {

    @Override
    public int compare(Tuple<Order, Integer> o1, Tuple<Order, Integer> o2) {
        int delay1 = o1.getY();
        int delay2 = o2.getY();
        if (delay1 > delay2)
            return 1;
        else if (delay1 < delay2)
            return -1;
        else
            return 0;
    }
}
