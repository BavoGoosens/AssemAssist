package businessmodel.observer;

/**
 * An interface that can be implemented by a class to utilize the observer design pattern.
 * 
 * The interface implementation is preferable over the standard java implementation because it 
 * adheres more to the design to an interface guideline and allows for more control.
 * 
 * @author Seal Team 10
 *
 */
public interface Subject {
	
	/**
	 * Register a new Observer which will be notified every time important data of the subject is altered.
	 *  
	 * @param 	o
	 * 			The new Observer who wants to be notified.
	 */
	public void subscribeObserver(Observer o);
	
	/**
	 * Remove an Observer from the service.
	 * 
	 * @param 	o
	 * 			The Observer who no longer wants to be notified.
	 */
	public void unsubscribeObserver(Observer o);
	
	/**
	 * Notify all the observers and give them a reference to the altered data.
	 * 
	 * @param 	alteredData
	 * 			The data that has changed.
	 */
	public void notifyObservers(Object alteredData);
	
	/**
	 * Notify all the observers.
	 */
	public void notifyObservers();

}
