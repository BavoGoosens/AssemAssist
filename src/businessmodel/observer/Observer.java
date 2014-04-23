package businessmodel.observer;

/**
 * An interface which holds all the methods an Observer needs to implement in order to use the observer design pattern.
 *  
 * @author Seal team 10
 *
 */
public interface Observer {
	
	/**
	 * This method gets called whenever the state of the subject changes.
	 * The implementer is responsible for the retrieval of the changed data.
	 * 
	 * @param 	subject
	 * 			The subject who requested this update.
	 */
	public void update(Subject subject);
	
}
