package businessmodel;

/**
 * A class representing an action.
 * 
 * @author team 10
 *
 */
public class Action {

	/**
	 * The description of this action.
	 */
	private String description;

	/**
	 * Creates a new action with a given description.
	 * 
	 * @param 	description
	 * 			The description of the new action.
	 */
	public Action(String descr) throws IllegalArgumentException {
		this.setDescription(descr);
	}

	/**
	 * Returns the description of the action.
	 * 
	 * @return	The description of this action.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the action.
	 * 
	 * @param	description
	 * 			The description of the action
	 * @throws	IllegalArgumentException
	 * 			| If the description is equal to 'null'
	 * 			| description == null
	 */
	private void setDescription(String description) throws IllegalArgumentException {
		if (description == null) throw new IllegalArgumentException("Bad description!");
		this.description = description;
	}
}
