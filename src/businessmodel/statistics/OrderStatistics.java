package businessmodel.statistics;

import businessmodel.OrderManager;
import businessmodel.exceptions.IllegalNumberException;
import businessmodel.observer.OrderStatisticsObserver;
import businessmodel.observer.OrderStatisticsSubject;
import businessmodel.order.Order;
import businessmodel.util.OrderTupleComperator;
import businessmodel.util.Tuple;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class representing the order statistics of the system.
 *
 * @author SWOP team 10
 */
public class OrderStatistics implements OrderStatisticsObserver {

    /**
     * The average delay of all the finished orders.
     */
    private int average;

    /**
     * The median delay of all the finished orders.
     */
    private int median;

    /**
     * A list of all finished orders with the delay time of each order.
     */
    private ArrayList<Tuple<Order, Integer>> finishedOrders;

    /**
     * Creates a new order statistics object with a given subject it needs to observe.
     *
     * @param subject The subject the statistics need to observe.
     * @throws IllegalArgumentException | If the subject is equal to 'null' or if the subject isn't an order manager.
     * | subject == null || !(subject instanceof OrderManager)
     */
    public OrderStatistics(OrderStatisticsSubject subject) throws IllegalArgumentException {
        if (subject == null || !(subject instanceof OrderManager)) throw new IllegalArgumentException("Bad subject!");
        this.finishedOrders = new ArrayList<Tuple<Order, Integer>>();
        subject.subscribeObserver(this);
    }

    /**
     * Returns the average delay of all finished orders.
     *
     * @return The average delay of all the finished orders.
     */
    public int getAverage() {
        return this.average;
    }

    /**
     * Returns the median delay of all finished orders.
     *
     * @return The median delay of all the finished orders.
     */
    public int getMedian() {
        return this.median;
    }

    /**
     * Returns the last 'number' orders and their delays.
     *
     * @param number The number of orders.
     * @return The last orders (length: number) and the delays of those orders.
     * @throws IllegalNumberException | If the number is smaller than zero or if the number is higher or equal than the number of finished orders.
     * | number < 0 || number >= this.finishedOrders.size()
     */
    public ArrayList<Tuple<Order, Integer>> getLast(int number) throws IllegalNumberException {
        if (number < 0 || this.finishedOrders.size() > number - 1) {
            ArrayList<Tuple<Order, Integer>> result = new ArrayList<Tuple<Order, Integer>>(number);
            for (int i = this.finishedOrders.size() - 1; i >= this.finishedOrders.size() - number; i--) {
                result.add(this.finishedOrders.get(i));
            }
            return result;
        } else
            throw new IllegalNumberException("The supplied number of days is too large or too small");
    }

    /**
     * Calculates and updates the average delay time.
     */
    private void updateAverage() {
        if (this.finishedOrders.size() > 0) {
            int count = 0;
            for (Tuple<Order, Integer> tup : this.finishedOrders) {
                count += tup.getY();
            }
            this.average = (int) Math.floor(count / this.finishedOrders.size());
        } else {
            this.average = 0;
        }
    }

    /**
     * Calculates and updates the median delay time.
     */
    @SuppressWarnings("unchecked")
    private void updateMedian() {
        if (this.finishedOrders.size() > 0) {
            ArrayList<Tuple<Order, Integer>> temp =
                    (ArrayList<Tuple<Order, Integer>>) this.finishedOrders.clone();
            Collections.sort(temp, new OrderTupleComperator());
            if (temp.size() % 2 == 0) {
                int fml = temp.get((temp.size() / 2) - 1).getY();
                int fol = temp.get((temp.size() / 2)).getY();
                this.median = (fml + fol) / 2;
            } else {
                this.median = temp.get((int) Math.ceil(temp.size() / 2)).getY();
            }
        } else {
            this.median = 0;
        }
    }

    /**
     * @throws IllegalArgumentException | If the subject is equal to 'null' or isn't an order manager
     * | subject == null || !(subject instanceof OrderManager)
     */
    @Override
    public void update(Order finished, int delay) throws IllegalArgumentException {
        if (finished == null) throw new IllegalArgumentException("Bad order!");
        this.finishedOrders.add(new Tuple<Order, Integer>(finished, delay));
        updateAverage();
        updateMedian();
    }

}
