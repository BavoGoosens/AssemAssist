package businessmodel.observer;

import businessmodel.order.Order;

/**
 * An interface that can be implemented by a class to utilize the observer design pattern.
 * <p/>
 * The interface implementation is preferable over the standard java implementation because it
 * adheres more to the design to an interface guideline and allows for more control.
 *
 * @author SWOP team 10 2013-2014
 */
public interface OrderStatisticsSubject {

    /**
     * Registers a new observer which will be notified every time important data of
     * the subject is changed.
     *
     * @param observer The new observer who wants to be notified by the subject.
     */
    public void subscribeObserver(OrderStatisticsObserver observer);

    /**
     * Removes an observer from the subject.
     *
     * @param observer The observer who no longer wants to be notified.
     */
    public void unSubscribeObserver(OrderStatisticsObserver observer);

    /**
     * Notifies all the observers.
     */
    public void notifyObservers(Order order, int delay);

}
