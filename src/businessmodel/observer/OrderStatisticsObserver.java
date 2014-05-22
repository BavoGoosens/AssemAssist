package businessmodel.observer;

import businessmodel.order.Order;

/**
 * An interface which holds all the methods an observer needs to implement in order 
 * to use the observer design pattern.
 *  
 * @author SWOP team 10 2013-2014
 *
 */
public interface OrderStatisticsObserver {
	
	/**
	 * This method gets called whenever the state of the subject changes.
	 * The implementer is responsible for the retrieval of the changed data.
	 * 
	 * 
	 */
	public void update(Order order, int delay);
	
}
