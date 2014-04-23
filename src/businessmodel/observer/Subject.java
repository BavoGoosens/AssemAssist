package businessmodel.observer;

/**
 * An interface that can be implemented by a class to utilize the observer design pattern.
 * 
 * The interface implementation is preferable over the standard java implementation because it 
 * adheres more to the design to an interface guideline and allows for more control.
 * 
 * @author SWOP team 10 2013-2014
 *
 */
public interface Subject {
	
	/**
	 * Registers a new observer which will be notified every time important data of 
	 * the subject is changed.
	 *  
	 * @param 	observer
	 * 			The new observer who wants to be notified by the subject.
	 */
	public void subscribeObserver(Observer observer);
	
	/**
	 * Removes an observer from the subject.
	 * 
	 * @param 	observer
	 * 			The observer who no longer wants to be notified.
	 */
	public void unsubscribeObserver(Observer observer);
	
	/**
	 * Notifies all the observers.
	 */
	public void notifyObservers();

}
