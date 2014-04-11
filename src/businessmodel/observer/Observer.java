package businessmodel.observer;

import java.util.UUID;

/**
 * An interface which holds all the methods an Observer needs to implement in order to use the observer design pattern.
 *  
 * @author Seal team 10
 *
 */
public interface Observer {
	
	/**
	 * This method gets called whenever the state of the subject changes.
	 * A reference to both the subject and the altered data is supplied.
	 * 
	 * @param 	s
	 * 			The Subject who requested this update.
	 * 
	 * @param 	o
	 * 			The data that has changed.
	 */
	public void update(Subject s, Object o);
	
	/**
	 * This method gets called whenever the state of the subject changes.
	 * The implementer is responsible for the retrieval of the changed data.
	 * 
	 * @param 	s
	 * 			The Subject who requested this update.
	 */
	public void update(Subject s);

}
